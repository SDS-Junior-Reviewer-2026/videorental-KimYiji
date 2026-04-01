package com.videorental;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class CustomerTest {

    Customer customer;

    @BeforeEach
    void setUp(){
        customer = new Customer("Yiji");
    }

    @Disabled
    @Test
    void testRegular_1(){
        customer.addRental(new Rental(new Movie("Reg1", Movie.REGULAR),1));

        String receipt = customer.statement();
        Assertions.assertEquals("", receipt);
    }

    @Disabled
    @Test
    void testNew_1(){
        customer.addRental(new Rental(new Movie("New1", Movie.NEW_RELEASE),1));

        String receipt = customer.statement();
        Assertions.assertEquals("", receipt);
    }

    @Disabled
    @Test
    void testChildrens_1(){
        customer.addRental(new Rental(new Movie("Child1", Movie.CHILDRENS),1));

        String receipt = customer.statement();
        Assertions.assertEquals("", receipt);

    }


    @Disabled
    @Test
    void test() {
        customer.addRental(new Rental(new Movie("Jaws", Movie.REGULAR),2));
        customer.addRental(new Rental(new Movie("GoldenEye", Movie.REGULAR),3));
        customer.addRental(new Rental(new Movie("ShortNew",Movie.NEW_RELEASE ),1));
        customer.addRental(new Rental(new Movie("LongNew",Movie.NEW_RELEASE ),2));
        customer.addRental(new Rental(new Movie("Bambi",Movie.CHILDRENS ),3));
        customer.addRental(new Rental(new Movie("Toy Story",Movie.CHILDRENS ),4));

        //명세서 뽑기
        String 영수증 = customer.statement();
//        System.out.println(영수증);

        //입출력은 안다.
        //Assertions.assertEquals(수정전 기댓값, 출력값);
        Assertions.assertEquals("Rental Record for Yiji\n" +
                "\t2.0(Jaws)\n" +
                "\t3.5(GoldenEye)\n" +
                "\t3.0(ShortNew)\n" +
                "\t6.0(LongNew)\n" +
                "\t1.5(Bambi)\n" +
                "\t3.0(Toy Story)\n" +
                "Amount owed is 19.0\n" +
                "You earned 7 frequent renter pointers", 영수증);

    }
}
