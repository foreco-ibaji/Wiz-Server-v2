package com.sesacthon.foreco.category.repository;

import com.sesacthon.foreco.category.entity.RegionCategory;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionCategoryRepository extends JpaRepository<RegionCategory,Long> {

  Optional<RegionCategory> findByTrashIdAndRegionId(Long parentTrashId, Long regionId);
}
