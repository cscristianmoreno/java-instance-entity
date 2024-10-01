package entity;

import java.util.Date;

import annotations.table.Entity;
import annotations.types.Integer;
import annotations.types.PrimaryKey;
import annotations.types.Timestamp;
import annotations.types.Varchar;

@Entity
public class Persons {
    @PrimaryKey
    private int id;
    
    @Varchar(length = 32)
    private String name;

    @Varchar(length = 50)
    private String lastname;

    @Integer
    private int dni;

    @Integer(unique = true)
    private int age;

    @Timestamp
    private Date date;

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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    @Override
    public String toString() {
        return "{id:" + id + ", name:" + name + ", lastname:" + lastname + ", age:" + age + ", date:" + date
                + "}";
    }
}
