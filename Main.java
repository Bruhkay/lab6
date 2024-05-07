public class Main {
    public static void main(String[] args) {
        School school = new School();
        school.processTextFile("input.txt");
        
        
        /* school.addStudent(new Student("John","Doe", 20, "9273636110"));
        school.addStudent(new Student("John","Doe", 20, "9473636110"));
        school.addStudent(new Student("Mikev","Dan", 20, "3453636110"));
        school.addStudent(new Student("Denver","Mully", 21, "1153636110"));
        school.getStudent("9273636110").setGrade("M1dd", 50, 64);
        school.getStudent("9273636110").setGrade("M2jj", 25, 60);
        school.printGradesOf("9273636110");
        System.out.println("average must be 62: "+school.getGradeAverage(school.getStudent("9273636110")));
        System.out.println();
        school.printStudents();
        System.out.println("adamimiz denver "+school.getStudent("1153636110"));
        school.printStudentsByNameOrder();*/
    }
}
