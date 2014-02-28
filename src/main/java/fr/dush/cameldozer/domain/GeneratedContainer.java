package fr.dush.cameldozer.domain;

import java.util.ArrayList;
import java.util.List;

public class GeneratedContainer {

    private List<Person> persons = new ArrayList<>();

    public class Person {

        private String name;

        private int age;

        private String employer;

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

        public String getEmployer() {
            return employer;
        }

        public void setEmployer(String employer) {
            this.employer = employer;
        }
    }
}
