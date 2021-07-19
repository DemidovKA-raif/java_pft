package ru.stqa.pft.addressbook.model;

public class NameNewContact {
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String nickName;
    private final String myHome;


    public NameNewContact(String firstName, String middleName, String lastName, String nickName, String myHome) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.myHome = myHome;
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

    public String getMyHome() {
        return myHome;
    }
}
