## Problem Statement: Class definition and Object creation

Complete the class Account and AccountService as per the below requirement

**Class Account**

Create the following properties. properties should be private:

    -accountNo  : int           
    -balance    : double
    -status     : String
    -counter    : int static


- Define parameterized constructor with two parameters to initialize balance and status. 
    - accountNo should be initialized by incrementing counter variable.

- Define Getters for all variables and setters for counter and status. 
     
-   Define the below methods:

        +depositAmount(double) : boolean       
            - Should add the input amount to the account balance and return true, if the account status is active
            - Should return false, if the account is inactive 

        +getAccountDetails() : String
            - Should return the account details as per the below format
       
                [Account No : 1, Status : active, Balance : 100.5]

**class AccountService**
-   Define the below methods:

        +createAccount(double, String) : Account
            - Should create an account object using input amount and status, and return the object 

        +depositAmountInAccount(Account, double) : boolean
            - Should deposit the input amount in to the Account passed as parameter 

        +getAccountDetails(Account) : String
            - Should return the account details for the Account passed as parameter

## Instructions
- Do not change the provided class/method names unless instructed
- Ensure your code compiles without any errors/warning/deprecations 
- Follow best practices while coding