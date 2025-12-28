# Design Notes – LearnTrack Project

## Why ArrayList Instead of Array?
In this project, `ArrayList` is used instead of arrays because the number of students, courses, and enrollments is not fixed.
`ArrayList` allows dynamic resizing, easy addition and removal of elements, and provides useful methods like add() and iteration using loops.
Using arrays would require manual resizing, which makes the code more complex and less readable.

---

## Use of Static Members
Static members are used in the `IdGenerator` utility class.
Static variables like studentIdCounter, courseIdCounter, and enrollmentIdCounter ensure that IDs are unique across the entire application.
Static methods are used so that ID generation does not require creating an object of `IdGenerator`.
This is appropriate because ID generation is a global utility responsibility.

---

## Use of Inheritance
Inheritance is implemented using a base class `Person` and a derived class `Student`.
Common fields such as id, firstName, lastName, and email are placed in the `Person` class.
The `Student` class extends `Person` and adds student-specific fields like batch and active.
This reduces code duplication and improves maintainability.

---

## Benefits of Method Overriding
Method overriding is demonstrated by overriding the `getDisplayName()` method in the `Student` class.
This allows student-specific behavior while still using the base class method via `super`.
It helps demonstrate polymorphism and makes the code more flexible for future extensions (e.g., Trainer class).

---

## Separation of Concerns
The project follows a layered structure:
- Entity classes store data
- Service classes handle business logic
- UI (Main.java) handles user interaction
  This separation makes the code easier to read, test, and maintain.
