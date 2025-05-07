import java.rmi.Naming;
import java.util.Scanner;

public class AdditionClient {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter first number: ");
            int a = scanner.nextInt();
            System.out.print("Enter second number: ");
            int b = scanner.nextInt();

            Addition stub = (Addition) Naming.lookup("rmi://localhost/AdditionService");
            int result = stub.add(a, b);
            System.out.println("Sum from remote server: " + result);

            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
