package com.sesacthon.foreco.mission.entity;

import static jakarta.persistence.GenerationType.*;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 미션 관련 정보를 담은 테이블
 */
@Entity
@NoArgsConstructor
@Getter
public class Mission {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  /**
   * 미션 주제 이름
   */
  private String title;

  /**
   * 미션 요청 API url
   */
  private String apiUrl;

  /**
   * 미션 요청 메서드
   */
  private String apiMethod;

}
