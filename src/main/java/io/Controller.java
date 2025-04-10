package io;
import logic.Customer;
import service.CustomerMap;
import service.CustomerService;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Controller {
    private static final CustomerService customerService= new CustomerService();
    private static final View view= new View();
    private CustomerMap customerMap = new CustomerMap();

    public void menu() {
        List<Customer> customers = CustomerService.returnCustomers();
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
                case 10 -> printCustomersGroupedByCityAndName();
                case 11 -> printTotalOutlayByCity();
                case 12 -> addCustomer(scanner, customers);
                case 13 -> removeCustomer(scanner, customers);
                case 14 -> view.showCustomers(customers);
                case 15 -> { view.messageExit(); scanner.close(); return;}
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
        view.messageEnterId();
        int id = Integer.parseInt(scanner.nextLine());
        Customer found = customerService.findCustomerById(customers, id);
        if (found != null) {
            double avg = customerService.findAveragePurchase(found);
            view.showCustomerAverage(found, avg);
        } else {
            view.messageInvalidChoice();
        }
    }
    private void addCustomer(Scanner scanner, List<Customer> customers)  {
        view.messageAddCustomer();
        view.messageid();
        int id = scanner.nextInt();
        scanner.nextLine();
        view.messageName();
        String fullName = scanner.nextLine();
        view.messageCity();
        String city = scanner.nextLine();
        view.messageCard();
        String creditCard = scanner.nextLine();
        view.messageBalance();
        double balance = scanner.nextDouble();
        scanner.nextLine();
        view.messageNumPur();
        int numberPur = scanner.nextInt();
        scanner.nextLine();
        view.messageOutlay();
        double numberOutlay = scanner.nextDouble();
        scanner.nextLine();

        customers.add(new Customer(id, fullName, city, creditCard, balance, numberPur, numberOutlay));
        view.messageCustomerAdded();
    }

    private void removeCustomer(Scanner scanner, List<Customer> customers) {
        view.messageRemoveCustomer();
        int id = scanner.nextInt();
        scanner.nextLine();
        boolean removed = customers.removeIf(c -> c.getId() == id);
        if (removed) {
            view.messageCustomerRemoved();
        } else {
            view.messageCustomerNotFound();
        }
    }
    public void printCustomersGroupedByCityAndName() {
        Map<String, List<Customer>> map = customerMap.sortByCityAndName();
        for (String city : map.keySet()) {
            System.out.println("Місто: " + city);
            List<Customer> customers = map.get(city);
            for (Customer customer : customers) {
                System.out.println("  " + customer);
            }
        }
    }
    public void printTotalOutlayByCity() {
        Map<String, Double> map = customerMap.sortByAllOutlay();
        for (String city : map.keySet()) {
            System.out.printf("Місто: %-15s | Сумарні витрати: %.2f\n", city, map.get(city));
        }
    }
}
