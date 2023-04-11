package com.stackroute;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    Person person;
@BeforeEach
    public void setUp(){
    person = new Person("Sachin","Password",23);
}

  @Test
    public void givenUserNameShouldreturnUserName(){
    person.setUserName("Joe");
    assertEquals("Joe",person.getUserName());
  }


  @Test
    public void givePasswordShouldNotbeNull(){
    assertNotNull(person.getPassword());

  }


  @Test
    public void giveNegativeAgeShouldThrowException(){
    person.setAge(-4);
    Exception exception = assertThrows(NegativeAgeException.class,()-> person.checkAge());
    assertEquals("Age cannot be -ve",exception.getMessage());
  }




  @Test
    public void giveUserNameAndPasswordShouldReturnLoginSuccessFully(){
     person.setPassword("password");
     person.setUserName("Anu");

     assertAll("check admin user with not null inputs",
      () ->{
         assertNotNull(person.getUserName());

         assertAll("admin user message is not valid",
                 ()-> assertEquals("Normal User",person.validate(person.getUserName(), person.getPassword())),
                 ()->assertEquals(true,person.checkPassword())
         );
      }
     );
  }



}