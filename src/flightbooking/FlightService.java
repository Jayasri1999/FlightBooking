package flightbooking;

import payment.Payment;
import validation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import userdetails.*;


public class FlightService {
    InputValidation inputValidation = new InputValidation();
    GetInput getInput = new GetInput();
    UserService userService = new UserService();
    FlightRepo flightRepo = FlightRepo.getInstance();
    Seat seat = new Seat();
    Payment payment = new Payment();
    UserDb userDb = UserDb.getInstance();
    public void bookFlight(String mail){
        String book,flightNo,foodOption,insuranceOption;
        int seatNo;
        String[][] seatMap;
        //String[][] seats=seat.setInitialSeats();
        String[][] seats;
        book = inputValidation.validateBooking();
        if(book.equals("Y")){
            System.out.println("** Welcome to flight booking **");
            flightNo = searchFlight();
            seatNo = seat.pickSeat(flightRepo.getSeatMap().get(flightNo));
            foodOption = selectFood();
            insuranceOption = insured();
            tripSummary(flightNo,seatNo,foodOption,insuranceOption);
            if(!payment.payAmount(flightNo, seatNo)){
                System.out.println("Ticket not booked");
            }else {

                seats=seat.updateSeatMap(seatNo, flightRepo.getSeatMap().get(flightNo), true); //true - add, false -remove
                seats=flightRepo.getSeatMap().put(flightNo, seats);
                addToBookedSeatsList(mail, seatNo, flightNo);
                printTicket(flightNo,seatNo,foodOption,insuranceOption);
                increasePrice(flightNo);
            }

        } else {
            System.out.println("Check out the latest deals");
            return;
        }
    }

    public String searchFlight(){
        //select date and places
        System.out.println("Searching for flights from Detroit to Delhi....");
        ArrayList<Flight> flightList = flightRepo.getFlightList();
        for(Flight flight: flightList){
            System.out.println(flight.id+". Flight Name: "+ flight.flightName+" | Depart: "+flight.depart+" | Destination: "+flight.destination+ " | Time: "+flight.time);
        }
        return inputValidation.validateFlightNo();
    }

    public String selectFood() {
        String foodChoice = inputValidation.validateFood();
        String number, selectedFood="Not selected";
        boolean flag = true;
        if (foodChoice.equals("N")) {
            System.out.println("Good choice. You have chosen to starve on the flight. proceeding to next step");
            selectedFood = "Not selected";
        } else {
            while (flag) {
                System.out.println("Please select from below menu");
                System.out.println("1: Non-veg \n2: veg \n3: No thanks");
                number = getInput.getIntegerString();
                switch (number) {
                    case "1":
                        selectedFood = "Non-veg";
                        System.out.println("You have selected Non-veg");
                        flag = false;
                        break;

                    case "2":
                        selectedFood = "Veg";
                        System.out.println("You have selected veg");
                        flag = false;
                        break;

                    case "3":
                        selectedFood = "Not selected";
                        System.out.println("You haven chosen to starve on the flight");
                        flag = false;
                        break;

                    default:
                        System.out.println("please select 1/2/3");
                        break;
                }
            }

        }
        return selectedFood;
    }

    public String insured(){
        String insuranceOption = inputValidation.validateInsurance();

        if(insuranceOption.equals("N")){
            System.out.println("Are you mad? However, proceeding to next step..");
        }else {
            System.out.println("Good job");
        }
        return insuranceOption;
    }
    //seat selection by viewing plane seat arrangement
    //agree to the terms
    //trip summary
    //price increment after payment
    //seat filling after payment
    public void tripSummary(String flightNo, int seatNo, String foodOption, String insuranceOption){
        int indexFlightNo = Integer.parseInt(flightNo)-1;
        String flightName = flightRepo.getFlightList().get(indexFlightNo).flightName;
        String flightTime= flightRepo.getFlightList().get(indexFlightNo).time;
        String destination = flightRepo.getFlightList().get(indexFlightNo).destination;
        String depart = flightRepo.getFlightList().get(indexFlightNo).depart;
        System.out.println("========== Your Trip summary ==========");
        System.out.println("Flight: "+ flightName);
        System.out.println("seat: "+seatNo);
        System.out.println("Departing Time: "+flightTime);
        System.out.println("Depart: "+depart);
        System.out.println("Destination: "+destination);
        System.out.println("Food :" +foodOption);
        System.out.println("Insured: "+insuranceOption);
        System.out.println("=======================================");
    }



    public void printTicket(String flightNo, int seatNo, String foodOption, String insuranceOption){
        int indexFlightNo = Integer.parseInt(flightNo)-1;
        String flightName = flightRepo.getFlightList().get(indexFlightNo).flightName;
        String flightTime= flightRepo.getFlightList().get(indexFlightNo).time;
        String destination = flightRepo.getFlightList().get(indexFlightNo).destination;
        String depart = flightRepo.getFlightList().get(indexFlightNo).depart;
        System.out.println("++++++++++++++++++++++++++++++++++++");
        System.out.println("*** Your Flight Ticket ***");
        System.out.println("Flight: "+ flightName);
        System.out.println("seat: "+seatNo);
        System.out.println("Departing Time: "+flightTime);
        System.out.println("Depart: "+depart);
        System.out.println("Destination: "+destination);
        System.out.println("Food :" +foodOption);
        System.out.println("Insured: "+insuranceOption);
        System.out.println("Have a safe trip!?");
        System.out.println("Travel Advice: Watch Final Destination 1 to have joyful journey");
        System.out.println("+++++++++++++++++++++++++++++++++++++");
    }

    public void increasePrice(String flightNo){
        int indexFlightNo = Integer.parseInt(flightNo)-1;
        int price = flightRepo.getFlightList().get(indexFlightNo).setPrice();
    }

    public void addToBookedSeatsList(String mail, int seatNo, String flightNo){
        String content="Flight No: "+flightNo+" Seat "+seatNo+" is booked";
        Map<String,Integer> seatHistory=userDb.getBookedSeatsList().get(mail);
        seatHistory.put(flightNo, seatNo);
        userDb.updateBookedSeatHistory(mail, seatHistory);
        userDb.updateBookingHistory(mail, content);
    }

    public void cancelSeatBooking(String mail){
        Map<String, Integer> seatHistory=userDb.getBookedSeatsList().get(mail);
        int cancelSeatNo=0;
        String cancelFlightNo="";
        boolean flag = true;
        String content;
        System.out.println("Seats you booked:");
        System.out.println("-------------------------");
        for (Map.Entry<String ,Integer> seat: seatHistory.entrySet()){
            System.out.println("Flight No: "+seat.getKey()+". Seat No: "+seat.getValue());
            System.out.println("-------------------------");
        }
        while (flag){
            cancelFlightNo= inputValidation.validateFlightNo();
            cancelSeatNo=inputValidation.validateCancelSeat();
            boolean flag1=true;
            try {
                flag1 = seatHistory.get(cancelFlightNo)!=cancelSeatNo;
            } catch (Exception e){
                System.out.println("Currently there are no booked seats");
                flag =false;
                continue;
            }

            if(flag1){
                    System.out.println("You can only cancel the seat you booked before.");
                    continue;
            }else {
                content = "Flight No: "+cancelFlightNo +" Seat: "+cancelSeatNo+" is cancelled";
                seatHistory.remove(cancelFlightNo);
                System.out.println("Your seat: "+cancelSeatNo+" booking is cancelled in Flight No: "+cancelFlightNo);
                seat.updateSeatMap(cancelSeatNo, flightRepo.getSeatMap().get(cancelFlightNo), false);
                userDb.updateBookingHistory(mail,content);
                break;
            }
        }
    }

    public void showBookingHistory(String mail){
        ArrayList<String> contentList= userDb.getBookingHistory().get(mail);
        System.out.println("++++++++++++Your Booking History++++++++++++");
        for(String content:contentList){
            System.out.println(content);
            System.out.println("----------------------------------------------");
        }
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
    }

}
