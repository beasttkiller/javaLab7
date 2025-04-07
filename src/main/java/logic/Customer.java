package logic;
import java.io.Serializable;
public class Customer implements Serializable {
    private final int id;
    private String fullName;
    private String creditCardNumber;
    private double balance;
    private final int numberPur;
    private double numberOutlay;

    public Customer(int id, String fullName, String creditCardNumber, double balance, int numberPur, double numberOutlay) {
        this.id = id;
        this.fullName = fullName;
        this.creditCardNumber = creditCardNumber;
        this.balance = balance;
        this.numberPur = numberPur;
        this.numberOutlay = numberOutlay;
    }
    public int getId() { return id; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getCreditCardNumber() { return creditCardNumber; }
    public void setCreditCardNumber(String creditCardNumber) { this.creditCardNumber = creditCardNumber; }
    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
    public int getNumberPur(){ return numberPur; }
    public double getNumberOutlay(){ return numberOutlay; }
    public void setNumberOutlay(double numberOutlay) { this.numberOutlay = numberOutlay; }

    @Override
    public String toString() {
        return "Customer " + id +
                ", ПІБ: " + fullName + '\'' +
                ", Номер картки: " + creditCardNumber + '\'' +
                ", Баланс: " + balance + '\'' +
                ", Кількість покупок: " + numberPur + '\'' +
                ", Загальна сума витрат: " + numberOutlay +
                '}';
    }
}
