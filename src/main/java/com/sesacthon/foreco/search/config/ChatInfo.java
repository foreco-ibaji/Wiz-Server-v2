package com.sesacthon.foreco.search.config;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Slf4j
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "chat.solar")
public class ChatInfo {

    private String chatUrl;
    private String secretKey;

}
