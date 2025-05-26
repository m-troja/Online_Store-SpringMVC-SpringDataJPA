package com.michal.onlinestore.persistence.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
<<<<<<< HEAD

=======
>>>>>>> 695085bb892170646e452eb0cdff3bf54a05b59b
import com.michal.onlinestore.persistence.entities.PurchaseStatus;

@Repository
public interface  JpaPurchaseStatusRepo<P> extends CrudRepository<PurchaseStatus, Integer>{

}
