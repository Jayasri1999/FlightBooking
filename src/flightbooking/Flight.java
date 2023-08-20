package flightbooking;
public class Flight {
    public String id, flightName, depart, destination, time;

    public int price;
    public Flight(String  id, String flightName, String depart, String destination, String time, int price){
        this.id=id;
        this.flightName = flightName;
        this.depart = depart;
        this.destination = destination;
        this.time = time;
        this.price = price;
    }

    public int setPrice(){
        price = price + (int) (price *0.03);
        return price;
    }


}
