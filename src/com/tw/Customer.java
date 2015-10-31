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
        String header = getHeaderFor(name);
        String result = "";

        for (Rental rental: rentals) {
            result += "\t" + rental.getMovie().getTitle() + "\t" + getAmountFor(rental) + "\n";
        }

        return header + result + getFooter(totalAmount, frequentRenterPoints);
    }

    private double getTotalAmountFor(List<Rental> rentals) {
        double totalAmount = 0;

        for (Rental rental : rentals)
            totalAmount += getAmountFor(rental);

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

    private double getAmountFor(Rental rental) {
        double amount = 0;
        switch (rental.getMovie().getPriceCode()) {
            case Movie.REGULAR:
                amount += 2;
                if (rental.getDaysRented() > 2)
                    amount += (rental.getDaysRented() - 2) * 1.5;
                break;
            case Movie.NEW_RELEASE:
                amount += rental.getDaysRented() * 3;
                break;
            case Movie.CHILDRENS:
                amount += 1.5;
                if (rental.getDaysRented() > 3)
                    amount += (rental.getDaysRented() - 3) * 1.5;
                break;
        }
        return amount;
    }
}
