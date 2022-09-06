package com.works.services;

import com.works.entities.Product;
import com.works.profile.ProfileConfig;
import com.works.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RefreshScope
@RequiredArgsConstructor
public class ProductService {

    final ProfileConfig config;
    final ProductRepository pRepo;

    @Value("${data.apikey}")
    private String apikey;

    public ResponseEntity apiKey() {
        Map<String, Object> hm = new LinkedHashMap<>();
        hm.put("apikey", apikey);
        hm.put("config", config.config());
        return new ResponseEntity(hm, HttpStatus.OK);
    }

    public ResponseEntity save(Product product) {
        pRepo.save(product);
        Map<String, Object> hm = new LinkedHashMap<>();
        hm.put("status", true);
        hm.put("result", product);
        return new ResponseEntity(hm, HttpStatus.OK);
    }

    public ResponseEntity findId( int pid ) {
        Optional<Product> optionalProduct = pRepo.findById((long) pid);
        Map<String, Object> hm = new LinkedHashMap<>();
        if ( optionalProduct.isPresent() ) {
            hm.put("status", true);
            hm.put("result", optionalProduct.get());
        }else {
            hm.put("status", false);
        }
        return new ResponseEntity(hm, HttpStatus.OK);
    }

}
