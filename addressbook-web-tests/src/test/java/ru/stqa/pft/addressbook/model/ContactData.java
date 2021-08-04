package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {

    private int id = Integer.MAX_VALUE;
    private  String firstName;
    private  String middleName;
    private  String lastName;
    private  String nickName;
    private  String myHome;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }


    public ContactData withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ContactData withMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public ContactData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ContactData withNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    public ContactData withMyHome(String myHome) {
        this.myHome = myHome;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }
    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    private  String group;


    @Override
    public String toString() {
        return "NameNewContact{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }


    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public String getGroup() {
        return group;
    }

    public int getId() {
        return id;
    }

}
