package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.Objects;

@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")
public class ContactData {

    @XStreamOmitField
    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;

    @Expose
    @Column(name = "firstname")
    private String firstName;

    @Expose
    @Column(name = "middlename")
    private String middleName;

    @Expose
    @Column(name = "lastname")
    private String lastName;

    @Expose
    @Column(name = "nickname")
    private String nickName;

    @Expose
    private String myHome;

    @Column(name = "home")
    private String homePhone;

    @Column(name = "mobile")
    private String mobilePhone;

    @Column(name = "work")
    private String workPhone;

    @Expose
    @Transient
    private String group;

    @Transient
    private String allPhones;

    private String mailFirst;

    private String mailSecond;

    private String mailThree;

    @Transient
    private String allMail;

    private String address;

    @Expose
//    @Column(name = "photo")
    private File photo;



    public ContactData withPhoto(File photo) {
        this.photo = photo;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }


    public ContactData withMailFirst(String mailFirst) {
        this.mailFirst = mailFirst;
        return this;
    }

    public ContactData withMailSecond(String mailSecond) {
        this.mailSecond = mailSecond;
        return this;
    }

    public ContactData withMailThree(String mailThree) {
        this.mailThree = mailThree;
        return this;
    }

    public ContactData withAllMail(String allMail) {
        this.allMail = allMail;
        return this;
    }

    public ContactData withHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;

    }

    public ContactData withMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;

    }

    public ContactData withWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
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

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }


    public String getHomePhone() {
        return homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getWorkPhone() {
        return workPhone;
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

    public String getAllPhones() {
        return allPhones;
    }

    public String getAllMail() {
        return allMail;
    }

    public String getMailThree() {
        return mailThree;
    }

    public String getMailSecond() {
        return mailSecond;
    }

    public String getMailFirst() {
        return mailFirst;
    }

    public String getAddress() {
        return address;
    }

    public File getPhoto() {
        return  photo;
    }

    public String getMyHome() {
        return myHome;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(homePhone, that.homePhone) && Objects.equals(mobilePhone, that.mobilePhone) && Objects.equals(workPhone, that.workPhone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, homePhone, mobilePhone, workPhone);
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", homePhone='" + homePhone + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", workPhone='" + workPhone + '\'' +
                '}';
    }
}


