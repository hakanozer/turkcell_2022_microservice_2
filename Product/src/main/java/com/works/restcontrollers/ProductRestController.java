package com.works.restcontrollers;

import com.works.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductRestController {

    final ProductService pService;

    @GetMapping("/apiKey")
    public ResponseEntity apiKey() {
        return pService.apiKey();
    }

}
