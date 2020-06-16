package com.easycopy;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author pankaj
 * @version 1.0
 * @since 2020-06-07
 */
public class RandD {
    public static void main(String[] args) {

        List<Emp> list = new ArrayList<>();

        list.add(new Emp("A", 10));
        list.add(new Emp("B", 18));
        list.add(new Emp("C", 8));
        list.add(new Emp("D", 28));
        list.add(new Emp("E", 3));
        list.add(new Emp("F", 15));

        //Given a list of employee, find maximum age of employee?

        List<Integer> l=list.stream().map(emp -> emp.getAge()).collect(Collectors.toList());

        //Join the all employee names with “,” using java 8?

        //Given the list of employee, group them by employee name?

        //Given the list of numbers, remove the duplicate elements from the list.

        List<Integer> l1= new ArrayList();

        //Given a list of numbers, square them and filter the numbers which are greater 10000 and then find average of them.( Java 8 APIs only)

    }


    public static class Emp {
        String name;
        int age;

        public Emp(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
