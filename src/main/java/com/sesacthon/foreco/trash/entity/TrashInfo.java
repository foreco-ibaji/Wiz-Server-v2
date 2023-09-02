package com.sesacthon.foreco.trash.entity;

import static jakarta.persistence.FetchType.*;

import com.sesacthon.foreco.category.entity.Trash;

import com.sesacthon.foreco.region.entity.Region;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 쓰레기의 자세한 정보
 */
@Entity
@NoArgsConstructor
@Getter
public class TrashInfo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * 쓰레기 상세 처리방법
   */
  private String method;

  /**
   * 쓰레기 처리형태 (=detail type)
   */
  @Convert(converter = DisposalTypeConverter.class)
  private DisposalType disposalType;

  /**
   * 쓰레기 카테고리
   */
  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "trash_id")
  private Trash trash;

  /**
   * 지역 Region
   */
  @ManyToOne
  @JoinColumn(name = "region_id")
  private Region region;

}