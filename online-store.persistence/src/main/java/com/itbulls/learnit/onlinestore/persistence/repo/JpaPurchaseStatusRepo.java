package com.itbulls.learnit.onlinestore.persistence.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.itbulls.learnit.onlinestore.persistence.entities.PurchaseStatus;

@Repository
public interface  JpaPurchaseStatusRepo<P> extends CrudRepository<PurchaseStatus, Integer>{

}
