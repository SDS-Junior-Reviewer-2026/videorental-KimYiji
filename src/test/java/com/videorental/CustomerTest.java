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

    @Test
    public void statementForNoRental(){
        String receipt = customer.statement();
        Assertions.assertEquals("Rental Record for Yiji\n" +
                "Amount owed is 0.0\n" +
                "You earned 0 frequent renter pointers",receipt);
    }

    @Test
    public void statementForRegularMovieRentalForLessThan3Days(){
        customer.addRental(new Rental(new Movie("Reg2", Movie.REGULAR),2));

        String receipt = customer.statement();
        Assertions.assertEquals("Rental Record for Yiji\n" +
                "\t2.0(Reg2)\n" +
                "Amount owed is 2.0\n" +
                "You earned 1 frequent renter pointers", receipt);
    }

    @Test
    public void statementForRegularMovieRentalForMoreThan2Days(){
        customer.addRental(new Rental(new Movie("Reg3", Movie.REGULAR),3));

        String receipt = customer.statement();
        Assertions.assertEquals("Rental Record for Yiji\n" +
                "\t3.5(Reg3)\n" +
                "Amount owed is 3.5\n" +
                "You earned 1 frequent renter pointers", receipt);
    }

    @Test
    public void statementForNewReleaseMovie(){
        customer.addRental(new Rental(new Movie("New1", Movie.NEW_RELEASE),1));

        String receipt = customer.statement();
        Assertions.assertEquals("Rental Record for Yiji\n" +
                "\t3.0(New1)\n" +
                "Amount owed is 3.0\n" +
                "You earned 1 frequent renter pointers", receipt);
    }

    @Test
    public void statementForNewReleaseMovieRentalMoreThan1Day(){
        customer.addRental(new Rental(new Movie("New2", Movie.NEW_RELEASE),2));

        String receipt = customer.statement();
        Assertions.assertEquals("Rental Record for Yiji\n" +
                "\t6.0(New2)\n" +
                "Amount owed is 6.0\n" +
                "You earned 2 frequent renter pointers", receipt);
    }

    @Test
    public void statementForChilderensMovieRentalMoreThan3Days(){
        customer.addRental(new Rental(new Movie("Child4", Movie.CHILDRENS),4));

        String receipt = customer.statement();
        Assertions.assertEquals("Rental Record for Yiji\n" +
                "\t3.0(Child4)\n" +
                "Amount owed is 3.0\n" +
                "You earned 1 frequent renter pointers", receipt);
    }

    @Test
    public void statementForChilderensMovieRentalLessThan4Days(){
        customer.addRental(new Rental(new Movie("Child3", Movie.CHILDRENS),3));

        String receipt = customer.statement();
        Assertions.assertEquals("Rental Record for Yiji\n" +
                "\t1.5(Child3)\n" +
                "Amount owed is 1.5\n" +
                "You earned 1 frequent renter pointers", receipt);
    }

    @Test
    public void statementForFewMovieRental(){
        customer.addRental(new Rental(new Movie("Reg1", Movie.REGULAR),1));
        customer.addRental(new Rental(new Movie("New4", Movie.NEW_RELEASE),4));
        customer.addRental(new Rental(new Movie("Child4", Movie.CHILDRENS),4));

        String receipt = customer.statement();
        Assertions.assertEquals("Rental Record for Yiji\n" +
                "\t2.0(Reg1)\n" +
                "\t12.0(New4)\n" +
                "\t3.0(Child4)\n" +
                "Amount owed is 17.0\n" +
                "You earned 4 frequent renter pointers", receipt);

    }


    @Disabled
    @Test
    public void testRegular_1(){
        customer.addRental(new Rental(new Movie("Reg1", Movie.REGULAR),1));

        String receipt = customer.statement();
        Assertions.assertEquals("", receipt);
    }

    @Disabled
    @Test
    public void testNew_1(){
        customer.addRental(new Rental(new Movie("New1", Movie.NEW_RELEASE),1));

        String receipt = customer.statement();
        System.out.println(receipt);
        Assertions.assertEquals("Rental Record for Yiji\n" +
                "\t3.0(New1)\n" +
                "Amount owed is 3.0\n" +
                "You earned 1 frequent renter pointers", receipt);
    }

    @Disabled
    @Test
    public void testChildrens_1(){
        customer.addRental(new Rental(new Movie("Child1", Movie.CHILDRENS),1));

        String receipt = customer.statement();
        Assertions.assertEquals("", receipt);

    }


    @Disabled
    @Test
    public void test() {
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
