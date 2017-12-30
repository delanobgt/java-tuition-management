package tuitionmanagement;

public class Student { 
    private int id;
    private String name;
    private String grade;
    private int fee;
    private String paymentDate;

    
    public Student(String name, String grade, int fee, String paymentDate) {
        this.id = -1;
        this.name = name;
        this.grade = grade;
        this.fee = fee;
        this.paymentDate = paymentDate;
    }

    public Student(int id, String name, String grade, int fee, String paymentDate) {
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.fee = fee;
        this.paymentDate = paymentDate;
    }
    
    public Object[] getContentAsArray() {
        return new Object[] {
            id,
            name,
            grade,
            fee,
            paymentDate
        };
    }
    
    public int getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }
    public int getFee() { return fee; }
    public void setFee(int fee) { this.fee = fee; }
    public String getPaymentDate() { return paymentDate; }
    public void setPaymentDate(String paymentDate) { this.paymentDate = paymentDate; }
}
