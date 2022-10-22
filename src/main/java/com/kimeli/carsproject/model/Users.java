package com.kimeli.carsproject.model;


import javax.persistence.*;

@Entity
@Table(name="carowners")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    private Long idnumber;

    public Users() {
        super();
    }

    public Users(String firstname, String lastname, Long idnumber) {
        super();
        this.firstname = firstname;
        this.lastname = lastname;
        this.idnumber = idnumber;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Long getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(Long idnumber) {
        this.idnumber = idnumber;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", idnumber=" + idnumber +
                '}';
    }
}
