package backend;

import java.io.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Scanner;

public class User implements UserService, Serializable {
    //Fields
    private String name;
    private String password;
    private Integer age;
    private String number;
    private String email;
    //Creating hashmap in order to store user's email and passwords
    private HashMap<String,String> data = new HashMap<>();
    //Methods


    public User() {
        loadFromFiles();
    }

    @Override
    public void register() {
        Scanner read = new Scanner(System.in);
        System.out.print("Enter your name: ");
        this.name = read.nextLine();

        System.out.print("Create your own password: ");
        this.password = read.nextLine();
        if(!Utils.isValidPassword(this.password))
            throw new RuntimeException("Password is not strong!");


        System.out.print("Your age: ");
        this.age = read.nextInt();

        read.nextLine();

        System.out.print("Enter your phone number: ");
        this.number = read.nextLine();
        if(!Utils.isValidPhoneNum(this.number))
            throw new RuntimeException("Invalid phone number");

        System.out.print("Enter your email: ");
        this.email = read.nextLine();
        if(!Utils.isValidEmail(this.email))
            throw new RuntimeException("Invalid email!");

        data.put(email,password);

        //Writing every information to a file "users.txt"
        saveUserToFile(name,email,password);
    }

    @Override
    public void login() {
        Scanner read = new Scanner(System.in);

        System.out.print("Enter your  email: ");
        String email = read.nextLine();

        System.out.print("Enter your password: ");
        String password = read.nextLine();

        String savedPassword = data.get(email);

        if(savedPassword != null && savedPassword.equals(password)){
            System.out.println("Welcome to the system!");

            while(true){
                System.out.println("----MENU----");
                System.out.println("1.Write message");
                System.out.println("2.Exit");

                System.out.print("Enter your choice: ");
                int choice = read.nextInt();
                read.nextLine();

                switch(choice){
                    case 1 ->{
                        System.out.print("Message: ");
                        String message = read.nextLine();
                        writingMessages(message);
                    }
                    case 2 -> {
                        System.out.println("Exiting from the system! Thank you!");
                        return;
                    }
                    default -> System.out.println("Invalid choice! Please try again!");
                }
            }
        }else{
            System.out.println("Wrong password or email! Please try again!");
        }
    }

    //Utility method in order to store informations into the hashmap everytime
    private void loadFromFiles(){
        File file = new File("Files/users.txt");
        if(!file.exists())
            throw new RuntimeException("File does not exist");

        try(FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader)
        ){
            String line;
            while((line = reader.readLine()) != null){
                String[] data = line.split("\\|");
                if(data.length == 3){
                    String email = data[1];
                    String password = data[2];

                    this.data.put(email,password);
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    //Utility method in order to write to the file
    private void saveUserToFile(String name,String email,String password){
        File file = new File("Files/users.txt");
        if(file.getParentFile() != null){
            file.getParentFile().mkdirs();
        }

        try(FileWriter fileWriter = new FileWriter(file,true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)
        ){
            bufferedWriter.write(name + "|" + email + "|" + password + "|\n");
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    //Utility method to use in the login method
    private void writingMessages(String message){
        File file = new File("Files/messages.txt");

        LocalTime localTime = LocalTime.now();
        String formattedTime = localTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        try(FileWriter fileWriter = new FileWriter(file,true);
            BufferedWriter writer = new BufferedWriter(fileWriter)
        ){
            writer.write(message + "|" + formattedTime + "\n");
        }catch(IOException e){
            e.printStackTrace();
        }
    }


    @Override
    public String toString() {
        return "Username: %s%n Password: %s%n, Email: %s%n%n ".formatted(name,password,email);
    }

    //Getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}