import java.io.*;
import java.util.*;

public class SimpleTokenRing {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Step 1: Get number of processes
        System.out.print("Enter number of processes in the ring: ");
        int n = sc.nextInt();
        sc.nextLine(); // clear buffer

        int token = 0; // Start with process 0

        while (true) {
            for (int i = 0; i < n; i++) {
                token = i;
                System.out.println("\nToken is with Process " + token);
                System.out.print("Process " + token + ", do you want to enter the critical section? (yes/no): ");
                String reply = sc.nextLine().toLowerCase();

                if (reply.equals("yes")) {
                    // Critical section
                    System.out.print("Enter text to write to file: ");
                    String text = sc.nextLine();

                    try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt", true))) {
                        writer.write("Process " + token + ": " + text);
                        writer.newLine();
                        System.out.println("Written to file: Process " + token + ": " + text);
                    } catch (IOException e) {
                        System.out.println("Error writing to file.");
                    }

                    // Simulate work
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }

                    System.out.println("Process " + token + " exited the Critical Section.");
                } else {
                    System.out.println("Process " + token + " passed the token to the next process.");
                }
            }
        }
    }
}
