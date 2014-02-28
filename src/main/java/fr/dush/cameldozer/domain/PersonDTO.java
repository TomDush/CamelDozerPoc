package fr.dush.cameldozer.domain;

/** Intermnal representation of a person */
public class PersonDTO {

    private String name;

    private int age;

    private String employer;

    public PersonDTO(String name, int age, String employer) {
        this.name = name;
        this.age = age;
        this.employer = employer;
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

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }
}
