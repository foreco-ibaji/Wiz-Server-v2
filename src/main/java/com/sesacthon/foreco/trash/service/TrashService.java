package com.sesacthon.foreco.trash.service;

import com.sesacthon.foreco.disposal.repository.DisposalRepository;
import com.sesacthon.foreco.region.service.RegionService;
import com.sesacthon.foreco.trash.repository.TrashInfoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrashService {

  private final TrashInfoRepository trashInfoRepository;
  private final DisposalRepository disposalRepository;
  private final RegionService regionService;

//  public RelevantTrashesDto findRelevantTrash(String region, String trashName, int tab) {
//
////    //지역 id
////    Long regionId = regionService.findRegion(region);
////    //category id
////    TrashInfo trashInfo = trashRepository.findTrashByRegionAndName(regionId, trashName, tab)
////        .orElseThrow(() -> new TrashNotFoundException(TRASH_NOT_FOUND));
////    Long categoryId = trashInfo.getTrash().getId();
////    //관련쓰레기 정보
////    List<TrashInfo> trashInfos = trashRepository.findTrashByRegionIdAndCategoryId(regionId, categoryId,
////        trashName);
////    //빈 List인 경우 error발생
////    if (trashInfos.isEmpty()) {
////      throw new RelatedTrashNotFoundException(RELATED_TRASH_NOT_FOUND);
////    }
////
////    return new RelevantTrashesDto(trashInfos);
//  }
}
