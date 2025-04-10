package io;
import logic.Customer;
import java.util.List;

public class View {
    public void showCustomers(List<Customer> customers){
        if (customers.isEmpty()) {
            System.out.println("Немає покупців для відображення.");
        } else {
            customers.forEach(System.out::println);
        }
    }
    public void showMenu() {
        System.out.println("Select an option:");
        System.out.println("1. Filter customers by name prefix");
        System.out.println("2. Filter customers by credit card range");
        System.out.println("3. Filter customers with negative balance");
        System.out.println("4. Save to text file");
        System.out.println("5. Read from text file");
        System.out.println("6. Save to binary file");
        System.out.println("7. Read from binary file");
        System.out.println("8. Display a list of buyers in descending order of spending amount (if equal – by full name)");
        System.out.println("9. Check the buyer by name and display their average purchase price");
        System.out.println("10. For each city of registration, a list of buyers from that city in alphabetical order by name");
        System.out.println("11. For each city of registration, the total amount of spending by all buyers");
        System.out.println("12. Add a new customer");
        System.out.println("13. Remove a customer by id");
        System.out.println("14. Show all customers");
        System.out.println("15. Exit");
        System.out.print("Enter your choice: ");
    }

    public void messageFirstletter() {
        System.out.print("Enter name firstLetter: ");
    }
    public void messageMinCreditCard() {
        System.out.print("Enter minimum credit card number: ");
    }
    public void messageMaxCreditCard() {
        System.out.print("Enter maximum credit card number: ");
    }
    public void messageExit() {
        System.out.println("Exiting...");
    }
    public void messageInvalidChoice() {
        System.out.println("Invalid choice, please try again.");
    }
    public void messageEnterId() { System.out.print("Enter the customer`s id: ");}
    public void showCustomerAverage(Customer customer, double average) {
        System.out.println("Знайдено покупця: " + customer);
        System.out.printf("Середня вартість покупки: %.2f\n", average);
    }
    public void showSortedCustomers(List<Customer> customers) { System.out.println("Список покупців за спаданням витрат:"); showCustomers(customers);}
    public void messageAddCustomer() {System.out.println("Введіть дані нового покупця:");}
    public void messageRemoveCustomer() {System.out.print("Введіть Id покупця, якого потрібно видалити: ");}
    public void messageCustomerAdded() {System.out.println("Покупця додано.");}
    public void messageCustomerRemoved() {System.out.println("Покупця видалено.");}
    public void messageCustomerNotFound() {System.out.println("Покупця не знайдено.");}


    public void messageid(){System.out.print("Id: ");}
    public void messageName(){System.out.print("ПІБ: ");}
    public void messageCity(){System.out.print("Місто реєстрації: ");}
    public void messageCard(){System.out.print("Номер кредитної картки: ");}
    public void messageBalance(){System.out.print("Баланс: ");}
    public void messageNumPur (){System.out.print("Кількість покупок: ");}
    public void messageOutlay(){System.out.print("Загальна сума витрат: ");}
}
