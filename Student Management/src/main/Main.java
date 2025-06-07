package main;

import databasemanagement.DataBaseUtilities;
import databasemanagement.DatabaseConnectivite;
import model.Student;

import java.sql.Connection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Databasename:");
        String databasename = sc.nextLine();
        System.out.println("Enter   Username:");
        String username = sc.nextLine();
        System.out.println("Enter Password:");
        String password = sc.nextLine();
        DatabaseConnectivite database = new DatabaseConnectivite(databasename, username, password);
        System.out.println("Enter table you want to create");
        String tablename = sc.nextLine();
        DataBaseUtilities utils = new DataBaseUtilities(database.getStatement(), tablename);
        utils.createTable();

        int option;
        do {
            System.out.println("1.Insert new Student");
            System.out.println("2.View all Students ");
            System.out.println("3.Update  Student name in list");
            System.out.println("4.Delete the data of a student ");
            System.out.println("5.Search for the student by rollno");
            System.out.println("6.exit");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    sc.nextLine();

                    System.out.println("Enter a name:");
                    String name = sc.nextLine();

                    System.out.println("Enter a roll_no:");
                    String roll_no = sc.nextLine();

                    System.out.println("Enter a Department:");
                    String department = sc.nextLine();

                    System.out.println("Enter a contact:");
                    String contact = sc.nextLine();

                    Student m = new Student(name, roll_no, department, contact);
                    utils.InsertData(m);
                    break;
                case 2:
                    utils.showList();
                    break;
                case 3:
                    System.out.println("enter a value to be set instead of recent value");
                    String s = sc.next();
                    System.out.println("Enter the already present value ");
                    String n = sc.next();
                    utils.updatelist(s, n);
                    break;
                case 4:
                    System.out.println("Enter hte students name, whose data you want to delete");
                    String d=sc.next();
                    utils.deleteStudent(d);
                    break;
                case 5:
                    System.out.println("Enter the rollno who you want to find ");
                    String v=sc.next();
                    utils.searchByRollNo(v);
                    break;
                                }

        } while (option != 6);
    }}

