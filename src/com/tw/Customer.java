package com.tw;
import java.util.ArrayList;
import java.util.List;

public class Customer {

    private String name;
    private List<Rental> rentals = new ArrayList<Rental>();

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public String statement() {
        double totalAmount = getTotalAmountFor(rentals);
        int frequentRenterPoints = getFrequentRenterPoints(rentals);
        String result = "";

        for (Rental rental: rentals) {
            result += getSubTotalFor(rental);
        }

        return getHeaderFor(name) + result + getFooter(totalAmount, frequentRenterPoints);
    }

    private String getSubTotalFor(Rental rental) {
        return "\t" + rental.getMovie().getTitle() + "\t" + rental.getAmountFor() + "\n";
    }

    private double getTotalAmountFor(List<Rental> rentals) {
        double totalAmount = 0;

        for (Rental rental : rentals)
            totalAmount += rental.getAmountFor();

        return totalAmount;
    }

    private int getFrequentRenterPoints(List<Rental> rentals) {
        int frequentRenterPoints = 0;

        for (Rental rental : rentals) {
            if ((rental.getMovie().getPriceCode() == Movie.NEW_RELEASE) && rental.getDaysRented() > 1)
                frequentRenterPoints ++;
            frequentRenterPoints++;
        }
        return frequentRenterPoints;
    }

    private String getHeaderFor(String name) {
        return "Rental Record for " + name + "\n";
    }

    private String getFooter(double totalAmount, int frequentRenterPoints) {
        return "Amount owed is " + String.valueOf(totalAmount) + "\n" +
                "You earned " + String.valueOf(frequentRenterPoints) +
                " frequent renter points";
    }

}
