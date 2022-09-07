package com.works.restcontrollers;

import com.works.entities.CreditCard;
import com.works.services.CreditCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/card")
public class CreditCardRestController {

    final CreditCardService cardService;

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody CreditCard creditCard) {
        return cardService.save(creditCard);
    }

    @PostMapping("/info")
    public ResponseEntity info(@RequestBody CreditCard creditCard) {
        return cardService.info(creditCard);
    }

}
