package com.sesacthon.foreco.mock.mission;

import com.sesacthon.foreco.mission.dto.QuizMissionChoice;
import com.sesacthon.foreco.mission.dto.MissionInfo;
import com.sesacthon.foreco.mission.dto.MissionResultDto;
import com.sesacthon.foreco.mission.dto.MissionResultInfoDto;
import com.sesacthon.foreco.mission.dto.QuizMissionAnswer;
import com.sesacthon.foreco.mission.dto.QuizMissionDto;
import com.sesacthon.global.response.DataResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Mission Mock API", description = "임시 api")
@RestController
@RequiredArgsConstructor
public class MissionMockController {

  @Operation(summary = "쓰레기 맞추기 미션 요청 api", description = "조각난 쓰레기 사진을 보고 어떤 쓰레기인지 맞추는 미션 api")
  @GetMapping("mock/api/v1/mission/{id}")
  public ResponseEntity<DataResponse<QuizMissionDto>> getMission1(
      @PathVariable(name = "id") Long id) {

    //실제 구현시, s3에 업로드하여 url로 반환해야함. -> 요청마다 url생성을 위해서 일회성 이미지를 업로드 하는 것이 맞는 건가...?
    List<String> images = Arrays.asList("url1", "url2", "url3", "url4");
    List<QuizMissionChoice> choices = new ArrayList<>();
    choices.add(new QuizMissionChoice(1L, "소파"));
    choices.add(new QuizMissionChoice(2L, "의류"));
    choices.add(new QuizMissionChoice(3L, "형광등"));
    choices.add(new QuizMissionChoice(4L, "소주병"));
    QuizMissionAnswer answer = new QuizMissionAnswer(1L, "소파", "소파이미지url", "지자체에 신고 후 버려주세요.");
    MissionInfo missionInfo = new MissionInfo(2000L, "쓰레기 퍼즐 맞추기", "아래에 조각난 쓰레기는 무엇일까요?", 1L, 5L);

    QuizMissionDto response = new QuizMissionDto(missionInfo, images, choices, answer);

    return new ResponseEntity<>(DataResponse.of(HttpStatus.OK, "퀴즈 미션 요청 성공", response),
        HttpStatus.OK);
  }

  @Operation(summary = "요청 결과 반영 api", description = "미션 수행이후 결과를 반영하고, 변경된 포인트 이력을 보여줍니다.")
  @PostMapping("mock/api/v1/mission/result")
  public ResponseEntity<DataResponse<MissionResultInfoDto>> updateMissionResult(
      @RequestBody MissionResultDto missionResultDto) {
    //TODO 실제 api 구현시 contextHolder에서 값을 가져온 후  user의 id를 활용해야함
    //유저아이디를 조회후, rewardPoint반영
    //성공했으면, 성공을 반영
    //실패했으면, 실패를 반영.
    //사용자
    MissionResultInfoDto response = new MissionResultInfoDto(5L, 10L);
    return new ResponseEntity<>(DataResponse.of(HttpStatus.OK, "결과 반영 성공", response),
        HttpStatus.OK);

  }
}
