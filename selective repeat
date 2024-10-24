# SELECETIVE REPEAT
import java.util.Random;
import java.util.Scanner;

public class Selective_repeat {

    public static int selectiveRepeatTransmission(int i, int N, int tf) {
        int tt = 0;  // Total frames sent and resent
        boolean[] ack = new boolean[tf + 1];  // Acknowledgment array
        
        Random rand = new Random();

        while (i <= tf) {
            // Sending frames in the window
            for (int k = i; k < Math.min(i + N, tf + 1); k++) {
                if (!ack[k]) {
                    System.out.println("Sending Frame " + k);
                    tt++;  // Count the frame sent
                }
            }

            // Simulating acknowledgments for the sent frames
            for (int k = i; k < Math.min(i + N, tf + 1); k++) {
                if (!ack[k]) {
                    int f = rand.nextInt(2);  // Random acknowledgment: 0 = success, 1 = failure
                    if (f == 0) {
                        System.out.println("Acknowledgment for Frame " + k);
                        ack[k] = true;  // Frame acknowledged
                    } else {
                        System.out.println("Timeout!! Frame Number: " + k + " Not Received");
                    }
                }
            }

            System.out.println();

            // Slide the window forward for acknowledged frames
            while (i <= tf && ack[i]) {
                i++;
            }
        }

        return tt;  // Return total frames sent and resent
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Total number of frames: ");
        int tf = scanner.nextInt();

        System.out.print("Window Size: ");
        int N = scanner.nextInt();

        int i = 1;  // Initial frame index
        int tt = selectiveRepeatTransmission(i, N, tf);  // Run selective repeat transmission

        System.out.println("Total number of frames which were sent and resent: " + tt);
        scanner.close();
    }
}
