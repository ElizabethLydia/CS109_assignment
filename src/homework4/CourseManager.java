package homework4;

import java.util.ArrayList;
import java.util.Comparator;

public class CourseManager {
    private ArrayList<Course> courses;
    // Maintains a record of all courses successfully registered.保存所有成功注册的课程
// 保证学生已经存在了
    private ArrayList<Student> students;
    // 保存所有成功注册的同学
    private boolean ifOpen;

    // 选课阶段还是选课结束阶段
    public CourseManager() {
        //创建一个构造器，初始化courses和students，并且将ifOpen默认为true，即选课阶段
        courses = new ArrayList<Course>();
        students = new ArrayList<Student>();
        ifOpen = true;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setIfOpen(Boolean ifOpen) {
        this.ifOpen = ifOpen;
    }

    public boolean getIfOpen() {
        return ifOpen;
    }

    //在这段代码中，courses和students都是ArrayList类型的实例变量。
    // ArrayList是Java中的一个集合类，可以存储多个元素并动态增长。
    // 在这里，courses和students分别存储了Course类型和Student类型的对象。
    // 通过调用addCourses方法和addStudents方法，可以向这两个ArrayList集合中添加新的课程和学生对象。
    public void addCourse(Course courses) {
        this.courses.add(courses);
        courses.setCourseManager(this);
        //通过将课程对象的courseManager属性设置为当前的manager对象，
        // 就可以建立课程对象和课程管理器之间的关联关系。
    }

    public void addStudent(Student students) {
        this.students.add(students);
        students.setCourseManager(this);
        //通过将学生对象的courseManager属性设置为当前的manager对象，
        // 就可以建立学生对象和课程管理器之间的关联关系。
    }


    //attemps to enroll a student in a course
    // 试图让一个学生去选择一门课程
    //选课成功的条件
    //1.课程存在
    //2.学生没有选过这门课
    //3.学生的积分>0，有几分去投注
    public boolean enrollStudentInCourse(Student student, String courseId, int credits) {
        //在这段代码中，首先判断选课阶段是否已经结束，如果选课阶段已经结束，则返回false。
        if (!ifOpen) {
            return false;
        } else {
            //然后，通过遍历courses集合，找到对应courseId的课程对象。
            Course course = null;
            for (Course c : courses) {
                if (c.getCourseID().equals(courseId)) {
                    course = c;
                    break;
                }
            }
            //如果课程对象为null,说明课程不存在，返回false。
            if (course == null) {
                return false;
            }
            //接着，判断学生是否已经选过这门课，如果选过，则返回false。
            if (student.getEnrollCourses().contains(course)) {
                return false;
            }
            //最后，判断学生的积分是否大于0，如果积分小于等于0，则返回false。
            if (credits <= 0) {
                return false;
            }
            if (student.getCredits() < credits) {
                return false;
            }
            //如果以上条件都满足，则将学生的积分减去credits，将课程添加到学生的选课列表中，
            student.setCredits(student.getCredits() - credits);
            student.getEnrollCourses().add(course);
            //将学生添加到课程的选课列表中，将credits添加到课程的积分列表中。
            course.getEnrollStudent().add(student);
            course.getCredits().add(credits);
            return true;
        }
    }

    //修改学生bid的积分方法
    public boolean modifyStudentEnrollmentCredits(Student student, String courseId, int credits) {
        //1.判断选课阶段是否已经结束，如果选课阶段已经结束，则返回false。
        //2.course存在
        //3.学生选过这门课
        //4.学生修改积分满足他的积分情况
        //1.判断选课阶段是否已经结束，如果选课阶段已经结束，则返回false。
        if (ifOpen == false) {
            return false;
        } else {//2.course存在
            Course course = null;
            for (Course c : courses) {
                if (c.getCourseID().equals(courseId)) {
                    course = c;
                    break;
                }
            }
            if (course == null) {
                return false;
            }
            //3.学生选过这门课
            if (!student.getEnrollCourses().contains(course)) {
                return false;
            }
            if (credits <= 0) {
                return false;
            }
            //4.判断他修改的积分是否满足他的积分情况
            //course.getCredits().get(student.getEnrollCourses().indexOf(course))是学生选的这门课的积分 这里需要好好理解一下
            if (student.getCredits() + course.getCredits().get(student.getEnrollCourses().indexOf(course)) < credits) {
                return false;
            }
            //这里以上的条件都满足了
            //1.更新学生目前的剩余积分
            //2.更新学生的这个课程的投分情况，变为此时的所投积分
            //3.更新课程的积分情况，变为此时的所投积分
            //1.更新学生目前的剩余积分
//            student.setCredits(student.getCredits() + course.getCredits().get(student.getEnrollCourses().indexOf(course)) - credits);
            student.setCredits(student.getCredits() + course.getCredits().get(course.getEnrollStudent().indexOf(student)) - credits);
            //注意第一种写法是错误的，因为学生课表list中这门课的索引不是这门课程选课学生的索引
            //2.更新学生的这个课程的投分情况，变为此时的所投积分
            course.getCredits().set(course.getEnrollStudent().indexOf(student), credits);
            //3.更新课程的积分情况，变为此时的所投积分
            course.getCredits().set(course.getEnrollStudent().indexOf(student), credits);
//            student.getEnrollCourses().indexOf(course), credits);
            return true;
        }
    }

    public boolean dropStudentEnrollmentCourse(Student student, String courseId) {
        if (!ifOpen) {
            return false;
        }
        Course course = null;
        for (Course c : courses) {
            if (c.getCourseID().equals(courseId)) {
                course = c;
                break;
            }
        }
        if (course == null) {
            return false;
        }
        if (!student.getEnrollCourses().contains(course)) {
            return false;
        }
        if (course.getEnrollStudent().indexOf(student) == -1) {
            return false;
        } else {
            int studentIndexInCourse = course.getEnrollStudent().indexOf(student);
            student.setCredits(student.getCredits() + course.getCredits().get(studentIndexInCourse));
            student.getEnrollCourses().remove(course);
            course.getEnrollStudent().remove(studentIndexInCourse); // 修正索引
            course.getCredits().remove(studentIndexInCourse); // 修正索引
            return true;
        }
    }
    //退课
//    public boolean dropStudentEnrollmentCourse(Student student, String courseId) {
//        //1.选课阶段
//        //2.课程存在
//        //3.学生选过这门课
//        if (!ifOpen) {
//            return false;
//        } else {//2.course存在
//            Course course = null;
//            for (Course c : courses) {
//                if (c.getCourseID().equals(courseId)) {
//                    course = c;
//                    break;
//                }
//            }
//            if (course == null) {
//                return false;
//            }
//            //3.学生选过这门课
//            if (!student.getEnrollCourses().contains(course)) {
//                return false;
//            }
//            //满足了退课的前提条件，成功退课之后
//            //1.积分退回，学生积分更新
//            //2.学生选课列表去除这门课
//            //3.课程的学生列表里面去除这个学生，课程的积分列表中去除这个学生对应的积分
//            //1.积分退回，学生积分更新
//            // (course.getCredits().get(student.getEnrollCourses().indexOf(course))和course.getEnrollStudent().indexOf(student)是不一样的
//            //学生中找到该课程的索引不一定是我要的这门课程投分学生的索引
//            student.setCredits(student.getCredits() + course.getCredits().get(course.getEnrollStudent().indexOf(student)));
//            //2.学生选课列表去除这门课
//            student.getEnrollCourses().remove(course);
//            //3.课程的学生列表中去除这个学生，课程的积分列表中去除这个学生对应的积分
//            course.getCredits().remove(course.getEnrollStudent().indexOf(student));
//            course.getEnrollStudent().remove(student);
////            course.getEnrollStudent().remove(student);
////            //注意这里已经把同学移除了，所以这里的course.getCredits().get(course.getEnrollStudent().indexOf(student))是找不到的
////            course.getCredits().remove(course.getEnrollStudent().indexOf(student));
//            return true;
//        }
//    }


    public void finalizeEnrollments() {
        //1.选课结束，状态ifOpen改成false
        ifOpen = false;
        //2.根据选课的课容和学生投分的排名来确认成功成功选课名单
        //3.学生的成功选课课表更新
        //4.课程的成功选课学生更新
        //特别注意选课学生大于课程容量会有同分溢出的情况
        //2.根据选课的课容和学生投分的排名来确认成功成功选课名单
        for (Course course : courses) {
            //一.课程容量大于等于选课学生数
            if (course.getMaxCapacity() >= course.getEnrollStudent().size()) {
                //3.学生的成功选课课表更新
                for (Student student : course.getEnrollStudent()) {
                    student.getSuccessCourses().add(course);
                    //4.课程的成功选课学生更新
                    course.getSuccessStudents().add(student);
                }
            }
            //二.课程容量小于选课学生数
            if (course.getMaxCapacity() < course.getEnrollStudent().size()) {
                ArrayList<Integer> inputCredits = new ArrayList<Integer>();
                for (int i = 0; i < course.getEnrollStudent().size(); i++) {
                    inputCredits.add(course.getCredits().get(i));
                }
                inputCredits.sort(Comparator.reverseOrder());//对这个数组进行从大到小的排序
//                //对这个数组进行从大到小的排序
//                for (int i = 0; i < inputCredits.size(); i++) {
//                    for (int j = 0; j < inputCredits.size() - i - 1; j++) {
//                        if (inputCredits.get(j) < inputCredits.get(j + 1)) {
//                            int temp = inputCredits.get(j);
//                            inputCredits.set(j, inputCredits.get(j + 1));
//                            inputCredits.set(j + 1, temp);
//                        }
//                    }
//                }
////               二.0:所有人都是一个分数的情况
//
                //二.1:不出现同分溢出的情况,即在满足选入课程的最后一人他不会和后面的同分，即使他与前面的同分，但是与后面不同分，他也可以正好选进去
                if (inputCredits.get(course.getMaxCapacity() - 1) != inputCredits.get(course.getMaxCapacity())) {
                    for (int i = 0; i < course.getMaxCapacity(); i++) {
                        //此时应该在从大到小的积分表inputCredits中从大的开始遍历，找到对应积分对应的学生，
                        //在这个学生的成功选课列表中把这个课程加到成功选课列表中
                        for (int j = 0; j < course.getEnrollStudent().size(); j++) {
                            if (course.getCredits().get(j).equals(inputCredits.get(i))) {
                                //这里找到多个积分相等的也不要紧，因为他们在积分溢出的情况外，都是可以录进去的
                                //3.学生的成功选课课表更新
                                course.getEnrollStudent().get(j).getSuccessCourses().add(course);
                                //4.课程的成功选课学生更新
                                course.getSuccessStudents().add(course.getEnrollStudent().get(j));
                                break;
                            }
                        }
                    }
                } else {
                    //二.2.出现同分溢出的情况，即满足选入课程的最后一人他会和后面的同分
                    //二.2.1.找到不会出现同分溢出的那个人
                    //二.2.2.在这个人之前的人都是可以录进去的，j之后的人都是不能录进去的
                    //二.2.1.找到不会出现同分溢出的那个人
                    boolean flag = true;//表示这种情况不存在
                    for (int i = 0; i < inputCredits.size(); i++) {
                        if (inputCredits.get(i) != inputCredits.get(i + 1)) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag == true) {//所有人一个分，所有人都掉了
                        course.getSuccessStudents().clear();
                    } else {
                        int maxSatisfy = 0;
                        for (int i = 0; i < course.getMaxCapacity(); i++) {
                            if (inputCredits.get(i).equals(inputCredits.get(course.getMaxCapacity() - 1))) {
                                maxSatisfy = i;
                                break;
                            }
                        }
                        for (int j = 0; j < maxSatisfy; j++) {
                            for (int k = 0; k < course.getCredits().size(); k++) {
                                if (course.getCredits().get(k).equals(inputCredits.get(j))) {
                                    course.getSuccessStudents().add(course.getEnrollStudent().get(k));
                                    course.getEnrollStudent().get(k).getSuccessCourses().add(course);
                                    course.getCredits().set(k, -1);
                                }
                            }
//                        //3.学生的成功选课课表更新
//                        course.getEnrollStudent().get(j).getSuccessCourses().add(course);
//                        //4.课程的成功选课学生更新
//                        course.getSuccessStudents().add(course.getEnrollStudent().get(j));
//                        break;
                        }
                    }
                }
            }
//                    for (int i = 0; i < maxSatisfy; i++) {
//                        for (int j = 0; j < course.getCredits().size(); j++) {
//                            if (course.getCredits().get(j).equals(inputCredits.get(i))) {
//                                course.getSuccessStudents().add(course.getEnrollStudent().get(j));
//                                course.getEnrollStudent().get(j).getSuccessCourses().add(course);
//                            }
//                        }
//                    }
//                }
//                //二.2.出现同分溢出的情况，即满足选入课程的最后一人他会和后面的同分
//                if (inputCredits.get(course.getMaxCapacity()-1) == inputCredits.indexOf(course.getMaxCapacity())) {
//                    for (int i = 0; i < inputCredits.get(course.getMaxCapacity()); i++) {
//                        int maxSatisfy = 0;//这个用来标记不会出现同分溢出的那个人
//                        //此时应该在从大到小的积分表inputCredits中从大的开始遍历，找到对应积分对应的学生，
//                        //在这个学生的成功选课列表中把这个课程加到成功选课列表中
//                        for (int j = 0; j < inputCredits.get(course.getMaxCapacity()); j++) {
////                            ArrayList<Boolean> check = new ArrayList<Boolean>(course.getEnrollStudent().size());
////                            //判断满足课容对应的那个人的前面有与他同分的人，如果有，那里标记为true，不加入成功选课列表，
////                            // 没有就标记为false，加入成功选课列表
//                            if (inputCredits.get(j) == inputCredits.get(course.getMaxCapacity()-1)) {
//                                //找到满足课容对应的那个人
//                                maxSatisfy = j - 1;//这个以为着第j个人之前的人都是可以录进去的，j之后的人都是不能录进去的
//                                break;
//                            }
//                        }
//                        for (int j = 0; j < maxSatisfy; j++) {
//                            //3.学生的成功选课课表更新
//                            course.getEnrollStudent().get(j).getSuccessCourses().add(course);
//                            //4.课程的成功选课学生更新
//                            course.getSuccessStudents().add(course.getEnrollStudent().get(j));
//                            break;
//                        }
//                    }
//                }
        }
    }

    /**
     * Retrieves a list of courses with associated credits for a given student.
     * Each String in the list includes the course ID and the points bid by the
     * student in enrollCourses, follow the format: "courseID: enrollmentCredits"
     * (without quotes).
     * Only available when ifOpen is true. Return null if ifOpen is false.
     *
     * @return A list of Strings, each representing the courses and the respective
     * credits the student enrolled.
     */
//生成一个String类型的学生的选课列表，每个格式是"courseID: enrollmentCredits"
//只有在选课阶段才能生成，选课结束阶段返回null
    public ArrayList<String> getEnrolledCoursesWithCredits(Student student) {
        if (!ifOpen) {
            return null;
        } else {
            ArrayList<String> result = new ArrayList<String>();
            for (Course course : student.getEnrollCourses()) {
                result.add(course.getCourseID() + ": " + course.getCredits().get(course.getEnrollStudent().indexOf(student)));
            }
            return result;
        }
    }
}