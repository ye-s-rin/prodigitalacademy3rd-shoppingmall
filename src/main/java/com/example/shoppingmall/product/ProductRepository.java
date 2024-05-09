package com.example.shoppingmall.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.java.Log;
import org.springframework.stereotype.Repository;

@Log
@Repository
public class ProductRepository {

    private Map<Integer, Product> productTable = new HashMap<>();
    private int id = 0; // DB auto_increment

    public ProductRepository() {
        this.productTable.put(id, new Product(id++, "cup0", 0));
        this.productTable.put(id, new Product(id++, "cup1", 0));
        this.productTable.put(id, new Product(id++, "t-shirts0", 1));
        this.productTable.put(id, new Product(id++, "t-shirts1", 1));
        this.productTable.put(id, new Product(id++, "pants0", 2));
        this.productTable.put(id, new Product(id++, "pants1", 2));
    }

    public Product findProduct(int id) {
        return this.productTable.get(id);
    }

    public Map findProducts() {
        Map result = new HashMap<String, Object>();
        result.put("products", this.productTable.values());

        return result;
    }

    public Map pagination(Integer currentPage, Integer limit, Integer categoryId) {
        Map result = new HashMap<String, Object>();
        Map subProducts = new HashMap<Integer, Product>();
        int lastPage;

        log.info("categoryId is " + categoryId);
        if (categoryId == null) {
            lastPage = (int) Math.floor((double) this.productTable.size() / limit);
            currentPage = Math.min(currentPage, lastPage);

            for (int i = currentPage * limit;
                i < Math.min(currentPage + limit, productTable.size()); i++) {
                subProducts.put(i, findProduct(i));
            }
        } else {
            int count = 0;
            ArrayList<Product> tempProducts = new ArrayList<>();
            for (Product product : this.productTable.values()) {
                if (product.getCategoryId() == categoryId) {
                    tempProducts.add(product);
                    count++;
                }
            }

            int tempCount = 0;
            for (Product product : tempProducts) {
                if (tempCount < limit) {
                    subProducts.put(product.getId(), product);
                    tempCount++;
                }
            }

            lastPage = (int) Math.floor((double) count / limit);
            currentPage = Math.min(currentPage, lastPage);
        }

        result.put("subProducts", subProducts);
        result.put("currentPage", currentPage);
        result.put("lastPage", lastPage);

        return result;
    }

    public Product registerProduct(Product product) {
        product.setId(id++);
        this.productTable.put(product.getId(), product);
        log.info("/product: repository - " + findProduct(product.getId()).getName());

        return findProduct(product.getId());
    }
}
