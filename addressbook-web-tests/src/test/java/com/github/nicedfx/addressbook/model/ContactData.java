package com.github.nicedfx.addressbook.model;

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
    private String firstName = "";
    @Expose
    @Column(name = "middlename")
    private String middleName = "";
    @Expose
    @Column(name = "lastname")
    private String lastName = "";
    @Expose
    @Column(name = "address")
    @Type(type = "text")
    private String address = "";
    @Expose
    @Column(name = "home")
    @Type(type = "text")
    private String homePhone = "";
    @Expose
    @Column(name = "mobile")
    @Type(type = "text")
    private String mobilePhone = "";
    @Expose
    @Column(name = "work")
    @Type(type = "text")
    private String workPhone = "";
    @Expose
    @Column(name = "phone2")
    @Type(type = "text")
    private String secondPhone = "";
    @Expose
    @Transient
    private String allPhones;
    @Expose
    @Column(name = "email")
    @Type(type = "text")
    private String email = "";
    @Expose
    @Column(name = "email2")
    @Type(type = "text")
    private String email2 = "";
    @Expose
    @Column(name = "email3")
    @Type(type = "text")
    private String email3 = "";
    @Expose
    @Transient
    private String emails;
    @Expose
    @Transient
    private String group;
    @Column(name = "photo")
    @Type(type = "text")
    private String photo;

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
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

    public String getSecondPhone() {
        return secondPhone;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public String getEmail() {
        return email;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public String getEmails() {
        return emails;
    }

    public String getGroup() {
        return group;
    }

    public int getId() {
        return id;
    }

    public File getPhoto() {
        if (photo != null) {
            return new File(photo);
        }
        return null;
    }

    public ContactData withId(int id) {
        this.id = id;
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

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }

    public ContactData withMobilePhone(String mobile) {
        this.mobilePhone = mobile;
        return this;
    }

    public ContactData withWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }

    public ContactData withSecondPhone(String secondPhone) {
        this.secondPhone = secondPhone;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public ContactData withEmails(String emails) {
        this.emails = emails;
        return this;
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

    public ContactData withAllPhones(String phones) {
        this.allPhones = phones;
        return this;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id && Objects.equals(firstName, that.firstName) && Objects.equals(middleName, that.middleName) && Objects.equals(lastName, that.lastName) && Objects.equals(address, that.address) && Objects.equals(homePhone, that.homePhone) && Objects.equals(mobilePhone, that.mobilePhone) && Objects.equals(workPhone, that.workPhone) && Objects.equals(secondPhone, that.secondPhone) && Objects.equals(email, that.email) && Objects.equals(email2, that.email2) && Objects.equals(email3, that.email3) && Objects.equals(group, that.group);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, middleName, lastName, address, homePhone, mobilePhone, workPhone, secondPhone, email, email2, email3, group);
    }
}
