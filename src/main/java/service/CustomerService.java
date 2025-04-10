package service;
import factory.Factory;
import interfaces.CustomerRepository;
import io.View;
import logic.Customer;
import repositories.CustomerRepositoryBinaryImpl;
import repositories.CustomerRepositoryTxtImpl;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.List;

public class CustomerService {
    private static List<Customer> customers= new ArrayList<>();
    public static List<Customer> returnCustomers() {
        if(customers.isEmpty()){
            customers = Factory.createCustomers();
        }
        return customers;
    }
    public List<Customer> findCustomersByFirstLetter(List<Customer> customers, char letter) {
        List<Customer> result = new ArrayList<>();
        for (Customer customer : customers) {
            if (!customer.getFullName().isEmpty() && customer.getFullName().charAt(0) == letter) {
                result.add(customer);
            }
        }
        return result;
    }
    public List<Customer> filterByCreditCardRange(List<Customer> customers, String min, String max) {
        List<Customer> result = new ArrayList<>();
        for (Customer customer : customers) {
            String card = customer.getCreditCardNumber();
            if (card.compareTo(min) >= 0 && card.compareTo(max) <= 0) {
                result.add(customer);
            }
        }
        return result;
    }
    public List<Customer> filterByDebt(List<Customer> customers) {
        List<Customer> result = new ArrayList<>();
        for (Customer customer : customers) {
            if (customer.getBalance() < 0) {
                result.add(customer);
            }
        }
        return result;
    }

    private static final View view= new View();
    private static final CustomerRepository txtrepository = new CustomerRepositoryTxtImpl();
    private static final CustomerRepository binaryrepository = new CustomerRepositoryBinaryImpl();
    private static final File txtFile = new File("customers.txt");
    private static final File binaryFile = new File("customers.bin");

    public void enterToTextFile(List<Customer> customers){
        txtrepository.outputList(customers, txtFile);
    }

    public void readFromTextFile() {
        List<Customer> customers = txtrepository.readList(txtFile);
        view.showCustomers(customers);
    }

    public void enterToBinaryFile(List<Customer> customers){
        binaryrepository.outputList(customers , binaryFile);
    }

    public void readFromBinaryFile() {
        List<Customer> customers = binaryrepository.readList(binaryFile);
        view.showCustomers(customers);
    }

    public List<Customer> sortByOutlayAndName(List<Customer> customers) {
        List<Customer> sortedList = new ArrayList<>(customers);
        sortedList.sort(new Comparator<Customer>() {
            @Override
            public int compare(Customer c1, Customer c2) {
                int outlayCompare = Double.compare(c2.getNumberOutlay(), c1.getNumberOutlay()); // зворотне порівняння
                if (outlayCompare != 0) {
                    return outlayCompare;
                }
                return c1.getFullName().compareTo(c2.getFullName());
            }
        });
        return sortedList;
    }
    public Customer findCustomerById(List<Customer> customers, int id) {
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                return customer;
            }
        }
        return null;
    }
    public double findAveragePurchase(Customer customer) {
        return customer.getNumberPur() > 0 ? customer.getNumberOutlay() / customer.getNumberPur() : 0;
    }
}

