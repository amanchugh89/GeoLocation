package com.bhati.dao;

import com.bhati.entity.UserDetails;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by aman on 23/11/15.
 */
public interface UserDetailsDao extends CrudRepository<UserDetails,Long> {
    public UserDetails  getDetails(String name);
    public UserDetails getDetails(long deviceID);
    public void setDetails(UserDetails details);
}
