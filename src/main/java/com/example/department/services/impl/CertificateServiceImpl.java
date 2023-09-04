package com.example.department.services.impl;

import com.example.department.models.Certificate;
import com.example.department.models.Employee;
import com.example.department.repositories.CertificateRepository;
import com.example.department.repositories.EmployeeRepository;
import com.example.department.services.CertificateService;
import com.example.department.services.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CertificateServiceImpl implements CertificateService {
    Logger log = LoggerFactory.getLogger(CertificateServiceImpl.class);
    @Autowired
    CertificateRepository certificateRepository;

    @Override
    public List<Certificate> getCertificates() {
        List<Certificate> certificates = new ArrayList<>();
        certificateRepository.findAll().forEach(c -> certificates.add(c));
        return certificates;
    }

    @Override
    public Certificate addOrUpdateCertificate(Certificate certificate) {
        try
        {
            return certificateRepository.save(certificate);
        }
        catch (DataIntegrityViolationException dive)
        {
            log.info(dive.toString());
            return new Certificate();
        }
    }

    @Override
    public boolean deleteCertificate(int id) {
        try{
            certificateRepository.deleteById(id);
            return true;
        }
        catch (EmptyResultDataAccessException erdae)
        {
            log.info(erdae.toString());
            return false;
        }
    }
}
