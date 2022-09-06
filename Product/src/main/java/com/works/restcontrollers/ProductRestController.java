package com.works.restcontrollers;

import com.works.entities.Product;
import com.works.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductRestController {

    final ProductService pService;

    @GetMapping("/apiKey")
    public ResponseEntity apiKey() {
        return pService.apiKey();
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Product product) {
        return pService.save(product);
    }

    @GetMapping("/findId/{pid}")
    public ResponseEntity findId( @PathVariable int pid ) {
        return pService.findId(pid);
    }

}
