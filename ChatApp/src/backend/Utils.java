package backend;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    ///Checking the phone number
    public static boolean isValidPhoneNum(String input){
        if (input == null)
            throw new RuntimeException("Input can not be null");

        Pattern pattern = Pattern.compile("^(\\+998)((71|88|9[01349])(\\d{7}))$");
        Matcher matcher = pattern.matcher(input);

        return matcher.matches();
    }


    ///Checking the email
    public static boolean isValidEmail(String input){
        if (input == null)
            throw new RuntimeException("Input can not be null");

        Pattern pattern = Pattern.compile("^(\\w+)@(gmail.com)$");
        Matcher matcher = pattern.matcher(input);

        return matcher.matches();
    }

    ///Checking the password
    public static boolean isValidPassword(String input){
        if (input == null)
            throw new RuntimeException("Input can not be null");

        Pattern pattern = Pattern.compile("(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[+-/*<>]).{8,20}");
        Matcher matcher = pattern.matcher(input);

        return matcher.matches();
    }


}
