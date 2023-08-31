package com.sesacthon.foreco.category.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="쓰레기 카테고리", description = "카테고리 관련 api")
@RestController
@RequiredArgsConstructor
public class CategoryController {
  //배포 확인용
  @GetMapping()
  public String test(){
    return "ok";
  }
}
