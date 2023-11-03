package com.sesacthon.infra.feign.dto.response;

import java.util.List;
import lombok.Getter;


@Getter
public class ImageAnalyzeResponseDto {

  private List<List<String>> bboxes;

}
