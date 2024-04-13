package homework2;

import java.util.Scanner;

/*
把操场分成了m*n的小方块，每个小方块可以用一个二维的坐标表示
注意第一行或者列的索引是0
在一个block上面可以放置balloons, lanterns, or nothing at all.
一个block上面放置了lanterns就用1表示，没有放置就用0表示。
具体要求：
    放灯笼的地方不能放气球，放灯笼的上下都不能放气球
    放气球的地方不能放气球，放气球的上下不能放气球
 */
public class homework23 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //1.输入放置气球的数量。
        int NumberOfBalloons = sc.nextInt();

        //2,输入操场的大小。
        //首先输入操作的rows 行数
        int m = sc.nextInt();
        //0 - （m-1）
        //首先输入操作的lines 列数
        int n = sc.nextInt();
        //0 - (n -1)

        //3.输入每个点的灯笼放置情况。
        int[][] layout = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                layout[i][j] = sc.nextInt();
            }
        }

        int total = 0;
        //4.判断是否可以放气球。
        for (int i = 0; i < n; i++) {//每一列分别进行遍历
            //判断每一列的lanterns位置
            if (m > 2) {
                for (int j = 0; j < m; j++) {//每一竖列着从上到下遍历（遍历的是行）
                    int max = 0;
                    if (layout[j][i] == 1) {
                        //此时这个位置放置了灯笼
                        if (j == 0) {
                            layout[1][i] = 2;
                        } else {
                            if (j == m - 1) {
                                layout[m - 2][i] = 2;
                            } else {
                                if (layout[j - 1][i] == 0 && layout[j + 1][i] == 0) {
                                    layout[j - 1][i] = 2;
                                    layout[j + 1][i] = 2;
                                    max++;
                                }
                            }
                        }
                    }
                    total += max;
                }
            } else {
                if (m == 0) {
                    total = 0;
                } else {
                    if (m == 1) {
                        for (int j = 0; j < m; j++) {
                            if (layout[i][j] == 0) {
                                total++;
                            }
                        }
                    }
                }
            }
        }
        //5.结果输出
        if (total >= NumberOfBalloons) {
            System.out.

                    println("True");
        } else {
            System.out.

                    println("False");
        }
    }
}