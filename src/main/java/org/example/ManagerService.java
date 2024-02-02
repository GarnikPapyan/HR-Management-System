package org.example;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ManagerService {
    public void createManager(Session session) {
        Transaction transaction = null;
        Scanner scanner = new Scanner(System.in);
        try {
            transaction = session.beginTransaction();
            Manager manager = new Manager();
            System.out.println("Enter manager name");
            String name = scanner.nextLine();
            manager.setName(name);
            System.out.println("Enter manager email");
            String email = scanner.nextLine();
            manager.setEmail(email);
            System.out.println("Enter manager phoneNumber");
            String phoneNumber =scanner.nextLine();
            manager.setPhoneNumber(phoneNumber);
            System.out.println("Enter manager hireDate` DD-MM-YYYY");
            String hireDate = scanner.nextLine();
            manager.setHireDate(hireDate);
            System.out.println("Enter manager jobTitle");
            String jobTitle = scanner.nextLine();
            manager.setJobTitle(jobTitle);
            boolean out = false;
            System.out.println("Enter management level Top level -> 0, Mid level -> 1, First level -> 2");
            while (!out) {
                String myCase = scanner.nextLine();
                switch(myCase){
                    case "0" -> {
                        manager.setManagementLevel(ManagementLevel.TOP_LEVEL);
                        out = true;
                    }
                    case "1" -> {
                        manager.setManagementLevel(ManagementLevel.MID_LEVEL);
                        out = true;
                    }
                    case "2" -> {
                        manager.setManagementLevel(ManagementLevel.FIRST_LINE);
                        out = true;
                    }
                    default -> System.out.println("Plz enter the valid choice -> 1 , 2  or 0");
                }
            }
            out = false;
            System.out.println("Enter Managed Department ");
            while (!out) {
                try {
                    Long managedDepartmentId = scanner.nextLong();
                    Department managedDepartment = new Department();
                    managedDepartment.setDepartmentId(managedDepartmentId);
                    manager.setManagedDepartment(managedDepartment);
                    session.persist(manager);
                    transaction.commit();
                    out = true;
                    System.out.println("nice work!!!");
                } catch (InputMismatchException e) {
                    System.out.println("plz enter only number ID from department ");
                    scanner.next();
                }
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error " + e.getMessage());
        }
    }
    public void updateManager(Session session) {
        Transaction transaction = null;
        Scanner scanner = new Scanner(System.in);
        boolean out = false;
        while (!out) {
            transaction = session.beginTransaction();
            try {
                System.out.println("enter the ID manager when you want update ");
                Long employeeId = scanner.nextLong();
                scanner.nextLine();
                Manager manager = session.get(Manager.class,employeeId);
                if(manager!=null) {
                    System.out.println("Enter manager  new name");
                    String name = scanner.nextLine();
                    manager.setName(name);
                    System.out.println("Enter manager  new email");
                    String email = scanner.nextLine();
                    manager.setEmail(email);
                    System.out.println("Enter manager new phoneNumber");
                    String phoneNumber =scanner.nextLine();
                    manager.setPhoneNumber(phoneNumber);
                    System.out.println("Enter manager new hireDate` DD-MM-YYYY");
                    String hireDate = scanner.nextLine();
                    manager.setHireDate(hireDate);
                    System.out.println("Enter manager new jobTitle");
                    String jobTitle = scanner.nextLine();
                    manager.setJobTitle(jobTitle);
                    System.out.println("Enter management new level Top level -> 0, Mid level -> 1, First level -> 2");
                    while (!out) {
                        String myCase = scanner.nextLine();
                        switch(myCase){
                            case "0" -> {
                                manager.setManagementLevel(ManagementLevel.TOP_LEVEL);
                                out = true;
                            }
                            case "1" -> {
                                manager.setManagementLevel(ManagementLevel.MID_LEVEL);
                                out = true;
                            }
                            case "2" -> {
                                manager.setManagementLevel(ManagementLevel.FIRST_LINE);
                                out = true;
                            }
                            default -> System.out.println("Plz enter the valid choice -> 1 , 2  or 0");
                        }

                    }
                    session.persist(manager);
                }else {
                    System.out.println("plz enter the valid ID");
                }
            } catch (InputMismatchException e) {
                System.out.println("plz enter the valid ID");
            }

        }
        transaction.commit();
    }
    public void deleteManager(Session session) {
        Transaction transaction = null;
        Scanner scanner = new Scanner(System.in);
        try {
            transaction = session.beginTransaction();
            System.out.println("Enter the manager ID then you want delete ");
            Long employeeId = scanner.nextLong();
            Manager manager = session.get(Manager.class,employeeId);
            boolean out = false;
            while (!out) {
                if(manager!=null) {
                    session.remove(manager);
                    System.out.println("Deleted was successful.");
                    transaction.commit();
                    out = true;
                } else {
                    System.out.println("Plz enter the valid employee ID ");
                }
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error " + e.getMessage());
        }
    }

}
