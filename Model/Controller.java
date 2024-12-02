package Model;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {
    private List<User> user;
    private List<Item> item;
    private List<Rent> rent;
    private List<Transaction> transactions;
    private int UserIdCounter;

    public Controller() {
        this.user = new ArrayList<>();
        this.item = new ArrayList<>();
        this.rent = new ArrayList<>();
        this.transactions = new ArrayList<>();
        this.UserIdCounter = 0;
    }

    public void addUserInfo(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input your username: ");
        String userName = scanner.nextLine();
        System.out.print("Input your password: ");
        String password = scanner.nextLine();
        System.out.print("Input your email: ");
        String email = scanner.nextLine();
        System.out.print("Input your date of birth(yyyy-mm-dd): ");
        String birthdayStr = scanner.nextLine();
        LocalDate birthDate = LocalDate.parse(birthdayStr);
        System.out.print("Input your age: ");
        int age = scanner.nextInt();

        user.add(new User(userName, password, email, birthDate, age));
        UserIdCounter++;
        System.out.println("User ID: " + UserIdCounter + ", " + "Username: " + userName + ", " + "Password: " + password + ", " + "Email: " + email + ", " +"Birthday: " + birthDate + ", " +"Age: "+ age);
    }

    public void listUserInfo(){
        for (User user: user){
            System.out.println("User ID: " + UserIdCounter);
            System.out.println("Username: " + user.getUserName());
            System.out.println("Email: " + user.getEmail());
            System.out.println("Birthday: " + user.getBirthDate());
            System.out.println("Age: " + user.getAge());
        }
    }
}

