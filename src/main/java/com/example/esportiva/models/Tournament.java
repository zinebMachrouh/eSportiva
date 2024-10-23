package com.example.esportiva.models;

import com.example.esportiva.models.enums.TournamentStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tournaments")
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "startDate", nullable = false)
    private Date startDate;

    @Column(name = "endDate", nullable = false)
    private Date endDate;

    @Column(name = "spectators", nullable = false)
    private Integer spectators;

    @Column(name = "estimatedDuration", nullable = false)
    private Integer estimatedDuration;

    @Column(name = "breakDuration", nullable = false)
    private Integer breakDuration;

    @Column(name = "ceremonyDuration", nullable = false)
    private Integer ceremonyDuration;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private TournamentStatus status;

    @Column(name = "prize", nullable = false)
    private double prize;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @ManyToMany
    @JoinTable(
            name = "tournament_teams",
            joinColumns = @JoinColumn(name = "tournament_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    private List<Team> teams = new ArrayList<>();

    public Tournament(){}

    public Tournament(UUID id, String title, Date startDate, Date endDate, int spectators, int estimatedDuration, int breakDuration, int ceremonyDuration, TournamentStatus status, double prize, Game game) {
        this.id = id;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.spectators = spectators;
        this.estimatedDuration = estimatedDuration;
        this.breakDuration = breakDuration;
        this.ceremonyDuration = ceremonyDuration;
        this.status = status;
        this.prize = prize;
        this.game = game;
    }

    public Tournament(String title, Date startDate, Date endDate, int spectators, int estimatedDuration, int breakDuration, int ceremonyDuration, TournamentStatus status, double prize, Game game) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.spectators = spectators;
        this.estimatedDuration = estimatedDuration;
        this.breakDuration = breakDuration;
        this.ceremonyDuration = ceremonyDuration;
        this.status = status;
        this.prize = prize;
        this.game = game;
    }

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getSpectators() {
        return spectators;
    }
    public void setSpectators(int spectators) {
        this.spectators = spectators;
    }

    public int getEstimatedDuration() {
        return estimatedDuration;
    }
    public void setEstimatedDuration(int estimatedDuration) {
        this.estimatedDuration = estimatedDuration;
    }

    public int getBreakDuration() {
        return breakDuration;
    }
    public void setBreakDuration(int breakDuration) {
        this.breakDuration = breakDuration;
    }

    public int getCeremonyDuration() {
        return ceremonyDuration;
    }
    public void setCeremonyDuration(int ceremonyDuration) {
        this.ceremonyDuration = ceremonyDuration;
    }

    public TournamentStatus getStatus() {
        return status;
    }
    public void setStatus(TournamentStatus status) {
        this.status = status;
    }

    public double getPrize() {
        return prize;
    }
    public void setPrize(double prize) {
        this.prize = prize;
    }

    public Game getGame() {
        return game;
    }
    public void setGame(Game game) {
        this.game = game;
    }

    public List<Team> getTeams() {
        return teams;
    }
    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public void addTeam(Team team) {
        if (!this.teams.contains(team)) {
            this.teams.add(team);
        }
    }

    public void removeTeam(Team team) {
        this.teams.remove(team);
    }

    @Override
    public String toString() {
        return "Tournament{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", spectators=" + spectators +
                ", estimatedDuration=" + estimatedDuration +
                ", breakDuration=" + breakDuration +
                ", ceremonyDuration=" + ceremonyDuration +
                ", status=" + status +
                ", prize=" + prize +
                ", game=" + game +
                '}';
    }
}
