package com.stackroute.oops;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*
    Class for Analyzing the products present in ProductRepository
 */
public class ProductService {

    /*
        Returns the name of the product given the productCode
     */
    public String findProductNameByCode(int productCode) {
    	
    	Product[] Products = ProductRepository.getProducts();
    	Map<Integer, Product> productsMap = new HashMap<>();
    	for (Product p : Products) {
    		productsMap.put(p.getProductCode(), p);
    	}
    	
    	if (productsMap.containsKey(productCode)) {
    		Product p = productsMap.get(productCode);
    		return p.getName();
    	}
    	
        return null;
    }

    /*
        Returns the Product with maximum price in a given category
     */
    public Product findMaxPriceProductInCategory(String category) {
    	Product[] Products = ProductRepository.getProducts();
    	Map<Integer, Product> productsMap = new HashMap<>();
    	for (Product p : Products) {
    		if (p.getCategory().equalsIgnoreCase(category)) {
    			productsMap.put(p.getProductCode(), p);
    		}
    	}
    	
    	if (productsMap.size() == 0) {
    		return null;
    	}
    	
    	double maxPriceSoFar = 0;
    	Product productToBeReturned = Products[0];
    	for (Map.Entry<Integer, Product> entry : productsMap.entrySet()) {
    		Product val = entry.getValue();
    		if (val.getPrice() > maxPriceSoFar) {
    			productToBeReturned = val;
    			maxPriceSoFar = val.getPrice();
    		}
    	}
    	
        return productToBeReturned;
    }

    /*
        Returns a array of products for a given category
     */
    public Product[] getProductsByCategory(String category) {
    	Product[] Products = ProductRepository.getProducts();
    	
    	ArrayList<Product> productsList = new ArrayList<Product>();
    	
    	for (Product p : Products) {
    		if (p.getCategory().equalsIgnoreCase(category)) {
    			productsList.add(p);
    		}
    	}
    	
    	if (productsList.size() == 0) {
    		return null;
    	}
    	
    	Product desiredProducts[] = productsList.toArray(new Product[0]);
    	
        return desiredProducts;
    }
}
