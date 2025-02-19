class Stock {
    constructor(
        public ticker: string,
        public open: number,
        public high: number,
        public low: number,
        public close: number
    ) {}

    display(): void {
        console.log(`Mã cổ phiếu: ${this.ticker}`);
        console.log(`Giá mở cửa: ${this.open}`);
        console.log(`Giá cao nhất: ${this.high}`);
        console.log(`Giá thấp nhất: ${this.low}`);
        console.log(`Giá đóng cửa: ${this.close}`);
        console.log('-'.repeat(30));
    }
}

class StockManager {
    private stocks: Stock[] = [];

    addStock(stock: Stock): void {
        this.stocks.push(stock);
    }

    displayAll(): void {
        console.log("Danh sách cổ phiếu:");
        this.stocks.forEach(stock => stock.display());
    }
}

// Sử dụng chương trình
const manager = new StockManager();
manager.addStock(new Stock("AAPL", 175.2, 180.0, 174.5, 179.8));
manager.addStock(new Stock("GOOGL", 2800.5, 2850.3, 2795.1, 2845.7));
manager.addStock(new Stock("MSFT", 325.4, 330.2, 324.1, 329.0));

manager.displayAll();
