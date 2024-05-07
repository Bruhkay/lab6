import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class School {
    
    protected Student[] students;

    School(){
        students = new Student[10];
    }

    public void addStudent(Student Student){
        if(students[0]==null){
            students[0]= Student;
        } 
        else{
            int count = 0;
            
            for (Student student2 : students) {
                if(student2!=null){
                    count++;
                } 
            }
            boolean alreadyTaken = false;
            for(int i =0; i< count-1; i++){
                if(Student.getSchoolId().equals(students[i].getSchoolId())){
                    alreadyTaken = true;
                    System.out.println("Duplicate ID: " + Student.getSchoolId());
                }
            }
            if(!alreadyTaken){

                Student[] newStudents = new Student[students.length];
                if(count-1 == students.length/2){
                    newStudents = new Student[students.length*2];
                }
                int index =0;
                boolean isOkay = false;
                
                while(!isOkay){
                    
                    if(Long.parseLong(Student.getSchoolId()) <= Long.parseLong(students[index].getSchoolId()) || index==count-1){
                        isOkay = true;
                        if(index==count-1){
                            index++;
                        }
                    }
                    else{
                        index++;
                    }
                    
                }
                for(int i=0; i<index; i++){
                    newStudents[i] = students[i];

                }
                newStudents[index]= Student;
                for(int i=index+1; i< students.length;i++){
                    
                    newStudents[i] = students[i-1];
                }
                students = newStudents;
            }
        }
    } 
    
    public Student getStudent(String id){
        int count = 0;
        for (Student student2 : students) {
            if(student2!=null){
                count++;
            } 
        }
        int upperindex = count-1;
        int lowerIndex = 0;
        int index = count/2;
        boolean finded = false;
        while(!finded){

            if(Math.abs(lowerIndex-upperindex) <=2){
                if(Long.parseLong(students[lowerIndex].getSchoolId())== Long.parseLong(id)){
                    index = lowerIndex;
                }
                else if(Long.parseLong(students[upperindex].getSchoolId())== Long.parseLong(id)){
                    index = upperindex;
                }
                else if(Long.parseLong(students[(upperindex+lowerIndex)/2].getSchoolId())== Long.parseLong(id)){
                    index = (lowerIndex+ upperindex)/2;
                }
                else{
                    System.out.println("No such student with the id " + id + "!");
                }
                finded = true;
            }
            try {
                if(Long.parseLong(students[index].getSchoolId())<= Long.parseLong(id)){
                    lowerIndex= index;
                    index = (lowerIndex + upperindex)/2;
                }
                else if(Long.parseLong(students[index].getSchoolId())>= Long.parseLong(id)){
                    upperindex= index;
                    index = (lowerIndex + upperindex)/2;
                }
            } catch (Exception e) {
                System.out.println("ogrenci yok");
                finded = true;
            }
            
        }
        return students[index];
    }
    public Student[] getStudentByNameOrder(){
        Student[] nameOrdered = new Student[students.length];
        for(int i=0; i<students.length; i++){
            nameOrdered[i] = students[i];
        }
        quickSort(nameOrdered, 0, students.length - 1);
        return nameOrdered;
    }
    public static void quickSort(Student[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = divide(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    public static int divide(Student[] arr, int low, int high) {
        String pivot = arr[high].getName();
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j].getName().compareTo(pivot) < 0) {
                i++;
                Student temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        Student temp = arr[i];
        arr[i+1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }
    public void printStudentsByNameOrder(){
        for (Student student : getStudentByNameOrder()) {
            System.out.println(student);
        }
    }
    
    public void printStudents(){
        System.out.println("List all students: ");
        for (Student student : students) {
            if(student!=null){
                System.out.println(student);    
            }
        }
    }
    public float getGradeAverage(Student s){
        float sum = 0;
        float weight =0;
        for(int i=0; i< s.getGrades().length; i++){
            weight += s.getGrades()[i].getWeight();
            sum += s.getGrades()[i].getPoints()*s.getGrades()[i].getWeight();
        }
        return sum/weight;
    }
    public void printStudentsGradeAverages(){ //decending order lazim
        for(int i = 0; i< students.length;i++){
            System.out.println(students[i] +"- Average: "+ getGradeAverage(students[i]));
        }
    }
    public void printGradesOf(String schoolID){
        System.out.println("Student: "+ getStudent(schoolID));
        System.out.println("Grades: ");
        for(int i = 0; i<getStudent(schoolID).getGrades().length; i++){
            System.out.println(getStudent(schoolID).getGrades()[i]);
        }
    }
    public void processTextFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length < 2) {
                    continue; // Skip invalid lines
                }
                String command = parts[0].trim();
                String[] args = parts[1].trim().split(", ");
                for (String string : args) {
                    System.out.println(string);
                }
                switch (command) {
                    case "Student":
                        if (args.length == 4) {
                            String name = args[0].trim();
                            String surname = args[1].trim();
                            int age = Integer.parseInt(args[2].trim());
                            String studentID = args[3].trim();
                            this.addStudent(new Student(name, surname, age, studentID));
                        }
                        break;
                    case "Grade":
                        if (args.length == 4) {
                            String studentID = args[0].trim();
                            String examName = args[1].trim();
                            double weight = Double.parseDouble(args[2].trim());
                            int grade = Integer.parseInt(args[3].trim());
                            getStudent(studentID).setGrade(examName, (float)weight, (float)grade);
                        }
                        break;
                    case "GradesOf":
                        if (args.length == 1) {
                            String studentID = args[0].trim();
                            printGradesOf(studentID);
                        }
                        break;
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
                        // Invalid command
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
