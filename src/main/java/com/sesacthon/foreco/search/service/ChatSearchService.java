package com.sesacthon.foreco.search.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sesacthon.foreco.search.config.ChatInfo;
import com.sesacthon.foreco.search.dto.request.ChatRequestDto;
import com.sesacthon.foreco.search.dto.response.ChatResponseDto;
import com.sesacthon.foreco.search.dto.response.UpstageResponseDto;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import okhttp3.OkHttpClient;

@Service
@RequiredArgsConstructor
public class ChatSearchService {

    private final ChatInfo chatInfo;

    public ChatResponseDto getChatResponse(String searchMessage) throws IOException {
        OkHttpClient client = new OkHttpClient();
        String chatRequest = ChatRequestDto.newInstance(searchMessage).toString();

        String authorization = "Bearer " + chatInfo.getSecretKey();
        Request request = new Request.Builder()
            .addHeader("Authorization", authorization)
            .addHeader("Content-Type", "application/json")
            .post(RequestBody.create(chatRequest.getBytes()))
            .url(chatInfo.getChatUrl())
            .build();

        try (Response response = client.newCall(request).execute()) {
            String jsonString = response.body().string();
            ObjectMapper objectMapper = new ObjectMapper();
            UpstageResponseDto chatResponse = objectMapper.readValue(jsonString, UpstageResponseDto.class);
            return new ChatResponseDto(chatResponse);
        } catch (IOException e) {
            throw e;
        }
    }
}
