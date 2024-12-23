package Service;

import Model.Rent;
import Repository.RentRepository;
import java.util.ArrayList;

public class RentingService {
    private RentRepository rentRepository;

    public RentingService(){
        this.rentRepository = new RentRepository();
    }

    public ArrayList<Rent> getRentList(){
        return rentRepository.getListRent();
    }

    public void rentItem(int itemID, int userID, int rentQuantity, long numberOfDayRent){
        int rentingStatusID = 1;
        rentRepository.rentMovie(itemID, userID, rentQuantity, rentingStatusID, numberOfDayRent);
    }

    public void rentList(){
        for (Rent rent: getRentList()){
            rent.printRentList();
        }
    }

    public boolean checkEmptyList() {
        return rentRepository.isEmpty();
    }

    public boolean changeStatus(int rentId, int storageQuantity){
        for (Rent rent: getRentList()) {
            if (rent.getRentId() == rentId) {
                if (rent.getRentQuantity() <= storageQuantity){
                    rentRepository.updateRentingStatus(rentId, 2);
                    return true;
                }
            }
        }
        return false;
    }

    public void changeCompleted(int rentId) throws Exception {
        for (Rent rent: getRentList()){
            if (rentId == rent.getRentId()){
                if (rent.getRentingStatusID() == 2){
                    rentRepository.updateRentingStatus(rentId, 3);
                } else {
                    throw new Exception("Invalid status");
                }
            }
        }
    }

    public int getItemIdByRentId(int rentId) throws Exception {
        int itemId;
        for (Rent rent : getRentList()) {
            if (rentId == rent.getRentId()){
                if (rent.getRentingStatusID() == 1) {
                    itemId = rent.getItemID();
                    return itemId;
                }
                throw new Exception("Invalid status");
            }
        }
        throw new Exception("Cannot found Id");
    }
}
