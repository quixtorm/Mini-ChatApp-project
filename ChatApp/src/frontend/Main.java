package frontend;

import backend.User;
import backend.UserService;

import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;


public class Main {
    //Creating Logger
    static {
        try (InputStream is = Main.class
                .getClassLoader()
                .getResourceAsStream("logging.properties")) {
            LogManager.getLogManager().readConfiguration(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static final Logger logger = Logger.getLogger(Main.class.getSimpleName());

    public static void main(String[] args) {
        UserService userService = new User();
        Scanner scanner  = new Scanner(System.in);

        try {
            while (true) {
                menu();
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1 -> userService.register();
                    case 2 -> userService.login();
                    case 3 -> {
                        System.out.println("Program ended successfully! Thank you!");
                        return;
                    }
                    default -> System.out.println("Invalid choice! Please try again!");
                }
            }
        }catch(InputMismatchException e){
            logger.warning("User entered invalid value!");
            scanner.nextLine();
        }catch(Exception e){
            logger.log(Level.SEVERE,"Unexpected error: ",e);
        }
    }

    //Utility methods
    public static void menu(){
        String[] menu = {"---------------MENU---------------","1.Register","2.Login","3.Exit"};
        for (String s : menu) {
            System.out.println(s);
        }
    }



}
