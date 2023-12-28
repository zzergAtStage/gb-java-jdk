package com.zergatstage.seminars.s04.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor
public class Storage {
    private List<Person> staff = new ArrayList<>();
    private Map<Integer, Person> phoneBook = new HashMap<>();
}
