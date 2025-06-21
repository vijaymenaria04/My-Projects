import java.util.Scanner;

class Employee {
    int id;
    String name;
    double salary;

    Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    void display() {
        System.out.println("ID: " + id + ", Name: " + name + ", Salary: " + salary);
    }
}

public class EmployeeManagementSystem{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Employee[] employees = new Employee[100];
        int count = 0;
        int choice;

        System.out.println("\nWelcome to Employee Management");

        do {
            System.out.println("\n1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            if (choice == 1) {
                System.out.print("Enter ID: ");
                int id = sc.nextInt();
                sc.nextLine();
                System.out.print("Enter Name: ");
                String name = sc.nextLine();
                System.out.print("Enter Salary: ");
                double salary = sc.nextDouble();

                employees[count] = new Employee(id, name, salary);
                count++;
                System.out.println("Employee added successfully.");
            } else if (choice == 2) {
                System.out.println("\nEmployee List:");
                for (int i = 0; i < count; i++) {
                    employees[i].display();
                }
            } else if (choice == 3) {
                System.out.println("Exiting Employee Management System...");
            } else {
                System.out.println("Invalid choice");
            }

        } while (choice != 3);
    }
}
