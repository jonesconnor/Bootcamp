package com.stackroute.collections;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

public class ProductSetServiceTests {
	
	private ProductSetService service;
	
	@BeforeEach
	public void setUp() {
		service = new ProductSetService();
	}
	
	@AfterEach
	public void end() {
		service = null;
	}
	
	@Test
	public void givenProductToAddToSetShouldReturnTrueAndSizeShouldReturn1() {
		assertTrue(service.addProductToSet(1, "Product 1", 10.0));
        assertEquals(1, service.getProductSet().size());
	}
	
	@Test
	public void givenProductReplaceProductNameShouldReturnUpdatedProductName() {
		service.getProductSet().add(new Product(1, "Product 1", 10.0));
        Product replacedProduct = service.replaceProductName("Product 1", "Product 2");
        assertNotNull(replacedProduct);
        assertEquals("Product 2", replacedProduct.getProductName());
	}
	
	@Test
    void givenProductSetIsEmptyShouldReturnNull() {
        assertNull(service.replaceProductName("OldProductName", "NewProductName"));
    }
	
	@Test
	void givenProductReplaceProductNameButGivenNameDoesNotExistShouldReturnNull() {
		service.getProductSet().add(new Product(1, "Product 1", 10.0));
		Product replacedProduct = service.replaceProductName("Product 2", "Product 3");
		assertNull(replacedProduct);
	}
	
	@Test
	void givenEmptyProductSetShouldReturnNull() {
		Product highestPrice = service.getProductWithHighestPrice();
		assertNull(highestPrice);
	}
	
	@Test
	void givenProductSetWithOneProductShouldReturnThatProduct() {
		Product highestPrice = new Product(1, "Apple", 10);
	    service.addProductToSet(1, "Apple", 10);
	    assertEquals(highestPrice, service.getProductWithHighestPrice());
	}

	@Test
	void givenProductSetHasMultipleProductsShouldReturnProductWithHighestPrice() {
		Product highestPrice = new Product(3, "Mango", 12);
	    service.addProductToSet(1, "Apple", 10);
	    service.addProductToSet(2, "Banana", 11);
	    service.addProductToSet(3, "Mango", 12);
	    assertEquals(highestPrice, service.getProductWithHighestPrice());
	}
	
	@Test
	void givenEmptyProductSetShouldReturnEmptyProductSet() {
		Set<Product> result = service.removeProductWithPriceHigherThanFiveThousand();
		assertTrue(result.isEmpty());
	}
	
	@Test
	void givenSetWithSomeProductsGreaterThan5000ShouldReturnSetWithoutThoseItems() {
		
		Set<Product> desiredSet = new HashSet<>();
		desiredSet.add(new Product(1, "Apple", 1200));
		
		service.addProductToSet(1, "Apple", 1200);
	    service.addProductToSet(2, "Banana", 6400);
	    service.addProductToSet(3, "Mango", 9000);
	    
	    Set<Product> result = service.removeProductWithPriceHigherThanFiveThousand();
	    assertEquals(desiredSet, result);
	}
	
	@Test
	void givenEmptyProductSetShouldReturnEmptyProductList() {
		List<Integer> productIdList = service.getProductIdList();
		assertTrue(productIdList.isEmpty());
	}
	
	@Test
	void givenPopulatedProductSetShouldReturnSortedProductIdListInDescendingOrder() {
		service.addProductToSet(1, "Apple", 10);
	    service.addProductToSet(2, "Banana", 11);
	    service.addProductToSet(3, "Mango", 12);
	    
	    List<Integer> productIdList = service.getProductIdList();
	    assertEquals(Arrays.asList(3,2,1), productIdList);
	    
	}

}
