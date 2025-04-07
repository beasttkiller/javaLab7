package interfaces;
import logic.Customer;
import java.io.File;
import java.util.List;

public interface Repository<T> {
    void outputList(List<Customer> list, File file);
    void outputList(List<Customer> list, String fileName);
    List<T> readList(File file);
    List<T> readList(String fileName);
}