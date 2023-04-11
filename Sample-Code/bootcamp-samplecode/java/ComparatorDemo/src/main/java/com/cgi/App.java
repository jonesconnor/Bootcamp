package com.cgi;

import com.cgi.sorting.MarkCompare;
import com.cgi.sorting.NameCompare;
import com.cgi.sorting.Student;

import java.util.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//        Student student = new Student("Alex",88);
//        Student student1 = new Student("Bob",79);
//        Student student2 = new Student("Joe",90);
//
//        List<Student> studentList = new ArrayList<>();
//        studentList.add(student);
//        studentList.add(student1);
//        studentList.add(student2);
//        for(Object val: studentList){
//            System.out.println(val);
//        }
//        Collections.sort(studentList,  new MarkCompare());
//        System.out.println("value after the sorting ");
//        ListIterator  iterator = studentList.listIterator();
//        while (iterator.hasNext()){
//            System.out.println(iterator.next());
//        }
//
//        System.out.println("------------------------------------------------------");
//        Collections.sort(studentList, new NameCompare());
//        System.out.println("value after the sorting ");
//        ListIterator  iterator1 = studentList.listIterator();
//        while (iterator1.hasNext()){
//            System.out.println(iterator1.next());
//        }


         int arr[] = {10,20,15,33,35};

//          int search = 15;
//        System.out.println(Arrays.binarySearch(arr,search));
        System.out.println(Arrays.toString(Arrays.copyOf(arr,3)));



    }
}
