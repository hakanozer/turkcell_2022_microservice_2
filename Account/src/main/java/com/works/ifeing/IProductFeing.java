package com.works.ifeing;

import com.works.props.ProConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "product")
public interface IProductFeing {

    @GetMapping("/apiKey")
    ProConfig fncProConfig();

}
