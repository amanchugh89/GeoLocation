package com.bhati.repository;

import com.bhati.entity.GeoEvent;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by aman on 25/11/15.
 */
public interface GeoEventRepository extends CrudRepository<GeoEvent , Long>{

    List<GeoEvent> findByuserId(long userId);
    GeoEvent findById(long id);

    List<GeoEvent> findLatestDistinctEvents();
}
