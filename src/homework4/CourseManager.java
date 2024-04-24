package homework4;

import java.util.ArrayList;
import java.util.Comparator;

public class CourseManager {
    private ArrayList<Course> courses;
    private ArrayList<Student> students;
    private boolean ifOpen;
    public CourseManager(){
        courses= new ArrayList<>();
        students= new ArrayList<>();
        ifOpen=true;
    }
    public ArrayList<Student> getStudents(){
        return students;
    }
    public ArrayList<Course> getCourses(){
        return courses;
    }
    public void setIfOpen(boolean ifOpen){
        this.ifOpen=ifOpen;
    }
    public boolean getIfOpen(){
        return ifOpen;
    }
    public void addCourse(Course course) {
        course.setCourseManager(this);
        courses.add(course);
    }

    public void addStudent(Student student) {
        student.setCourseManager(this);
        students.add(student);
    }
    public boolean enrollStudentInCourse(Student student, String courseId, int credits) {
        if (!ifOpen) {
            return false;
        } else {
            if (courses==null || courses.isEmpty()){ //加一条防止courses是空的或不存在，要不然下面遍历courses会有问题
                return false;
            }else {
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
                if (credits <= 0) {
                    return false;
                }
                if (student.getCredits() < credits) {
                    return false;
                }
                if (student.getEnrollCourses().contains(course)) {
                    return false;
                }else {
                    course.getEnrollStudent().add(student);
                    course.getCredits().add(credits);
                    student.setCredits(student.getCredits() - credits);
                    student.getEnrollCourses().add(course);
                    return true;
                }
            }
        }
    }

    public boolean modifyStudentEnrollmentCredits(Student student, String courseId, int credits) {
        if (!ifOpen) {
            return false;
        } else {
            if (courses==null || courses.isEmpty()){ //加一条防止courses是空的或不存在，要不然下面遍历courses会有问题
                return false;
            }else {
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
                if (credits <= 0) {
                    return false;
                }
                if (!student.getEnrollCourses().contains(course)) {
                    return false;
                }else {
                    if (course.getEnrollStudent().indexOf(student)!=-1){
                        if ((student.getCredits() + course.getCredits().get(course.getEnrollStudent().indexOf(student)) - credits)>=0){ //防止修改后student剩余学分变为负数
                            student.setCredits(student.getCredits() + course.getCredits().get(course.getEnrollStudent().indexOf(student)) - credits);
                            course.getCredits().set(course.getEnrollStudent().indexOf(student), credits);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    public boolean dropStudentEnrollmentCourse(Student student, String courseId) {
        if (!ifOpen) {
            return false;
        } else {
            if (courses==null || courses.isEmpty()){ //加一条防止courses是空的或不存在，要不然下面遍历courses会有问题
                return false;
            }else {
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
                }else {
                    int studentIndex = course.getEnrollStudent().indexOf(student);
                    if (studentIndex != -1) {
                        student.setCredits(student.getCredits() + course.getCredits().get(studentIndex));
                        student.getEnrollCourses().remove(course);
                        course.getCredits().remove(studentIndex);
                        course.getEnrollStudent().remove(student);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public ArrayList<String> getEnrolledCoursesWithCredits(Student student) {
        if (!ifOpen) {
            return new ArrayList<>();
        } else {
            ArrayList<String> result = new ArrayList<>();
            for (Course course : student.getEnrollCourses()) {
                if (course.getEnrollStudent().indexOf(student)!=-1){
                    result.add(course.getCourseID() + ": " + course.getCredits().get(course.getEnrollStudent().indexOf(student)));
                }
            }
            return result;
        }
    }
    public void finalizeEnrollments() {
        ifOpen = false;
        for (Course course : courses) {
            if (course.getMaxCapacity() >= course.getEnrollStudent().size()) {
                for (Student student : course.getEnrollStudent()) {
                    student.getSuccessCourses().add(course);
                    course.getSuccessStudents().add(student);
                }
            }else {
                ArrayList<Integer> inputCredits = new ArrayList<>();
                for (int i = 0; i < course.getEnrollStudent().size(); i++) {
                    inputCredits.add(course.getCredits().get(i));
                }
                inputCredits.sort(Comparator.reverseOrder());

                if (inputCredits.get(course.getMaxCapacity() - 1) != inputCredits.get(course.getMaxCapacity())) {
                    for (int i = 0; i < course.getMaxCapacity(); i++) {
                        for (int j = 0; j < course.getEnrollStudent().size(); j++) {
                            if (course.getCredits().get(j).equals(inputCredits.get(i))) {
                                course.getEnrollStudent().get(j).getSuccessCourses().add(course);
                                course.getSuccessStudents().add(course.getEnrollStudent().get(j));
                                course.getCredits().set(j,-1); //如果不设置，break跳出内层循环后，外层下一个要找的数字和上一个一样时，会在相同的student处检测到然后break出去，导致重复检测
                                break;
                            }
                        }
                    }
                } else {
                    int maxSatisfy = 0;
                    //for (int i = 0; i < inputCredits.get(course.getMaxCapacity()); i++) {遍历的次数应该时前course.getMaxCapacity()个，而不是第course.getMaxCapacity()个的数值大小
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
                    }
                }
            }
        }
    }
}