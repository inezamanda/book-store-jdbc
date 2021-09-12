package com.enigma.bookstore;

import com.enigma.bookstore.book.BookDAO;
import com.enigma.bookstore.member.MemberDAO;
import com.enigma.bookstore.transaction.TransactionDAO;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void displayMenu() {
        System.out.println("============================");
        System.out.println("Choose one of these options : ");
        System.out.println("1. Member\t 2. Book\n3. Purchase\t 4. Quit");
        System.out.print("Selection : ");
    }

    public static void displayQueryMenu() {
        System.out.println("============================");
        System.out.println("Choose one of these options : ");
        System.out.println("1. All\t 2. Search\t3. Insert\t4. Update\t5. Delete");
        System.out.print("Selection : ");
    }

    public static void main(String[] args) throws SQLException {
        MemberDAO memberDAO = new MemberDAO();
        BookDAO bookDAO = new BookDAO();
        TransactionDAO transactionDAO = new TransactionDAO();

        System.out.println("WELCOME TO ENIGMA BOOK STORE");

        Scanner scanner = new Scanner(System.in);

        displayMenu();
        while (true){
            switch (scanner.nextInt()) {
                case 1:
                    displayQueryMenu();
                    switch (scanner.nextInt()) {
                        case 1 :
                            System.out.println(memberDAO.getAllMember());
                            displayMenu();
                            break;
                        case 2 :
                            System.out.println("Input member id : ");
                            Integer memberId = scanner.nextInt();
                            memberDAO.getMemberById(memberId);
                            displayMenu();
                            break;
                        case 3 :
                            System.out.print("Input first name : ");
                            String firstName = scanner.next();
                            System.out.print("Input last name : ");
                            String lastName = scanner.next();
                            System.out.print("Input email : ");
                            String email = scanner.next();
                            System.out.print("Input username : ");
                            String username = scanner.next();
                            System.out.print("Input password : ");
                            String password = scanner.next();
                            memberDAO.insertMember(firstName, lastName, email, username, password);
                            displayMenu();
                            break;
                        case 4 :
                            System.out.print("Insert id of desired data : ");
                            Integer updateMemberId = scanner.nextInt();
                            System.out.print("Insert new first name : ");
                            String newFirstName = scanner.next();
                            memberDAO.updateMemberById(updateMemberId, newFirstName);
                            displayMenu();
                            break;
                        case 5 :
                            System.out.print("Insert id of desired data : ");
                            Integer deleteMemberId = scanner.nextInt();
                            memberDAO.deleteMemberById(deleteMemberId);
                            displayMenu();
                            break;
                    }
                    break;
                case 2:
                    displayQueryMenu();
                    switch (scanner.nextInt()) {
                        case 1 :
                            System.out.println(bookDAO.getAllBook());
                            displayMenu();
                            break;
                        case 2 :
                            System.out.print("Input book id : ");
                            Integer bookId = scanner.nextInt();
                            bookDAO.getBookById(bookId);
                            displayMenu();
                            break;
                        case 3 :
                            System.out.print("Input title : ");
                            String title = scanner.nextLine();
                            scanner.nextLine();
                            System.out.print("Input description : ");
                            String description = scanner.nextLine();
                            System.out.print("Input publisher : ");
                            String publisher = scanner.nextLine();
                            System.out.print("Input year : ");
                            Integer year = scanner.nextInt();
                            System.out.print("Input page : ");
                            Integer page = scanner.nextInt();
                            System.out.print("Input language : ");
                            String language = scanner.nextLine();
                            scanner.nextLine();
                            System.out.print("Input stock : ");
                            Integer stock = scanner.nextInt();
                            System.out.print("Input price : ");
                            Integer price = scanner.nextInt();
                            bookDAO.insertBook(title, description, publisher, year, page, language, stock, price);
                            displayMenu();
                            break;
                        case 4 :
                            System.out.print("Insert id of desired data : ");
                            Integer updateBookId = scanner.nextInt();
                            System.out.print("Insert new title : ");
                            String newTitle = scanner.next();
                            bookDAO.updateBookById(updateBookId, newTitle);
                            displayMenu();
                            break;
                        case 5 :
                            System.out.print("Insert id of desired data : ");
                            Integer deleteBookId = scanner.nextInt();
                            bookDAO.deleteBookById(deleteBookId);
                            displayMenu();
                            break;
                    }
                    break;
                case 3 :
                    System.out.print("Transaction date : ");
                    String transactionDate = scanner.next();
                    System.out.print("Insert member id : ");
                    Integer memberId = scanner.nextInt();
                    System.out.print("Insert book id : ");
                    Integer bookId = scanner.nextInt();
                    System.out.print("How many books do you want? ");
                    Integer qty = scanner.nextInt();
                    transactionDAO.purchasingBook(Date.valueOf(transactionDate), memberId, bookId, qty);
                    displayMenu();
                    break;
                case 4 :
                    System.out.print("Exit the program");
                    System.exit(0);
                default:
                    System.out.println("Option not available");
                    displayMenu();
                    break;
            }
        }
    }
}
