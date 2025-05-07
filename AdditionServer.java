import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class AdditionServer {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(2000); // start RMI registry
            AdditionImpl obj = new AdditionImpl();
            Naming.rebind("AdditionService", obj);
            System.out.println("AdditionService is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
