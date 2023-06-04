package com.sesacthon.foreco.trash.entity;

import com.sesacthon.foreco.trash.DisposalTypeConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * 세부 품목(쓰레기 이름)
 */
@Entity
public class Trash {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String trashName;
  private String method;
  private String material;
  private String remark;

  @Convert(converter = DisposalTypeConverter.class)
  private DisposalType type;

}
