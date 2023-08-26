package com.workshop.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.workshop.Entity.CabInfo;
@Repository
public interface CabInfoRepo extends JpaRepository<CabInfo, Integer> {

}
