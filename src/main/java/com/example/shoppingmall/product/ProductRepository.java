package com.example.shoppingmall.product;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private Map<Integer, Product> productTable = new HashMap<>();
    private int id = 0; // DB auto_increment

    public Product findById(int id) {
        return this.entityManager.find(Product.class, id);
    }

    public Map findProducts() {
        Map result = new HashMap<String, Object>();
        result.put("products", this.productTable.values());

        return result;
    }

    public Map findProducts(int currentPage, int limit) {
        Map result = new HashMap<String, Object>();
        ArrayList products = new ArrayList<Product>();
        Map pagination = new HashMap<String, Integer>();
        limit = limit > 0 ? limit : this.productTable.size();
        int lastPage = (int) Math.ceil((double) this.productTable.size() / limit);
        currentPage = Math.min(currentPage, lastPage);

        int si = (currentPage - 1) * limit;
        for (int i = si; i < Math.min(si + limit, productTable.size()); i++) {
            products.add(findById(i));
        }

        pagination.put("currentPage", currentPage);
        pagination.put("lastPage", lastPage);
        pagination.put("totalCount", this.productTable.size());

        result.put("products", products);
        result.put("pagination", pagination);

        return result;
    }

    public Map findProducts(int currentPage, int limit, int categoryId) {
        Map result = new HashMap<String, Object>();
        ArrayList products = new ArrayList<Product>();
        Map pagination = new HashMap<String, Integer>();
        limit = limit > 0 ? limit : this.productTable.size();
        int count = 0;

        ArrayList<Product> tempProducts = new ArrayList<>();
        for (Product product : this.productTable.values()) {
            if (product.getCategoryId() == categoryId) {
                tempProducts.add(product);
                count++;
            }
        }

        int lastPage = (int) Math.ceil((double) count / limit);
        currentPage = Math.min(currentPage, lastPage);

        count = 0;
        int si = (currentPage - 1) * limit;
        for (Product product : tempProducts) {
            if (count >= si && count < si + limit) {
                products.add(product);
            }
            count++;
        }

        pagination.put("currentPage", currentPage);
        pagination.put("lastPage", lastPage);
        pagination.put("totalCount", tempProducts.size());

        result.put("products", products);
        result.put("pagination", pagination);

        return result;
    }

    public Product registerProduct(Product product) {
        product.setId(id++);
        this.productTable.put(product.getId(), product);

        return findById(product.getId());
    }

    public Product deleteProduct(int id) {
        return this.productTable.remove(id);
    }
}
