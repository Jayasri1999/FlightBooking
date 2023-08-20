package driver;

import entry.EntryPage;
import validation.GetInput;
import validation.InputValidation;
import validation.PrintStatement;
import flightbooking.*;

import java.util.Scanner;

public class MainDriver {
    GetInput getInput = new GetInput();
    InputValidation inputValidation = new InputValidation();
    PrintStatement printStatement = new PrintStatement();
    EntryPage entryPage = new EntryPage();
    FlightService flightService = new FlightService();
    Scanner sc=new Scanner(System.in);
    public void welcome(){
        System.out.println("Welcome to Flight booking site!!");
        //init.run();
        String name, password, mail, phoneNumber,number;
        boolean flag=true, num_flag=true,mail_flag=true,name_flag=true,pwd_flag=true;

        long long_phoneNumber;
        while (flag) {
            System.out.println("1: Login\n2: Signup\n3: Exit");
            number=getInput.getIntegerString();
            switch (number) {
                case "1":
                    entryPage.login();
                    break;

                case "2":
                    entryPage.signup();
                    break;

                case "3":
                    flag =false;
                    break;

                default:
                    System.out.println("please select 1/2/3");
                    break;
            }

        }
    }

}
