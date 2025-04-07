package service;
import interfaces.CustomerRepository;
import io.View;
import logic.Customer;
import repositories.CustomerRepositoryBinaryImpl;
import repositories.CustomerRepositoryTxtImpl;
import java.io.File;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.List;

public class CustomerService {
    public List<Customer> findCustomersByFirstLetter(List<Customer> customers, char letter) {
        return customers.stream()
                .filter(c -> c.getFullName().charAt(0) == letter)
                .collect(Collectors.toList());
    }
    public List<Customer> filterByCreditCardRange(List<Customer> customers, String min, String max) {
        return customers.stream()
                .filter(c -> c.getCreditCardNumber().compareTo(min) >= 0 &&
                        c.getCreditCardNumber().compareTo(max) <= 0)
                .collect(Collectors.toList());
    }

    public List<Customer> filterByDebt(List<Customer> customers) {
        return customers.stream()
                .filter(c -> c.getBalance() < 0)
                .collect(Collectors.toList());
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
        return customers.stream()
                .sorted(Comparator.comparingDouble(Customer::getNumberOutlay).reversed()
                        .thenComparing(Customer::getFullName))
                .collect(Collectors.toList());
    }
    public Customer findCustomerByName(List<Customer> customers, String name) {
        return customers.stream()
                .filter(c -> c.getFullName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
    public double findAveragePurchase(Customer customer) {
        return customer.getNumberPur() > 0 ? customer.getNumberOutlay() / customer.getNumberPur() : 0;
    }
}

