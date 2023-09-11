package controller;

import domain.Student;
import service.StudentService;

import java.util.Scanner;

public class StudentController{
    public static StudentService studentService;
    public static Scanner longScanner = new Scanner(System.in);
    public static Scanner stringScanner = new Scanner(System.in);

    public static Scanner intScanner = new Scanner(System.in);

    public static Student inputStudentData() {
        System.out.print("Enter ID: ");
        Long id = longScanner.nextLong();
        longScanner.nextLine();

        System.out.print("Enter Name: ");
        String name = stringScanner.nextLine();

        System.out.print("Enter Email: ");
        String email = stringScanner.nextLine();

        System.out.print("Enter Age: ");
        int age = intScanner.nextInt();

        return new Student(id, name, email, age);
    }

    public static void addNewIntern() {
        System.out.println("Adding New domain.Student");
        Student newStudent = inputStudentData();
        studentService.addNewStudent(newStudent);
        System.out.println("domain.Student added successfully.");
    }

    public static void readDataOfStudent() {
        studentService.readDataOfStudent();
    }

    public static void updateDataOfStudent() {
        System.out.print("Enter domain.Student ID to update: ");
        Long id = longScanner.nextLong();
        longScanner.nextLine();
        Student updatedStudent = inputStudentData();
        studentService.updateDataOfStudent(id, updatedStudent.getName(), updatedStudent.getEmail(),  updatedStudent.getAge());
    }

    public static void deleteDataOfStudent() {
        System.out.print("Enter domain.Student ID to delete: ");
        Long id = longScanner.nextLong();
        studentService.deleteStudentById(id);
    }

    public static void mainMenu() {
        Scanner choiceScanner = new Scanner(System.in);

        while (true) {
            System.out.println(" domain.Student Management System ");
            System.out.println("1. Add New domain.Student");
            System.out.println("2. List All Students");
            System.out.println("3. Update Data of domain.Student");
            System.out.println("4. Delete domain.Student");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            int choice = choiceScanner.nextInt();
            choiceScanner.nextLine();

            switch (choice) {
                case 1:
                    addNewIntern();
                    break;
                case 2:
                    readDataOfStudent();
                    break;

                case 3:
                  updateDataOfStudent();
                    break;

                case 4:
                    deleteDataOfStudent();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    choiceScanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }



    public static void main(String[] args) {
        studentService = new StudentService();
        mainMenu();
    }
}



