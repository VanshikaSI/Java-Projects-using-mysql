package main;

import databasemanagement.DataBaseUtilities;
import databasemanagement.DatabaseConnection;
import model.Employee;

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
        DatabaseConnection database = new DatabaseConnection(databasename, username, password);
        System.out.println("Enter table you want to create");
        String tablename = sc.nextLine();
        DataBaseUtilities utils = new DataBaseUtilities(database.getStatement(), tablename);
        utils.createTable();

        int option;
        do {
            System.out.println("1.Insert an employee");
            System.out.println("2.Show employee's list ");
            System.out.println("3.Update name in list");
            System.out.println("4.Exit");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    sc.nextLine();

                    System.out.println("Enter a name:");
                    String name = sc.nextLine();

                    System.out.println("Enter a position:");
                    String position = sc.nextLine();

                    System.out.println("Enter a Department:");
                    String department = sc.nextLine();

                    System.out.println("Enter a contact:");
                    String contact = sc.nextLine();

                    Employee m = new Employee(name, position, department, contact);
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

            }

        } while (option != 4);
    }}
