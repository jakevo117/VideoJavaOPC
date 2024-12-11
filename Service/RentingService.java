package Service;

import Model.Item;
import Model.Rent;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RentingService {
    private ArrayList<Rent> rentList;
    private int RentIdCounter;

    public RentingService(){
        this.rentList = new ArrayList<>();
        this.RentIdCounter = 1;
    }

    public Rent rentItem(int userId, Item rentMovie, int numberOfCopies, long rentingDay){
        Rent movieRent = new Rent(RentIdCounter, userId, rentMovie, numberOfCopies, rentingDay);
        rentList.add(movieRent);
        RentIdCounter++;
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

    public LocalDateTime getDateAndTimeRent(){
        LocalDateTime dateTime = null;
        for (Rent rent: rentList){
            dateTime =  rent.getDateTimeRent();
        }
        return dateTime;
    }

    public boolean changeStatus(int rentId, int storageQuantity){
        for (Rent rent: rentList) {
            if (rentId == rent.getRentId()) {
                if (rent.getRentQuantity() <= storageQuantity){
                    rent.setStatus(true);
                    return true;
                }
            }
        }
        return false;
    }

    public int getItemIdByRentId(int rentId) throws Exception {
        int itemId;
        for (Rent rent : rentList) {
            if (rentId == rent.getRentId()){
                itemId = rent.getItem().getItemId();
                return itemId;
            }
        }
        throw new Exception("Cannot found Id");
    }

    public ArrayList<Rent> getRentList(){
        return this.rentList;
    }
}
