package com.sesacthon.foreco.trash.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "쓰레기 세부품목", description = "세부품목 관련 api")
@RestController
@RequiredArgsConstructor
public class TrashController {

  @Operation(summary = "세부 품목 쓰레기 정보", description = "지역 기반 쓰레기 배출 정보를 조회할 수 있습니다.")
  @GetMapping("/api/v1/trash")
  public String getTrashInfoWithRegionCond(
      @RequestParam("name") String name) {
    return "ok";
  }

  @Operation(summary = "관련 쓰레기 정보", description = "관련된 쓰레기들의 정보를 조회할 수 있습니다.")
  @GetMapping("api/v1/trash/relation")
  public String getRelationTrashInfo(@RequestParam("name") String name) {
    return "ok";
  }
}
