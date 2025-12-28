LearnTrack – Student & Course Management System

Project Overview

LearnTrack is a console-based Student & Course Management System built using Core Java.
The project helps practice Java fundamentals, object-oriented programming, collections, and basic exception handling.

Features

Student Management

Add new students
View all students
Search student by ID
Deactivate a student (soft delete)

Course Management

Add new courses
View all courses
Activate / Deactivate courses

Enrollment Management

Enroll a student into a course
View enrollments for a student
Update enrollment status

Technologies Used

Java (Core Java)
JDK 17
IntelliJ IDEA
ArrayList for in-memory storage

How to Run the Project

1.Open the project in IntelliJ IDEA
2.Configure JDK if required
3.Run:
com.airtribe.learntrack.ui.Main

## Class Diagram

The following class diagram shows the relationships between the main classes in the LearnTrack system.

```mermaid
classDiagram
    Person <|-- Student

    class Person {
        int id
        String firstName
        String lastName
        String email
        +getDisplayName()
    }

    class Student {
        String batch
        boolean active
    }

    class Course {
        int id
        String courseName
        String description
        int durationInWeeks
        boolean active
    }

    class Enrollment {
        int id
        int studentId
        int courseId
        LocalDate enrollmentDate
        String status
    }

    StudentService --> Student
    CourseService --> Course
    EnrollmentService --> Enrollment

    Main --> StudentService
    Main --> CourseService
    Main --> EnrollmentService

    IdGenerator ..> Student : generates ID
    IdGenerator ..> Course : generates ID
    IdGenerator ..> Enrollment : generates ID


Author
Kundan Kumar