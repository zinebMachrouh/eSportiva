package com.example.esportiva.services;

import com.example.esportiva.dto.GamerDTO;
import com.example.esportiva.models.Gamer;
import com.example.esportiva.repositories.interfaces.GamerRepository;
import com.example.esportiva.services.interfaces.GamerService;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class GamerServiceImpl implements GamerService {
    private final GamerRepository gamerRepository;

    public GamerServiceImpl(GamerRepository gamerRepository) {
        this.gamerRepository = gamerRepository;
    }

    @Override
    public Gamer addGamer(GamerDTO gamer) {
        if (gamer == null) {
            return null;
        }else {
            return gamerRepository.addGamer(gamer.dtoToModel());
        }
    }

    @Override
    public Gamer updateGamer(GamerDTO gamer) {
        if (gamer == null) {
            return null;
        }else {
            return gamerRepository.updateGamer(gamer.dtoToModel());
        }
    }

    @Override
    public boolean deleteGamer(Long id) {
        if (id == null) {
            return false;
        }else {
            return gamerRepository.deleteGamer(id);
        }
    }

    @Override
    public Gamer getGamer(Long id) {
        if (id == null) {
            return null;
        }else {
            return gamerRepository.getGamer(id);
        }
    }

    @Override
    public List<Gamer> getAllGamers() {
        return gamerRepository.getAllGamers();
    }
}
