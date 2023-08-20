import driver.*;

public class Main {
    static private Init init;
    static {
        init= new Init();
    }


    public static void main(String[] args) {
        init.run();
        MainDriver mainDriver = new MainDriver();
        mainDriver.welcome();


    }
}