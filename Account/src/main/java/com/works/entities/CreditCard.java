package com.works.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cid;

    private Long uid;
    @Column(length = 1000)
    private String cardNo;
    private String associatedData;


}
