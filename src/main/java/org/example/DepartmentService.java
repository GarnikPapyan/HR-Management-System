package org.example;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

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
            System.out.println("Enter department head ");
            Long departmentHead = scanner.nextLong();
            ManagerService managerService = new ManagerService();
            transaction.commit();
            managerService.createManager(session);
            Manager departmentHeadName  = session.get(Manager.class,departmentHead);
            session.persist(departmentHeadName);
            department.setDepartmentHead(departmentHeadName);
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
}
