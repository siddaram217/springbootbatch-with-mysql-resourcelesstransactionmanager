package com.springbootbatch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springbootbatch.model.RecordSO;

@Repository
public interface ReaderSORepository extends JpaRepository<RecordSO, Long>{

}
