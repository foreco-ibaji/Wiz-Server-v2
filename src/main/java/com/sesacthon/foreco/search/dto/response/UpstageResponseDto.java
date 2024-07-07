package com.sesacthon.foreco.search.dto.response;

import com.sesacthon.foreco.search.dto.Message;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpstageResponseDto {
    private String id;
    private String object;
    private Long created;
    private String model;
    private List<Choice> choices;
    private Usage usage;
    private String system_fingerprint;

    @Getter
    public static class Choice {
        private Long index;
        private Message message;
        private final String logprobs = null;
        private String finish_reason;
    }

    @Getter
    public static class Usage {
        private Long prompt_tokens;
        private Long completion_tokens;
        private Long total_tokens;
    }
}
