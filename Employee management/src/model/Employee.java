package model;

public class Employee {

    private String name;
    private String position;
    private String department;
    private String contact;
    public Employee(String name,String position,String department,String contact){
        this.position=position;
        this.name=name;
        this.department=department;
        this.contact=contact;

    }
     public String getPosition(){
         return this.position;
     }
     public String getName(){
        return this.name;
     }

    public String getContact() {
        return this.contact;
    }

    public String getDepartment() {
        return this.department;
    }
}
