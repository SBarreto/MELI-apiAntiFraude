package com.mercadolibre.apiantifraude.service;

import com.mercadolibre.apiantifraude.dto.IpInfoDTO;
import org.springframework.stereotype.Service;

@Service
public class GetInfoFromClient implements IGetIpInfoStrategy{
    @Override
    public IpInfoDTO getIpInfo() {
        return null;
    }
}
