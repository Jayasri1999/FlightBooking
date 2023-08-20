package payment;

import flightbooking.Flight;
import flightbooking.FlightRepo;
import flightbooking.FlightService;
import flightbooking.Seat;
import validation.InputValidation;

import java.util.ArrayList;

public class Payment {
    InputValidation inputValidation = new InputValidation();
    Seat seat = new Seat();
    FlightRepo flightRepo = FlightRepo.getInstance();

    //calculate tax
    //type of payment
    //totalamount
    public boolean payAmount(String flightNo, int seatNo){
        int indexFlightNo= Integer.parseInt(flightNo)-1;
        int price, totalAmount;
        Flight flight = flightRepo.getFlightList().get(indexFlightNo);
        if(seat.isEconomy(seatNo)){
            price = economyPrice(flight.price);
        } else {
            price = flight.price;
        }
        if(seat.isCornerseat(seatNo)){
            price = cornerPrice(price);
        }
        totalAmount = amountSummary(price);
        return makePayment(totalAmount);
    }

    public int economyPrice(int price){
        return (int) (price+price*0.03);
    }

    public int cornerPrice(int price){
        return (int) (price + price*0.03);
    }

    public int calculateTax(int price){
        return (int) (price*0.06);
    }

    public int amountSummary(int price){
        int tax = calculateTax(price);
        int totalPrice = price+tax;
        System.out.println("==== Amount Summary ====");
        System.out.println("Price: $"+price);
        System.out.println("Estimated Tax: $"+tax);
        System.out.println("Total Amount: $"+totalPrice);
        System.out.println("======================");
        return totalPrice;
    }

    public boolean makePayment(int price){
        String doPayment = inputValidation.validatePayment();
        if(doPayment.equals("N")){
            System.out.println("Get a life! Do travel");
            return false;
        }else {
            System.out.println("Payment done");
            return true;
        }
    }




}
