

require 'date'
require 'json'
require 'logger'
module LibraryManagement
  class Book
    attr_reader :id, :title, :author, :isbn
    attr_accessor :status, :borrowed_by, :due_date

    def initialize(id:, title:, author:, isbn:)
      @id = id
      @title = title
      @author = author
      @isbn = isbn
      @status = :available
      @borrowed_by = nil
      @due_date = nil
    end

    def to_json(*args)
      {
        id: @id,
        title: @title,
        author: @author,
        isbn: @isbn,
        status: @status,
        borrowed_by: @borrowed_by,
        due_date: @due_date&.iso8601
      }.to_json(*args)
    end
  end

  class Library
    LOAN_PERIOD = 14 # days
    DAILY_FINE = 2.0 # dollars

    def initialize
      @books = {}
      @members = {}
      @loans = {}
      @logger = Logger.new($stdout)
      @logger.level = Logger::INFO
    end

    def add_book(title:, author:, isbn:)
      id = generate_book_id
      book = Book.new(id: id, title: title, author: author, isbn: isbn)
      @books[id] = book
      @logger.info("Book added: #{title} by #{author}")
      book
    rescue StandardError => e
      @logger.error("Error adding book: #{e.message}")
      raise
    end

    def register_member(name:, email:)
      member_id = generate_member_id
      @members[member_id] = {
        id: member_id,
        name: name,
        email: email,
        joined_date: Date.today,
        active: true
      }
      @logger.info("Member registered: #{name}")
      member_id
    rescue StandardError => e
      @logger.error("Error registering member: #{e.message}")
      raise
    end

    def borrow_book(book_id:, member_id:)
      validate_book_and_member(book_id, member_id)
      book = @books[book_id]

      if book.status != :available
        raise "Book is not available for borrowing"
      end

      book.status = :borrowed
      book.borrowed_by = member_id
      book.due_date = Date.today + LOAN_PERIOD

      loan_id = SecureRandom.uuid
      @loans[loan_id] = {
        book_id: book_id,
        member_id: member_id,
        borrowed_date: Date.today,
        due_date: book.due_date,
        returned_date: nil
      }

      @logger.info("Book #{book_id} borrowed by member #{member_id}")
      loan_id
    end

    def return_book(book_id:)
      book = @books[book_id]
      raise "Book not found" unless book
      raise "Book is not borrowed" if book.status == :available

      loan = find_active_loan(book_id)
      loan[:returned_date] = Date.today
      
      fine = calculate_fine(loan[:due_date], Date.today)
      book.status = :available
      book.borrowed_by = nil
      book.due_date = nil

      @logger.info("Book #{book_id} returned. Fine: $#{fine}")
      { fine: fine }
    end

    def search_books(query)
      @books.values.select do |book|
        book.title.downcase.include?(query.downcase) ||
          book.author.downcase.include?(query.downcase) ||
          book.isbn.include?(query)
      end
    end

    def get_member_loans(member_id)
      raise "Member not found" unless @members[member_id]

      @loans.select { |_, loan| loan[:member_id] == member_id }
    end

    def get_overdue_books
      @books.values.select do |book|
        book.status == :borrowed && book.due_date && book.due_date < Date.today
      end
    end

    def generate_report
      {
        total_books: @books.size,
        available_books: @books.values.count { |b| b.status == :available },
        borrowed_books: @books.values.count { |b| b.status == :borrowed },
        total_members: @members.size,
        active_loans: @loans.count { |_, l| l[:returned_date].nil? },
        overdue_books: get_overdue_books.size
      }
    end

    private

    def generate_book_id
      "BK#{SecureRandom.hex(4).upcase}"
    end

    def generate_member_id
      "MEM#{SecureRandom.hex(4).upcase}"
    end

    def validate_book_and_member(book_id, member_id)
      raise "Book not found" unless @books[book_id]
      raise "Member not found" unless @members[member_id]
      raise "Member is not active" unless @members[member_id][:active]
    end

    def find_active_loan(book_id)
      @loans.values.find { |loan| loan[:book_id] == book_id && loan[:returned_date].nil? }
    end

    def calculate_fine(due_date, return_date)
      return 0.0 if return_date <= due_date

      days_overdue = (return_date - due_date).to_i
      days_overdue * DAILY_FINE
    end
  end
end

# Example usage
if __FILE__ == $PROGRAM_NAME
  library = LibraryManagement::Library.new

  # Add some books
  book1 = library.add_book(
    title: "The Great Gatsby",
    author: "F. Scott Fitzgerald",
    isbn: "978-0743273565"
  )

  book2 = library.add_book(
    title: "1984",
    author: "George Orwell",
    isbn: "978-0451524935"
  )

  # Register a member
  member_id = library.register_member(
    name: "John Doe",
    email: "john@example.com"
  )

  # Borrow a book
  loan_id = library.borrow_book(
    book_id: book1.id,
    member_id: member_id
  )

  # Generate report
  puts library.generate_report.to_json
end
