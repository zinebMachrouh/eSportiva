package com.example.esportiva.dto;

import com.example.esportiva.models.Team;

import java.util.UUID;

public class TeamDTO {
    private UUID id;
    private String name;
    private Integer ranking;

    public TeamDTO() {
    }

    public TeamDTO(String name, int ranking) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.ranking = ranking;
    }

    public TeamDTO(UUID id, String name, int ranking) {
        this.id = id;
        this.name = name;
        this.ranking = ranking;
    }

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Integer getRanking() {
        return ranking;
    }
    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ranking=" + ranking +
                '}';
    }

    public Team dtoToModel(){
        return new Team(this.id, this.name, this.ranking);
    }

    public static TeamDTO modelToDTO(Team team){
        return new TeamDTO(team.getId(), team.getName(), team.getRanking());
    }
}
