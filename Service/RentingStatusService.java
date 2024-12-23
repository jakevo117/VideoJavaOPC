package Service;

import Repository.RentingStatusRepository;
import java.util.Map;

public class RentingStatusService {
    private RentingStatusRepository rentingStatusRepository;
    public RentingStatusService() {
        this.rentingStatusRepository = new RentingStatusRepository();
    }

    public Map<Integer, String> getRentStatusList() {
        return rentingStatusRepository.getRentingStatus();
    }

    public void listRentingStatus() {
        Map<Integer, String> categories = getRentStatusList();
        for (Map.Entry<Integer, String> entry : categories.entrySet()) {
            System.out.println("Category ID: " + entry.getKey() + ", Category Name: " + entry.getValue());
        }
    }
}
