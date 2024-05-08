package ma.xproce.stockmanag.service;

import ma.xproce.stockmanag.dao.entities.Stock;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StockService {
    Boolean deleteStockById(Integer id);

    Stock addStock(Stock stock);

    List<Stock> getAllStocks();

    Page<Stock> searchStocks(String keyword, int page, int size);

    List<Stock> getByKeyword(String keyword);

    Stock getStockById(Integer id);

    Stock updateStock(Stock stock);

    Boolean deleteStock(Integer id);

}
