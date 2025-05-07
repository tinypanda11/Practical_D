import java.util.*;

class Clock {
    int hours, minutes;

    Clock(int h, int m) {
        hours = h % 24;
        minutes = m % 60;
    }

    int getTotalMinutes() {
        return hours * 60 + minutes;
    }

    void adjustTime(int diff) {
        int total = getTotalMinutes() + diff;
        hours = (total / 60) % 24;
        minutes = total % 60;
    }

    String getTime() {
        return String.format("%02d:%02d", hours, minutes);
    }
}

public class BerkeleySimple {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Clock[] clocks = new Clock[3];

        // Step 1: Input time for 3 clocks
        for (int i = 0; i < 3; i++) {
            System.out.print("Enter time for Clock " + (i + 1) + " (HH MM): ");
            int h = sc.nextInt();
            int m = sc.nextInt();
            clocks[i] = new Clock(h, m);
        }

        // Display initial times
        System.out.println("\nInitial Clock Times:");
        for (int i = 0; i < 3; i++) {
            System.out.println("Clock " + (i + 1) + (i == 0 ? " (master)" : "") + " -> " + clocks[i].getTime());
        }

        // Step 2: Show all times
        System.out.println("\nMaster requests time from all clocks:");
        for (int i = 0; i < 3; i++) {
            System.out.println("Clock " + (i + 1) + " -> " + clocks[i].getTime());
        }

        // Step 3: Calculate average time in minutes
        int total = 0;
        for (Clock c : clocks) total += c.getTotalMinutes();
        int average = total / 3;

        // Step 4: Show differences from master
        System.out.println("\nTime differences from master:");
        int masterTime = clocks[0].getTotalMinutes();
        for (int i = 1; i < 3; i++) {
            int diff = clocks[i].getTotalMinutes() - masterTime;
            System.out.printf("Clock %d: %+d minutes\n", i + 1, diff);
        }

        // Step 5: Adjust all clocks to average
        System.out.println("\nCorrected Clock Times:");
        for (int i = 0; i < 3; i++) {
            int diff = average - clocks[i].getTotalMinutes();
            clocks[i].adjustTime(diff);
            System.out.printf("Clock %d -> %s (%+d min)\n", i + 1, clocks[i].getTime(), diff);
        }

        sc.close();
    }
}
