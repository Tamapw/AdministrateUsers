package ru.tama.administrateuser.entity;

import com.sun.org.apache.xml.internal.utils.XMLStringFactory;
import org.springframework.stereotype.Controller;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Class {@code User} is entity User in ORM with using Annotations. It contains information about User.
 *
 * @author tama.
 */
@Entity
@Table(name = "users")
public class User implements Serializable {
    private Integer id;
    private String firstName;
    private String lastName;
    private Date dateBirthday;
    private String login;
    private String password;
    private String aboutUser;
    private String residence;

    public User() {

    }

    public User(String firstName, String lastName, Date dateBirthday,
                String login, String password, String aboutUser, String residence) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateBirthday = dateBirthday;
        this.login = login;
        this.password = password;
        this.aboutUser = aboutUser;
        this.residence = residence;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "firstname", nullable = false, length = 40)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "lastname", nullable = false, length = 40)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "datebirthday", nullable = false)
    @Temporal(TemporalType.DATE)
    public Date getDateBirthday() {
        return dateBirthday;
    }

    public void setDateBirthday(Date dateBirthday) {
        this.dateBirthday = dateBirthday;
    }

    @Column(name = "login", nullable = false, length = 40)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Column(name = "password", nullable = false, length = 40)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "aboutuser")
    public String getAboutUser() {
        return aboutUser;
    }

    public void setAboutUser(String aboutUser) {
        this.aboutUser = aboutUser;
    }

    @Column(name = "residence")
    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    @Override
    public String toString() {
        return String.format("User:\n\tid: %d\n\tfirstName: %s\n\tlastName: %s\n\tdateBirthday: %s" +
                "\n\tlogin: %s\n\tpassword: %s\n\tresidence: %s\n\taboutUser: %s",
                id, firstName, lastName, dateBirthday,
                login, password, residence, aboutUser);
    }
}
