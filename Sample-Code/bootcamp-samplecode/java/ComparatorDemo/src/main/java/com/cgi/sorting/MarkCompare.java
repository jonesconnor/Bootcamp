package com.cgi.sorting;

import java.util.Comparator;

public class MarkCompare implements Comparator {

    @Override
    public int compare(Object obj1, Object obj2) {
        Student student = (Student) obj1;
        Student student1 = (Student) obj2;
        if(student.getMarks()> student1.getMarks()){
            return 1;
        }
        else if(student.getMarks()< student1.getMarks()){
            return -1;
        }
        else {
            return 0;
        }
    }
}
