package com.zergatstage.seminars.s04.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

public class Employee extends Person{
    @Getter
    @Setter
    private String position;
    @Getter
    private int personnelNumber;
    @Getter
    private Date startWorkingDate;
    public Employee(String name, int personnelNumber, String position, Date startWorkingDate) {
        super(name);
        this.personnelNumber = personnelNumber;
        this.position = position;
        this.startWorkingDate = startWorkingDate;
    }

    public int getEmployeeTenure(){
        return DateTimeUtils.getDiffYears(this.startWorkingDate, new Date());
    }

    @Override
    public String toString() {
        return "Employee{" +
                "position='" + position + '\'' +
                ", personnelNumber=" + personnelNumber +
                ", startWorkingDate=" + startWorkingDate +
                "} " + super.toString();
    }
}
