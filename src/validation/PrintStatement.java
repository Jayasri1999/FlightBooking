package validation;

public class PrintStatement {
    public String tryAgain = "You have entered the wrong input please try again..";
    public String nameError = "Name should not be empty";
    public String pwdError = "Password should contain minimum 5 characters";
    public String accError = "Your account doesn't exist. Please sign up";

    public void printMessage(String str){
        System.out.println("Invalid "+ str + ". Please try again");
    }
}
