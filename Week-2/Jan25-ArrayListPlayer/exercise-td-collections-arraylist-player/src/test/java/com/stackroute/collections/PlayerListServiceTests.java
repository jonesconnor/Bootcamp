package com.stackroute.collections;

import com.stackroute.collections.Player;
import com.stackroute.collections.PlayerListService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;

public class PlayerListServiceTests {
    private static final String MESSAGE_ONE = "class PlayerListService should have a property PlayerList of expected type";
    private static final String MESSAGE_TWO = "PlayerList should be empty, when Player service object is created";
    private static final String MESSAGE_THREE = "Players are not added to the list correctly";
    private static final String MESSAGE_FOUR = "List of players are not sorted by name properly";
    private static final String MESSAGE_FIVE = "List of players are not sorted by score properly";
    private static final String MESSAGE_SIX = "Sorted list of Players whose score greater than 50 is not returned properly";
    private static final String MESSAGE_SEVEN = "Sorted list of players who played in same match is not returned properly";

    private PlayerListService playerListService;
    private Player playerOne, playerTwo, playerThree, playerFour;

    @BeforeEach
    public void setUp() {
        playerListService = new PlayerListService();
    }

    @AfterEach
    public void tearDown() {
        playerListService = null;
        playerOne = null;
        playerTwo = null;
        playerThree = null;
        playerFour = null;
    }

    @Test
    public void givenPlayerListServiceObjectThenPlayerListInitializedAsArrayList() {
        assertThat(MESSAGE_ONE, playerListService, hasProperty("playerList", instanceOf(ArrayList.class)));
        assertThat(MESSAGE_TWO, playerListService.getPlayerList(), is(empty()));
    }

    @Test
    public void givenPlayerValuesWhenAddedThenReturnPlayerList() {
        List<Player> addedPlayerList;
        playerOne = new Player(1, "Sachin Tendulkar", 38, "India Vs Australia");
        playerTwo = new Player(2, "M S Dhoni", 38, "India Vs West Indies");
        playerListService.addPlayersToList(playerOne);
        playerListService.addPlayersToList(playerTwo);
        addedPlayerList = playerListService.getPlayerList();
        assertThat(MESSAGE_THREE, addedPlayerList, hasSize(2));
        assertThat(MESSAGE_THREE, addedPlayerList, contains(playerOne, playerTwo));
    }

    @Test
    public void givenPlayerServiceWhenEmptyListExistsThenReturnEmptyPlayerList() {
        List<Player> playerList = playerListService.getPlayerListSortedByNameIgnoringCaseInAscendingOrder();
        assertThat(MESSAGE_THREE, playerList, is(empty()));
    }

    @Test
    public void givenPlayerServiceWhenSortedByNameThenReturnNameSortedPlayerList() {
        addPlayersToList();
        List<Player> nameSortedPlayerList = playerListService.getPlayerListSortedByNameIgnoringCaseInAscendingOrder();
        assertThat(MESSAGE_FOUR, nameSortedPlayerList, contains(playerTwo, playerThree, playerOne, playerFour));
    }

    @Test
    public void givenPlayerServiceWhenSortedByScoreThenReturnScoreSortedPlayerList() {
        playerOne = new Player(3, "Rohit Sharma", 68, "India Vs West Indies");
        playerTwo = new Player(4, "Virat Kohli", 80, "India Vs England");
        playerListService.addPlayersToList(playerOne);
        playerListService.addPlayersToList(playerTwo);
        List<Player> scoreSortedPlayerList = playerListService.getPlayerListSortedByScoreInDescendingOrder();
        assertThat(MESSAGE_FIVE, scoreSortedPlayerList, contains(playerTwo, playerOne));
    }

    @Test
    public void givenPlayerServiceWhenScoresAreSameThenReturnPlayerIdSortedPlayerList() {
        addPlayersToList();
        List<Player> scoreSortedPlayerList = playerListService.getPlayerListSortedByScoreInDescendingOrder();
        assertThat(MESSAGE_FIVE, scoreSortedPlayerList, contains(playerFour, playerThree, playerOne, playerTwo));
    }

    @Test
    public void givenPlayerServiceWhenFilteredScoreGreaterThanFiftyMatchedThenReturnScoreSortedPlayerList() {
        addPlayersToList();
        List<Player> scoreSortedPlayerList = playerListService.getPlayerListGreaterThanFiftySortedByScoreInDescendingOrder();
        assertThat(MESSAGE_SIX, scoreSortedPlayerList, hasSize(2));
        assertThat(MESSAGE_SIX, scoreSortedPlayerList, contains(playerFour, playerThree));
    }

    @Test
    public void givenPlayerServiceWhenScoreGreaterThanFiftyNotMatchedThenReturnEmptyList() {
        playerOne = new Player(1, "Sachin Tendulkar", 38, "India Vs Australia");
        playerTwo = new Player(2, "M S Dhoni", 38, "India Vs West Indies");
        playerListService.addPlayersToList(playerOne);
        playerListService.addPlayersToList(playerTwo);
        List<Player> scoreSortedPlayerList = playerListService.getPlayerListGreaterThanFiftySortedByScoreInDescendingOrder();
        assertThat(MESSAGE_SIX, scoreSortedPlayerList, hasSize(0));
    }

    @Test
    public void givenPlayerServiceWhenFilteredBySameMatchPlayedThenReturnNameSortedPlayerList() {
        addPlayersToList();
        List<Player> nameSortedPlayerList = playerListService.getPlayerListPlayedInSameMatchSortedByNameInAscendingOrder("India Vs West Indies");
        assertThat(MESSAGE_SEVEN, nameSortedPlayerList, hasSize(2));
        assertThat(MESSAGE_SEVEN, nameSortedPlayerList, contains(playerTwo, playerThree));
    }

    @Test
    public void givenPlayerServiceWhenNotMatchedBySameMatchThenReturnEmptyList() {
        playerOne = new Player(1, "Sachin Tendulkar", 38, "India Vs Australia");
        playerTwo = new Player(2, "M S Dhoni", 38, "India Vs West Indies");
        List<Player> nameSortedPlayerList = playerListService.getPlayerListPlayedInSameMatchSortedByNameInAscendingOrder("India Vs West Indies");
        assertThat(MESSAGE_SEVEN, nameSortedPlayerList, hasSize(0));
    }

    /**
     * Setting up Player data in PlayerListService for some Tests
     */
    private void addPlayersToList() {
        playerOne = new Player(1, "Sachin Tendulkar", 38, "India Vs Australia");
        playerTwo = new Player(2, "M S Dhoni", 38, "India Vs West Indies");
        playerThree = new Player(3, "Rohit Sharma", 68, "India Vs West Indies");
        playerFour = new Player(4, "Virat Kohli", 80, "India Vs England");
        playerListService.getPlayerList().add(playerOne);
        playerListService.getPlayerList().add(playerTwo);
        playerListService.getPlayerList().add(playerThree);
        playerListService.getPlayerList().add(playerFour);
    }
}
