package Model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

public class User  {
    private int userId;
    private String userName;
    private String password;
    private String email;
    private Date birthDate;
    private ArrayList<Rent> rent;
    private ArrayList<Transaction> transactions;
    private double wallet;
    private ArrayList<Item> cart;

    public User(int userId, String userName, String email,  Date birthDate){
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.birthDate = birthDate;
    }

    public User(int userId, String userName, String password, String email, Date birthDate) {
        this(userId, userName, email, birthDate);
        this.password = password;
        this.rent = new ArrayList<>();
    }

    public User(ResultSet res) {
        try {
            this.userId = res.getInt("userID");
            this.userName = res.getString("userName");
            this.email = res.getString("email");
            this.birthDate = res.getDate("birthDate");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
//    public User(String userName, String password, String email, LocalDate birthDate, ArrayList<Rent> rent){
//        this(userName, password, email, birthDate);
//        this.rent = rent;
//    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    public ArrayList<Rent> getRentingItems(){
        return rent;
    }

    public void addRentingItems(Rent rent){
        this.rent.add(rent);
    }

    public void removeRentingItems(Rent rent){
        this.rent.remove(rent);
    }

    public double getWallet() {
        return wallet;
    }

    public void setWallet(double wallet) {
        this.wallet = wallet;
    }

    public void addItemToCart(Item item) {
        cart.add(item);
    }

    public ArrayList<Item> getCart() {
        return cart;
    }


    public void printUserInfo(){
        System.out.println("User ID: " + userId);
        System.out.println("Username: " + userName);
        System.out.println("Email: " + email);
        System.out.println("Birthday: " + birthDate);
        System.out.println();
    }
}

