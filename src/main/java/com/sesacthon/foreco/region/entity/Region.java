package com.sesacthon.foreco.region.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * 지역
 * - 시, 구, 동
 * - 배출 요일
 * - 배출 날짜
 */
@Entity
public class Region {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String city;

  private String gu;

  private String dong;

  private String disposalDay;

  private String disposalTime;


}
