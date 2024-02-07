package org.example;


import org.hibernate.Session;
import org.hibernate.Transaction;


import java.util.InputMismatchException;
import java.util.Scanner;


public class DepartmentService {

    public void createDepartment(Session session) {
        Transaction transaction = null;
        Scanner scanner = new Scanner(System.in);
        try {
            transaction = session.beginTransaction();
            Department department = new Department();
            System.out.println("Enter department name ");
            String departmentName = scanner.nextLine();
            department.setDepartmentName(departmentName);
            System.out.println("Enter department location ");
            String location = scanner.nextLine();
            department.setLocation(location);
            session.persist(department);
            transaction.commit();
            System.out.println("Department successfully created!");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error creating department: " + e.getMessage());
        }
    }

    public void updateDepartment(Session session) {
        Transaction transaction = null;
        Scanner scanner = new Scanner(System.in);
        transaction = session.beginTransaction();
            boolean out = false;
            System.out.println("Enter that ID who you want update ");
            while (!out) {
                try{
                Long departmentId = scanner.nextLong();
                scanner.nextLine();
                Department department =session.get(Department.class,departmentId);
                if(department!=null) {
                    System.out.println("Enter department new name ");
                    String departmentName = scanner.nextLine();
                    department.setDepartmentName(departmentName);
                    System.out.println("Enter department new location ");
                    String departmentLocation = scanner.nextLine();
                    department.setLocation(departmentLocation);
                    session.persist(department);
                    out = true;
                    transaction.commit();
                    System.out.println("Department successfully updated! ");
                } else {
                    System.out.println("Not valid ID plz try again ");
                }
                } catch (InputMismatchException e) {
                    System.out.println("Plz enter only number for ID ");
                    scanner.next();
                }
            }
    }

    public void deleteDepartment(Session session) {
        Transaction transaction = null;
        Scanner scanner =  new Scanner(System.in);
        transaction = session.beginTransaction();
        boolean out = false;
        System.out.println("Enter the ID department which you want delete! ");
        while (!out) {
            try {
                Long departmentId = scanner.nextLong();
                Department department = session.get(Department.class,departmentId);
                if(department!=null) {
                    session.remove(department);
                    System.out.println("Department successfully deleted! ");
                    out =true;
                } else {
                    System.out.println("Enter the valid ID !!! ");
                }
                transaction.commit();
            } catch (InputMismatchException e){
                System.out.println("Plz enter only number from ID ");
                scanner.next();
            }
        }
    }

    public void assignEmployeesToDepartments(Session session) {
        Transaction transaction = null;
        Scanner scanner = new Scanner(System.in);
        transaction = session.beginTransaction();
        boolean out = false;
        while (!out) {
            try {
                System.out.println("Enter Employee ID which you want add to Departments ");
                Long employeeId = scanner.nextLong();
                System.out.println("End now go to the assign department ");
                Long departmentId = scanner.nextLong();
                Employee employee = session.get(Employee.class,employeeId);

                Department department = session.get(Department.class,departmentId);




                employee.setDepartment(department);
                session.persist(department);
                transaction.commit();
                out = true;
                System.out.println("Assigned was successfully ");
            } catch (InputMismatchException e) {
                System.out.println("Plz enter only Number");
                scanner.next();
            }

        }
    }
}
