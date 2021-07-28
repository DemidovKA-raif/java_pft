package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class NameNewContact {
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String nickName;
    private final String myHome;
    private String group;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NameNewContact contact = (NameNewContact) o;
        return Objects.equals(firstName, contact.firstName) && Objects.equals(middleName, contact.middleName) && Objects.equals(lastName, contact.lastName) && Objects.equals(nickName, contact.nickName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, middleName, lastName, nickName);
    }

    @Override
    public String toString() {
        return "NameNewContact{" +
                "firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }

    public NameNewContact(String firstName,
                          String middleName,
                          String lastName,
                          String nickName,
                          String myHome,
                          String group) {
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
}
