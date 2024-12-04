package Service;

import Model.User;
import Util.Reader;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class UserService {
    private ArrayList<User> userList;
    private int UserIdCounter;
    private Reader reader = Reader.getReader();
    public ArrayList<User> getUser() {
        return userList;
    }
    public UserService() {
        this.userList = new ArrayList<>();
        this.UserIdCounter = 1;
    }

//    USER FUNCTIONS
    public void addUser(String userName, String password, String email, String birthdayStr) throws Exception {
        if (checkExist(userName, "username")) {
            throw new Exception("Username is already existed. Please choose another one.");
        }
        if (checkExist(email, "email")){
            throw new Exception("Email is already existed. Please choose another one.");
        }
        userList.add(new User(userName, password,email, LocalDate.parse(birthdayStr)));
    }

    public void addUser(String userName, String password, String email, LocalDate birthday) throws Exception {
        if (checkExist(userName, "username")) {
            throw new Exception("Username is already existed. Please choose another one.");
        }
        if (checkExist(email, "email")){
            throw new Exception("Email is already existed. Please choose another one.");
        }
        userList.add(new User(userName, password,email, birthday));
    }

    public boolean checkExist(String inputNeedCheck, String fieldCase) {
        boolean returnValue = false;
        switch (fieldCase) {
            case "username":
                returnValue = this.userList.stream().anyMatch(u -> inputNeedCheck.equals(u.getUserName()));
                break;
            case "email":
                returnValue = this.userList.stream().anyMatch(u -> inputNeedCheck.equals(u.getEmail()));
                break;
        }
        return returnValue;
    }


//  VIEWING ALL USER
//    public void listUserInfo() {
//        if (this.user.isEmpty()) {
//            System.out.println("Users list is empty");
//            return;
//        }
//        System.out.println("Users list:");
//        for (User user : this.user) {
//            System.out.println();
//            user.printUserInfo();
//        }
//    }
//
//// VIEW A SPECIFIC USER
//    public void searchUser(){
//        Scanner scanner = new Scanner(System.in);
//        boolean isUser = false;
//        if (isUser){
//            System.out.println("Users list is empty");
//            return;
//        }
//        System.out.print("Enter the username you want to find: ");
//        String userSearch = scanner.nextLine();
//        System.out.println("Searching user....");
//        System.out.println();
//
//        for (User user: this.user) {
//            if (userSearch.equals(user.getUserName())){
//                isUser = true;
//                user.printUserInfo();
//                break;
//            }
//        }
//        if (!isUser){
//            System.out.println("The user is not exist");
//        }
//    }
//
//// DELETE A USER
//    public void deleteUser(){
//        Scanner sc = new Scanner(System.in);
//        boolean isUser = false;
//        User userRemoved = null;
//        if (isUser){
//            System.out.println("Users list is empty");
//            return;
//        }
//        System.out.println("Deleting User");
//        System.out.print("Enter the user you want to delete: ");
//        String userDelete = sc.nextLine();
//
//        for (User user: this.user) {
//            if (userDelete.equals(user.getUserName())){
//                isUser = true;
//                userRemoved = user;
//                while (true){
//                    System.out.print("Do you wish to continue? (Y/N): ");
//                    String choice = sc.nextLine();
//
//                    if (choice.equalsIgnoreCase("Y")){
//                        this.user.remove(userRemoved);
//                        System.out.println("User removed successfully");
//                        for (User u: this.user){
//                            if (u.getUserId() > userRemoved.getUserId()){
//                                u.setUserId(u.getUserId() - 1);
//                            }
//                        }
//                        break;
//                    }
//                    else if (choice.equalsIgnoreCase("N")) {
//                        System.out.println("Canceling user deletion");
//                        break;
//                    }
//                    else {
//                        System.out.println("Invalid choice");
//                    }
//                }
//                break;
//            }
//        }
//        if (!isUser){
//            System.out.println("The user is not exist");
//        }
//    }
//
////EDIT USER INFORMATION
//    public void editUserInfo(){
//        Scanner scanner = new Scanner(System.in);
//        boolean isUser = false;
//        System.out.println("Editing user information....");
//        System.out.print("Enter the user you want to edit: ");
//        String userInfoEdit = scanner.nextLine();
//        User user = null;
//
//        try {
//            user = this.user.stream().filter(u -> u.getUserName().equals(userInfoEdit)).findFirst().orElseThrow();
//            // TODO: Replace all the for loops using the line above.
//
//        } catch (Exception e) {
//            System.out.println("The user is not exist");
//            return;
//        }
//        while (true){
//                System.out.println("What would you want to change: ");
//                System.out.println("1. Username     2. Email    3. Date of birth    4. Cancel change");
//                System.out.print("Input your choice: ");
//                String choice = scanner.nextLine().trim();
//
//                if (choice.isEmpty()){
//                    System.out.println("Choice cannot be empty.");
//                    continue;
//                }
//
//                if (!choice.matches("\\d+")){
//                    System.out.println("Choice cannot be words. Please choose again");
//                    continue;
//                }
//                int option = Integer.parseInt(choice);
//
//                switch (option) {
//                    case 1 -> {
//                        if (userInfoEdit.equalsIgnoreCase(user.getUserName())) {
//                            System.out.print("Enter new username: ");
//                            String newUserName = scanner.nextLine();
//                            if (newUserName.equalsIgnoreCase(user.getUserName())){
//                                System.out.println("The username is already exist.");
//                                continue;
//                            }
//                            user.setUserName(newUserName);
//                            System.out.println("Username changed successfully");
//                            return;
//                        }
//                    }
//                    case 2 -> {
//                        while (true) {
//                            System.out.print("Enter the new email: ");
//                            String newEmail = scanner.nextLine();
//                            if (newEmail.equalsIgnoreCase(user.getEmail())){
//                                System.out.println("The email is already exist.");
//                                continue;
//                            }
//                            user.setEmail(newEmail);
//                            break;
//                        }
//                        System.out.println("Email changed successfully");
//                        return;
//                        }
//                    case 3 -> {
//                        System.out.println("Enter the new date of birth (yyyy-mm-dd): ");
//                        String birthdayStr = scanner.nextLine();
//                        LocalDate birthDate = LocalDate.parse(birthdayStr);
//                        user.setBirthDate(birthDate);
//                        System.out.println("Date of birth changed successfully.");
//                        return;
//                    }
//
//                    case 4 -> System.out.println("Cancelling change");
//                    default -> System.out.println("Invalid choice. Please choose again");
//                }
//             }
//        }
}

