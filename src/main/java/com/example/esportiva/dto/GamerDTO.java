package com.example.esportiva.dto;

import com.example.esportiva.models.Gamer;

import java.util.UUID;

public class GamerDTO {
    private UUID id;
    private String username;
    private Integer age;
    private TeamDTO team;

    public GamerDTO() {
    }

    public GamerDTO(UUID id, String username, Integer age, TeamDTO team) {
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

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
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
        return new Gamer(this.id, this.username, this.age, this.team.dtoToModel());
    }

    public static GamerDTO modelToDTO(Gamer gamer) {
        return new GamerDTO(gamer.getId(), gamer.getUsername(), gamer.getAge(), TeamDTO.modelToDTO(gamer.getTeam()));
    }

}
