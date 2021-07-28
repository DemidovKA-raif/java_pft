package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class NameNewContact {
    private final String id;
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String nickName;
    private final String myHome;
    private String group;


    public NameNewContact(String id,
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
    public NameNewContact(
                          String firstName,
                          String middleName,
                          String lastName,
                          String nickName,
                          String myHome,
                          String group) {
        this.id = null;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.myHome = myHome;
        this.group = group;
    }
    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "NameNewContact{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
//                ", middleName='" + middleName + '\'' +
//                ", lastName='" + lastName + '\'' +
//                ", nickName='" + nickName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NameNewContact that = (NameNewContact) o;
        return Objects.equals(id, that.id) && Objects.equals(firstName, that.firstName) ;
//                && Objects.equals(middleName, that.middleName) && Objects.equals(lastName, that.lastName) && Objects.equals(nickName, that.nickName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName);
//                , middleName, lastName, nickName);
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
