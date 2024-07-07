package com.sesacthon.foreco.search.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Message {

    private String role;
    private String content;

    @Override
    public String toString() {
        return "{\n" +
            "\"role\": \"" + role + "\",\n" +
            "\"content\": \"" + content + "\"\n" +
            "}";
    }

}
