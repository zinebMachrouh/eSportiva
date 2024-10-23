package com.example.esportiva.services.interfaces;

import com.example.esportiva.dto.GamerDTO;
import com.example.esportiva.models.Gamer;

import java.util.List;
import java.util.UUID;

public interface GamerService {
    public Gamer addGamer(GamerDTO gamer);
    public Gamer updateGamer(GamerDTO gamer);
    public boolean deleteGamer(UUID id);
    public Gamer getGamer(UUID id);
    public List<Gamer> getAllGamers();

}
