import java.util.Scanner;

public class crc {

    // Function to perform the division operation (similar to binary XOR)
    public static int[] divide(int[] dividend, int[] divisor) {
        int n = divisor.length;
        int m = dividend.length;
        int[] temp = dividend.clone();

        for (int i = 0; i <= m - n; i++) {
            if (temp[i] == 1) {
                for (int j = 0; j < n; j++) {
                    temp[i + j] ^= divisor[j];
                }
            }
        }

        int[] remainder = new int[n - 1];
        System.arraycopy(temp, m - n + 1, remainder, 0, n - 1);

        return remainder;
    }

    // Function to check for errors by calculating the remainder
    public static boolean checkError(int[] received, int[] divisor) {
        int[] remainder = divide(received, divisor);
        for (int bit : remainder) {
            if (bit == 1) {
                return true; // Error found if any bit in the remainder is 1
            }
        }
        return false; // No error
    }

    // Sender function to generate the data with CRC appended
    public static Object[] sender() {
        int[] data = {1, 0, 0, 0, 1, 0, 0};
        int[] divisor = {1, 1, 0, 1, 0};
        System.out.println("Original Data: ");
        for (int d : data) {
            System.out.print(d);
        }
        System.out.println();
        System.out.println("Divisor: ");
        for (int d : divisor) {
            System.out.print(d);
        }
        System.out.println();

        // Append zeros to the data
        int[] newData = new int[data.length + divisor.length - 1];
        System.arraycopy(data, 0, newData, 0, data.length);

        // Get remainder from division
        int[] remainder = divide(newData, divisor);

        // Add the remainder to the original data
        int[] code = new int[data.length + remainder.length];
        System.arraycopy(data, 0, code, 0, data.length);
        System.arraycopy(remainder, 0, code, data.length, remainder.length);

        System.out.println("Data sent with CRC appended: ");
        for (int c : code) {
            System.out.print(c);
        }
        System.out.println();
        System.out.println("CRC remainder: ");
        for (int r : remainder) {
            System.out.print(r);
        }
        System.out.println();

        return new Object[]{code, divisor};
    }

    // Receiver function to check for errors in received data
    public static void receiver(int[] sentData, int[] divisor) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the received data with CRC appended: ");
        String input = scanner.nextLine();
        int[] receivedData = new int[input.length()];
        for (int i = 0; i < input.length(); i++) {
            receivedData[i] = Character.getNumericValue(input.charAt(i));
        }

        if (checkError(receivedData, divisor)) {
            System.out.println("Errors found in the received data.");
        } else {
            System.out.println("No errors found in the received data.");
        }
    }

    public static void main(String[] args) {
        System.out.println("\nSender Side");
        Object[] senderOutput = sender();
        int[] sentData = (int[]) senderOutput[0];
        int[] divisor = (int[]) senderOutput[1];

        System.out.println("\nReceiver Side");
        receiver(sentData, divisor);
    }
}
