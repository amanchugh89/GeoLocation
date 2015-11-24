package com.bhati.repository;

import com.bhati.entity.UserDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by aman on 23/11/15.
 */
public interface UserDetailsRepository extends CrudRepository<UserDetails,Long> {

    UserDetails findByMobile(Long mobile);
    List<UserDetails> findByName(Long name);
}
