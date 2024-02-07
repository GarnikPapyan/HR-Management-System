package org.example;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.InputMismatchException;
import java.util.Scanner;

public class EmployeeService {
    /**This method is for creating Employee */
    public void createEmployee(Session session) {
        Transaction transaction = null;
        Scanner scanner = new Scanner(System.in);
        try {
            transaction = session.beginTransaction();
            Employee employee = new Employee();
            System.out.println("Enter employee name");
            String name = scanner.nextLine();
            employee.setName(name);
            System.out.println("Enter employee email");
            String email = scanner.nextLine();
            employee.setEmail(email);
            System.out.println("Enter employee phoneNumber");
            String phoneNumber =scanner.nextLine();
            employee.setPhoneNumber(phoneNumber);
            System.out.println("Enter employee hireDate` DD-MM-YYYY");
            String hireDate = scanner.nextLine();
            employee.setHireDate(hireDate);
            System.out.println("Enter employee jobTitle");
            String jobTitle = scanner.nextLine();
            employee.setJobTitle(jobTitle);
            session.flush();
            session.persist(employee);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error " + e.getMessage());
        }
    }
    /**This method is for update Employee */
    public void updateEmployee(Session session) {
        Transaction transaction = null;
        Scanner scanner = new Scanner(System.in);
        try{
            transaction = session.beginTransaction();
            System.out.println("Enter that ID who you want update ");
            Long employeeId = scanner.nextLong();
            scanner.nextLine();
            Employee employee =session.get(Employee.class,employeeId);
            boolean out = false;
            while (!out) {
                if(employee!=null) {
                    System.out.println("Enter employee new name");
                    String name = scanner.nextLine();
                    employee.setName(name);
                    System.out.println("Enter employee new email");
                    String email = scanner.nextLine();
                    employee.setEmail(email);
                    System.out.println("Enter employee new phoneNumber");
                    String phoneNumber =scanner.nextLine();
                    employee.setPhoneNumber(phoneNumber);
                    System.out.println("Enter employee new hireDate` DD-MM-YYYY");
                    String hireDate = scanner.nextLine();
                    employee.setHireDate(hireDate);
                    System.out.println("Enter employee new jobTitle");
                    String jobTitle = scanner.nextLine();
                    employee.setJobTitle(jobTitle);
                    session.persist(employee);
                    out = true;
                    transaction.commit();
                } else {
                    System.out.println("Not valid ID plz try again ");
                }
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error " + e.getMessage());
        }
    }
    /**This method is for deleting Employee */
    public void deleteEmployee(Session session) {
        Transaction transaction = session.beginTransaction();
        Scanner scanner = new Scanner(System.in);
        boolean out = false;
        System.out.println("Enter the employee ID then you want delete ");
        while (!out) {
            try {
                Long employeeId = scanner.nextLong();
                Employee employee = session.get(Employee.class,employeeId);
                    if(employee!=null) {
                        session.remove(employee);
                        System.out.println("Deleted was successful.");
                        transaction.commit();
                        out = true;
                    } else {
                        System.out.println("Plz enter the valid employee ID ");
                    }

            } catch (InputMismatchException e) {
                scanner.next();
                System.out.println("Input only number from ID ");
            }
        }

    }
}
