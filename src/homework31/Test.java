package homework31;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //输入多少个信息
        int number = sc.nextInt();
        //由于不知道会输入多少个course和classroom
        ArrayList<Classroom> classrooms = new ArrayList<>();
        ArrayList<Course> courses = new ArrayList<>();


        for (int i = 0; i < number; i++) {
            String[] input = sc.next().split(",");
            String information = input[0];
            String category = input[1];
            int capacity = Integer.parseInt(input[2]);
            if ("R".equals(information)) {
                Classroom R = new Classroom(category, capacity);
//                R.setCategory(sc.next());
//                R.setCapacity(sc.nextInt());
                classrooms.add(R);
            } else if ("C".equals(information)) {
                Course C = new Course(category, capacity);
//                C.setCategory(sc.next());
//                C.setCapacity(sc.nextInt());
                courses.add(C);
            }
        }
//            if (info.equals("R")) {
//                Classroom classroom = new Classroom(category, capacity);
//                classrooms.put(classroom, classroom);
//            } else if (info.equals("C")) {
//                Course course = new Course(type, capacity);
//                courses.put(type, course);
//            }
//        }

//        for (int i = 0; i < number; i++) {
//            String information = sc.next();
//            if ("R".equals(information)) {
//                Classroom R = new Classroom();
//                R.setCategory(sc.next());
//                R.setCapacity(sc.nextInt());
//                classrooms.add(R);
//            } else if ("C".equals(information)) {
//                Course C = new Course();
//                C.setCategory(sc.next());
//                C.setCapacity(sc.nextInt());
//                courses.add(C);
//            }
//        }

        //目前已经完成了所有数据的存储，要进行判断了
        boolean satisfy = true;
        //我先按照capacity从小到大吧arraylist给重新排布一下
        classrooms.sort(Comparator.comparing(Classroom::getCapacity));
        courses.sort(Comparator.comparing(Course::getCapacity));

        //然后把每一个course提出来，找到匹配的教室类型和满足的最小教室
        //注意一旦有一个course不满足，立即输出No，所有course都满足才输出yes
        for (Course course : courses) {
            int a = 0;
            for (Classroom classroom : classrooms) {
                if (classroom.getCategory().equals(course.getCategory()) && classroom.getCapacity() >= course.getCapacity() && classroom.isVisited == false) {
                    classroom.isVisited = true;
                    satisfy = true;
                    a = 1;
                    break;
                } else {
                    continue;
                }
            }
            if (a == 0){
                satisfy = false;
            }
        }
        if (satisfy) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}

//        while (!courses.isEmpty() && satisfy == true) {
//            for (Course course : courses.values()) {
//                for (Classroom classroom : classrooms.values()) {
//                    ArrayList<Integer> minmax = new ArrayList<>();
//                    if (classroom.getCategory().equals(course.getCategory())) {
//                        //此时说明他们类型已经匹配，应该找到最小的大于course capacity的值
//                        if (classroom.getCapacity() >= course.getCapacity()) {
//                            int difference = classroom.getCapacity() - course.getCapacity();
//                            minmax.add(difference);
//                        }
//                        //我现在需要找的minmax中对应的最小值的index
//                        //再根据index删掉对应的classr和course
//                        int index = 0;
//                        for (int i = 0, j = minmax.size() - 1; i < j; i++, j--) {
//                            if (minmax.get(i) <= minmax.get(j)) {
//                                index = i;
//                            } else {
//                                index = j;
//                            }
//                        }
//                        //删除对应的classroom和course
//                        int count = 0;
//                        for (Classroom cr : classrooms.values()) {
//                            if (cr.getCategory().equals(course.getCategory())) {
//                                if (count == index) {
//                                    classrooms.remove(cr.getCategory());
//                                    break;
//                                }
//                                count++;
//                            }
//                        }
//                        courses.remove(course.getCategory());
//                    } else {
//                        satisfy = false;
//                        System.out.println("No");
//                    }
//                }
//            }
//        }
//        if (courses.isEmpty()) {
//            System.out.println("Yes");
//        } else {
//            System.out.println("No");
//        }
//    }
//}
