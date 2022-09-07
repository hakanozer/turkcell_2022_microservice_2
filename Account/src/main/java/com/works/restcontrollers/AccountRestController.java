package com.works.restcontrollers;

import com.works.props.Product;
import com.works.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountRestController {

    final AccountService service;

    @GetMapping("/info")
    public ResponseEntity info() {
        return service.info();
    }

    @GetMapping("/infoFeing")
    public ResponseEntity infoFeing( @RequestParam(defaultValue = "") String id ) {
        return service.feingInfo(id);
    }

    @GetMapping("/feingSample")
    public ResponseEntity feingSample() {
        return service.feingSample();
    }

    @GetMapping("/news")
    public ResponseEntity news(@RequestBody Product product) {
        return service.news();
    }

}
