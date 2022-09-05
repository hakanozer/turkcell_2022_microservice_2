package com.works.profile;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
@Profile("dev")
public class Dev implements ProfileConfig {

    @Override
    public Map config() {
        Map<String, Object> hm = new LinkedHashMap<>();
        hm.put("key", "dev123");
        hm.put("url", "dev.url.com");
        hm.put("api", "devApi12312312");
        return hm;
    }

}
