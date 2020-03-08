package com.springbootbatch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springbootbatch.model.WriterSO;

@Repository
public interface WriterSORepository extends JpaRepository<WriterSO, Long>{

}
