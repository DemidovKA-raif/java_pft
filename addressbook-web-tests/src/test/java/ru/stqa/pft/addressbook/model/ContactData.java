package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {


    private int id;
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String nickName;
    private final String myHome;
    private String group;


    @Override
    public String toString() {
        return "NameNewContact{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }


    public ContactData(int id,
                       String firstName,
                       String middleName,
                       String lastName,
                       String nickName,
                       String myHome,
                       String group) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.myHome = myHome;
        this.group = group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData contact = (ContactData) o;
        return Objects.equals(firstName, contact.firstName) && Objects.equals(lastName, contact.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    public ContactData(
            String firstName,
            String middleName,
            String lastName,
            String nickName,
            String myHome,
            String group) {
        this.id = Integer.MAX_VALUE;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.myHome = myHome;
        this.group = group;
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

    public void setId(int id) {
        this.id = id;
    }

}
