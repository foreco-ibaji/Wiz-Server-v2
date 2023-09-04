package com.sesacthon.foreco.disposal.repository;


import com.sesacthon.foreco.disposal.entity.Disposal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DisposalRepository extends JpaRepository<Disposal, Long> {

  @Query("select d.disposableDay from Disposal d where d.regionCategory.id = :regionCategoryId")
  List<String> findDisposalInfos(@Param("regionCategoryId") Long regionCategoryId);
}
