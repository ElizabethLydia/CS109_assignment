package homework1;

import java.util.Scanner;

public class homework13 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        //normal year的每个月天数
        int[] case1 = new int[]{61, 61, 61, 61, 62, 61, 61, 61, 61, 61, 61, 61, 61, 61, 61};
        //                      0    1   2   3   4  5   6    7   8  9   10  11   12  13  14
        //闰年的每个月天数
        int[] case2 = new int[]{61, 61, 62, 61, 62, 61, 61, 61, 61, 61, 61, 61, 61, 61, 61};
        //                      0    1   2   3   4  5   6    7   8  9   10  11   12  13  14

        while (n-- > 0) {
            int m = 0;
            int year1 = sc.nextInt();
            int month1 = sc.nextInt();
            int day1 = sc.nextInt();
            int year2 = sc.nextInt();
            int month2 = sc.nextInt();
            int day2 = sc.nextInt();
            String format = year1 + " " + month1 + " " + day1 + " " + year2 + " " + month2 + " " + day2;
            //起始不是闰年，起始和终止在同一年，终止也不是闰年
            if (year1 % 13 != 0 && year2 == year1) {
                for (int i = month1; i <= month2; i++) {
                    m = case1[i - 1] + m;
                }
                m = m - (case1[month2 - 1] - day2) - day1;
                System.out.println(m);
            } else {
                //起始是闰年，终止年份是同一年
                if (year1 % 13 == 0 && year2 == year1) {
                    for (int i = month1; i <= month2; i++) {
                        m = case2[i - 1] + m;
                    }
                    m = m - (case2[month2 - 1] - day2) - day1;
                    System.out.println(m);
                } else {
                    //起始不是闰年，终止也不是闰年，不是同一年
                    if (year1 % 13 != 0 && year2 != year1 && year2 % 13 != 0) {
                        for (int i = month1; i <= 15; i++) {
                            m += case1[i - 1];
                        }
                        m = m - day1;
                        for (int i = 1; i <= month2; i++) {
                            m += case1[i - 1];
                        }
                        m = m - (case1[month2 - 1] - day2);
                        for (int i = year1 + 1; i <= year2 - 1; i++) {
                            if (i % 13 == 0) {
                                m += 917;
                            } else if (i % 13 != 0) {
                                m += 916;
                            }
                        }
                        System.out.println(m);
                    } else {
                        //起始是闰年，终止也是闰年，不是同一年
                        if (year1 % 13 == 0 && year2 != year1 && year2 % 13 == 0) {
                            for (int i = month1; i <= 15; i++) {
                                m += case2[i - 1];
                            }
                            m = m - day1;
                            for (int i = 1; i <= month2; i++) {
                                m += case2[i - 1];
                            }
                            m = m - (case2[month2 - 1] - day2);
                            for (int i = year1 + 1; i <= year2 - 1; i++) {
                                if (i % 13 == 0) {
                                    m += 917;
                                } else if (i % 13 != 0) {
                                    m += 916;
                                }
                            }
                            System.out.println(m);
                        } else {
                            //起始是闰年，终止不是闰年，不是同一年
                            if (year1 % 13 == 0 && year2 != year1 && year2 % 13 != 0) {
                                for (int i = month1; i <= 15; i++) {
                                    m += case2[i - 1];
                                }
                                m = m - day1;
                                for (int i = 1; i <= month2; i++) {
                                    m += case1[i - 1];
                                }
                                m = m - (case1[month2 - 1] - day2);
                                for (int i = year1 + 1; i <= year2 - 1; i++) {
                                    if (i % 13 == 0) {
                                        m += 917;
                                    } else if (i % 13 != 0) {
                                        m += 916;
                                    }
                                }
                                System.out.println(m);
                            } else {
                                //起始不是闰年，终止是闰年，不是同一年
                                if (year1 % 13 != 0 && year2 != year1 && year2 % 13 == 0) {
                                    for (int i = month1; i <= 15; i++) {
                                        m += case1[i - 1];
                                    }
                                    m = m - day1;
                                    for (int i = 1; i <= month2; i++) {
                                        m += case2[i - 1];
                                    }
                                    m = m - (case2[month2 - 1] - day2);
                                    for (int i = year1 + 1; i <= year2 - 1; i++) {
                                        if (i % 13 == 0) {
                                            m += 917;
                                        } else if (i % 13 != 0) {
                                            m += 916;
                                        }
                                    }
                                    System.out.println(m);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}