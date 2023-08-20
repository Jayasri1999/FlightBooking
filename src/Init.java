import flightbooking.Seat;
import userdetails.User;
import userdetails.UserService;
import flightbooking.FlightRepo;
import flightbooking.Flight;
import userdetails.UserDb;
import flightbooking.FlightService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Init {
    private UserDb userDb = UserDb.getInstance();
    private FlightRepo flightRepo = FlightRepo.getInstance();
    private Seat seat = new Seat();
    Init(){
        userDb.getUserList().add(new User("jaya","jayasri","j@gmail.com","1234567890"));
        userDb.getUserList().add(new User("sethu","jayasri","s@gmail.com","1234567890"));
        userDb.getBookedSeatsList().put("j@gmail.com",new HashMap<>());
        userDb.getBookedSeatsList().put("s@gmail.com", new HashMap<>());
        userDb.getBookingHistory().put("j@gmail.com",new ArrayList<>());
        userDb.getBookingHistory().put("s@gmail.com",new ArrayList<>());
        flightRepo.getFlightList().add(new Flight("1","Air India","Detroit","Delhi", "20:30",700));
        flightRepo.getFlightList().add(new Flight("2","Ethihad Airways","Detroit","Delhi", "22:30",800));
        flightRepo.getSeatMap().put("1", seat.setInitialSeats());
        flightRepo.getSeatMap().put("2", seat.setInitialSeats());
    }
    public void run(){
        System.out.println("in init");
    }

}
