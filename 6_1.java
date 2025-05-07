import java.util.Scanner;

public class BullySimple {
    static int n;                  // Number of processes
    static boolean[] alive;       // Track alive status
    static int coordinator;       // Current coordinator

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        n = sc.nextInt();
        alive = new boolean[n];

        for (int i = 0; i < n; i++) {
            alive[i] = true; // All processes start as alive
        }

        System.out.print("Enter initial coordinator (0 to " + (n - 1) + "): ");
        coordinator = sc.nextInt();
        System.out.println("Initial Coordinator: Process " + coordinator);

        System.out.print("Enter process to start election: ");
        int starter = sc.nextInt();

        System.out.print("Enter process to crash (-1 for none): ");
        int crash = sc.nextInt();

        if (crash >= 0 && crash < n) {
            alive[crash] = false;
            System.out.println("Process " + crash + " has crashed.");
        }

        // If the current coordinator is alive, no election is needed
        if (alive[coordinator]) {
            System.out.println("Coordinator is still active. No election needed.");
            return;
        }

        // Start election from the starter process
        System.out.println("\nProcess " + starter + " starts election...");
        for (int i = n - 1; i >= 0; i--) {
            if (alive[i]) {
                coordinator = i;
                System.out.println("Process " + coordinator + " becomes new coordinator.");
                break;
            }
        }

        // Notify all alive processes
        for (int i = 0; i < n; i++) {
            if (i != coordinator && alive[i]) {
                System.out.println("Process " + coordinator + " -> Process " + i + " (COORDINATOR)");
            }
        }

        sc.close();
    }
}
