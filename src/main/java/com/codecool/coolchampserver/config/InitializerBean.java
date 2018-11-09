package com.codecool.coolchampserver.config;

import com.codecool.coolchampserver.model.Championship;
import com.codecool.coolchampserver.repository.ChampionshipRepository;
import org.springframework.stereotype.Component;

@Component
public class InitializerBean {

    public InitializerBean(ChampionshipRepository championshipRepository) {

        championshipRepository.save(new Championship("first"));
        championshipRepository.save(new Championship("second"));
        championshipRepository.save(new Championship("third"));

    }

}
