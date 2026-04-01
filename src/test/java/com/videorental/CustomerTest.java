package com.videorental;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class CustomerTest {

    public static final String NAME = "Yiji";
    public static final String TITLE = "TITLE_NOT_IMPORTANT";
    Customer customer;

    private static Rental createRental(int dayRented, int priceCode) {
        Movie movie = new Movie(TITLE, priceCode);
        Rental rental = new Rental(movie, dayRented);
        return rental;
    }

    @BeforeEach
    void setUp(){
        customer = new Customer(NAME);
    }

    @Test
    public void testSetPrice(){
        Movie movie = new Movie("testSetPriceCode", 1);
        movie.setPriceCode(2);
        customer.addRental(new Rental(movie, 1));
        Assertions.assertEquals("Rental Record for Yiji\n" +
                "\t1.5(testSetPriceCode)\n" +
                "Amount owed is 1.5\n" +
                "You earned 1 frequent renter pointers", customer.statement()); sta
    }

    @Test
    public void statementForNoRental(){
        Assertions.assertEquals("Rental Record for Yiji\n" +
                "Amount owed is 0.0\n" +
                "You earned 0 frequent renter pointers",customer.statement());
    }

    @Test
    public void statementForRegularMovieRentalForLessThan3Days(){
        customer.addRental(createRental(2, Movie.REGULAR));

        Assertions.assertEquals("Rental Record for Yiji\n" +
                "\t2.0(TITLE_NOT_IMPORTANT)\n" +
                "Amount owed is 2.0\n" +
                "You earned 1 frequent renter pointers", customer.statement());
    }

    @Test
    public void statementForRegularMovieRentalForMoreThan2Days(){
        customer.addRental(createRental(3, Movie.REGULAR));

        Assertions.assertEquals("Rental Record for Yiji\n" +
                "\t3.5(TITLE_NOT_IMPORTANT)\n" +
                "Amount owed is 3.5\n" +
                "You earned 1 frequent renter pointers", customer.statement());
    }

    @Test
    public void statementForNewReleaseMovie(){
        customer.addRental(createRental(1, Movie.NEW_RELEASE));

        Assertions.assertEquals("Rental Record for Yiji\n" +
                "\t3.0(TITLE_NOT_IMPORTANT)\n" +
                "Amount owed is 3.0\n" +
                "You earned 1 frequent renter pointers", customer.statement());
    }

    @Test
    public void statementForNewReleaseMovieRentalMoreThan1Day(){
        customer.addRental(createRental(2, Movie.NEW_RELEASE));

        Assertions.assertEquals("Rental Record for Yiji\n" +
                "\t6.0(TITLE_NOT_IMPORTANT)\n" +
                "Amount owed is 6.0\n" +
                "You earned 2 frequent renter pointers", customer.statement());
    }

    @Test
    public void statementForChilderensMovieRentalMoreThan3Days(){
        customer.addRental(createRental(4, Movie.CHILDRENS));

        Assertions.assertEquals("Rental Record for Yiji\n" +
                "\t3.0(TITLE_NOT_IMPORTANT)\n" +
                "Amount owed is 3.0\n" +
                "You earned 1 frequent renter pointers", customer.statement());
    }

    @Test
    public void statementForChilderensMovieRentalLessThan4Days(){
        customer.addRental(createRental(3, Movie.CHILDRENS));

        Assertions.assertEquals("Rental Record for Yiji\n" +
                "\t1.5(TITLE_NOT_IMPORTANT)\n" +
                "Amount owed is 1.5\n" +
                "You earned 1 frequent renter pointers", customer.statement());
    }

    @Test
    public void statementForFewMovieRental(){
        customer.addRental(createRental(1, Movie.REGULAR));
        customer.addRental(createRental(4, Movie.NEW_RELEASE));
        customer.addRental(createRental(4, Movie.CHILDRENS));

        Assertions.assertEquals("Rental Record for Yiji\n" +
                "\t2.0(TITLE_NOT_IMPORTANT)\n" +
                "\t12.0(TITLE_NOT_IMPORTANT)\n" +
                "\t3.0(TITLE_NOT_IMPORTANT)\n" +
                "Amount owed is 17.0\n" +
                "You earned 4 frequent renter pointers", customer.statement());

    }


}
