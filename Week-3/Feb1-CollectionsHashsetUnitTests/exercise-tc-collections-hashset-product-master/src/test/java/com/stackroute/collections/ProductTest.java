package com.stackroute.collections;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class ProductTest {
	
	    @Test
	    void givenValidInputShouldCreateProduct(){
	        Product product = new Product(1, "Apple", 10.5);
	        assertNotNull(product);
	        assertEquals(1, product.getProductId());
	        assertEquals("Apple", product.getProductName());
	        assertEquals(10.5, product.getPrice());
	    }
	    

	    @Test
	    void givenInvalidPriceShouldThrowException() {
	        Exception exception = assertThrows(InvalidPriceException.class, () -> new Product(1, "Apple", -10.5));
	        assertEquals("Product price should be greater than zero", exception.getMessage());
	    }

	    @Test
	    void givenNewProductNameShouldReturnUpdatedProductName() {
	    	Product product = new Product(1, "Apple", 14);
	    	product.setProductName("Apple Pie");
	    	assertEquals("Apple Pie", product.getProductName());
	    }

	    @Test 
	    void givenObjectWhenComparedWithItselfShouldReturnTrue() {
	    	Product product = new Product(1, "Apple", 10);
	    	assertTrue(product.equals(product));
	    }
	    
	    @Test
	    void givenDifferentObjectSamePropertiesShouldReturnTrue() {
	        Product product1 = new Product(1, "Apple", 10.5);
	        Product product2 = new Product(1, "Apple", 10.5);
	        assertTrue(product1.equals(product2));
	    }
	    
	    @Test
	    void givenDifferentObjectsDifferentPropertiesShouldReturnFalse() {
	    	Product product1 = new Product(1, "Apple", 10);
	    	Product product2 = new Product(2, "Banana", 11);
	    	assertFalse(product1.equals(product2));
	    }
	    
	    @Test
	    void givenObjectWhenComparedWithNullShouldReturnFalse() {
	    	Product product = new Product(1, "Apple", 10);
	    	assertFalse(product.equals(null));
	    }
	    
	    @Test
	    void givenObjectWhenComparedWithDifferentClassShouldReturnFalse() {
	    	Product product = new Product(1, "Apple", 10);
	    	assertFalse(product.equals("String"));
	    }

	    @Test
	    void givenDifferentObjectDifferentPropertiesShouldReturnFalse() {
	        Product product1 = new Product(1, "Apple", 10.5);
	        Product product2 = new Product(2, "Banana", 20);
	        assertFalse(product1.equals(product2));
	    }
	    
	    @Test
	    void givenTwoDifferentObjectsWithDifferentProductIdShouldReturnFalse() {
	        Product product1 = new Product(1, "Apple", 10.5);
	        Product product2 = new Product(2, "Apple", 10.5);
	        assertFalse(product1.equals(product2));
	    }

	    @Test
	    void givenTwoDifferentObjectsWithDifferentPriceShouldReturnFalse() {
	        Product product1 = new Product(1, "Apple", 10.5);
	        Product product2 = new Product(1, "Apple", 20);
	        assertFalse(product1.equals(product2));
	    }

	    @Test
	    void givenTwoDifferentObjectsWithDifferentProductNameShouldReturnFalse() {
	        Product product1 = new Product(1, "Apple", 10.5);
	        Product product2 = new Product(1, "Banana", 10.5);
	        assertFalse(product1.equals(product2));
	    }

	    @Test
	    void givenTwoDifferentObjectsCompareToSamePriceShouldReturnZero() {
	        Product product1 = new Product(1, "Apple", 10.5);
	        Product product2 = new Product(2, "Banana", 10.5);
	        assertEquals(0, product1.compareTo(product2));
	    }
	    
	    @Test
	    public void givenTwoIdenticalProductsHashCodeReturnsSame() {
	        Product product1 = new Product(1, "Test Product", 10.0);
	        Product product2 = new Product(1, "Test Product", 10.0);
	        assertEquals(product1.hashCode(), product2.hashCode());
	    }
	    
	    @Test
	    public void givenTwoDifferentProductsHashCodeReturnsDifferent() {
	        Product product1 = new Product(1, "Test Product", 10.0);
	        Product product2 = new Product(2, "Test Product", 10.0);
	        assertNotEquals(product1.hashCode(), product2.hashCode());
	    }
	    
	    @Test
	    public void givenProductShouldReturnCorrectToStringFormat() {
	    	Product product = new Product(1, "Test Product", 10);
	    	String toString = "Product{productId=1, productName=Test Product, price=10.0}";
	    	assertEquals(toString, product.toString());
	    }
	
}
