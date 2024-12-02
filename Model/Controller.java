package Model;

import java.awt.*;
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

    public void addItem(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Input movie title: ");
        String title = sc.nextLine();
        Category selectCate = null;

        while (selectCate == null) {
            System.out.print("Input the movie category(Default is GENERAL): ");
            String category = sc.nextLine().toUpperCase();
            if (!category.matches("[a-zA-z]+")){
                System.out.println("Invalid type of movie. Please type again");
                continue;
            }
            selectCate = Category.getCateOrDefault(category);
            if (selectCate == Category.GENERAL){
                System.out.println("The type cannot be found. Defaulting to GENERAL");
            }
        }


        item.add(new Item(title, selectCate, true));
    }

    public void listItem(){
        for (Item item: item){
            System.out.println("Title: " + item.getTitle());
            System.out.println("Category: " + item.getCategory());
            if (item.getAvailable()){
                System.out.println("Status: In-stock");
            }
            else{
                System.out.println("Status: Out-of-stock");
            }
        }
    }

    public void searchUser(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the username you want to find: ");
        String userSearch = scanner.nextLine();
        Boolean isUser = false;

        for (User user: this.user) {
            if (userSearch.equals(user.getUserName())){
                isUser = true;
                System.out.println("User ID: " + UserIdCounter);
                System.out.println("Username: " + user.getUserName());
                System.out.println("Email: " + user.getEmail());
                System.out.println("Birthday: " + user.getBirthDate());
                System.out.println("Age: " + user.getAge());
                break;
            }
        }
        if (!isUser){
            System.out.println("The user is not exist");
        }
    }

    public void deleteUser(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the user you want to delete: ");
        String userDelete = sc.nextLine();
        Boolean isUser = false;

        for (User user: this.user) {
            if (userDelete.equals(user.getUserName())){
                isUser = true;
                while (true){
                    System.out.print("Do you wish to continue? (Y/N): ");
                    String choice = sc.nextLine();

                    if (choice.equalsIgnoreCase("Y")){
                        this.user.remove(userDelete);
                        break;
                    }
                    else if (choice.equalsIgnoreCase("N")) {
                        break;
                    }
                    else {
                        System.out.println("Invalid choice");
                    }
                }
            }
        }
        if (!isUser){
            System.out.println("The user is not exist");
        }
    }

    public void deleteItem(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the item you want to delete: ");
        String itemDelete = sc.nextLine();
        Boolean isItem = false;

        for (Item item: this.item) {
            if (itemDelete.equals(item.getTitle())){
                isItem = true;
                while (true){
                    System.out.print("Do you wish to continue? (Y/N): ");
                    String choice = sc.nextLine();

                    if (choice.equalsIgnoreCase("Y")){
                        this.user.remove(itemDelete);
                        break;
                    }
                    else if (choice.equalsIgnoreCase("N")) {
                        break;
                    }
                    else {
                        System.out.println("Invalid choice");
                    }
                }
            }
        }
        if (!isItem){
            System.out.println("The user is not exist");
        }
    }

}

