package com.works.ifeing;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "GoogleNews", url = "https://newsapi.org/v2/")
public interface INewsFeing {

    @GetMapping(value = "/top-headline")
    Object newsApi(@RequestParam String country, @RequestParam String category, @RequestParam String apiKey);

}
