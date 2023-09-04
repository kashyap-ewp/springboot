package com.example.department.repositories;

import com.example.department.models.Certificate;
import com.example.department.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate,Integer> {
}
