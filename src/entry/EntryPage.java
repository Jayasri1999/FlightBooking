package entry;

import flightbooking.FlightService;
import flightbooking.Seat;
import userdetails.*;
import userdetails.UserService;
import validation.GetInput;
import validation.InputValidation;
import validation.PrintStatement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class EntryPage {
    InputValidation inputValidation=new InputValidation();
    GetInput getInput = new GetInput();
    PrintStatement printStatements = new PrintStatement();
    UserService userService = new UserService();
    UserDb userDb = UserDb.getInstance();
    FlightService flightService = new FlightService();
    Seat seat = new Seat();


    public void login() {
        String mail,password, number;
        boolean flag = true;
        System.out.println("Login Page");
        mail = inputValidation.validateMail();
        password = inputValidation.validatePassword();
        if (userService.userExists(mail, password)) {
            System.out.println("User account " + mail + " logged in");
            while (flag) {
                System.out.println("1: Book Ticket \n2: Cancel \n3: Booking History \n4: Explore \n5: Logout");
                number = getInput.getIntegerString();
                switch (number){
                    case "1":
                        flightService.bookFlight(mail);
                        break;
                    case "2":
                        flightService.cancelSeatBooking(mail);
                        break;
                    case "3":
                        flightService.showBookingHistory(mail);
                        break;
                    case "4":
                        System.out.println("Get a job. There is nothing to explore");
                        break;
                    case "5":
                        System.out.println("Logged Out");
                        flag = false;
                        break;
                    default:
                        System.out.println("please select 1/2/3");
                        break;
                }

            }
        } else {
            System.out.println(printStatements.accError);
        }
    }

    public void signup(){
        String mail,password,name,phoneNumber;
        System.out.println("Signup page");
        mail = inputValidation.validateMail();
        if (userService.userExists(mail)){
            System.out.println("User account mail already exists");
            return;
        }
        name=inputValidation.validateName();
        phoneNumber=inputValidation.validatePhone();
        password = inputValidation.validatePassword();
        userDb.getUserList().add(new User(name, password, mail, phoneNumber));
        userDb.getBookedSeatsList().put(mail,new HashMap<>());
        userDb.getBookingHistory().put(mail,new ArrayList<>());
        System.out.println("User " + name + " signed up!");
    }

}
