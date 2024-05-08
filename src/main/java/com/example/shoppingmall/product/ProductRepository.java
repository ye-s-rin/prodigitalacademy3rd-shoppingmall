package com.example.shoppingmall.product;

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
        this.productTable.put(id, new Product(id++, "상품0"));
        this.productTable.put(id, new Product(id++, "상품1"));
        this.productTable.put(id, new Product(id++, "상품2"));
    }

    public Product findProduct(int id) {
        return this.productTable.get(id);
    }

    public Map findProducts() {
        Map result = new HashMap<String, Object>();
        result.put("products", this.productTable.values());

        return result;
    }

    public Map pagination(int currentPage, int limit) {
        Map result = new HashMap<String, Object>();
        Map subProducts = new HashMap<Integer, Product>();
        int lastPage = (int) Math.floor((double) this.productTable.size() / limit);

        currentPage = Math.min(currentPage, lastPage);
        for (int i = currentPage * limit; i < Math.min(currentPage + limit, productTable.size()); i++) {
            subProducts.put(i, findProduct(i));
        }

        result.put("subProducts", subProducts);
        result.put("currentPage", currentPage);
        result.put("lastPage", lastPage);

        return result;
    }

    public Product registerProduct(Product product){
        product.setId(id++);
        this.productTable.put(product.getId(), product);
        log.info("/product: repository - " + findProduct(product.getId()).getName());

        return findProduct(product.getId());
    }
}
