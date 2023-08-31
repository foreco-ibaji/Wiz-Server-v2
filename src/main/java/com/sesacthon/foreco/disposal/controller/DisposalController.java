package com.sesacthon.foreco.disposal.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="쓰레기 배출 정보", description = "배출정보 관련 api")
@RestController
@RequiredArgsConstructor
public class DisposalController {

  /**
   * 메인 화면에서 사용자의 지역정보와 오늘 날짜를 기준으로 배출 가능 품목을 안내
   * @param region 사용자의 지역정보(시,구,동)
   * @return 배출 가능 카테고리 응답 dto
   */
  @Operation(summary = "배출 정보", description = "요일에 해당하는 배출 가능 품목과 시간을 조회할 수 있습니다.")
  @GetMapping("/api/v1/disposal")
  public String getDisposalInfoWithCond(
      @RequestParam("region") String region, @RequestParam("day") String day) {
    return "ok";
  }


}
