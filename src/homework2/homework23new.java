package homework2;

import java.util.Scanner;

public class homework23new {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 输入放置气球的数量
        int NumberOfBalloons = sc.nextInt();

        // 输入操场的大小
        int m = sc.nextInt(); // 行数
        int n = sc.nextInt(); // 列数

        // 输入每个点的灯笼放置情况
        int[][] layout = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                layout[i][j] = sc.nextInt();
            }
        }

        int total = 0;
        // 判断是否可以放气球
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (layout[i][j] == 0) { // 当前位置没有灯笼
                    if (m > 2 && i > 0 && layout[i - 1][j] == 0 && i < m - 1 && layout[i + 1][j] == 0) {
                        total++; // 可以放置气球
                        layout[i - 1][j] = 1;
                        layout[i + 1][j] = 1;
                    }else if (m>2 && i == 0 && layout[1][j] == 0){
                        total++;
                        layout[0][j] = 1;
                    }else if (m>2 && i == m -1 && layout[m - 2][j] == 0){
                        total ++;
                        layout[m - 1][j] = 1;
                    }
                }
            }
        }

        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                System.out.printf("%d ",layout[i][j]);
            }
            System.out.println();
        }

        for (int j = 0; j < n; j++) {
            if (m == 1 && layout[0][j] == 0) {
                total++;
            } else {
                if (m == 2 && layout[0][j] == 0 && layout[1][j] == 0) {
                    total++;
                }
            }
        }

        // 输出结果
        if (m == 0) {
            System.out.println("False");
        } else {
            if (total >= NumberOfBalloons) {
                System.out.println("True");
            } else {
                System.out.println("False");
            }
        }
    }
}
