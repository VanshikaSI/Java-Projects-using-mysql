package databasemanagement;

import model.Student;

import java.sql.*;


public class DataBaseUtilities {
    private Statement statement;

    private Connection connection;
    private String tablename;
    public DataBaseUtilities(Statement statement,String tablename){
        this.statement=statement;
        this.tablename=tablename;
        this.connection = connection;
    }


    public void createTable() {
        try {
            String query = "CREATE TABLE " + this.tablename + " (" +
                    "name VARCHAR(40), " +
                    "roll_no VARCHAR(40), " +
                    "department VARCHAR(40), " +
                    "contact VARCHAR(40));";
            this.statement.executeUpdate(query);
            System.out.println(" Table created successfully.");
        } catch (SQLException e) {
            System.out.println(" Table already exists.");
            // Optional: Uncomment below if you want to see the specific error
            // e.printStackTrace();
        }
    }



    public void InsertData(Student e){
        try {
            this.statement.executeUpdate("insert into "+this.tablename+" values('"+e.getName()+"','"+e.getRollno()+"','"+e.getDepartment()+"','"+e.getContact()+"');");
            System.out.println("insert successfully");
        } catch (SQLException ex) {
            throw new RuntimeException();}}


    public void showList(){

        try {
            ResultSet employeelist =this.statement.executeQuery("select *from "+this.tablename+";");
            System.out.println("Listed");
            while(employeelist.next()){
                System.out.println("Name:"+employeelist.getString("name")+" RollNO:"+employeelist.getString("position")+" Department:"+employeelist.getString("department")+" Contact:"+employeelist.getString("contact")+";");}
        } catch (SQLException e) {
            throw new RuntimeException(e);}}
    public void updatelist(String set,String update){

        try {
            this.statement.executeUpdate("update "+this.tablename+" set name="+"'"+set+"'"+" where name="+"'"+update+"';");
            System.out.println("Updated successfully");
        } catch (SQLException e) {
            System.out.println("error in update");
            throw new RuntimeException(e);
        }}


    public void searchByRollNo(String rollno) {
        try {
            if (connection == null) {
                System.out.println("The student is not available.");
                return;
            }
            // Use placeholder (?) for PreparedStatement
            String query = "SELECT * FROM " + this.tablename + " WHERE roll_no = ?";
            PreparedStatement pstmt = this.connection.prepareStatement(query);
            pstmt.setString(1, rollno);  // Set the roll number safely

            ResultSet employeelist = pstmt.executeQuery();

            if (employeelist.next()) {
                System.out.println("Name: " + employeelist.getString("name") +
                        " Roll_no: " + employeelist.getString("roll_no") +
                        " Department: " + employeelist.getString("department") +
                        " Contact: " + employeelist.getString("contact") + ";");
            } else {
                System.out.println("No student found with roll number: " + rollno);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }




    public void deleteStudent(String name) {
        try {
            int result = this.statement.executeUpdate(
                    "DELETE FROM " + this.tablename + " WHERE name = '" + name + "';"
            );

            if (result > 0) {
                System.out.println(" Student deleted successfully.");
            } else {
                System.out.println("️ No student found with that name.");
            }

        } catch (SQLException e) {
            System.out.println(" Error deleting student.");
            e.printStackTrace();
        }
    }

}




