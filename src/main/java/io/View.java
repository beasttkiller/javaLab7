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
        System.out.println("10. Exit");
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
    public void messageEnterFullName() { System.out.print("Enter the buyer's full name: ");}
    public void showCustomerAverage(Customer customer, double average) {
        System.out.println("Знайдено покупця: " + customer);
        System.out.printf("Середня вартість покупки: %.2f\n", average);
    }
    public void showSortedCustomers(List<Customer> customers) { System.out.println("Список покупців за спаданням витрат:"); showCustomers(customers);}
}
