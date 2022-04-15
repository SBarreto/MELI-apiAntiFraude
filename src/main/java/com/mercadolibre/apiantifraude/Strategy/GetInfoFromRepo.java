package com.mercadolibre.apiantifraude.Strategy;

import com.mercadolibre.apiantifraude.dto.IpInfoDTO;
import org.springframework.stereotype.Component;

@Component
public class GetInfoFromRepo implements IGetIpInfoStrategy {


    @Override
    public IpInfoDTO getIpInfo(String ip) {

        return null;
    }

    private IpInfoDTO buildResponse() {
        return null;
    }
}
