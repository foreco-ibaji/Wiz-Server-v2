package com.sesacthon.foreco.region.service;

import static com.sesacthon.global.exception.ErrorCode.REGION_NOT_FOUND;

import com.sesacthon.foreco.region.entity.Region;
import com.sesacthon.foreco.region.exception.RegionNotFoundException;
import com.sesacthon.foreco.region.repository.RegionRepository;
import com.sesacthon.global.verification.VerifyRegionValueService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegionService {

  private final RegionRepository regionRepository;
  private final VerifyRegionValueService verifyRegionValueService;

  public Region findRegion(String region) {
    String[] regionVal = verifyRegionValueService.checkParam(region);

    Optional<Region> regionInfo = regionRepository.findRegionByCityAndGuAndDong(regionVal[0],
        regionVal[1], regionVal[2]);

    // 1번에 가장 기본지역 데이터를 넣어둬야 함
    return regionInfo.orElseGet(() -> findRegion(1L));
  }

  //region 정보가 없을 경우
  public Region findRegion(Long regionId) {
    return regionRepository.findById(regionId)
        .orElseThrow(() -> new RegionNotFoundException(REGION_NOT_FOUND));
  }






}
