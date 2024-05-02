/**
 * Student
 */
public class Student {
    protected String name;
    protected String surname;
    protected String SchoolId; 
    protected int age;
    protected Grade[];

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