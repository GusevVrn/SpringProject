package ru.myproject.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class Person {
    private int id;

    @NotEmpty(message = "FIO shouldn't be empty")
    @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+ [A-Z]\\w+", message = "FIO should be: Name Surname Secondname")
    private String fio;

    @Min(value = 7, message = "Age should be more than 7")
    private int age;

    public Person() {
    }

    public Person(int id, String fio, int age) {
        this.id = id;
        this.fio = fio;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
