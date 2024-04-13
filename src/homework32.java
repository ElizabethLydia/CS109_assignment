import java.util.Scanner;

public class homework32 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String[] parts = input.split(" ");
        String str = parts[0];
        int k = Integer.parseInt(parts[1]);
        int a = str.length();
        //创建一个string类型的数组，填充每一个小模块的数据
        int arraySize = a % k == 0 ? a / k : a / k + 1;
        String[] strings = new String[arraySize];//string[4]
        //先填充前面所有规整的
        if (a % k == 0) {
            for (int i = 0; i < arraySize; i++) {//i = 0 1 2
                strings[i] = str.substring((i) * k, k * (i + 1));
            }
        } else {
            for (int i = 0; i < arraySize - 1; i++) {//i = 0 1 2
                strings[i] = str.substring((i) * k, k * (i + 1));
            }
            //填充最后一个不规整的
            StringBuilder last = new StringBuilder();
            for (int i = (a / k) * k; i < str.length(); i++) {
                last.append(str.charAt(i));
            }
            strings[arraySize-1] = last.toString();
        }

        //完成所有模块数据的反转
        for (int i = 0; i < arraySize; i++) {
            strings[i] = new StringBuilder(strings[i]).reverse().toString();
        }

        //完成所有模块的数据求和
        long sum = 0;
        for (int i = 0; i < arraySize; i++) {
            sum += Long.parseLong(strings[i]);
        }
        System.out.println(sum);
    }
}