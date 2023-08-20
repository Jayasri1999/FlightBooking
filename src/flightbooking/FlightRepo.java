package flightbooking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import flightbooking.Flight;
public class FlightRepo {
    private static FlightRepo flightRepo = null;
    private FlightRepo(){

    }
    public static FlightRepo getInstance(){
        if(flightRepo == null) {
            flightRepo = new FlightRepo();
        }
        return flightRepo;
    }
    Seat seat = new Seat();
    private ArrayList<Flight> flightList = new ArrayList<>();
    private Map<String , String[][]> seatMap = new HashMap<String,String[][]>();

    public ArrayList<Flight> getFlightList(){

        return flightList;
    }

    public void setFlightList(ArrayList<Flight> flightList){
        this.flightList = flightList;
    }

    public Map<String, String[][]> getSeatMap(){
        return seatMap;
    }

    public void setSeatMap(Map<String , String[][]> seatMap){
        this.seatMap = seatMap;
    }


}
