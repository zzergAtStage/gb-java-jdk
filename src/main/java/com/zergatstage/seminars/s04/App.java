package com.zergatstage.seminars.s04;

import com.zergatstage.seminars.s04.controler.EmployeeProvider;
import com.zergatstage.seminars.s04.model.Employee;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class App {
    public static void main(String[] args) {
        //task01();
        //Homework s04 Collections
        //Create an employee provider
        EmployeeProvider easyStaffCompany = new EmployeeProvider();
        //init new Employee
        Employee staffPerson1 = new Employee("Snegko Belic", 01, "Snegovik", new Date(1990,1,1) );
        Employee staffPerson2 = new Employee("Bake Jaga", 02, "Ved'ma", new Date(2002,12,31));

        int snegovikPhone = 999;
        int storagePhone = 20112;
        //add to database
        easyStaffCompany.addEmployee(staffPerson1, snegovikPhone);
        easyStaffCompany.addEmployee(staffPerson2,storagePhone);

        //test
        System.out.printf("Get person by tenure: %s\n",easyStaffCompany.getEmployeeByTenure(21) );
        System.out.printf("Get phone by person name(Snegko Belic): %s",easyStaffCompany.getEmployeePhoneNumberByName("Snegko Belic"));



    }

    private static void task01() {
        List<String> names = new ArrayList<>();
        initArrayNames(names);
        System.out.println(names);
        names.sort(String::compareTo);
        System.out.println(names);
        names.sort(Comparator.comparingInt(String::length));
        System.out.println(names);

    }

    private static void initArrayNames(List<String> names) {
        names.add("Frank");
        names.add("Alice");
        names.add("David");
        names.add("Brian");
        names.add("Claire");
        names.add("Emma");
        names.add("Grace");
        names.add("Henry");
        names.add("Isabel");
        names.add("Jack");
        names.add("Katie");
        names.add("Liam");
        names.add("Molly");
        names.add("Nathan");
        names.add("Olivia");
        names.add("Peter");
        names.add("Rachel");
        names.add("Samuel");
        names.add("Tara");
        names.add("Victor");
    }
}
