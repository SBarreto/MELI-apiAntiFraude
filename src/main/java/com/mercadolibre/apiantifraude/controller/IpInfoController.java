package com.mercadolibre.apiantifraude.controller;

import com.mercadolibre.apiantifraude.dto.IpInfoDTO;
import com.mercadolibre.apiantifraude.model.Ip;
import com.mercadolibre.apiantifraude.service.GetIpInfoService;
import com.mercadolibre.apiantifraude.service.SaveIpInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IpInfoController {

    @Autowired
    private GetIpInfoService ipInfoService;

    @Autowired
    private SaveIpInfoService saveIpInfoService;

    @GetMapping("meli/api/ipinfo/{ip}")
    IpInfoDTO getIpInfo(@PathVariable String ip) {
       return ipInfoService.getIpInfo(ip);
    }

    @PutMapping("meli/api/blockip/{ip}")
    Ip blockIp(@PathVariable String ip) {
        return saveIpInfoService.saveBlackListedIp(ip);
    }

}
