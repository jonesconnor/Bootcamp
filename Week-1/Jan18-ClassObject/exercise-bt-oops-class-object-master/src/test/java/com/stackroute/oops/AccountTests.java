package com.stackroute.oops;

import com.stackroute.oops.Account;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTests {
    private static final String MSG_01 = "check the constructor for proper initialization of properties";
    private static final String MSG_02 = "depositAmount method should return true on successful deposit and false otherwise";
    private static final String MSG_03 = "balance is not set correctly after depositAmount method execution";
    private static final String MSG_04 = "details returned by getAccountDetails method is not correct or not as per required format";
    public static final String ACTIVE = "active";

    private Account account;

    /*
        Sets up the tests by creating an Account object
         Account counter is set to Zero as the account no is created by incrementing the counter
    */
    @BeforeEach
    public void setUp() {
        Account.setCounter(0);
        account = new Account(400.5, ACTIVE);
    }

    /*
        Dereference the account object
    */
    @AfterEach
    public void tearDown() {
        account = null;
    }

    @Test
    public void givenValidPropertyValuesWhenAccountCreateThenPropertiesAreSet() {
        account = new Account(500.5, ACTIVE);
        assertEquals(2, account.getAccountNo(), MSG_01);
        assertEquals(500.5, account.getBalance(), 0.01, MSG_01);
        assertEquals(ACTIVE, account.getStatus(), MSG_01);
    }

    @Test
    public void givenActiveAccountWhenAmountDepositedThenDepositSuccess() {
        boolean actual = account.depositAmount(50.25);
        assertTrue(actual, MSG_02);
        assertEquals(450.75, account.getBalance(), 0.01, MSG_03);
    }

    @Test
    public void givenInactiveAccountWhenAmountDepositedThenDepositFails() {
        account.setStatus("inactive");
        boolean actual = account.depositAmount(50.25);

        assertFalse(actual, MSG_02);
        assertEquals(400.5, account.getBalance(), 0.01, MSG_03);
    }

    @Test
    public void givenValidAccountThenGetAccountDetails() {
        var actualDetails = account.getAccountDetails();
        var expectedDetails = "[Account No : 1, Status : active, Balance : 400.5]";
        final var regex = "//s+";
        assertEquals(expectedDetails.toLowerCase().replaceAll(regex, " "), actualDetails.toLowerCase().replaceAll(regex, " "), MSG_04);
    }
}