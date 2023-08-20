package userdetails;

public class User {
    String name,password,mail,phoneNumber;
    public User(String name, String password, String mail, String phoneNumber){
        this.name = name;
        this.password=password;
        this.mail=mail;
        this.phoneNumber=phoneNumber;
    }

    public boolean equals(String mail, String password){
        if(mail.equals(this.mail) && password.equals(this.password)){
            return true;
        }
        return false;
    }

    public boolean equals(String mail){
        if(mail.equals(this.mail)){
            return  true;
        }
        return  false;
    }
}
