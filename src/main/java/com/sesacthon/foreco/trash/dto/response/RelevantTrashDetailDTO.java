package com.sesacthon.foreco.trash.dto.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RelevantTrashDetailDTO {
    private final Long id;
    private final String name;
    private final int orderOfTab;
    private final String imgUrl;


    public RelevantTrashDetailDTO(Long id, String name, int orderOfTab, String imgUrl) {
        this.id = id;
        this.name = name;
        this.orderOfTab = orderOfTab;
        this.imgUrl = imgUrl;
    }
}
