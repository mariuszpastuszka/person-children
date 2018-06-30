package pl.mpas.parent_children.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Person {
    public static final int DEFAULT_ID = -1;
    private int id = DEFAULT_ID;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String name;
    private String surname;
    private int age;
    private AdultOrChild adultOrChild;
    private Sex sex;
    private Set<Person> children = Collections.emptySet();

    public Person(String name, String surname, int age, AdultOrChild adultOrChild, Sex sex) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.adultOrChild = adultOrChild;
        this.sex = sex;
        children = new HashSet<>();
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public AdultOrChild getAdultOrChild() {
        return adultOrChild;
    }

    public void setAdultOrChild(AdultOrChild adultOrChild) {
        this.adultOrChild = adultOrChild;
    }

    public Set<Person> getChildren() {
        return children;
    }

    public void setChildren(Set<Person> children) {
        this.children = children;
    }

    public void addChild(Person child) {
        children.add(child);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", adultOrChild=" + adultOrChild +
                ", sex=" + sex +
                ", children=" + children +
                '}';
    }
}
