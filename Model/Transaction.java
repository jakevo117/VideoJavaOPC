package Model;

public class Transaction {
    private Rent rent;
    private Double amount;
    private Boolean isPaid;

    public Transaction(){

    }

    public Transaction(Rent rent, Double amount, Boolean isPaid) {
        this.rent = rent;
        this.amount = amount;
        this.isPaid = false;
    }

    public Rent getRent() {
        return rent;
    }

    public void setRent(Rent rent) {
        this.rent = rent;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Boolean getPaid() {
        return isPaid;
    }

    public void Paid(Boolean paid) {
        this.isPaid = true;
    }
}
