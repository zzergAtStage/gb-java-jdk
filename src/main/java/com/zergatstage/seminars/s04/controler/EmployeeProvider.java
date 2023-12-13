package com.zergatstage.seminars.s04.controler;

import com.zergatstage.seminars.s04.model.Employee;
import com.zergatstage.seminars.s04.model.Person;
import com.zergatstage.seminars.s04.model.Storage;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EmployeeProvider implements PersonProvider{
    Storage storage;

    public EmployeeProvider(){
        this.storage = new Storage();
    }

    @Override
    public List<Person> getPersonById(int personId) {
        return this.storage.getStaff().stream()
                .filter(p -> p.getId() == personId)
                .collect(Collectors.toList());
    }

    public void addEmployee(Employee employee, int phonenumber){
        if (employee == null || phonenumber == 0)
            throw new IllegalArgumentException("Fields Employee and phone number can't be null");
        this.storage.getPhoneBook().put(phonenumber, employee);
        this.storage.getStaff().add(employee);
    }

    public List<Person> getEmployeeByTenure(int tenure){
        return this.storage.getStaff().stream()
                .filter(person  -> person instanceof Employee &&
                        ((Employee)person).getEmployeeTenure() == tenure)
                .collect(Collectors.toList());
    }

    public List<Person> getEmployeePhoneNumberByName(String name){
        return this.storage.getPhoneBook().entrySet().stream()
                .filter(entry -> entry.getValue().getName().equalsIgnoreCase(name))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    public List<Person> getEmployeeByPersonnelNumber(int employeePersonnelNumber){
        return this.storage.getStaff().stream()
                .filter( person  -> person instanceof Employee &&
                        ((Employee)person).getPersonnelNumber() == employeePersonnelNumber)
                .collect(Collectors.toList());
    }
}
