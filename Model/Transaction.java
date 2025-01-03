package Model;

import java.time.LocalDate;

public class Transaction {
    private int transactionId;
    private int code;
    private Double amount;
    private LocalDate paidDate;

    public Transaction(Double amount, LocalDate paidDate) {
//        this.rent = rent;
        this.amount = amount;
        this.paidDate = paidDate;
    }

//    public Rent getRent() {
//        return rent;
//    }
//
//    public void setRent(Rent rent) {
//        this.rent = rent;
//    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDate getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(LocalDate paidDate) {
        this.paidDate = paidDate;
    }
}
