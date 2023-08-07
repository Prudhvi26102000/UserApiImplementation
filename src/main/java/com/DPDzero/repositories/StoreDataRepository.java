package com.DPDzero.repositories;

import com.DPDzero.model.StoreData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreDataRepository extends JpaRepository<StoreData, String> {

}
