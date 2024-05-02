public class Grade {
    protected String examName;
    protected float weight;
    protected float points;

    
    public void setExamName(String examName) {
        this.examName = examName;
    }
    public void setPoints(float points) {
        this.points = points;
    }
    public void setWeight(float weight) {
        this.weight = weight;
    }
    public String getExamName() {
        return examName;
    }
    public float getPoints() {
        return points;
    }
    public float getWeight() {
        return weight;
    }
}
