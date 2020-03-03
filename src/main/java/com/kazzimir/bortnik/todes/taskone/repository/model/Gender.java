package com.kazzimir.bortnik.todes.taskone.repository.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity(name = "Gender")
public class Gender {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_gender", nullable = false, unique = true)
    private int id_gender;

    @Column(name = "sex", nullable = false, unique = true)
    private String sex;

    public int getId() {
        return id_gender;
    }

    public void setId(int id) {
        this.id_gender = id;
    }

    public String getName() {
        return sex;
    }

    public void setName(String name) {
        this.sex = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gender gender = (Gender) o;
        return id_gender == gender.id_gender &&
                Objects.equals(sex, gender.sex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_gender, sex);
    }

    @Override
    public String toString() {
        return "Gender{" +
                "id=" + id_gender +
                ", name='" + sex + '\'' +
                '}';
    }
}
