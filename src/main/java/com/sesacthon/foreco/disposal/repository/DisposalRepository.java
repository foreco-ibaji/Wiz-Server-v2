package com.sesacthon.foreco.disposal.repository;


import com.sesacthon.foreco.disposal.entity.Disposal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisposalRepository extends JpaRepository<Disposal, Long> {

}
