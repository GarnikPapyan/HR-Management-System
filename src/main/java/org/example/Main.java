package org.example;


import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        SessionFactory sessionFactory = HibernateConfig.getSessionFactory();
        Session session = sessionFactory.openSession();
        Scanner scanner = new Scanner(System.in);
        EmployeeService employeeService = new EmployeeService();
        DepartmentService departmentService = new DepartmentService();
        ManagerService managerService = new ManagerService();
        ProjectService projectService = new ProjectService();
        boolean out = false;

        while (!out) {
            System.out.println("Enter choice:");
            System.out.println("1. Create Employee");
            System.out.println("2. Update Employee");
            System.out.println("3. Delete Employee");
            System.out.println("4. Create Manager");
            System.out.println("5. Update Manager");
            System.out.println("6. Delete Manager");
            System.out.println("7. Create Department");
            System.out.println("8. Update Department");
            System.out.println("9. Delete Department");
            System.out.println("10. Assign employees to departments ");
            System.out.println("11. Reassign employees to departments ");
            System.out.println("12. Create Project ");
            System.out.println("13. Update Project ");
            System.out.println("14. Delete Project ");
            System.out.println("14. Delete Project ");
            System.out.println("15. Assign employees to projects ");
            System.out.println("16. Reassign employees to projects ");
            System.out.println("17. Assign Departments to managers ");
            System.out.println("18. Reassign Departments to managers ");

            System.out.println("0. Exit");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1"-> employeeService.createEmployee(session);
                case "2"-> employeeService.updateEmployee(session);
                case "3"-> employeeService.deleteEmployee(session);
                case "4"-> managerService.createManager(session);
                case "5"-> managerService.updateManager(session);
                case "6"-> managerService.deleteManager(session);
                case "7"-> departmentService.createDepartment(session);
                case "8"-> departmentService.updateDepartment(session);
                case "9"-> departmentService.deleteDepartment(session);
                case "10"-> departmentService.assignEmployeesToDepartments(session);
                case "11"-> departmentService.reassignEmployeeToDepartment(session);
                case "12"-> projectService.createProject(session);
                case "13"-> projectService.updateProject(session);
                case "14"-> projectService.deleteProject(session);
                case "15"-> projectService.assignEmployeesToProjects(session);
                case "16"-> projectService.reAssignEmployeesToProjects(session);
                case "17"-> managerService.assignEmployeesToProjects(session);
                case "18"-> managerService.ReAssignEmployeesToProjects(session);


                case "0"-> out = true;
                default->  System.out.println("plz enter valid choice.");
            }
        }
        session.close();
    }
}