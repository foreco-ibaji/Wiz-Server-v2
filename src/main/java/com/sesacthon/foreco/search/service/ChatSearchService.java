package com.sesacthon.foreco.search.service;

import com.sesacthon.foreco.search.config.ChatInfo;
import com.sesacthon.foreco.search.dto.request.ChatRequestDto;
import com.sesacthon.foreco.search.dto.request.ChatSearchRequestDto;
import com.sesacthon.foreco.search.dto.response.ChatResponseDto;
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

    public ChatResponseDto getChatResponse(ChatSearchRequestDto searchMessage) throws IOException {
        OkHttpClient client = new OkHttpClient();
        String chatRequest = ChatRequestDto.newInstance(searchMessage).toJson();

        Request request = new Request.Builder()
            .addHeader("Content-Type", "application/json")
            .post(RequestBody.create(chatRequest.getBytes()))
            .url(chatInfo.getUrl())
            .build();

        try (Response response = client.newCall(request).execute()) {
            String responseString = response.body().string();
            return new ChatResponseDto(responseString);
        } catch (IOException e) {
            throw e;
        }
    }
}
