package com.mercadolibre.apiantifraude.Strategy;

import com.mercadolibre.apiantifraude.dto.IpInfoDTO;

public interface IGetIpInfoStrategy {

    IpInfoDTO getIpInfo(String ip);
}
