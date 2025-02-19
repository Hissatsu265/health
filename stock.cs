using System;
using System.Collections.Generic;

class Stock
{
    public string Ticker { get; set; }
    public double Open { get; set; }
    public double High { get; set; }
    public double Low { get; set; }
    public double Close { get; set; }

    public Stock(string ticker, double open, double high, double low, double close)
    {
        Ticker = ticker;
        Open = open;
        High = high;
        Low = low;
        Close = close;
    }

    public void Display()
    {
        Console.WriteLine($"Mã cổ phiếu: {Ticker}");
        Console.WriteLine($"Giá mở cửa: {Open}");
        Console.WriteLine($"Giá cao nhất: {High}");
        Console.WriteLine($"Giá thấp nhất: {Low}");
        Console.WriteLine($"Giá đóng cửa: {Close}");
        Console.WriteLine(new string('-', 30));
    }
}

class Program
{
    static void Main()
    {
        List<Stock> stocks = new List<Stock>
        {
            new Stock("AAPL", 175.2, 180.0, 174.5, 179.8),
            new Stock("GOOGL", 2800.5, 2850.3, 2795.1, 2845.7),
            new Stock("MSFT", 325.4, 330.2, 324.1, 329.0)
        };

        Console.WriteLine("Thông tin cổ phiếu:");
        foreach (var stock in stocks)
        {
            stock.Display();
        }
    }
}
