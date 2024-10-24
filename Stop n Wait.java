//# STOP N WAIT
import java.util.Random;
import java.util.Scanner;

public class StopAndWait {

    static class Sender {
        int packetNo;
        boolean sent;
        boolean acknowledged;

        Sender(int packetNo) {
            this.packetNo = packetNo;
            this.sent = false;
            this.acknowledged = false;
        }
    }

    static class Receiver {
        int acknowledgementNo;
        boolean received;
        boolean acknowledgementSent;

        Receiver(int acknowledgementNo) {
            this.acknowledgementNo = acknowledgementNo;
            this.received = false;
            this.acknowledgementSent = false;
        }
    }

    static boolean simulateSuccessOrFailure() {
        Random random = new Random();
        return random.nextBoolean();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of frames: ");
        int n = scanner.nextInt();

        Sender[] senders = new Sender[n];
        Receiver[] receivers = new Receiver[n];

        for (int i = 0; i < n; i++) {
            senders[i] = new Sender(i);
            receivers[i] = new Receiver(i + 1);
        }

        for (int i = 0; i < n; i++) {
            while (true) {
                System.out.println("\nNext Transmission");
                System.out.println("Sending PacketNo " + i + " from Sender");
                senders[i].sent = true;

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                if (simulateSuccessOrFailure()) {
                    receivers[i].received = true;
                    System.out.println("Data PacketNo " + i + " successfully received.");
                    receivers[i].acknowledgementSent = true;
                    System.out.println("Sending Acknowledgement No " + (i + 1) + " to Sender");

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }

                    if (simulateSuccessOrFailure()) {
                        senders[i].acknowledged = true;
                        System.out.println("Acknowledgement for PacketNo " + i + " Received");
                        break;
                    } else {
                        senders[i].acknowledged = false;
                        System.out.println("Acknowledgement for PacketNo " + i + " lost.");
                        System.out.println("Process ended due to acknowledgment loss.");
                        return;
                    }
                } else {
                    receivers[i].received = false;
                    System.out.println("Data PacketNo " + i + " lost in transmission.");
                    continue;
                }
            }
        }

        scanner.close();
    }
}
