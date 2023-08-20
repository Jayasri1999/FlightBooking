package userdetails;

import java.util.ArrayList;
import java.util.Map;

import validation.*;

public class UserService {
    UserDb userDb = UserDb.getInstance();
    InputValidation inputValidation = new InputValidation();


    public boolean userExists(String mail, String password){
        for(User u: userDb.getUserList()){
            if(u.equals(mail, password)){
                return true;
            }
        }
        return false;
    }

    public boolean userExists(String mail){
        for (User u: userDb.getUserList()){
            if(u.equals(mail)){
                return true;
            }
        }
        return false;
    }




}
