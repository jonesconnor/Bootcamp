package com.stackroute.collections;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class FruitsListServiceTests {

    private static final String MSG_01 = "returned list must contain unique fruit names(case insensitive) passed as parameters";
    private static final String MSG_02 = "searchFruitInList method should return the index of the fruit in the list if it exists, else should return -1. " +
            "case sensitive search should be done in the method";
    private static final String MSG_03 = "searchFruitInListIgnoreCase method should return the index of the fruit in the list if it exists, else should return -1. " +
            "case insensitive search should be done";
    private static final String MSG_04 = "should return empty list if the input parameter is null or empty";

    private List<String> fruits;

    @BeforeEach
    public void setUp() {
        fruits = Arrays.asList("Mango", "Apple", "Banana", "Grapes");
    }

    @AfterEach
    public void tearDown() {
        fruits = null;
    }

    @Test
    public void givenUniqueFruitNamesAsInputParameterThenReturnListWithFruitNames() {
        final List<String> fruitsList = FruitsListService.addFruitsToList("Apple,Mango,Grapes");
        assertThat(MSG_01, fruitsList, hasSize(3));
        assertThat(MSG_01, fruitsList, contains("Apple", "Mango", "Grapes"));
    }

    @Test
    public void givenFruitNamesAsNullThenReturnEmptyList() {
        final List<String> fruitsList = FruitsListService.addFruitsToList(null);
        assertThat(MSG_04, fruitsList, hasSize(0));
    }

    @Test
    public void givenFruitNamesAsEmptyStringThenReturnEmptyList() {
        final List<String> fruitsList = FruitsListService.addFruitsToList("");
        assertThat(MSG_04, fruitsList, hasSize(0));
    }

    @Test
    public void givenDuplicateFruitNamesAsInputParameterThenReturnListWithUniqueFruitNames() {
        final List<String> fruitsList = FruitsListService.addFruitsToList("Apple,Mango,Grapes,apple,MANGO,Grapes");
        assertThat(MSG_01, fruitsList, hasSize(3));
        assertThat(MSG_01, fruitsList, contains("Apple", "Mango", "Grapes"));
    }

    @Test
    public void givenListOfFruitsWhenExistingFruitSearchedThenReturnIndexOfFruitInList() {
        final int indexOfFruit = FruitsListService.searchFruitInList(fruits, "Banana");
        assertThat(MSG_02, indexOfFruit, is(2));
    }

    @Test
    public void givenListOfFruitsWhenExistingFruitSearchedWithDifferentCaseThenReturnMinusOne() {
        final int indexOfFruit = FruitsListService.searchFruitInList(fruits, "banana");
        assertThat(MSG_02, indexOfFruit, is(-1));
    }

    @Test
    public void givenListOfFruitsWhenNonExistingFruitSearchedThenReturnMinusOne() {
        final int indexOfFruit = FruitsListService.searchFruitInList(fruits, "Guava");
        assertThat(MSG_02, indexOfFruit, is(-1));
    }

    @Test
    public void givenListOfFruitsWhenExistingFruitSearchedIgnoringCaseThenReturnIndexOfFruitInList() {
        final int indexOfFruit = FruitsListService.searchFruitInListIgnoreCase(fruits, "AppLE");
        assertThat(MSG_03, indexOfFruit, is(1));
    }

    @Test
    public void givenListOfFruitsWhenExistingFruitSearchedWithSameCaseThenReturnIndexOfFruitInList() {
        final int indexOfFruit = FruitsListService.searchFruitInListIgnoreCase(fruits, "Apple");
        assertThat(MSG_03, indexOfFruit, is(1));
    }

    @Test
    public void givenListOfFruitsWhenNonExistingFruitSearchedIgnoringCaseThenReturnMinusOne() {
        final int indexOfFruit = FruitsListService.searchFruitInListIgnoreCase(fruits, "Guava");
        assertThat(MSG_03, indexOfFruit, is(-1));
    }
}