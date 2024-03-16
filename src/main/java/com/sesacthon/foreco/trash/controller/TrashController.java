package com.sesacthon.foreco.trash.controller;

import com.sesacthon.foreco.jwt.dto.SessionUser;
import com.sesacthon.foreco.trash.dto.RelevantTrashesDto;
import com.sesacthon.foreco.trash.dto.SearchedTrashesDto;
import com.sesacthon.foreco.trash.dto.TrashDetailDto;
import com.sesacthon.foreco.trash.service.TrashService;
import com.sesacthon.global.response.DataResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "쓰레기 세부품목", description = "세부품목 관련 api")
@RestController
@RequiredArgsConstructor
@Slf4j
public class TrashController {

  private final TrashService trashService;

  @Operation(summary = "쓰레기 상세 조회 API", description = "쓰레기 상세조회 API 입니다.")
  @PreAuthorize("isAuthenticated()")
  @GetMapping("/api/v1/trash/detail")
  public ResponseEntity<DataResponse<TrashDetailDto>> getTrashDetails(
      @RequestParam("id") Long id,
      @AuthenticationPrincipal SessionUser sessionUser
  ) {
    TrashDetailDto response = trashService.getTrashDetail(id, sessionUser.getRegionId());
    return new ResponseEntity<>(
        DataResponse.of(HttpStatus.OK, "쓰레기 상세조회 성공", response), HttpStatus.OK);
  }

  @Operation(summary = "쓰레기 상세 조회시 필요한, 관련된 쓰레기 정보 조회API", description = "쓰레기 상세조회 화면에서 제공되야할 관련된 쓰레기들 입니다.")
  @PreAuthorize("isAuthenticated()")
  @GetMapping("/api/v1/trash/relation")
  public ResponseEntity<DataResponse<RelevantTrashesDto>> getRelevantTrashes(
      @Parameter(description = "조회할 쓰레기 id(숫자)") @RequestParam("id") Long id,
      @AuthenticationPrincipal SessionUser sessionUser
  ) {
    RelevantTrashesDto response = trashService.getRelevantTrashes(id, sessionUser.getRegionId());
    return new ResponseEntity<>(DataResponse.of(HttpStatus.OK, "관련(추천) 쓰레기 정보 조회 성공", response),
        HttpStatus.OK);
  }

  @Operation(summary = "쓰레기 검색결과 조회 API", description = "메인 화면에서 검색을 통해 상세조회가 가능한 쓰레기의 정보를 조회합니다.")
  @PreAuthorize("isAuthenticated()")
  @GetMapping("/api/v1/trash")
  public ResponseEntity<DataResponse<SearchedTrashesDto>> getTrashesBySearch(
      @Parameter(description = "검색할 키워드를 입력해주세요") @RequestParam("keyword") String keyword,
      @AuthenticationPrincipal SessionUser sessionUser) {
    SearchedTrashesDto searchedTrashes = trashService.searchTrashWithKeyword(sessionUser.getRegionId(), keyword);
    return new ResponseEntity<>(DataResponse.of(HttpStatus.OK, "검색 결과 조회", searchedTrashes), HttpStatus.OK);
  }
}
