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
            session.persist(manager);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error " + e.getMessage());
        }
    }
   //*********************************
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
        Transaction transaction = session.beginTransaction();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the manager ID then you want delete ");
        boolean out = false;
        while (!out) {
            try {
                Long employeeId = scanner.nextLong();
                Manager manager = session.get(Manager.class,employeeId);

                    if(manager!=null) {
                        session.remove(manager);
                        System.out.println("Deleted was successful.");
                        transaction.commit();
                        out = true;
                    } else {
                        System.out.println("Plz enter the valid employee ID ");
                    }
            } catch (InputMismatchException e) {
                scanner.next();
                System.out.println("plz enter only number from ID");
            }
        }

    }

    public void assignEmployeesToProjects(Session session) {
        Transaction transaction = session.beginTransaction();
        Scanner scanner = new Scanner(System.in);
        boolean out = false;
        while (!out) {
            try {
                while (!out) {
                    System.out.println("Enter Manager ID which you want add to Departments ");
                    Long managerId = scanner.nextLong();
                    Manager manager = session.get(Manager.class,managerId);
                    if(manager!=null) {
                        System.out.println("End now go to the assign department ");
                        Long departmentId = scanner.nextLong();
                        Department department = session.get(Department.class,departmentId);
                        if(department!=null) {
                            manager.setDepartment(department);
                            manager.setManagedDepartment(department);
                            session.persist(department);
                            transaction.commit();
                            out = true;
                            System.out.println("Assigned was successfully ");
                        } else {
                            System.out.println("Plz enter valid Id from Department ");
                        }
                    } else {
                        System.out.println("Plz enter valid Id from Manager ");
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Plz enter only Number");
                scanner.next();
            }
        }
    }

    public void ReAssignEmployeesToProjects(Session session) {
        Transaction transaction = session.beginTransaction();
        Scanner scanner = new Scanner(System.in);
        boolean out = false;
        while (!out) {
            try{
                while (!out) {
                    System.out.println("Enter Manager ID which you want delete to Departments ");
                    Long managerId = scanner.nextLong();
                    Manager manager = session.get(Manager.class,managerId);
                    if(manager!=null) {
                        System.out.println("End now go to the reassign department ");
                        Long departmentId = scanner.nextLong();
                        Department department = session.get(Department.class,departmentId);
                        if(department!=null) {
                            manager.getDepartment().getEmployees().remove(manager);
                            manager.setDepartment(department);
                            department.getEmployees().add(manager);
                            manager.setManagedDepartment(department);
                            session.persist(department);
                            transaction.commit();
                            out = true;
                            System.out.println("Reassigned was successfully ");
                        } else {
                            System.out.println("Plz enter valid Id from Department ");
                        }
                    } else {
                        System.out.println("Plz enter valid Id from Manager ");
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Plz enter only number!! ");
                scanner.next();
            }
        }
        }
    }


