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
}
