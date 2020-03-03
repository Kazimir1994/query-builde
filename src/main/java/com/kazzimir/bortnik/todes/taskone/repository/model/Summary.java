package com.kazzimir.bortnik.todes.taskone.repository.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity(name = "Summary")
public class Summary {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "patronymic", nullable = false)
    private String patronymic;

    @Column(name = "date_Of_Birth", nullable = false)
    private Date dateOfBirth;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gender_id", nullable = false)
    private Gender gender;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "summary")
    private Collection<Contacts> contacts;

    @ManyToMany
    @JoinTable(
            name = "Summary_Technology",
            joinColumns = @JoinColumn(
                    name = "summary_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "technology_id", referencedColumnName = "id"))
    private Collection<Technology> technology;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Collection<Contacts> getContacts() {
        return contacts;
    }

    public void setContacts(Collection<Contacts> contacts) {
        this.contacts = contacts;
    }

    public Collection<Technology> getTechnology() {
        return technology;
    }

    public void setTechnology(Collection<Technology> technology) {
        this.technology = technology;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Summary summary = (Summary) o;
        return id == summary.id &&
                Objects.equals(name, summary.name) &&
                Objects.equals(surname, summary.surname) &&
                Objects.equals(patronymic, summary.patronymic) &&
                Objects.equals(dateOfBirth, summary.dateOfBirth) &&
                Objects.equals(gender, summary.gender) &&
                Objects.equals(contacts, summary.contacts) &&
                Objects.equals(technology, summary.technology);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, patronymic, dateOfBirth, gender, contacts, technology);
    }

    @Override
    public String toString() {
        return "Summary{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender=" + gender +
                ", contacts=" + contacts +
                ", technology=" + technology +
                '}';
    }
}
