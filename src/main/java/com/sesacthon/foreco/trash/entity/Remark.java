package com.sesacthon.foreco.trash.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

/**
 * 유의사항 테이블
 */
@Entity
@Getter
public class Remark {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String description;

  /**
   * remark(유의사항)
   */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "trash_info_id")
  private TrashInfo trashInfo;

}
