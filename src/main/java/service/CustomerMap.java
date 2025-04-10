package service;
import logic.Customer;
import java.util.*;

public class CustomerMap {

    public Map<String, List<Customer>> sortByCityAndName() {
        List<Customer> customers = CustomerService.returnCustomers();

        customers.sort(new Comparator<Customer>() {
            @Override
            public int compare(Customer c1, Customer c2) {
                return c1.getFullName().compareTo(c2.getFullName());
            }
        });

        Map<String, List<Customer>> result = new HashMap<>();
        for (Customer customer : customers) {
            String city = customer.getCity();
            if (!result.containsKey(city)) {
                result.put(city, new ArrayList<>());
            }
            result.get(city).add(customer);
        }

        return result;
    }

    public Map<String, Double> sortByAllOutlay() {
        List<Customer> customers = CustomerService.returnCustomers();
        Map<String, Double> result = new HashMap<>();

        for (Customer customer : customers) {
            String city = customer.getCity();
            double outlay = customer.getNumberOutlay();

            if (result.containsKey(city)) {
                result.put(city, result.get(city) + outlay);
            } else {
                result.put(city, outlay);
            }
        }
        return result;
    }
}
