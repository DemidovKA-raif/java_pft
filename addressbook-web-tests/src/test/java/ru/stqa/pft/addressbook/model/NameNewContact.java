package ru.stqa.pft.addressbook.model;

public class NameNewContact {
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String nickName;
    private final String myHome;
    private String group;


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
