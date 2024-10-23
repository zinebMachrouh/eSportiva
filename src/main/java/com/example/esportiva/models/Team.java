package com.example.esportiva.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "ranking", nullable = false)
    private Integer ranking;

    @ManyToMany
    @JoinTable(
            name = "tournament_teams",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "tournament_id")
    )


    private List<Tournament> tournaments = new ArrayList<>();

    public Team() {
    }

    public Team(String name, int ranking) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.ranking = ranking;
    }

    public Team(UUID id, String name, int ranking) {
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

    public List<Tournament> getTournaments() {
        return tournaments;
    }
    public void setTournaments(List<Tournament> tournaments) {
        this.tournaments = tournaments;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ranking=" + ranking +
                '}';
    }

    public void addTournament(Tournament tournament) {
        if (!tournaments.contains(tournament)) {
            tournaments.add(tournament);
            tournament.addTeam(this);
        }
    }

    public void removeTournament(Tournament tournament) {
        if (tournaments.contains(tournament)) {
            tournaments.remove(tournament);
            tournament.removeTeam(this);
        }
    }
}
