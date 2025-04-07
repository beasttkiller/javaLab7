package repositories;
import interfaces.CustomerRepository;
import logic.Customer;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
public class CustomerRepositoryTxtImpl implements CustomerRepository {
    @Override
    public void outputList(List<Customer> t, File file) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
            for(Customer customer : t){
                writer.write(customer.getId()+","+customer.getFullName()+','+customer.getCity()+','+customer.getCreditCardNumber()+','+customer.getBalance()+
                        ','+customer.getNumberPur() + ','+customer.getNumberOutlay());
                writer.newLine();
            }
            System.out.println("Data saved to text file.");
        } catch (IOException e) {
            System.err.println("Error writing to text file.");
        }
    }

    @Override
    public void outputList(List<Customer> t, String fileName) {
        outputList(t , new File(fileName) );
    }
    @Override
    public List<Customer> readList(File file) {
        List<Customer> customers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 7) {
                    customers.add(new Customer(
                            Integer.parseInt(parts[0]), parts[1],parts[2], parts[3],
                            Double.parseDouble(parts[4]), Integer.parseInt(parts[5]), Double.parseDouble(parts[6])));
                } else {
                    System.err.println("Invalid line format: " + line);
                }
            }
            System.out.println("Data loaded from text file.");
        } catch (IOException e) {
            System.err.println("Error reading text file: " + e.getMessage());
        }
        return List.of(customers.toArray(new Customer[0]));
    }

    @Override
    public List<Customer> readList(String fileName) {
        return readList(new File(fileName));
    }

}
