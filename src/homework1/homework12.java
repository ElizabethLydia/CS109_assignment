package homework1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class homework12 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long d;

        //开始测试用例n次
        while (n-- > 0) {
            //在每一个测试用例中输入需要转换的原本的10进制数字
            d = sc.nextInt();
            //定义一个D使它的数值和d相同，这样每次对D进行改变，没有真正改变d的值
            long D = d;
            //生成一个ArrayList，size = 0，可以无限添加元素去装每次取余的值
            ArrayList<String> list = new ArrayList<String>();
            //当可以对7取余时，就一次一次去除以7，把每次取余的值装入ArrayList中
            while (D >= 7) {
                long c = D % 7;
                //由于取余得到的形式是long，但是Arraylist中装的是String，所以需要把long的数值赋值到String类型的变量Number中
                //这样才可以把这个值成功装入到ArrayList之中
                String Number = String.valueOf(c);
                list.add(Number);
                D /= 7;
            }
            //当一次一次除以7之后数值小于7之后，不能再一次取余了，这个值为最后一个数值，同样的方法添加到ArrayList中
            long end = D;
            String D1 = String.valueOf(D);
            list.add(D1);

            //将ArrayList中的每一个元素进行反转，这个需要两个index和一个过渡变量temp
            for (int i = 0, j = list.size() - 1; i < j; i++, j--) {
                //注意根据index去读取list中的element需要list.get()方法
                String temp = list.get(j);
                //list.set(int index, String element)方法是将index处的值用element值替代
                list.set(j, list.get(i));
                list.set(i, temp);
            }

            //遍历list中的每一个元素，再把数字一个个合并起来
            //此时会出现的问题是list中是String类型的变量，但是我们最后合并得到的应该是long类型的数字
            //1.先定义一个空的String类型的变量，用于装每一次遍历的list中的元素
            String answer = "";
            for (int i = 0; i < list.size(); i++) {
                //2.把每个sting合并在一起
                answer = answer + list.get(i);
            }
            //3.将合并得到的String类型变量转换成long类型的变量，需要使用方法Long.parseLong()
            long m = Long.parseLong(answer);
            System.out.println(answer);
        }
    }
}
