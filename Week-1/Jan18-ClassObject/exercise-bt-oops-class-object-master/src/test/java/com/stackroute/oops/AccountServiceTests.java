package com.stackroute.oops;


import com.stackroute.oops.Account;
import com.stackroute.oops.AccountService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class AccountServiceTests {
    private static final String MSG_01 = "Should return an account object for valid values";
    private static final String MSG_02 = "Should return true when amount deposited in active account";
    private static final String MSG_03 = "details returned by getAccountDetails method is not correct or not as per required format";

    private AccountService accountService;
    private Account account;

    /*
     Sets up the tests by creating an Account object, AccountService Object.
     Account counter is set to Zero as the account no is created by incrementing the counter
    */
    @BeforeEach
    public void setUp() {
        Account.setCounter(0);
        accountService = new AccountService();
        account = new Account(100,"active");
    }

    /*
    Dereference the accountService and account object
*/
    @AfterEach
    public void tearDown() {
        accountService = null;
        account = null;
    }

    @Test
    public void givenAccountPropertyValuesThenCreatesAccountObject() {
        Account account = accountService.createAccount(100, "active");
        assertNotNull(account, MSG_01);
    }

    @Test
    public void givenActiveAccountWhenAmountDepositedThenReturnsTrue() {
        final boolean validDeposit = accountService.depositAmountInAccount(account, 100);
        assertTrue(validDeposit,MSG_02);
    }

    @Test
    public void givenAccountThenGetAccountDetails() {
        final String accountDetails = accountService.getAccountDetails(account);
        final String expectedDetails = "[Account No : 1, Status : active, Balance : 100.0]";
        final var regex = "//s+";
        assertEquals(expectedDetails.toLowerCase().replaceAll(regex, " "), accountDetails.toLowerCase().replaceAll(regex, " "), MSG_03);
        assertEquals(expectedDetails, accountDetails,MSG_03);
    }
}