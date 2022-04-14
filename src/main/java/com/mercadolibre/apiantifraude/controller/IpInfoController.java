package com.mercadolibre.apiantifraude.controller;

import com.mercadolibre.apiantifraude.dto.IpInfoDTO;
import com.mercadolibre.apiantifraude.service.IpInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IpInfoController {

    @Autowired
    private IpInfoService ipInfoService;

    @GetMapping("meli/api/ipinfo/{ip}")
    IpInfoDTO getIpInfo(@PathVariable String ip) {
       return ipInfoService.getIpInfo(ip);
    }

}
