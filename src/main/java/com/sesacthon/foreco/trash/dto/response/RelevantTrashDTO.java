package com.sesacthon.foreco.trash.dto.response;

import lombok.Getter;

import java.util.List;

@Getter
public class RelevantTrashDTO {
    private final List<RelevantTrashDetailDTO> relevantTrashInfo;

    public RelevantTrashDTO(List<RelevantTrashDetailDTO> relevantTrashInfo) {
        this.relevantTrashInfo = relevantTrashInfo;
    }
}
