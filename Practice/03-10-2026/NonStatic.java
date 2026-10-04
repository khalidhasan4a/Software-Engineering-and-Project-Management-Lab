class Student {
    int count = 0;

    Student() {
        count++;
    }
}

public class NonStatic {
    public static void main(String[] args) {
        Student s1 = new Student();
        Student s2 = new Student();
        Student s3 = new Student();

        System.out.println(s1.count);
        System.out.println(s2.count);
        System.out.println(s3.count);



    }
}
