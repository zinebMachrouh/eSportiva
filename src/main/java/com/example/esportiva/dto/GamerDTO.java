package com.example.esportiva.dto;

import com.example.esportiva.models.Gamer;

import java.util.UUID;

public class GamerDTO {
    private Long id;
    private String username;
    private Integer age;
    private TeamDTO team;

    public GamerDTO() {
    }

    public GamerDTO(Long id, String username, Integer age, TeamDTO team) {
        this.id = id;
        this.username = username;
        this.age = age;
        this.team = team;
    }

    public GamerDTO(String username, Integer age, TeamDTO team) {
        this.username = username;
        this.age = age;
        this.team = team;
    }
    public GamerDTO(Long id, String username, Integer age) {
        this.id = id;
        this.username = username;
        this.age = age;
    }

    public GamerDTO(String username, Integer age) {
        this.username = username;
        this.age = age;
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

    public TeamDTO getTeam() {
        return team;
    }
    public void setTeam(TeamDTO team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "GamerDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", age=" + age +
                ", team=" + team +
                '}';
    }

    public Gamer dtoToModel() {
        if (this.team == null) {
            return new Gamer(this.id, this.username, this.age);
        } else {
            return new Gamer(this.id, this.username, this.age, this.team.dtoToModel());
        }
    }

    public Gamer dtoToModel(Gamer gamer) {
        return new Gamer(this.id, this.username, this.age);
    }

    public static GamerDTO modelToDTO(Gamer gamer) {
        if (gamer.getTeam() == null) {
            return new GamerDTO(gamer.getId(), gamer.getUsername(), gamer.getAge());
        } else {
            return new GamerDTO(gamer.getId(), gamer.getUsername(), gamer.getAge(), TeamDTO.modelToDTO(gamer.getTeam()));
        }
    }

}
