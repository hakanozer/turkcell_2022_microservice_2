package com.works.ifeing;

import com.works.props.ProConfig;
import com.works.props.Product;
import com.works.props.ResultProduct;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "product")
public interface IProductFeing {

    @GetMapping("/product/apiKey")
    ProConfig fncProConfig();

    @PostMapping("/product/save")
    ResultProduct proSave(@RequestBody Product product);

    @GetMapping("/product/findId/{pid}")
    ResultProduct findId(@PathVariable int pid);

}
