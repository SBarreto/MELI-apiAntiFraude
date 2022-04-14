package com.mercadolibre.apiantifraude.service;

import com.mercadolibre.apiantifraude.dto.IpInfoDTO;
import org.springframework.stereotype.Service;

public interface IGetIpInfoStrategy {

    IpInfoDTO getIpInfo();
}
