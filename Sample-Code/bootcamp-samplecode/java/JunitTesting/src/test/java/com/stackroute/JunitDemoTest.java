package com.stackroute;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;


class JunitDemoTest {

    JunitDemo demo;
    @BeforeEach
    public void setUp(){
     demo = new JunitDemo();
        System.out.println("Before method");
    }

    @BeforeAll
    public static void setupbeforeAll(){
        System.out.println("This is a before All method");
    }

    @AfterEach
    public void tearDown(){
        demo = null;
        System.out.println("After Each method");
    }

@AfterAll
public  static  void AfterAll(){
    System.out.println("For Cleaning memory");
}



    @Test
    public void givenTwoStringShouldReturnConcanatedStringInUpperCase(){

        String actual = demo.concateTwoStringInUpperCase("Hello","world");
        assertEquals("HELLOWORLD",actual);
    }

    @Test
    public void giveTestCaseShouldReturnTrue(){
        assertTrue(true);
    }

    @Order(1)
    @Test
    public void giveStringAndNullShouldRetrunErrorMessage(){
        String concatedString = demo.concateTwoStringInUpperCase("Hello",null);
        assertEquals("String cannot be null",concatedString);
    }


    @Test
    public void givenNumbersShouldReturnMultipleOfTwoNUmbers(){
        int result = demo.multiply("4","5");
        assertEquals(20,result,"Result should be 20 ");
    }


    @Test
    public void givenNegativeNumberShouldReturnTheException(){
        assertThrows(IllegalArgumentException.class,()->demo.multiply("-5","6"));
    }


    @Order(2)
    @Test
    public void givenStringandNullShouldReturnNumberFormatException(){
        assertThrows(NumberFormatException.class,()->demo.multiply("4","a"));
    }


    @ParameterizedTest
    @ValueSource(ints = {2,6,4,12,68,10})
    public void givenvaluesShouldRetunTrueForEvenValues(int number) {
        assertTrue(demo.isEven(number));
    }

@Test
@DisplayName("Return Size of Array")
    public void givenArrayShouldReturnSizeOfArray(){
        int len = JunitDemo.array.length;
        assertEquals(5,len);
//    assertTrue(5==len);
}
  @Test
  @RepeatedTest(2)
    public void givenArrayShouldReturnValueofIndex(){
        int val = JunitDemo.array[1];
        assertEquals(2,val,"Second index of array should return 2");
  }



}