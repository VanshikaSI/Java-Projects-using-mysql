package main;

import databasemanagement.DatabaseConnection;
import databasemanagement.DatabaseUtilities;
import model.Account;
import model.Customer;
import model.Transation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Get DB connection info
        System.out.print("Enter Database name: ");
        String dbName = sc.nextLine();
        System.out.print("Enter Username: ");
        String username = sc.nextLine();
        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        // Connect to DB
        DatabaseConnection db = new DatabaseConnection(dbName, username, password);

        System.out.print("Enter customer table name: ");
        String customerTable = sc.nextLine();
        System.out.print("Enter account table name: ");
        String accountTable = sc.nextLine();
        System.out.print("Enter transaction table name: ");
        String transactionTable = sc.nextLine();

        DatabaseUtilities utils = new DatabaseUtilities(db.getStatement(), customerTable, accountTable, transactionTable);

        // Create Tables
        utils.CreateCustomerTable();
        utils.CreateAccounttable();
        utils.CreateTransactiontable();

        int option;
        do {
            System.out.println("\n1. Create Customer");
            System.out.println("2. Create Account");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Show Balance");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            // Validate option input
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number between 1 and 6.");
                sc.next();
            }
            option = sc.nextInt();
            sc.nextLine(); // consume leftover newline

            switch (option) {
                case 1:
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Age: ");
                    while (!sc.hasNextInt()) {
                        System.out.println("Please enter a valid integer for age.");
                        sc.next();
                    }
                    int age = sc.nextInt();
                    sc.nextLine(); // consume newline
                    System.out.print("Enter Occupation: ");
                    String occupation = sc.nextLine();
                    System.out.print("Enter Address: ");
                    String address = sc.nextLine();
                    Customer c = new Customer(name, age, occupation, address);
                    utils.InsertCustomer(c);
                    break;

                case 2:
                    System.out.print("Enter Customer ID: ");
                    while (!sc.hasNextInt()) {
                        System.out.println("Please enter a valid integer for Customer ID.");
                        sc.next();
                    }
                    int cid = sc.nextInt();
                    sc.nextLine();
                    if (!utils.CustomerIdPresent(cid)) break;
                    System.out.print("Enter Account Type: ");
                    String type = sc.nextLine();
                    System.out.print("Enter Initial Balance: ");
                    while (!sc.hasNextInt()) {
                        System.out.println("Please enter a valid integer for Initial Balance.");
                        sc.next();
                    }
                    int balance = sc.nextInt();
                    sc.nextLine();
                    Account a = new Account(cid, type, balance);
                    utils.InsertAccount(a);
                    break;

                case 3:
                    System.out.print("Enter Account ID: ");
                    while (!sc.hasNextInt()) {
                        System.out.println("Please enter a valid integer for Account ID.");
                        sc.next();
                    }
                    int depAcc = sc.nextInt();
                    sc.nextLine();
                    if (!utils.AccountIdPresent(Integer.parseInt(String.valueOf(depAcc)))) break;
                    System.out.print("Enter Amount to Deposit: ");
                    while (!sc.hasNextInt()) {
                        System.out.println("Please enter a valid integer for deposit amount.");
                        sc.next();
                    }
                    int depAmt = sc.nextInt();
                    sc.nextLine();
                    Transation depTrans = new Transation("deposit", depAmt, depAcc);
                    int depId = utils.InsertTransaction(depTrans);
                    utils.Credit(depAcc, depId);
                    break;

                case 4:
                    System.out.print("Enter Account ID: ");
                    while (!sc.hasNextInt()) {
                        System.out.println("Please enter a valid integer for Account ID.");
                        sc.next();
                    }
                    int wdAcc = sc.nextInt();
                    sc.nextLine();
                    if (!utils.AccountIdPresent(Integer.parseInt(String.valueOf(wdAcc)))) {
                        System.out.println("Account ID not found. Please try again.");
                        break;
                    }
                    System.out.print("Enter Amount to Withdraw: ");
                    while (!sc.hasNextInt()) {
                        System.out.println("Please enter a valid integer for withdrawal amount.");
                        sc.next();
                    }
                    int wdAmt = sc.nextInt();
                    sc.nextLine();
                    if (wdAmt <= 0) {
                        System.out.println("Withdrawal amount must be greater than zero.");
                        break;
                    }
                    Transation wdTrans = new Transation("withdraw", wdAmt, wdAcc);
                    int wdId = utils.InsertTransaction(wdTrans);
                    if (utils.ValidateAmount(wdAcc, wdId)) {
                        utils.Debit(wdAcc, wdId);
                        System.out.println("Withdrawal successful.");
                    } else {
                        utils.FailedTransaction(wdId);
                        System.out.println("Insufficient balance.");
                    }
                    break;

                case 5:
                    System.out.print("Enter Account ID: ");
                    while (!sc.hasNextInt()) {
                        System.out.println("Please enter a valid integer for Account ID.");
                        sc.next();
                    }
                    int accId = sc.nextInt();
                    sc.nextLine();
                    if (!utils.AccountIdPresent(Integer.parseInt(String.valueOf(accId)))) {
                        System.out.println("Account ID not found.");
                        break;
                    }
                    utils.Showbalance(accId);
                    break;

                case 6:
                    System.out.println("👋 Thank you for using the banking system.");
                    break;

                default:
                    System.out.println("❌ Invalid option.");
            }

        } while (option != 6);

        sc.close();
    }
}
