package com.velvet.investing.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "asset_info")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssetInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;
    private Double mutualFunds;
    private Double stocks;
    private Double fixedDeposits;
    private Double realEstate;
    private Double gold;
    private Double cashAndBank;
}