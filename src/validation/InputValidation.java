package validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidation {
    GetInput in=new GetInput();
    PrintStatement printStatement=new PrintStatement();
    boolean flag=true;
    String str="";

    //Name validation
    public String validateName(){
        while (flag){
            System.out.print("name: ");
            str = in.getNormalString();
            if (str.isEmpty()) {
                printStatement.printMessage("Name");
                continue;
            }else {
                break;
            }
        }
        return str;
    }

    //Mail id validation
    public String validateMail(){
        String regex="^[\\w.+\\-]+@gmail\\.com$";
        while (flag) {
            System.out.print("Mail Id: ");
            str=in.getNormalString();
            Pattern p =Pattern.compile(regex);
            Matcher m = p.matcher(str);
            if (!m.matches()) {
                printStatement.printMessage("Mail Id");
                continue;
            }else {
                break;
            }
        }
        return str;
    }


    //Password validation
    public String validatePassword(){
        while (flag) {
            System.out.print("password: ");
            str = in.getNormalString();
            if (str.length() < 5) {
                printStatement.printMessage("Password with less than 5 characters");
                continue;
            }else {
                break;
            }
        }
        return str;
    }

    //Phone number validation
    public String validatePhone(){
        while(flag) {
            System.out.print("Phone Number: ");
            str = in.getLongString();
            if (str.length() != 10) {
                printStatement.printMessage("Phone Number");
                continue;
            }else {
                break;
            }
        }
        return str;
    }

    public String validateBooking(){
        System.out.println("Book Flight? (Y/N)");
        return validatYorN();
    }

    public String validatYorN(){
        while (flag) {
            str = in.getNormalString();
            if(!str.equals("Y") && !str.equals("N")){
                printStatement.printMessage("input. You should enter 'Y' or 'N'");
                continue;
            }else {
                break;
            }
        }
        return str;
    }

    public int validateSeatNo(){
        int num=0;
        while (flag){
            System.out.println("Please pick a seat");
            str = in.getIntegerString();
            num= Integer.parseInt(str);
            if (!(num>=11 && num <=16) && !(num>=21 && num <=26) && !(num>=31 && num <=36) && !(num>=41 && num <=46) && !(num>=51 && num <=56) && !(num>=61 && num <=66) && !(num>=71 && num <=76) && !(num>=81 && num <=86) && !(num>=91 && num <=96)){
                printStatement.printMessage("input. You should enter valid seat number");
                continue;
            }else {
                break;
            }
        }
        return num;
    }

    public String validateFlightNo(){
        while(flag) {
            System.out.println("Please select a flight from available flights");
            str = in.getIntegerString();
            //hardcoded
            if (!str.equals("1") && !str.equals("2")) {
                printStatement.printMessage("Flight No");
                continue;
            }else {
                break;
            }
        }
        return str;
    }

    public String validateFood(){
        System.out.println("Do you want to have food onboard? (Y/N)");
        return validatYorN();
    }

    public String validateInsurance(){
        System.out.println("Do you want to get insured? (Y/N)");
        return validatYorN();
    }

    public String validatePayment(){
        System.out.println("Proceed to pay? (Y/N)");
        return validatYorN();
    }

    public int validateCancelSeat(){
        System.out.println("Please enter the seatNo which you want to cancel: ");
        String seatNo = in.getIntegerString();
        return Integer.parseInt(seatNo);
    }

    public String validateCancelFlightNo(){
        System.out.println("Please enter the flightNo which you want to cancel: ");
        String flightNo = in.getIntegerString();
        return flightNo;
    }
}
