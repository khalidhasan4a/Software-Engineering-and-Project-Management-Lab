import java.util.Scanner;

public class Student {
    int age;                       // instance variable -> stored inside the object on the Heap

    Student(int age) {
        this.age = age;
    }

    void display(Scanner sc) {
        System.out.print("Marks: ");
        int marks = sc.nextInt();  // local variable -> stored in display()'s Stack frame
        System.out.println("Instance Variable (age): " + age);
        System.out.println("Local Variable (marks): " + marks);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Age: ");
        int age = sc.nextInt();    // local variable of main() -> Stack

        Student s = new Student(age);   // object -> Heap, reference 's' -> Stack
        System.out.println("Student Object Created");
        System.out.println("Object Identity Hash Code: " + System.identityHashCode(s));
        s.display(sc);
        sc.close();
    }
}
