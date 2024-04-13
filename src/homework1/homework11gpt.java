package homework1;

import java.util.Scanner;

public class homework11gpt {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCases = scanner.nextInt();

        for (int i = 0; i < testCases; i++) {
            int start = scanner.nextInt();
            int end = scanner.nextInt();
            int count = 0;

            for (int j = start; j <= end; j++) {
                if (j % 7 == 0 || String.valueOf(j).contains("7")) {
                    count++;
                }
            }

            System.out.println(count);
        }
    }
}