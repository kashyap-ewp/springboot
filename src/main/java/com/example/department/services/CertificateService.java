package com.example.department.services;

import com.example.department.models.Certificate;
import com.example.department.models.Employee;

import java.util.List;

public interface CertificateService {
    List<Certificate> getCertificates();

    Certificate addOrUpdateCertificate(Certificate certificate);

    boolean deleteCertificate(int id);

}
