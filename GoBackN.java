import java.util.Random;
import java.util.Scanner;

public class GoBackN {
    public static int transmission(int i, int N, int tf) {
        int tt = 0;
        Random random = new Random();

        while (i <= tf) {
            int z = 0;

            for (int k = i; k < Math.min(i + N, tf + 1); k++) {
                System.out.println("Sending Frame " + k);
                tt++;
            }

            for (int k = i; k < Math.min(i + N, tf + 1); k++) {
                int f = random.nextInt(2);
                if (f == 0) {
                    System.out.println("Acknowledgment for Frame " + k);
                    z++;
                } else {
                    System.out.println("Timeout!! Frame Number : " + k + " Not Received");
                    System.out.println("Retransmitting Window");
                    break;
                }
            }

            System.out.println();
            i += z;
        }

        return tt;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Total number of frames: ");
        int tf = scanner.nextInt();

        System.out.print("Window Size: ");
        int N = scanner.nextInt();

        int i = 1;
        int tt = transmission(i, N, tf);
        System.out.println("Total number of frames which were sent and resent are: " + tt);

        scanner.close();
    }
}
