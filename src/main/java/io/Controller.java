package io;
import factory.Factory;
import logic.Customer;
import service.CustomerService;
import java.util.List;
import java.util.Scanner;

public class Controller {
    private static final CustomerService customerService= new CustomerService();
    private static final View view= new View();

    public void menu() {
        List<Customer> customers = Factory.createCustomers();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            view.showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 ->  findFirstletter(scanner, customers);
                case 2 ->  findByCreditCardRange(scanner, customers);
                case 3 ->  findByDebt(customers);
                case 4 ->  customerService.enterToTextFile(customers);
                case 5 ->  customerService.readFromTextFile();
                case 6 ->  customerService.enterToBinaryFile(customers);
                case 7 ->  customerService.readFromBinaryFile();
                case 8 ->  showSortedCustomers(customers);
                case 9 ->  findCustomerAndAverage(scanner, customers);
                case 10 -> { view.messageExit(); scanner.close(); return;}
                default -> view.messageInvalidChoice();
            }
        }
    }
    private void findFirstletter(Scanner scanner, List<Customer> customers){
        view.messageFirstletter();
        char letter = scanner.next().charAt(0);
        scanner.nextLine();
        List<Customer> nameFiltered = customerService.findCustomersByFirstLetter(customers, letter);
        view.showCustomers(nameFiltered);
    }
    private void findByCreditCardRange(Scanner scanner, List<Customer> customers) {
        view.messageMinCreditCard();
        String min = scanner.nextLine();
        view.messageMaxCreditCard();
        String max = scanner.nextLine();
        List<Customer> cardFilter = customerService.filterByCreditCardRange(customers, min, max);
        view.showCustomers(cardFilter);
    }
    private void findByDebt(List<Customer> customers) {
        List<Customer> debtors = customerService.filterByDebt(customers);
        view.showCustomers(debtors);
    }
    private void showSortedCustomers(List<Customer> customers) {
        List<Customer> sorted = customerService.sortByOutlayAndName(customers);
        view.showSortedCustomers(sorted);
    }

    private void findCustomerAndAverage(Scanner scanner, List<Customer> customers) {
        view.messageEnterFullName();
        String name = scanner.nextLine();
        Customer found = customerService.findCustomerByName(customers, name);
        if (found != null) {
            double avg = customerService.findAveragePurchase(found);
            view.showCustomerAverage(found, avg);
        } else {
            view.messageInvalidChoice();
        }
    }

}
