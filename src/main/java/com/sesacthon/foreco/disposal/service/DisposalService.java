package com.sesacthon.foreco.disposal.service;

import com.sesacthon.foreco.category.entity.RegionCategory;
import com.sesacthon.foreco.category.repository.RegionCategoryRepository;
import com.sesacthon.foreco.disposal.dto.response.TodayDisposableCategoriesDto;
import com.sesacthon.foreco.disposal.repository.DisposalRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DisposalService {
  private final DisposalRepository disposalRepository;
  private final RegionCategoryRepository regionCategoryRepository;

  //TODO: member 로그인을 아직 하지 않기 때문에 임시로 지역 정보를 고정시켜놓습니다.
  private static final Long REGION_ID = 1L;

  public TodayDisposableCategoriesDto getDisposalInfo(String day) {
    //1. regionId로 묶여있는 지역카테고리 Id들을 가져온다.
    List<RegionCategory> regionCategories = regionCategoryRepository.findRegionCategories(REGION_ID);

    //2. 요청 파라미터로 받은 요일이 배출가능 요일 목록에 포함되어 있으면 카테고리 이름을 가져온다.
    //TODO: 쿼리 최적화 필요
    List<String> resultInfo = regionCategories.stream()
        .filter(category -> {
          List<String> disposableDays = disposalRepository.findDisposalInfos(category.getId());
          return disposableDays.contains(day);
        })
        .map(category -> category.getTrash().getName())
        .collect(Collectors.toList());

    return new TodayDisposableCategoriesDto(resultInfo);
  }


}
