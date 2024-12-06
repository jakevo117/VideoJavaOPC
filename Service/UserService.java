package Service;

import Model.User;
import Util.Reader;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class UserService {
    private ArrayList<User> userList;
    private int UserIdCounter;
    public ArrayList<User> getUser() {
        return userList;
    }
    public UserService() {
        this.userList = new ArrayList<>();
        this.UserIdCounter = 1;
    }

//    USER FUNCTIONS

    public void addUser(String userName, String password, String email, LocalDate birthday) throws Exception {
        if (checkExist(userName, "username")) {
            throw new Exception("Username is already existed. Please choose another one.");
        }
        if (checkExist(email, "email")){
            throw new Exception("Email is already existed. Please choose another one.");
        }

        userList.add(new User(this.UserIdCounter, userName, password,email, birthday));
        this.UserIdCounter++;
    }

    public void listUserInfo() {
        for (User user: userList){
            user.printUserInfo();
        }
    }

//// VIEW A SPECIFIC USER
    public User searchUser(String userFind) throws Exception {
        User user = null;
        for (User userSearch : userList) {
            if (userFind.equals(userSearch.getUserName())) {
                user = userSearch;
            }
        }
        return user;
    }

//// DELETE A USER
    public void deleteUser(User userToDelete){
        userList.remove(userToDelete);
    }

//EDIT USER INFORMATION
    public void editUserInfo(User userToEdit) throws Exception {
        int index = userList.indexOf(userToEdit);
        userList.set(index, userToEdit);
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

    public boolean checkEmpty(){
        return userList.isEmpty();
    }
}

