package com.bhati.dao;

import com.bhati.entity.UserDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

/**
 * Created by aman on 23/11/15.
 */
public interface UserDetailsRepository extends CrudRepository<UserDetails,Long> {


}
