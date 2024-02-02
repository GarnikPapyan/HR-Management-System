package org.example;


import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {

        SessionFactory sessionFactory = HibernateConfig.getSessionFactory();
        Session session = sessionFactory.openSession();

        EmployeeService employeeService = new EmployeeService();
       // employeeService.createEmployee(session);
       // employeeService.updateEmployee(session);
       // employeeService.deleteEmployee(session);
//        employeeService.createEmployee(session);
        ManagerService managerService = new ManagerService();
      managerService.createManager(session);
      //  managerService.updateManager(session);
//        DepartmentService departmentService = new DepartmentService();
//       departmentService.createDepartment(session);
//        ProjectService projectService = new ProjectService();
//        projectService.createProject(session);
        System.out.println("Hello world!");
        session.close();
    }
}