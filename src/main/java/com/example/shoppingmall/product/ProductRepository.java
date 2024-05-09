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
        this.productTable.put(id, new Product(id++, "pants2", 2));
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
        ArrayList products = new ArrayList<Product>();
        Map pagination = new HashMap<String, Integer>();
        currentPage = currentPage == 0 ? 1 : currentPage;
        int lastPage;

        log.info("categoryId is " + categoryId);
        if (categoryId == null) {
            lastPage = (int) Math.ceil((double) this.productTable.size() / limit);
            currentPage = Math.min(currentPage, lastPage);

            int si = (currentPage - 1) * limit;
            for (int i = si;
                i < Math.min(si + limit, productTable.size()); i++) {
                products.add(findProduct(i));
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

            int si = (currentPage - 1) * limit;
            int tempCount = 0;
            for (Product product : tempProducts) {
                if (tempCount >= si && tempCount < si + limit) {
                    products.add(product);
                }
                tempCount++;
            }

            lastPage = (int) Math.ceil((double) count / limit);
            currentPage = Math.min(currentPage, lastPage);
        }

        pagination.put("currentPage", currentPage);
        pagination.put("lastPage", lastPage);

        result.put("products", products);
        result.put("pagination", pagination);

        return result;
    }

    public Product registerProduct(Product product) {
        product.setId(id++);
        this.productTable.put(product.getId(), product);
        log.info("/product: repository - " + findProduct(product.getId()).getName());

        return findProduct(product.getId());
    }
}
