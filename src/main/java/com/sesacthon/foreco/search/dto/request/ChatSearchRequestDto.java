package com.sesacthon.foreco.search.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChatSearchRequestDto {
    @Schema(description = "사용자의 AI 검색 메시지")
    private String searchMessage;
}
