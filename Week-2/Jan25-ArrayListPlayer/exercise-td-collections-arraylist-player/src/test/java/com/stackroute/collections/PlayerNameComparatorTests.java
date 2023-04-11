package com.stackroute.collections;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerNameComparatorTests {
    private static final String MESSAGE_ONE = "PlayerNameComparator should be of comparator type";
    private static final String MESSAGE_TWO = "Player list returned has not sorted player names alphabetically";
    private ArrayList<Player> playerList = new ArrayList<>();
    private Player playerOne;
    private Player playerTwo;

    @BeforeEach
    public void setUp() {
        playerOne = new Player(1, "Sachin Tendulkar", 49, "India Vs Australia");
        playerTwo = new Player(2, "M S Dhoni", 38, "India Vs Australia");
        playerList.add(playerOne);
        playerList.add(playerTwo);
    }

    @AfterEach
    public void tearDown() {
        playerList = null;
        playerOne = null;
        playerTwo = null;
    }

    @Test
    public void givenPlayerNameComparatorClassThenShouldBeOfComparatorType() {
        Assertions.assertTrue(Comparator.class.isAssignableFrom(PlayerNameComparator.class), MESSAGE_ONE);
    }

    @Test
    public void givenPlayerListWhenComparedReturnPlayerListSortedByNameInAscendingOrder() {
        Collections.sort(playerList, new PlayerNameComparator());
        assertThat(MESSAGE_TWO, playerList, contains(playerTwo, playerOne));
    }
}
