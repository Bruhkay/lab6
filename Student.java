/**
 * Student
 */
public class Student {
    protected String name;
    protected String surname;
    protected String SchoolId; 
    protected int age;
    protected Grade[] grades;

    Student(String name, String surname, int age , String SchoolId){
        grades = new Grade[0];
        this.name = name;
        this.surname = surname;
        this.age= age;
        this.SchoolId= SchoolId;
    }

    public void addGrade(Grade grade){
        Grade[] newGrades = new Grade[grades.length + 1];
        for(int i = 0; i < grades.length; i++){
            newGrades[i] = grades[i];
        }
        newGrades[grades.length] = grade;
        grades = newGrades;
    }
    
    public void setGrade(String examName, float weight, float points){
        boolean isFound = false;
        for (Grade grade : grades) {
            if(grade.getExamName().equals(examName)){
                grade.setPoints(points);
                grade.setWeight(weight);
                isFound = true;
                break;
            }
        }
        if(!isFound){
            if(examName.length() > 3){
                Grade newGrade = new Grade();
                newGrade.setExamName(examName);
                newGrade.setPoints(points);
                newGrade.setWeight(weight);
                addGrade(newGrade);
            }
            else{
                System.out.println(examName + " must be longer than 3 characters!");
            }
        }
    }
    
    public Grade[] getGrades() {
        return grades;
    }
    public int getAge() {
        return age;
    }
    public String getName() {
        return name;
    }
    public String getSchoolId() {
        return SchoolId;
    }
    public String getSurname() {
        return surname;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSchoolId(String schoolId) {
        SchoolId = schoolId;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return SchoolId+", "+ name+ " "+ surname+", "+age;
    }
}