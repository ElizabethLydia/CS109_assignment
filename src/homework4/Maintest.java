package homework4;

public class Maintest {
    public static void main(String[] args) {
        CourseManager courseManager = new CourseManager();
        courseManager.setIfOpen(true);

        Student student1 = new Student("s001", "student1@example.com", "Student One", 100);
        Student student2 = new Student("s002", "student2@example.com", "Student Two", 120);
        Student student3 = new Student("s003", "student3@example.com", "Student Three", 150);
        Student student4 = new Student("s004", "student3@example.com", "Student 4", 100);

        Course course1 = new Course("c001", "Course One", 3);
        Course course2 = new Course("c002", "Course Two", 3);
        Course course3 = new Course("c003", "Course 3", 3);

        courseManager.addStudent(student1);
        courseManager.addStudent(student2);
        courseManager.addStudent(student3);
        courseManager.addStudent(student4);
        courseManager.addCourse(course1);
        courseManager.addCourse(course2);
        courseManager.addCourse(course3);


        //Test08
        System.out.println(courseManager.enrollStudentInCourse(student1,"c001",50));
        courseManager.enrollStudentInCourse(student2,"c001",40);
        courseManager.enrollStudentInCourse(student3,"c001",10);
        courseManager.enrollStudentInCourse(student4,"c001",30);

        courseManager.enrollStudentInCourse(student1,"c002",20);
        courseManager.enrollStudentInCourse(student2,"c002",20);
        courseManager.enrollStudentInCourse(student3,"c002",20);
        courseManager.enrollStudentInCourse(student4,"c002",10);

        courseManager.enrollStudentInCourse(student1,"c003",20);
        courseManager.enrollStudentInCourse(student2,"c003",10);
        courseManager.enrollStudentInCourse(student3,"c003",10);
        courseManager.enrollStudentInCourse(student4,"c003",20);

        courseManager.modifyStudentEnrollmentCredits(student2,"c003",30);

        courseManager.finalizeEnrollments();

        for (Student s:course2.getSuccessStudents()
        ) {
            System.out.println(s.getStudentID());
        }
    }
}
