package Service;

import Model.Storage;

import Repository.StorageRepository;

import java.util.List;

public class StorageService {
    private StorageRepository storageRepository;

    public StorageService() {
        this.storageRepository = new StorageRepository();
    }

    public List<Storage> getListStorage() {
        return storageRepository.getListStorage();
    }
    public void listStorage(){
        for (Storage storage: getListStorage()){
            storage.printStorageInfo();
        }
    }

    public void addQuantity(int itemID, int code, int typeID, int itemQuantity) {
        storageRepository.addNewQuantity(itemID, code, typeID, itemQuantity);
    }

    public void addQuantityWithCode(int itemID, int code, int itemQuantity){
        int typeID = 3;
        storageRepository.addNewQuantity(itemID, code, typeID, itemQuantity);
    }

    public void deductQuantity(int itemId, int code, int typeID, int amount){
        storageRepository.addNewQuantity(itemId, code, typeID, -amount);
    }

    public int getItemQuantityByItemId(int itemId) {
        int count = 0;
        for (Storage storage: getListStorage() ) {
            if (storage.getItemId() == itemId) {
                count += storage.getQuantity();
            }
        }
        return count;
    }

    public boolean checkEmptyList(){
        return storageRepository.isEmpty();
    }
}
