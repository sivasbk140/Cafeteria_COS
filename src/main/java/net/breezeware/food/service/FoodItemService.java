package net.breezeware.food.service;

import net.breezeware.food.dao.FoodItemDao;
import net.breezeware.food.entity.FoodItem;

import java.sql.SQLException;
import java.util.List;

public class FoodItemService {

    private final FoodItemDao dao = new FoodItemDao();

    public void create(FoodItem item) throws SQLException {
        if (item.getPrice() <= 0) {
            throw new IllegalArgumentException("Invalid price");
        }
        dao.create(item);
    }

    public List<FoodItem> list() throws SQLException {
        return dao.findAll();
    }

    public void update(FoodItem item) throws SQLException {
        dao.update(item);
    }

    public void delete(int id) throws SQLException {
        dao.delete(id);
    }
}
