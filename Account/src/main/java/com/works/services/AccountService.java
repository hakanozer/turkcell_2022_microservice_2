package com.works.services;

import com.works.ifeing.IProductFeing;
import com.works.props.ProConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    final DiscoveryClient discoveryClient;
    final IProductFeing feing;

    public ResponseEntity info() {
        List<ServiceInstance> ls = discoveryClient.getInstances("product");
        if ( ls != null && !ls.isEmpty() ) {
            ServiceInstance instance = ls.get(0);
            String url = instance.getUri().toString();

            RestTemplate restTemplate = new RestTemplate();
            ProConfig obj = restTemplate.getForObject(url+"/apiKey", ProConfig.class);

            return new ResponseEntity(obj.getConfig(), HttpStatus.OK);
        }
        return new ResponseEntity(null, HttpStatus.NOT_FOUND);
    }


    public ResponseEntity feingInfo() {
        return new ResponseEntity( feing.fncProConfig(), HttpStatus.OK );
    }

}
