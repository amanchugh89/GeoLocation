package com.bhati.repository;

import com.bhati.entity.MarshallDetails;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by aman on 23/11/15.
 */
public interface UserDetailsRepository extends CrudRepository<MarshallDetails,Long> {

    MarshallDetails findByMobile(Long mobile);
    List<MarshallDetails> findByName(Long name);
}
