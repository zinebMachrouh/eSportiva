package com.example.esportiva.dto;

import com.example.esportiva.models.Tournament;
import com.example.esportiva.models.enums.TournamentStatus;

import java.util.Date;
import java.util.UUID;

public class TournamentDTO {
    private UUID id;
    private String title;
    private Date startDate;
    private Date endDate;
    private Integer spectators;
    private Integer estimatedDuration;
    private  Integer breakDuration;
    private Integer ceremonyDuration;
    private TournamentStatus status;
    private double prize;
    private GameDTO game;

    public TournamentDTO() {
    }

    public TournamentDTO(String title, Date startDate, Date endDate, Integer spectators, Integer estimatedDuration, Integer breakDuration, Integer ceremonyDuration, TournamentStatus status, double prize, GameDTO game) {
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

    public TournamentDTO(UUID id, String title, Date startDate, Date endDate, Integer spectators, Integer estimatedDuration, Integer breakDuration, Integer ceremonyDuration, TournamentStatus status, double prize, GameDTO game) {
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

    public Integer getSpectators() {
        return spectators;
    }
    public void setSpectators(Integer spectators) {
        this.spectators = spectators;
    }

    public Integer getEstimatedDuration() {
        return estimatedDuration;
    }
    public void setEstimatedDuration(Integer estimatedDuration) {
        this.estimatedDuration = estimatedDuration;
    }

    public Integer getBreakDuration() {
        return breakDuration;
    }
    public void setBreakDuration(Integer breakDuration) {
        this.breakDuration = breakDuration;
    }

    public Integer getCeremonyDuration() {
        return ceremonyDuration;
    }
    public void setCeremonyDuration(Integer ceremonyDuration) {
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

    public GameDTO getGame() {
        return game;
    }
    public void setGame(GameDTO game) {
        this.game = game;
    }

    @Override
    public String toString() {
        return "TournamentDTO{" +
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

    public Tournament dtoToModel(){
        return new Tournament(this.id, this.title, this.startDate, this.endDate, this.spectators, this.estimatedDuration, this.breakDuration, this.ceremonyDuration, this.status, this.prize, this.game.dtoToModel());
    }

    public TournamentDTO modelToDTO(Tournament tournament){
        return new TournamentDTO(tournament.getId(), tournament.getTitle(), tournament.getStartDate(), tournament.getEndDate(), tournament.getSpectators(), tournament.getEstimatedDuration(), tournament.getBreakDuration(), tournament.getCeremonyDuration(), tournament.getStatus(), tournament.getPrize(), GameDTO.modelToDTO(tournament.getGame()));
    }
}
