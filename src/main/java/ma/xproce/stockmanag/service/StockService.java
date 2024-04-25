package ma.xproce.stockmanag.service;

import ma.xproce.stockmanag.dao.entities.Stock;

import java.util.List;

public interface StockService {
    Stock creerStock(Stock stock);
    Stock obtenirStockParId(Integer id);
    List<Stock> obtenirTousLesStocks();
    void supprimerStock(Integer id);
}
