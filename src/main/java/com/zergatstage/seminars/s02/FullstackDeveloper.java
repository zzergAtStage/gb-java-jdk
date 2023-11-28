package com.zergatstage.seminars.s02;

public class FullstackDeveloper implements FullStackDeveloperInterface{
    @Override
    public void createForms(String formsDescription) {
        System.out.println("All forms are done in " + formsDescription);
    }

    @Override
    public void createServerCore(String servers) {
        System.out.println("A server is done by " + servers);
    }
}
