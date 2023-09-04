package com.example.department.repositories;

import com.example.department.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
//    @Query("delete from Department d where d.manager_id = ?1")
//    void deleteByQuery(int id);
}
