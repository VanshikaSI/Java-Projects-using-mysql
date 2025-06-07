package databasemanagement;

import model.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseUtilities {
    private Statement statement;
     private String tablename;
     public DataBaseUtilities(Statement statement,String tablename){
         this.statement=statement;
         this.tablename=tablename;
     }
     public void createTable(){

             try {
                 this.statement.executeUpdate("create table "+this.tablename+"(name varchar(40),position varchar(40),department varchar(40),contact varchar(40));");
                 System.out.println("Table created successfully");
             } catch (SQLException e) {
                 System.out.println("table already exit");
             }}
     public void InsertData(Employee e){
         try {
             this.statement.executeUpdate("insert into "+this.tablename+" values('"+e.getName()+"','"+e.getPosition()+"','"+e.getDepartment()+"','"+e.getContact()+"');");
              System.out.println("insert successfully");
         } catch (SQLException ex) {
             throw new RuntimeException();}}
     public void showList(){

             try {
                  ResultSet employeelist =this.statement.executeQuery("select *from "+this.tablename+";");
                 System.out.println("Listed");
                 while(employeelist.next()){
                 System.out.println("Name:"+employeelist.getString("name")+" Position:"+employeelist.getString("position")+" Department:"+employeelist.getString("department")+" Contact:"+employeelist.getString("contact")+";");}
             } catch (SQLException e) {
                 throw new RuntimeException(e);}}
     public void updatelist(String set,String update){

             try {
                 this.statement.executeUpdate("update "+this.tablename+" set name="+"'"+set+"'"+" where name="+"'"+update+"';");
                 System.out.println("Updated successfully");
             } catch (SQLException e) {
                 System.out.println("eror in update");
                 throw new RuntimeException(e);
             }
         }
     }

