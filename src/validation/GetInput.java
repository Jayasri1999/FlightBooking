package validation;

import validation.PrintStatement;

import java.util.Scanner;

public class GetInput {
    PrintStatement printStatement = new PrintStatement();
    Scanner sc=new Scanner(System.in);
    String str="";
    public String getNormalString(){
        boolean str_flag=true;
        while (str_flag) {
            try {
                str = sc.nextLine();
                str_flag = false;
            } catch (Exception e) {
                System.out.println(printStatement.tryAgain);
                str_flag =true;
            }
        }
        return str;
    }

    public String getLongString(){
        boolean long_flag=true;
        long num;
        while (long_flag){
            try {
                str = sc.nextLine();
                num = Long.parseLong(str);
                long_flag=false;
            } catch (Exception e) {
                System.out.println(printStatement.tryAgain);
                long_flag=true;
            }
        }
        return str;
    }

    public String getIntegerString(){
        boolean int_flag=true;
        int num;
        while (int_flag){
            try {
                str = sc.nextLine();
                num = Integer.parseInt(str);
                int_flag = false;
            } catch (Exception e){
                System.out.println(printStatement.tryAgain);
                int_flag = true;
            }
        }
        return str;
    }

    public String getBooleanString(){
        boolean boolean_flag = true;
        boolean bool;
        while (boolean_flag){
            try {
                str = sc.nextLine();
                bool = Boolean.parseBoolean(str);
                boolean_flag = false;
            }catch (Exception e){
                System.out.println(printStatement.tryAgain);
                boolean_flag = true;
            }
        }
        return str;
    }

}
