package com.works.restcontrollers;

import com.works.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity infoFeing() {
        return service.feingInfo();
    }

}
