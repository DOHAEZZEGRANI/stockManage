package ma.xproce.stockmanag.service;

import ma.xproce.stockmanag.dao.entities.Stock;
import ma.xproce.stockmanag.dao.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StockManager implements StockService{
    @Autowired
    private StockRepository stockRepository;

    @Override
    public Boolean deleteStockById(Integer id) {
        try {
            stockRepository.deleteById(id);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    @Override
    public Stock addStock(Stock stock) {
        try {
            return stockRepository.save(stock);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Stock> getAllStocks() {
        try {
            return stockRepository.findAll();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Page<Stock> searchStocks(String keyword, int page, int size) {
        return stockRepository.findByDescriptionContains(keyword, PageRequest.of(page, size));
    }

    @Override
    public List<Stock> getByKeyword(String keyword) {
        // Implémentez la logique de recherche par mot-clé pour les stocks si nécessaire
        return null;
    }

    @Override
    public Stock getStockById(Integer id) {
        return stockRepository.findById(id).orElse(null);
    }

    @Override
    public Stock updateStock(Stock stock) {
        try {
            return stockRepository.save(stock);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Boolean deleteStock(Integer id) {
        try {
            stockRepository.deleteById(id);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
}