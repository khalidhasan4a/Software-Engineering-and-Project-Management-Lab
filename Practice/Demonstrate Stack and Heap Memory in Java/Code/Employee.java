import java.util.Scanner;

public class Employee {
    int salary;                    // instance field -> lives in the Employee object (Heap)

    Employee(int salary) {
        this.salary = salary;
    }

    void display(Scanner sc) {
        System.out.print("Enter bonus: ");
        final int bonus = sc.nextInt();   // local variable -> display()'s Stack frame

        System.out.println("\nInside display() Method:");
        System.out.println("Salary (Instance Variable): " + salary);
        System.out.println("Bonus (Local Variable): " + bonus);

        // Local inner class declared inside the method
        class Report {
            void show() {              // runs in its own method frame on the Stack
                System.out.println("Report: Employee salary is " + salary);
            }
        }

        Report r = new Report();       // Report object -> Heap
        System.out.println("\nReport Object Created");
        System.out.println("Report Identity Hash Code: " + System.identityHashCode(r));
        r.show();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter salary: ");
        int salary = sc.nextInt();

        Employee e = new Employee(salary);   // object -> Heap, reference 'e' -> Stack
        System.out.println("Employee Object Created");
        System.out.println("Employee Identity Hash Code: " + System.identityHashCode(e));
        e.display(sc);

        System.out.println("\nMemory Concept:");
        System.out.println("Employee object -> Heap");
        System.out.println("Report object -> Heap");
        System.out.println("salary -> Instance field");
        System.out.println("bonus -> Local variable in display()");
        System.out.println("show() -> Executes in its own method frame");
        sc.close();
    }
}
