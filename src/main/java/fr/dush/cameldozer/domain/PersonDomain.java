package fr.dush.cameldozer.domain;

/** Person representation of external use */
public class PersonDomain {

    private String name;

    private int age;

    private String employer;

    public PersonDomain() {}

    public PersonDomain(String name, int age, String employer) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof PersonDomain))
            return false;

        PersonDomain that = (PersonDomain) o;

        if (age != that.age)
            return false;
        if (employer != null ? !employer.equals(that.employer) : that.employer != null)
            return false;
        if (name != null ? !name.equals(that.name) : that.name != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + age;
        result = 31 * result + (employer != null ? employer.hashCode() : 0);
        return result;
    }
}
