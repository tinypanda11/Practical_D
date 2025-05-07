import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

class RingElection {
    private int numProcesses;
    private int coordinator;
    private boolean[] activeProcesses;

    public RingElection(int numProcesses, int initialCoordinator) {
        this.numProcesses = numProcesses;
        this.activeProcesses = new boolean[numProcesses];

        // Set all processes as active
        for (int i = 0; i < numProcesses; i++) {
            activeProcesses[i] = true;
        }

        // Crash the initial coordinator
        if (initialCoordinator >= 0 && initialCoordinator < numProcesses) {
            coordinator = initialCoordinator;
            activeProcesses[coordinator] = false;
            System.out.println("Initial Coordinator: Process " + coordinator);
            System.out.println("Process " + coordinator + " has crashed.");
        } else {
            coordinator = numProcesses - 1;
            activeProcesses[coordinator] = false;
            System.out.println("Invalid coordinator ID. Defaulting and crashing Process " + coordinator);
        }
    }

    public void startElection(int initiator) {
        if (!activeProcesses[initiator]) {
            System.out.println("Process " + initiator + " is crashed and cannot initiate the election.");
            return;
        }

        System.out.println("\nProcess " + initiator + " is initiating an election...");
        List<Integer> electionPath = new ArrayList<>();
        electionPath.add(initiator);
        System.out.println("Election path: " + electionPath);

        int maxId = initiator;
        int current = (initiator + 1) % numProcesses;

        while (current != initiator) {
            if (activeProcesses[current]) {
                System.out.println("Process " + maxId + " -> Process " + current + " (ELECTION)");
                electionPath.add(current);
                System.out.println("Election path: " + electionPath);
                if (current > maxId) {
                    maxId = current;
                }
            } else {
                System.out.println("Process " + current + " is skipped (CRASHED).");
            }
            current = (current + 1) % numProcesses;
        }

        coordinator = maxId;
        System.out.println("Process " + coordinator + " wins the election and becomes the new coordinator.");
        announceNewCoordinator();
    }

    private void announceNewCoordinator() {
        int current = (coordinator + 1) % numProcesses;
        while (current != coordinator) {
            if (activeProcesses[current]) {
                System.out.println("Process " + coordinator + " -> Process " + current + " (ELECTED)");
            }
            current = (current + 1) % numProcesses;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int numProcesses = scanner.nextInt();

        System.out.print("Enter the initial coordinator process (0 to " + (numProcesses - 1) + "): ");
        int initialCoordinator = scanner.nextInt();

        RingElection ringElection = new RingElection(numProcesses, initialCoordinator);

        System.out.print("Enter the process to start the election: ");
        int initiator = scanner.nextInt();

        if (initiator >= 0 && initiator < numProcesses) {
            ringElection.startElection(initiator);
        } else {
            System.out.println("Invalid initiator process ID.");
        }
    }
}
