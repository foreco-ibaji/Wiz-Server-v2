package com.sesacthon.foreco.trash.repository;

import com.sesacthon.foreco.trash.entity.TrashInfo;
import java.util.List;
import java.util.Optional;
import javax.swing.text.html.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TrashInfoRepository extends JpaRepository<TrashInfo, Long> {

  Optional<TrashInfo> findByTrashIdAndRegionId(Long trashId, Long regionId);

  Optional<TrashInfo> findByIdAndRegionId(Long id, Long regionId);

}
