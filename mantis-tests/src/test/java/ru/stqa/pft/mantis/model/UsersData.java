package ru.stqa.pft.mantis.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;


@XStreamAlias("users")
@Entity
@Table(name="mantis_user_table")
public class UsersData {

    @XStreamOmitField
    @Id
    @Column(name="id")
    private int id = Integer.MAX_VALUE;

    @Column(name="username")
    private String username;


    public UsersData withId(int id) {
        this.id = id;
        return this;
    }

    public UsersData withUsername(String username) {
        this.username = username;
        return this;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "UsersData{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersData usersData = (UsersData) o;
        return id == usersData.id && Objects.equals(username, usersData.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username);
    }


}
