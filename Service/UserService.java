package Service;

import Model.User;
import Repository.UserRepository;
import java.util.ArrayList;
import java.sql.Date;

public class UserService {
    private UserRepository userRepository;

    public UserService (){
        this.userRepository = new UserRepository();
    }
    public ArrayList<User> getUserList() {

        return userRepository.getListUser();
    }

    public void addUser(String userName, String password, String email, Date birthday) throws Exception {
        if (checkExist(userName, "username")) {
            throw new Exception("Username is already existed. Please choose another one.");
        }
        if (checkExist(email, "email")){
            throw new Exception("Email is already existed. Please choose another one.");
        }

        userRepository.addNewUser(userName, password, email, birthday);
    }

    public void listUserInfo() {
        ArrayList<User> userList = userRepository.getListUser();
        for (User user: userList){
            user.printUserInfo();
        }
    }

    public User searchUser(int userFind) {
        User user = userRepository.getUserById(userFind);
        return user;
    }

    public boolean checkUserId(int userId){
        boolean isExist = userRepository.getUserById(userId) != null;
        return isExist;
    }

//// DELETE A USER
    public void deleteUser(User userToDelete){
        userRepository.removeUserById(userToDelete);
    }

//EDIT USER INFORMATION
    public void editUserInfo(User userToEdit, String fieldCase, String valueToEdit)  {
        userRepository.EditUserInfoDB(userToEdit, fieldCase, valueToEdit);
    }

    public boolean checkExist(String inputNeedCheck, String fieldCase) {
        boolean returnValue = false;
        switch (fieldCase) {
            case "username":
                returnValue = this.getUserList().stream().anyMatch(u -> inputNeedCheck.equals(u.getUserName()));
                break;
            case "email":
                returnValue = this.getUserList().stream().anyMatch(u -> inputNeedCheck.equals(u.getEmail()));
                break;
        }
        return returnValue;
    }

    public boolean checkEmpty(){
        return userRepository.isEmpty();
    }
//
}

