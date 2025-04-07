package repositories;

import interfaces.CustomerRepository;
import logic.Customer;

import java.io.*;
import java.util.List;

public class CustomerRepositoryBinaryImpl implements CustomerRepository {
    @Override
    public void outputList(List<Customer> t, File file) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            outputStream.writeObject(t);
            System.out.println("Data saved to binary file.");
        } catch (IOException e) {
            System.err.println("Error writing to binary file. " + e.getMessage());
        }
    }

    @Override
    public void outputList(List<Customer> t, String fileName) {
        outputList(t, new File(fileName));
    }

    @Override
    public List<Customer> readList(File file) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Customer>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error reading binary file.");
        }
        return List.of(new Customer[0]);
    }

    @Override
    public List<Customer> readList(String fileName) {
        return readList(new File(fileName));
    }
}
