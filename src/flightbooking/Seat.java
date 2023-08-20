package flightbooking;

import jdk.internal.util.xml.impl.Input;
import userdetails.UserDb;
import validation.*;

import java.util.ArrayList;
import java.util.Map;


public class Seat {
    InputValidation inputValidation = new InputValidation();
    UserDb userDb = UserDb.getInstance();
    //FlightRepo flightRepo = new FlightRepo();
    int removeSeatNo=0;
    String reset = "\u001B[0m";
    String red = "\u001B[31m";
    String green = "\u001B[32m";
    String yellow = "\u001B[33m";
    public String[][] setInitialSeats(){
        String[][] seats= new String[10][7];
        for(int i =1; i<=9; i++) {
            for (int j = 1; j <= 6; j++) {
                seats[i][j] = "A";
            }
        }
        return seats;
    }


    public int  pickSeat(String[][] seats){
        int seatNo=0,i,j;
        boolean flag = true;
        printSeatMap(seats);
        while (flag){
            seatNo = inputValidation.validateSeatNo();
            i = seatNo/10;
            j = seatNo%10;
            if(seats[i][j].equals("NA")){
                System.out.println(seatNo+ " is already occupied. Kindly pick some other seat");
                continue;
            }else {
                break;
            }
        }
        return seatNo;
    }

    public String[][]  updateSeatMap(int seatNo, String[][] seats, boolean flag){
        int i = seatNo/10,j = seatNo%10;
        if(flag){
            seats[i][j]="NA";
        }else {
            //removeFromSeatMap()
            seats[i][j] = "A";
        }

        return seats;
    }

    public void printSeatMap(String[][] seats){
        System.out.println(yellow+"__________________________"+reset);
        System.out.println(yellow+ "---Business Class Starts---"+reset);
        for(int i =1; i<=9; i++){
            for (int j=1;j<=6;j++){
                if(!seats[i][j].equals("NA")){
                    System.out.print(green+seats[i][j]+Integer.toString(i)+Integer.toString(j)+" "+reset);
                }else{
                    System.out.print(red+seats[i][j]+Integer.toString(i)+Integer.toString(j)+" "+reset);
                }

                if(j==3){
                    System.out.print("   ");
                }
            }
            if (i==3){
                System.out.println();
                System.out.println(yellow+"---Business Class Ends---"+reset);
                System.out.print(yellow+"---Economy Class Starts---"+reset);
            }else if(i==9){
                System.out.println();
                System.out.println(yellow+"---Economy Class Ends---"+reset);
                System.out.print(yellow+"__________________________"+reset);
            }
            System.out.println();
        }
    }

    public boolean isEconomy(int seatNo){
        if(seatNo/10<=3){
            return true;
        }else {
            return false;
        }
    }
    public boolean isCornerseat(int seatNo){
        seatNo = seatNo%10;
        if(seatNo!=2 && seatNo!=5){
            return true;
        }else {
            return false;
        }
    }



}
