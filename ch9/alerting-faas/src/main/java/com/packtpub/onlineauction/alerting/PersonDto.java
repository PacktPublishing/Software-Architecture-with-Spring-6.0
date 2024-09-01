package com.packtpub.onlineauction.alerting;

import java.time.LocalDate;

public class PersonDto {

    private String name;
    private LocalDate birthDate;
    private Integer age;

    public PersonDto(String name, LocalDate birthDate, Integer age) {
        this.name = name;
        this.birthDate = birthDate;
        this.age = age;
    }

    public PersonDto(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
