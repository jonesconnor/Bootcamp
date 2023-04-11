package com.stackroute.collections;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerTests {
    private static final String MESSAGE_ONE = "Player properties are not set correctly in the constructor";
    private static final String MESSAGE_TWO = "Player details returned by toString() are not correct";
    private Player player;

    @BeforeEach
    public void setUp() {
        player = new Player(1, "Sachin Tendulkar", 49, "India Vs Australia");
    }

    @AfterEach
    public void tearDown() {
        player = null;
    }

    @Test
    public void givenValidPlayerValuesWhenCreatedThenSetProperties() {
        assertEquals(1, player.getPlayerId(), MESSAGE_ONE);
        assertEquals("Sachin Tendulkar", player.getPlayerName(), MESSAGE_ONE);
        assertEquals(49, player.getPlayerScore(), MESSAGE_ONE);
        assertEquals("India Vs Australia", player.getPlayerMatch(), MESSAGE_ONE);
    }

    @Test
    public void givenValidPlayerValuesThenReturnPlayerDetails() {
        String details = player.toString();
        assertTrue(details.contains("playerId=1, playerName=Sachin Tendulkar, playerScore=49"), MESSAGE_TWO);
        assertTrue(details.contains("playerMatch=India Vs Australia"), MESSAGE_TWO);
    }

}
