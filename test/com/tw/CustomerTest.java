package com.tw;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerTest {

    Customer customer;
    Movie sholey;
    Movie bahubali;

    @Before
    public void setUp() throws Exception {
        customer = new Customer("Vikas");
        sholey = new Movie("Sholey", Movie.REGULAR);
        bahubali = new Movie("Bahubali", Movie.NEW_RELEASE);

    }

    @Test
    public void testStatementGivesTheCurrentStatusOfTheCustomerForRegularMovie () throws Exception {
        Rental rental = new Rental(sholey, 2);
        customer.addRental(rental);
        String expectedStatement = "Rental Record for Vikas\n"
                + "\tSholey\t2.0\n"
                + "Amount owed is 2.0\nYou earned 1 frequent renter points";
        assertEquals(expectedStatement, customer.statement());
    }

    @Test
    public void testStatementGivesTheCurrentStatusForNewReleaseMovie () {
        customer.addRental(new Rental(bahubali, 4));
        String expectedStatement = "Rental Record for Vikas\n"
                + "\tBahubali\t12.0\n"
                + "Amount owed is 12.0\nYou earned 2 frequent renter points";

        assertEquals(expectedStatement, customer.statement());
    }

    @Test
    public void testStatementGivesTheCurrentStatusForChildrenMovie () {
        Movie jungleBook = new Movie("Jungle Book", Movie.CHILDRENS);
        customer.addRental(new Rental(jungleBook, 5));
        String expectedStatement = "Rental Record for Vikas\n"
                + "\tJungle Book\t4.5\n"
                + "Amount owed is 4.5\nYou earned 1 frequent renter points";

        assertEquals(expectedStatement, customer.statement());

    }

    @Test
    public void testStatementGivesTheCurrentStatusForDiffrentTypeOfMovies () {
        Rental rental = new Rental(sholey, 2);
        Movie jungleBook = new Movie("Jungle Book", Movie.CHILDRENS);
        customer.addRental(new Rental(jungleBook, 5));
        customer.addRental(new Rental(bahubali, 5));
        customer.addRental(rental);

        String expectedStatement = "Rental Record for Vikas\n"
                + "\tJungle Book\t4.5\n"
                + "\tBahubali\t15.0\n"
                + "\tSholey\t2.0\n"
                + "Amount owed is 21.5\nYou earned 4 frequent renter points";

        assertEquals(expectedStatement, customer.statement());

    }
}