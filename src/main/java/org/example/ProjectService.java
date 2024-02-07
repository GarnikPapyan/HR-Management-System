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

    public void updateProject(Session session) {
        Transaction transaction = session.beginTransaction();
        Scanner scanner = new Scanner(System.in);
        boolean out = false;
        System.out.println("Enter project Id which you want to delete ");
        while (!out) {
            try {
                Long projectId = scanner.nextLong();
                scanner.nextLine();
                Project project = session.get(Project.class,projectId);
                if(project!=null) {
                    System.out.println("Enter the update project name");
                    String projectName = scanner.nextLine();
                    project.setProjectName(projectName);
                    System.out.println("Update to start time from project ");
                    String projectStarDate = scanner.nextLine();
                    project.setStartDate(projectStarDate);
                    System.out.println("Update to final date from project");
                    String projectEndDate = scanner.nextLine();
                    scanner.nextDouble();
                    project.setEndDate(projectEndDate);
                    System.out.println("Update the budget from project ");
                    Double projectBudget = scanner.nextDouble();
                    project.setBudget(projectBudget);
                    transaction.commit();
                    out = true;
                    System.out.println("Project update was successfully");
                } else {
                    System.out.println("Enter the valid project ID ");
                }
            }catch (InputMismatchException e) {
                System.out.println("Plz enter only number from project ID ");
                scanner.next();
            }
        }
    }

    public void deleteProject(Session session) {
        Transaction transaction = session.beginTransaction();
        Scanner scanner = new Scanner(System.in);
        boolean out = false;
        System.out.println("Enter Id from project which you want delete ");
        while (!out) {
            try {
                Long projectId =  scanner.nextLong();
                Project project = session.get(Project.class,projectId);
                if(project != null) {
                    session.remove(project);
                    System.out.println("Deleted was successful.");
                    transaction.commit();
                    out = true;
                } else  {
                    System.out.println("Enter valid Id for delete ");
                }
            } catch (InputMismatchException e) {
                System.out.println("Plz enter only number for project ID!! ");
                scanner.next();
            }
        }
    }
}
