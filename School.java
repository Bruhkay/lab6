import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class School {
    
    protected Student[] students;

    School(){
        students = new Student[10];
    }
    public void addStudent(Student student) {
        int count = 0;
        for (Student s : students) {
            if (s != null) {
                count++;
            }
        }
    
        for (int i = 0; i < count; i++) {
            if (student.getSchoolId().equals(students[i].getSchoolId())) {
                System.out.println("Duplicate ID: " + student.getSchoolId());
                return;
            }
        }
    
        if (count == students.length) {
            Student[] newStudents = new Student[students.length * 2];
            System.arraycopy(students, 0, newStudents, 0, students.length);
            students = newStudents;
        }
    
        int index = count;
        for (int i = 0; i < count; i++) {
            if (Long.parseLong(student.getSchoolId()) < Long.parseLong(students[i].getSchoolId())) {
                index = i;
                break;
            }
        }
    
        for (int i = count; i > index; i--) {
            students[i] = students[i - 1];
        }
    
        students[index] = student;
    }
    
    public Student getStudent(String id) {
        
        int count =0;
        for (Student student2 : students) {
            if(student2!=null){
                count++;
            } 
        }
        int lowerIndex = 0;
        int upperindex = count - 1;
    
        while (lowerIndex <= upperindex) {
            int index = (lowerIndex + upperindex) / 2;
            long studentId = Long.parseLong(students[index].getSchoolId());
    
            if (studentId == Long.parseLong(id)) {
                return students[index];
            } else if (studentId < Long.parseLong(id)) {
                lowerIndex = index + 1;
            } else {
                upperindex = index - 1;
            }
        }
    
    
        System.out.println("No such student with the id " + id + "!");
        return null;
    }
    
    public Student[] getStudentByNameOrder(){
        
        Student[] nameOrdered = new Student[students.length];
        for(int i=0; i<students.length; i++){
            nameOrdered[i] = students[i];
        }
        int count =0;
        for (Student student2 : students) {
            if(student2!=null){
                count++;
            } 
        }
        quickSort(nameOrdered, 0, count - 1);
        return nameOrdered;
    }

    public void printStudentsByNameOrder(){
        System.out.println();
        System.out.println("Students by name order: ");
        for (Student student : getStudentByNameOrder()) {
            if(student != null){
                System.out.println(student);
            }
        }
        System.out.println();
    }
           
    public static void quickSort(Student[] array, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(array, begin, end);

            quickSort(array, begin, partitionIndex-1);
            quickSort(array, partitionIndex+1, end);
        }
    }

    private static int partition(Student[] array, int begin, int end) {
        String pivot = array[end].getName()+array[end].getSurname();
        int i = (begin-1);

        for (int j = begin; j < end; j++) {
            if ((array[j].getName()+array[j].getSurname()).compareToIgnoreCase(pivot) < 0) {
                i++;

                Student swapTemp = array[i];
                array[i] = array[j];
                array[j] = swapTemp;
            }
        }

        Student swapTemp = array[i+1];
        array[i+1] = array[end];
        array[end] = swapTemp;

        return i+1;
    }


    public void printStudents(){
        System.out.println("List all students: ");
        for (Student student : students) {
            if(student!=null){
                System.out.println(student);    
            }
        }
    }
    
    public float getGradeAverage(Student s) {
        if (s.getGrades() == null || s.getGrades().length == 0) {
            return 0; 
        }
        float sum = 0;
        float weight = 0;
        for (Grade grade : s.getGrades()) {
            if (grade != null && grade.getWeight() != 0) {
                weight += grade.getWeight();
                sum += grade.getPoints() * grade.getWeight();
            } else {
                return 0; 
            }
        }
    
        if (weight == 0) {
            return 0; 
        }
    
        return sum / weight;
    }
    

    public void printStudentsGradeAverages(){ 
        Student[] newList = new Student[students.length];
        for (int i = 0; i< students.length; i++) {
            newList[i]= students[i];
        }
        int count=0;
        for (Student student2 : students) {
            if(student2!=null){
                count++;
            } 
        }
        boolean isChanged = true;
        while(isChanged){
            isChanged = false;
            for(int j = 0; j< count-1; j++){
                if(getGradeAverage(newList[j]) < getGradeAverage(newList[j+1])){
                    Student temp;
                    temp = newList[j];
                    newList[j] = newList[j+1];
                    newList[j+1] = temp;
                    isChanged = true;
                }
            }
        }
        System.out.println();
        System.out.println("Printing by grade averages; ");
        System.out.println();
        for(int i = 0; i< count;i++){
            if(newList[i] != null ){
                if(newList[i].getGrades() != null){
                    System.out.println(newList[i] +"- Average: "+ getGradeAverage(newList[i]));
                }
            }
        }
        System.out.println();
    }

    public void printGradesOf(String schoolID){
        System.out.println();
        System.out.println("Student: "+ getStudent(schoolID));
        System.out.println();
        System.out.println("Grades: "); 
        if(getStudent(schoolID)!=null){
            for(int i = 0; i < getStudent(schoolID).getGrades().length; i++){
                System.out.println(getStudent(schoolID).getGrades()[i]);
            }
            System.out.println();
        }
    }

    public void processTextFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            
            while ((line = reader.readLine()) != null) {
                if(line.contains(":")){
                    String[] parts = line.split(":");
                    String command = parts[0];
                    String[] arguments = parts[1].split(", ");
                    

                    switch (command) {
                        case "Student":
                            String fullName = arguments[0];
                            String[] parts2 = fullName.split(" ");
                            
                            String name = parts2[0] ;
                            String surname = parts2[1];
                            int age = Integer.parseInt(arguments[1].trim());
                            String studentId = arguments[2].trim();
                            addStudent(new Student(name, surname, age, studentId));
                            
                             break;
                        case "Grade":
                            String id = arguments[0].trim();
                            String examName = arguments[1].trim();
                            float weight = Float.parseFloat(arguments[2].trim());
                            float grade = Float.parseFloat(arguments[3].trim());
                            try {
                                Student student = getStudent(id);
                                if(student!=null){
                                    student.setGrade(examName, weight, grade);
                                }
                                
                            } catch (Exception e) {
                                System.out.println("Error: " + e.getMessage());
                            }
                            break;
                        case "GradesOf":
                            printGradesOf(arguments[0].trim());
                            break;
                        default:
                            System.out.println("Unknown command: " + command);
                            break;
                    }
                }
                else{
                    String command = line;
                    
                    switch (command) {
                        case "PrintByNameOrder":
                            printStudentsByNameOrder();
                            break;
                        case "PrintByGradeAverages":
                            printStudentsGradeAverages();
                            break;
                        case "PrintStudents":
                            printStudents();
                            break;
                        default:
                            System.out.println("Unknown command: " + command);
                            break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }
    
}
