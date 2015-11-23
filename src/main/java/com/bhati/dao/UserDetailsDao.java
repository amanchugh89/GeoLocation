package com.bhati.dao;

import com.bhati.entity.UserDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

/**
 * Created by aman on 23/11/15.
 */
@Component
public interface UserDetailsDao extends CrudRepository<UserDetails,Long> {
    UserDetails  getUserDetails(String name);
    UserDetails getUserDetails(long deviceID);
    void setUserDetails(UserDetails details);
}
