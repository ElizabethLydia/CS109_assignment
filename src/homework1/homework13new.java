package homework1;
//错的，懒得修改了
import java.util.Scanner;

public class homework13new {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        while (n-- > 0) {
            int m = 0;
            int year1 = sc.nextInt();
            int month1 = sc.nextInt();
            int day1 = sc.nextInt();
            int year2 = sc.nextInt();
            int month2 = sc.nextInt();
            int day2 = sc.nextInt();
            boolean isLeapYear1 = year1 % 13 == 0;
            boolean isLeapYear2 = year2 % 13 == 0;

            for (int i = 1; i < month1; i++) {
                switch (i) {
                    case 1:
                    case 2:
                    case 4:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                    case 11:
                    case 12:
                    case 13:
                    case 14:
                        m += 61;
                        break;
                    case 3:
                        m += isLeapYear1 ? 62 : 61;
                        break;
                    case 5:
                        m += 62;
                        break;
                }
            }
            m += day1;
            m = isLeapYear1 ? 917 - m : 916 - m;
            if (year1 != year2) {

                for (int j = 1; j < month2; j++) {
                    switch (j) {
                        case 1:
                        case 2:
                        case 4:
                        case 6:
                        case 7:
                        case 8:
                        case 9:
                        case 10:
                        case 11:
                        case 12:
                        case 13:
                        case 14:
                            m += 61;
                            break;
                        case 3:
                            m += isLeapYear2 ? 62 : 61;
                            break;
                        case 5:
                            m += 62;
                            break;
                    }
                }
                m += day2;

                for (int r = year1 + 1; r < year2; r++) {
                    if (r % 13 == 0) {
                        m += 917;
                    } else {
                        m += 916;
                    }
                }
                System.out.println(m);
            }else {
                for (int j = 1; j < month2; j++) {
                    switch (j) {
                        case 1:
                        case 2:
                        case 4:
                        case 6:
                        case 7:
                        case 8:
                        case 9:
                        case 10:
                        case 11:
                        case 12:
                        case 13:
                        case 14:
                            m += 61;
                            break;
                        case 3:
                            m += isLeapYear2 ? 62 : 61;
                            break;
                        case 5:
                            m += 62;
                            break;
                    }
                }
                m += day2;
                m -= isLeapYear1 ? 917 : 916;
                System.out.println(m);
            }
        }
    }
}