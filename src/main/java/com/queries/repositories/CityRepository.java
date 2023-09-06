package com.queries.repositories;

import com.queries.models.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
    @Query("SELECT c FROM City c WHERE c.id=?1")
    City findByIdC(int id);
    @Query("SELECT c FROM City c WHERE c.name=?1")
    City findByNameC(String name);
    @Query("SELECT c FROM City c WHERE c.name like %?1%")
    List<City> findAllByNameC(String like);
}
