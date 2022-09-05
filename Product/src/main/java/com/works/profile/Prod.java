package com.works.profile;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
@Profile("prod")
public class Prod implements ProfileConfig {

    @Override
    public Map config() {
        Map<String, Object> hm = new LinkedHashMap<>();
        hm.put("key", "pro123");
        hm.put("url", "pro.url.com");
        hm.put("api", "proApi12312312");
        return hm;
    }

}
