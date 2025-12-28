package com.airtribe.learntrack.ui;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.service.StudentService;
import com.airtribe.learntrack.util.IdGenerator;

import java.util.Scanner;

public class Main {

    private static final StudentService studentService = new StudentService();
    private static final CourseService courseService = new CourseService();
    private static final EnrollmentService enrollmentService = new EnrollmentService();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            showMenu();
            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1 -> addStudent(scanner);
                    case 2 -> listStudents();
                    case 3 -> deactivateStudent(scanner);
                    case 4 -> addCourse(scanner);
                    case 5 -> listCourses();
                    case 6 -> enrollStudent(scanner);
                    case 7 -> viewEnrollments(scanner);
                    case 0 -> running = false;
                    default -> System.out.println("Invalid option. Try again.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            } catch (EntityNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("Application exited.");
    }

    private static void showMenu() {
        System.out.println("\n=== LearnTrack Menu ===");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Deactivate Student");
        System.out.println("4. Add Course");
        System.out.println("5. View All Courses");
        System.out.println("6. Enroll Student in Course");
        System.out.println("7. View Enrollments by Student");
        System.out.println("0. Exit");
        System.out.print("Choose option: ");
    }

    private static void addStudent(Scanner scanner) {
        System.out.print("First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Batch: ");
        String batch = scanner.nextLine();

        Student student = new Student(
                IdGenerator.getNextStudentId(),
                firstName,
                lastName,
                email,
                batch
        );

        studentService.addStudent(student);
        System.out.println("Student added successfully.");
    }

    private static void listStudents() {
        for (Student s : studentService.getAllStudents()) {
            System.out.println(
                    s.getId() + " | " +
                            s.getDisplayName() +
                            " | Active: " + s.isActive()
            );
        }
    }

    private static void deactivateStudent(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        studentService.deactivateStudent(id);
        System.out.println("Student deactivated.");
    }

    private static void addCourse(Scanner scanner) {
        System.out.print("Course Name: ");
        String name = scanner.nextLine();

        System.out.print("Description: ");
        String desc = scanner.nextLine();

        System.out.print("Duration (weeks): ");
        int duration = Integer.parseInt(scanner.nextLine());

        Course course = new Course(
                IdGenerator.getNextCourseId(),
                name,
                desc,
                duration
        );

        courseService.addCourse(course);
        System.out.println("Course added.");
    }

    private static void listCourses() {
        for (Course c : courseService.getAllCourses()) {
            System.out.println(
                    c.getId() + " | " +
                            c.getCourseName() +
                            " | Active: " + c.isActive()
            );
        }
    }

    private static void enrollStudent(Scanner scanner) {
        System.out.print("Student ID: ");
        int studentId = Integer.parseInt(scanner.nextLine());

        System.out.print("Course ID: ");
        int courseId = Integer.parseInt(scanner.nextLine());

        Enrollment enrollment = new Enrollment(
                IdGenerator.getNextEnrollmentId(),
                studentId,
                courseId
        );

        enrollmentService.enrollStudent(enrollment);
        System.out.println("Enrollment successful.");
    }

    private static void viewEnrollments(Scanner scanner) {
        System.out.print("Student ID: ");
        int studentId = Integer.parseInt(scanner.nextLine());

        for (Enrollment e : enrollmentService.getEnrollmentsByStudentId(studentId)) {
            System.out.println(
                    "Enrollment ID: " + e.getId() +
                            " | Course ID: " + e.getCourseId() +
                            " | Status: " + e.getStatus()
            );
        }
    }
}
