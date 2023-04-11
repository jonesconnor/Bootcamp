package com.cgi;

import com.cgi.sorting.Student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Student student = new Student("Alex",88);
        Student student1 = new Student("Bob",90);
        Student student2 = new Student("Joe",78);

        List<Student> studentList = new ArrayList<>();
        studentList.add(student);
        studentList.add(student1);
        studentList.add(student2);
//        for(Object val: studentList) {
//            System.out.println(val);
//        }
        ListIterator iterator =  studentList.listIterator();
        while ((iterator.hasNext())){
        System.out.println(iterator.next());
    }
        Collections.sort(studentList);
        System.out.println("Values after the sorting ");
        ListIterator iterator1 =  studentList.listIterator();
        while ((iterator1.hasNext())) {
            System.out.println(iterator1.next());
        }
    }
}
