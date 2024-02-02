package org.example;


import org.hibernate.Session;
import org.hibernate.Transaction;

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
            System.out.println("Mutq ara depi anuny");
            String departmentName = scanner.nextLine();
            department.setDepartmentName(departmentName);
            System.out.println("Mutq ara depi locationy");
            String location = scanner.nextLine();
            department.setLocation(location);
//            System.out.println("mutq ara deparmenti gluxy");
//            String departmentHead = scanner.nextLine();
//            department.setDepartmentHead(departmentHead);
            Set<Employee> employeeSet = new HashSet<>();
            department.setEmployeeSet(employeeSet);
            session.persist(department);
            transaction.commit();
        } catch (Exception e) {
            System.out.printf("dsf" + e);
        }
    }
}
