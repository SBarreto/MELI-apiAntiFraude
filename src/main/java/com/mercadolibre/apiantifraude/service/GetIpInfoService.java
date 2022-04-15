package com.mercadolibre.apiantifraude.service;

import com.mercadolibre.apiantifraude.dto.IpInfoDTO;
import com.mercadolibre.apiantifraude.repository.IpRepository;
import com.mercadolibre.apiantifraude.Strategy.GetInfoFromClient;
import com.mercadolibre.apiantifraude.Strategy.IGetIpInfoStrategy;
import com.mercadolibre.apiantifraude.Strategy.StrategyFactory;
import com.mercadolibre.apiantifraude.Strategy.StrategyName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Clase encargada de elegir y traer la informacion de una IP
 */
@Service
public class GetIpInfoService {

    @Autowired
    StrategyFactory strategyFactory;

    @Autowired
    IpRepository ipRepository;

    @Autowired
    SaveIpInfoService saveIpInfoService;

    public IpInfoDTO getIpInfo(String ip) {
        IGetIpInfoStrategy getIpInfoStrategy;
        if (ipRepository.existsByIp(ip)){
             getIpInfoStrategy = strategyFactory.getStrategy(StrategyName.FromRepositoryStrategy);
        }
        else {
            getIpInfoStrategy = strategyFactory.getStrategy(StrategyName.FromClientStrategy);
        }
        IpInfoDTO ipInfoDTO = getIpInfoStrategy.getIpInfo(ip);
        saveIpInfoService.saveNewIpInfo(ipInfoDTO);
        return ipInfoDTO;
    }

}
