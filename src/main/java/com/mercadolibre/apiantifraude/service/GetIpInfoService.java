package com.mercadolibre.apiantifraude.service;

import com.mercadolibre.apiantifraude.Exception.IpNotAllowedException;
import com.mercadolibre.apiantifraude.dto.IpInfoDTO;
import com.mercadolibre.apiantifraude.repository.IpRepository;
import com.mercadolibre.apiantifraude.Strategy.IGetIpInfoStrategy;
import com.mercadolibre.apiantifraude.Strategy.StrategyFactory;
import com.mercadolibre.apiantifraude.Strategy.StrategyName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Clase encargada de verificar que IP no este bloqueada
 * y determinar estrategia para traer informacion
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

        if (ipRepository.isIpBlocked(ip))
            throw new IpNotAllowedException("Ip is blocked");

        if (ipRepository.existsByIp(ip)){
             getIpInfoStrategy = strategyFactory.getStrategy(StrategyName.FromRepositoryStrategy);
        }
        else {
            getIpInfoStrategy = strategyFactory.getStrategy(StrategyName.FromClientStrategy);
        }

        IpInfoDTO ipInfoDTO = getIpInfoStrategy.getIpInfo(ip);
        saveIpInfoService.saveIpInfoIfNew(ipInfoDTO);
        return ipInfoDTO;
    }

}
