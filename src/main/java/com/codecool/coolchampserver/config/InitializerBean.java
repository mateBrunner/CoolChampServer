package com.codecool.coolchampserver.config;

import com.codecool.coolchampserver.model.Championship;
import com.codecool.coolchampserver.model.ChampionshipSettings;
import com.codecool.coolchampserver.repository.ChampionshipRepository;
import com.codecool.coolchampserver.repository.ChampionshipSettingsRepository;
import org.springframework.stereotype.Component;

@Component
public class InitializerBean {

    public InitializerBean(ChampionshipRepository championshipRepository,
                           ChampionshipSettingsRepository settingsRepository) {

        //settingsRepository.save(new ChampionshipSettings());
        championshipRepository.save(new Championship("firsttt"));
        championshipRepository.save(new Championship("seconddd"));
        championshipRepository.save(new Championship("thirddd"));

    }

}
