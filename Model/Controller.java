package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
    private ArrayList<User> user;
    private ArrayList<Item> availableItems;
    private int UserIdCounter;

    public Controller() {
        this.user = new ArrayList<>();
        availableItems = new ArrayList<>();
        this.UserIdCounter = 1;
    }

    public void addUser(){
        Scanner scanner = new Scanner(System.in);
        String userName;
        String email;
        System.out.println();
        System.out.println("Adding user");
        while (true){
            System.out.print("Input username: ");
            userName = scanner.nextLine();

            boolean usernameExist = false;
            for (User user: this.user) {
                if (user.getUserName().equalsIgnoreCase(userName)) {
                    usernameExist = true;
                    break;
                }
            }
            if (usernameExist){
                System.out.println("Username is already exist. Please choose another");
            } else {
                break;
            }
        }
        System.out.print("Input password: ");
        String password = scanner.nextLine();

        while (true){
            System.out.print("Input email: ");
            email = scanner.nextLine();

            boolean emailExist = false;
            for (User user: this.user) {
                if (user.getEmail().equalsIgnoreCase(email)) {
                    emailExist = true;
                    break;
                }
            }
            if (emailExist){
                System.out.println("Email is already exist. Please choose another");
            } else {
                break;
            }
        }


        System.out.print("Input date of birth(yyyy-mm-dd): ");
        String birthdayStr = scanner.nextLine();
        LocalDate birthDate = LocalDate.parse(birthdayStr);

        User toAdd = new User(userName, password, email, birthDate);
        toAdd.setUserId(UserIdCounter);
        user.add(toAdd);
        System.out.println("User is created successfully");
        System.out.println();
        UserIdCounter++;
    }

    public void listUserInfo() {
        if (this.user.isEmpty()) {
            System.out.println("Users list is empty");
            return;
        }
        System.out.println("Users list:");
        for (User user : this.user) {
            System.out.println();
            user.printUserInfo();
        }
    }

    public void searchUser(){
        Scanner scanner = new Scanner(System.in);
        boolean isUser = false;
        if (isUser){
            System.out.println("Users list is empty");
            return;
        }
        System.out.print("Enter the username you want to find: ");
        String userSearch = scanner.nextLine();
        System.out.println("Searching user....");
        System.out.println();

        for (User user: this.user) {
            if (userSearch.equals(user.getUserName())){
                isUser = true;
                user.printUserInfo();
                break;
            }
        }
        if (!isUser){
            System.out.println("The user is not exist");
        }
    }

    public void deleteUser(){
        Scanner sc = new Scanner(System.in);
        boolean isUser = false;
        User userRemoved = null;
        if (isUser){
            System.out.println("Users list is empty");
            return;
        }
        System.out.println("Deleting User");
        System.out.print("Enter the user you want to delete: ");
        String userDelete = sc.nextLine();

        for (User user: this.user) {
            if (userDelete.equals(user.getUserName())){
                isUser = true;
                userRemoved = user;
                while (true){
                    System.out.print("Do you wish to continue? (Y/N): ");
                    String choice = sc.nextLine();

                    if (choice.equalsIgnoreCase("Y")){
                        this.user.remove(userRemoved);
                        System.out.println("User removed successfully");
                        for (User u: this.user){
                            if (u.getUserId() > userRemoved.getUserId()){
                                u.setUserId(u.getUserId() - 1);
                            }
                        }
                        break;
                    }
                    else if (choice.equalsIgnoreCase("N")) {
                        System.out.println("Canceling user deletion");
                        break;
                    }
                    else {
                        System.out.println("Invalid choice");
                    }
                }
                break;
            }
        }
        if (!isUser){
            System.out.println("The user is not exist");
        }
    }

    public void editUserInfo(){
        Scanner scanner = new Scanner(System.in);
        boolean isUser = false;
        System.out.println("Editing user information....");
        System.out.print("Enter the user you want to edit: ");
        String userInfoEdit = scanner.nextLine();

        for (User user: this.user) {
            if (userInfoEdit.equals(user.getUserName())){
                isUser = true;
                while (true){
                    System.out.println("What would you want to change: ");
                    System.out.println("1. Username     2. Email    3. Date of birth    4. Cancel change");
                    System.out.print("Input your choice: ");
                    String choice = scanner.nextLine().trim();

                    if (choice.isEmpty()){
                        System.out.println("Choice cannot be empty.");
                        continue;
                    }

                    if (!choice.matches("\\d+")){
                        System.out.println("Choice cannot be words. Please choose again");
                        continue;
                    }
                    int option = Integer.parseInt(choice);

                    switch (option) {
                        case 1 -> {
                            if (userInfoEdit.equalsIgnoreCase(user.getUserName())) {
                                System.out.print("Enter new username: ");
                                String newUserName = scanner.nextLine();
                                user.setUserName(newUserName);
                                System.out.println("Username changed successfully");
                                return;
                            }
                        }
                        case 2 -> {
                            System.out.print("Enter the new email: ");
                            String newEmail = scanner.nextLine();
                            user.setEmail(newEmail);
                            System.out.println("Email changed successfully");
                            return;
                            }

                        case 4 -> System.out.println("Cancelling change");
                        default -> System.out.println("Invalid choice. Please choose again");
                    }
                 }
            }
        }

        if (!isUser){
            System.out.println("The user is not exist");
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
        System.out.print("Input price: ");
        double price = sc.nextDouble();

        availableItems.add(new Item(title, selectCate, true, price));
    }

    public void listItem(){
        for (Item item: availableItems){
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



    public void deleteItem(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the item you want to delete: ");
        String itemDelete = sc.nextLine();
        Boolean isItem = false;

        for (Item item: this.availableItems) {
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

