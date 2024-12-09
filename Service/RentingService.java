package Service;

import Model.Item;
import Model.Rent;
import Model.User;

import java.time.LocalDate;
import java.util.ArrayList;

public class RentingService {
    private ArrayList<Rent> rentList;
    private int RentIdCounter;

    public RentingService(){
        this.rentList = new ArrayList<>();
        this.RentIdCounter = 1;
    }

//    public void rentingMovie(String movieToBeRent, int numberOfRent){
//        double amountMoneyToRent = item.getPrice() * numberOfRent;
//
//        if (item.getTitle().equalsIgnoreCase(movieToBeRent)){
//            if (user.getWallet() >= amountMoneyToRent && itemInStorage.getQuantity() >= numberOfRent) {
//                deductQuantity(numberOfRent);
//            }
//        }
//    }

    public Rent rentItem(int userId, Item rentMovie, int numberOfCopies, long rentingDay){
        Rent movieRent = new Rent(userId, rentMovie, numberOfCopies, rentingDay);
        rentList.add(movieRent);
        this.RentIdCounter++;
        return movieRent;
    }

    public void rentList(){
        for (Rent rent: rentList){
            rent.printRentList();
        }
    }

    public boolean checkEmptyList() {
        return rentList.isEmpty();
    }

//    public void deductQuantity(int numberOfRent){
//        itemInStorage.setQuantity(itemInStorage.getQuantity() - numberOfRent);
//    }
}
