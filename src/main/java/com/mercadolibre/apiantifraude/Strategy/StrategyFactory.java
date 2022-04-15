package com.mercadolibre.apiantifraude.Strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class StrategyFactory {

    private final GetInfoFromClient getInfoFromClient;

    private final GetInfoFromRepo getInfoFromRepo;

    private Map<StrategyName, IGetIpInfoStrategy> strategies;

    @Autowired
    public StrategyFactory(GetInfoFromClient getInfoFromClient, GetInfoFromRepo getInfoFromRepo) {
        this.getInfoFromClient = getInfoFromClient;
        this.getInfoFromRepo = getInfoFromRepo;
        strategies = new HashMap<>();
        initStrategies();
    }

    private void initStrategies() {
        strategies.put(StrategyName.FromClientStrategy, getInfoFromClient);
        strategies.put(StrategyName.FromRepositoryStrategy, getInfoFromRepo);
    }

    public IGetIpInfoStrategy getStrategy(StrategyName strategyName) {
        return strategies.get(strategyName);
    }
}
