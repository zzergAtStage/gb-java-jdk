package com.zergatstage.seminars.s04.controler;

import com.zergatstage.seminars.s04.model.Person;

import java.util.List;

public interface PersonProvider {
    public List<Person> getPersonById(int personId);
}
