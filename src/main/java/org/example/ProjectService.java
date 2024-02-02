package org.example;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ProjectService {
    public void createProject(Session session) {
        Transaction transaction = null;
        Scanner scanner = new Scanner(System.in);
        try {
            transaction = session.beginTransaction();
            Project project = new Project();
            System.out.println("Enter the project name ");
            String projectName = scanner.nextLine();
            project.setProjectName(projectName);
            System.out.println("Enter project start date ");
            String startDate = scanner.nextLine();
            project.setStartDate(startDate);
            System.out.println("Enter final date ");
            String endDate = scanner.nextLine();
            project.setEndDate(endDate);
            System.out.println("Enter the project budget ");
            boolean out = false;
            while (!out) {
                try {
                    Double budget = scanner.nextDouble();
                    project.setBudget(budget);
                    out = true;
                } catch (InputMismatchException e) {
                    System.out.println("Plz enter only number` x,y ");
                    scanner.next();
                }
            }
            session.persist(project);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error " + e.getMessage());
        }
    }
}
