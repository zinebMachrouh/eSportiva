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
    private Long id;

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

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Gamer> gamers = new ArrayList<>();

    public Team() {
    }

    public Team(String name, int ranking) {
        this.name = name;
        this.ranking = ranking;
    }

    public Team(Long id, String name, int ranking) {
        this.id = id;
        this.name = name;
        this.ranking = ranking;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
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

    public List<Gamer> getGamers() {
        return gamers;
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

    public void addGamer(Gamer gamer) {
        if (!this.gamers.contains(gamer)) {
            this.gamers.add(gamer);
        }
    }

    public void removeGamer(Gamer gamer) {
        if (this.gamers.contains(gamer)) {
            this.gamers.remove(gamer);
        }
    }
}
