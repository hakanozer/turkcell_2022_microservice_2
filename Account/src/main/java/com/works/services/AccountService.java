package com.works.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.works.ifeing.INewsFeing;
import com.works.ifeing.IProductFeing;
import com.works.props.ProConfig;
import com.works.props.Product;
import com.works.props.ResultProduct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.SpanAndScope;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {

    final DiscoveryClient discoveryClient;
    final IProductFeing feing;
    final INewsFeing iNewsFeing;
    final CacheManager cacheManager;
    final Tracer tracer;

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

    @HystrixCommand(fallbackMethod = "errorFeingInfo")
    public ResponseEntity feingInfo( String id ) {
        cacheManager.getCache("news").clear();
        /*
        Span span = tracer.currentSpan();
        if (span != null) {
            log.info("Span ID hex {}", span.context().spanId());
            log.info("Trace ID hex {}", span.context().traceId());
        }
        HttpHeaders headers = new HttpHeaders();
        headers.set("traceId", span.context().traceId());
        */
        return new ResponseEntity( feing.fncProConfig(), HttpStatus.OK );
    }


    public ResponseEntity errorFeingInfo( String id ) {
        Map<String, Object> hm = new LinkedHashMap<>();
        hm.put("status", false);
        hm.put("id", id);
        hm.put("message", "FeingInfo Error");
        return new ResponseEntity( hm, HttpStatus.BAD_REQUEST );
    }


    public ResponseEntity feingSample() {
        Product product = new Product();
        product.setTitle("Bisiklet");
        product.setPrice(4000);
        ResultProduct resultProduct = feing.proSave(product);
        return new ResponseEntity( resultProduct , HttpStatus.OK );
    }

    @Cacheable("news")
    public ResponseEntity news() {
        Object obj = iNewsFeing.newsApi("tr", "business", "38a9e086f10b445faabb4461c4aa71f8");
        return new ResponseEntity( obj , HttpStatus.OK );
    }

}
