package homework1;

import java.util.Scanner;

public class homeworkgpt13gpt {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCases = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < testCases; i++) {
            String[] dates = scanner.nextLine().split(" ");
            int[] currentDate = parseDate(dates[0]);
            int[] vacationDate = parseDate(dates[1]);

            int days = calculateDays(currentDate, vacationDate);
            System.out.println(days);
        }

        scanner.close();
    }

    private static int[] parseDate(String dateStr) {
        String[] dateParts = dateStr.split(" ");
        int[] date = new int[3];
        for (int i = 0; i < 3; i++) {
            date[i] = Integer.parseInt(dateParts[i]);
        }
        return date;
    }

    private static int calculateDays(int[] currentDate, int[] vacationDate) {
        int days = 0;
        int currentYear = currentDate[0];
        int vacationYear = vacationDate[0];

        while (currentYear < vacationYear) {
            days += isLeapYear(currentYear) ? 917 : 916;
            currentYear++;
        }

        days += calculateDaysInYear(currentDate, vacationDate);

        return days;
    }

    private static boolean isLeapYear(int year) {
        return (year % 13) == 0;
    }

    private static int calculateDaysInYear(int[] currentDate, int[] vacationDate) {
        int days = 0;

        for (int i = currentDate[1]; i <= 15; i++) {
            int daysInMonth = i == 3 && isLeapYear(currentDate[0]) ? 62 : (i == 5 ? 62 : 61);
            if (i == currentDate[1]) {
                days += daysInMonth - currentDate[2] + 1;
            } else {
                days += daysInMonth;
            }
        }

        for (int i = 1; i < vacationDate[1]; i++) {
            int daysInMonth = i == 3 && isLeapYear(vacationDate[0]) ? 62 : (i == 5 ? 62 : 61);
            days += daysInMonth;
        }

        days -= vacationDate[2];

        return days;
    }
}