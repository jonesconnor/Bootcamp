package com.cgi.sorting;

public class Student implements Comparable {
    private  String name;
    private int marks;

    public Student(String name, int marks) {
        this.name = name;
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    @Override
    public int compareTo(Object obj) {
        Student student = (Student) obj;
//       if(this.marks>((Student) obj).getMarks())    {
//           return 1;
//       } else if (this.marks < ((Student) obj).getMarks()) {
//           return -1;
//       }
//       else {
//        return 0;
//    }


//        return this.name.compareTo(((Student) obj).getName());
        return  ((Student) obj).getName().compareTo(this.name);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", marks=" + marks +
                '}';
    }
}
