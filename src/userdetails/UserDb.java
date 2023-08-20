package userdetails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserDb {
    private static UserDb userDb = null;

    private UserDb(){
    }
    public static UserDb getInstance(){
        if(userDb == null){
            userDb = new UserDb();
        }
        return userDb;
    }

    private ArrayList<User> userList = new ArrayList<>();

    private Map<String , ArrayList<String > > bookingHistory = new HashMap<>();
    private Map<String , Map<String ,Integer>> bookedSeatsList = new HashMap<>();

    public ArrayList<User> getUserList(){
        return userList;
    }
    public void setUserList(ArrayList<User> userList){

        this.userList = userList;
    }

    public Map<String , Map<String,Integer>> getBookedSeatsList() {

        return bookedSeatsList;
    }
    public void setBookedSeatsList(Map<String , Map<String,Integer>> bookedSeatsList){
        this.bookedSeatsList = bookedSeatsList;
    }

    public void updateBookedSeatHistory(String mail, Map<String ,Integer> seatHistory){
        bookedSeatsList.put(mail, seatHistory);
    }

    public void setBookingHistory(Map<String , ArrayList<Integer>> bookedSeatsList){
        this.bookingHistory = bookingHistory;
    }

    public Map<String , ArrayList<String >> getBookingHistory() {
        return bookingHistory;
    }

    public void updateBookingHistory(String mail, String content){
        ArrayList<String > description = userDb.getBookingHistory().get(mail);
        description.add(content);
    }
    }
