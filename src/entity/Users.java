package entity;

import java.util.Date;

import annotations.relations.OneToOne;
import annotations.table.Entity;
import annotations.types.PrimaryKey;
import annotations.types.Timestamp;
import annotations.types.Varchar;

@Entity
public class Users  {
    @PrimaryKey
    private int id;

    @Varchar(length = 32, unique = true)
    private String username;

    @Varchar(length = 32)
    private String password;

    @Timestamp()
    private Date date;

    @OneToOne(entity = Persons.class)
    private Persons persons;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Persons getPersons() {
        return persons;
    }

    public void setPersons(Persons persons) {
        this.persons = persons;
    }

    @Override
    public String toString() {
        return "{id:" + id + ", username:" + username + ", password:" + password + ", date:" + date + ", persons:"
                + persons + "}";
    }
}
