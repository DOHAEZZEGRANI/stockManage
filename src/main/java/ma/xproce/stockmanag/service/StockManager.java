package ma.xproce.stockmanag.service;

import ma.xproce.stockmanag.dao.entities.Stock;
import ma.xproce.stockmanag.dao.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StockManager implements StockService{
    @Autowired
    private StockRepository stockRepository;

    @Override
    public Stock creerStock(Stock stock) {
        return stockRepository.save(stock);
    }

    @Override
    public Stock obtenirStockParId(Integer id) {
        return stockRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Stock introuvable"));
    }

    @Override
    public List<Stock> obtenirTousLesStocks() {
        return stockRepository.findAll();
    }

    @Override
    public void supprimerStock(Integer id) {
        stockRepository.deleteById(id);
    }
}
