package com.zergatstage.seminars.s02;

import java.util.ArrayList;

public class HRTeamCreator {
    public static void main(String[] args) {
        ArrayList<Developer> developers = new ArrayList<>();
        FullstackDeveloper fullStackDeveloper = new FullstackDeveloper();
        FrontendDeveloperInteface fdevTrue = formsDescription -> System.out.println("Form is ready");
        BackenderInterface backender = servers -> System.out.println("All servers are ready");
        Developer fdev = new FullstackDeveloper();
        developers.add(fdev);
        developers.add(fullStackDeveloper);
        developers.add(fdevTrue);
        developers.add(backender);
        for (Developer dev :
                developers) {
            if (dev instanceof FrontendDeveloperInteface) ((FrontendDeveloperInteface) dev).createForms("True crime form!");
        }

        fullStackDeveloper.createForms("Formo-shlep");
        fullStackDeveloper.createServerCore("Easy-detuned-server");
    }
}
