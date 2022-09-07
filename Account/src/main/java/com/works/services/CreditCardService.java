package com.works.services;

import com.works.entities.CreditCard;
import com.works.repositories.CreditCardRepository;
import com.works.util.TinkEncDec;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CreditCardService {

    final CreditCardRepository cRepo;

    public ResponseEntity save(CreditCard creditCard) {
        String cipherTextCardNo = TinkEncDec.encrypt(creditCard.getCardNo(), creditCard.getAssociatedData());
        creditCard.setCardNo(cipherTextCardNo);
        creditCard.setAssociatedData(null);
        cRepo.save(creditCard);
        Map<String, Object> hm = new LinkedHashMap<>();
        hm.put("status", true);
        hm.put("cardNo", creditCard.getCardNo());
        hm.put("uid", creditCard.getUid());
        return new ResponseEntity(hm, HttpStatus.OK);
    }

    public ResponseEntity info( CreditCard creditCard ) {
        Optional<CreditCard> optionalCreditCard = cRepo.findByUidEquals( creditCard.getUid() );
        Map<String, Object> hm = new LinkedHashMap<>();
        if (optionalCreditCard.isPresent() ) {
            CreditCard dbCreditCart = optionalCreditCard.get();
            String planTextCardNo = TinkEncDec.decrypt(dbCreditCart.getCardNo(), creditCard.getAssociatedData() );
            if ( planTextCardNo.equals("") ) {
                hm.put("status", false);
            }else {
                hm.put("status", true);
                hm.put("cardNo", planTextCardNo);
            }
        }else {
            hm.put("status", false);
        }
        return new ResponseEntity(hm, HttpStatus.OK);
    }

}
