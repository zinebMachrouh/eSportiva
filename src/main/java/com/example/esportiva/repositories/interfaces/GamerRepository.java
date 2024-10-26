package com.example.esportiva.repositories.interfaces;

import com.example.esportiva.models.Gamer;

import java.util.List;
import java.util.UUID;

public interface GamerRepository {
    public Gamer addGamer(Gamer gamer);
    public Gamer updateGamer(Gamer gamer);
    public boolean deleteGamer(Long id);
    public Gamer getGamer(Long id);
    public List<Gamer> getAllGamers();

}
