package homework2;

import java.util.Scanner;

/*
1.两个人答题，答完所有的题，得分高着获胜
2.计算分数的规则如下:每道题的分数可能不同。
    如果答案正确，这个问题的得分就会加到玩家的总分中。
    total += score;
    如果答案是错误的，该问题的得分将从玩家的总分中扣除。
    total -= score;
    如果玩家没有回答问题，那么该问题的一半分数将从玩家的总分中扣除。
    total -= 0.5*score;
3.输入：
第一行是问题数n (1 < n < 50)。
第二行是每个问题的分数信息。每个分数是一个整数，用空格分隔。
第三行为祁的答疑记录，中间用空格隔开。
第四行是参赛者的答题信息记录，也用空格隔开。
回答信息有三种类型，分别是2、1、0。分别是正确回答，错误回答和不回答。
4.输出：
If Qi's final score is higher than the competitor's final score, please output Qi won.
If Qi's final score is lower than the competitor's final score, please output Qi lost.
If Qi's final score is as same as the competitor's final score, please output Qi need another round.
 */
public class homework21 {
    public static void main(String[] args) {
        //1.第一行输入要求：题目个数。
        Scanner sc = new Scanner(System.in);
        System.out.println("请您输入题目个数：");
        int n = sc.nextInt();

        //2,第二行输入要求:每个题目对应的分数。
        int[] score = new int[n];
        System.out.println("请您输入题目对应的分数：");
        for (int i = 0; i < n; i++) {
            score[i] = sc.nextInt();
        }

        //3.第三行输入要求：记录Qi的答题情况。
        int[] Qi = new int[n];
        System.out.println("请您输入Qi的答题情况（2代表答对，1代表答错，0代表未答题）：");
        for (int i = 0; i < n; i++) {
            Qi[i] = sc.nextInt();
        }

        //4.第4行输入要求：记录Competitor的答题情况。
        int[] Competitor = new int[n];
        System.out.println("请您输入Competitor的答题情况（2代表答对，1代表答错，0代表未答题）：");
        for (int i = 0; i < n; i++) {
            Competitor[i] = sc.nextInt();
        }

        //5.进行Qi分数计算。
        double ScoreOfQi = 0;
        for (int i = 0; i < n; i++) {
            if (Qi[i] == 2) {
                //这个题目答对
                ScoreOfQi += score[i];
            } else {
                if (Qi[i] == 1) {
                    //这个题目答错
                    ScoreOfQi -= score[i];
                } else {
                    if (Qi[i] == 0) {
                        //未答此题
                        ScoreOfQi -= 0.5 * score[i];
                    }
                }
            }
        }

        //6.进行Competitor分数计算。
        double ScoreOfCompetitor = 0;
        for (int i = 0; i < n; i++) {
            if (Competitor[i] == 2) {
                //这个题目答对
                ScoreOfCompetitor += score[i];
            } else {
                if (Competitor[i] == 1) {
                    //这个题目答错
                    ScoreOfCompetitor -= score[i];
                } else {
                    if (Competitor[i] == 0) {
                        //未答此题
                        ScoreOfCompetitor -= 0.5 * score[i];
                    }
                }
            }
        }

        //7.对两者的分进行大小判断和结果输出。
        if (ScoreOfQi > ScoreOfCompetitor){
            System.out.println("Qi won");
        }else {
            if (ScoreOfQi < ScoreOfCompetitor){
                System.out.println("Qi lost");
            }else {
                if (ScoreOfQi == ScoreOfCompetitor){
                    System.out.println("Qi need another round");
                }
            }
        }
    }
}
