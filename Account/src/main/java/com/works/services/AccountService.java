package com.works.services;

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
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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


    public ResponseEntity feingInfo() {
        Span span = tracer.currentSpan();
        cacheManager.getCache("news").clear();
        log.trace("this line error");
        String parentId = span.context().parentId();
        String spanId = span.context().spanId();
        log.info( "parentId", parentId, spanId );
        return new ResponseEntity( feing.fncProConfig(), HttpStatus.OK );
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
