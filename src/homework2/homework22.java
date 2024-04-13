package homework2;

import java.util.ArrayList;
import java.util.Scanner;

/*
Qi和两个同学一起去看电影，找出他们的共同喜欢。
每个人先输入自己想看的电影数量
再输入自己喜欢的电影编号 (1≤num≤1000)
三个人重复三次这个操作
然后判断他们重合的数字个数进行输出
 */
public class homework22 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //1,第一个人输入他喜欢的电影数量。
        int n1 = sc.nextInt();
        int[] movie1 = new int[n1];
        //2.第一个人输入他喜欢的电影编号
        for (int i = 0; i < n1; i++) {
            movie1[i] = sc.nextInt();
        }

        //2.第二个人输入他喜欢的电影数量
        int n2 = sc.nextInt();
        int[] movie2 = new int[n2];
        //2.第一个人输入他喜欢的电影编号
        for (int i = 0; i < n2; i++) {
            movie2[i] = sc.nextInt();
        }

        //3.第三个人输入他喜欢的电影数量
        int n3 = sc.nextInt();
        int[] movie3 = new int[n3];
        //2.第一个人输入他喜欢的电影编号
        for (int i = 0; i < n3; i++) {
            movie3[i] = sc.nextInt();
        }

        //4.判断他们重合的数字个数。
        // 我的想法是先判断前两个人，再把他俩重合的数字装到新的数组中
        // 这个数组再和第三个人比较
        ArrayList<Integer> same = new ArrayList<>();
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < n2; j++) {
                if(movie1[i] == movie2[j]){
                    same.add(movie1[i]);
                }else {
                    continue;
                }
            }
        }

        int number = 0;
        for (int i = 0; i < same.size(); i++) {
            for (int j = 0; j < n3; j++) {
                if(same.get(i) == movie3[j]){
                    number++;
                }else {
                    continue;
                }
            }
        }

        System.out.println(number);
    }
}
