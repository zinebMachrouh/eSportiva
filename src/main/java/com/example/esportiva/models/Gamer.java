package com.example.esportiva.models;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "gamers")
public class Gamer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name="age", nullable = false)
    private Integer age;

    @ManyToOne
    @JoinColumn(name = "team_id", nullable = true)
    private Team team;

    public Gamer() {
    }

    public Gamer(String username, int age) {
        this.username = username;
        this.age = age;
    }

    public Gamer(Long id, String username, int age) {
        this.id = id;
        this.username = username;
        this.age = age;
    }

    public Gamer(Long id, String username, int age, Team team) {
        this.id = id;
        this.username = username;
        this.age = age;
        this.team = team;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }

    public Team getTeam() {
        return team;
    }
    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "Gamer{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", age=" + age +
                '}';
    }

}
