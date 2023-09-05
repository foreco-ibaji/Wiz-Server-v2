package com.sesacthon.foreco.category.repository;

import com.sesacthon.foreco.category.entity.Trash;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TrashRepository extends JpaRepository<Trash, Long> {

  @Query("select t from Trash t where t.parentTrash.id = :categoryId and t.name like concat('%', :keyword, '%')")
  List<Trash> searchTrashWithKeyword(
      @Param("categoryId") Long categoryId, @Param("keyword") String keyword);

  //keyword와 동일한 name을 가지고 있으며, parentTrash가  null이 아닌 경우를 찾음.
  Optional<Trash> findByNameAndParentTrashIsNotNull(String keyword);
}
