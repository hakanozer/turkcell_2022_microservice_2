package com.works.services;

import com.works.profile.ProfileConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
@RefreshScope
@RequiredArgsConstructor
public class ProductService {

    final ProfileConfig config;

    @Value("${data.apikey}")
    private String apikey;

    public ResponseEntity apiKey() {
        Map<String, Object> hm = new LinkedHashMap<>();
        hm.put("apikey", apikey);
        hm.put("config", config.config());
        return new ResponseEntity(hm, HttpStatus.OK);
    }

}
