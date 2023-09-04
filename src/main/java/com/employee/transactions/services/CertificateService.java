package com.employee.transactions.services;

import com.employee.transactions.models.Certificate;

import java.util.List;

public interface CertificateService {
    List<Certificate> getCertificates();

    Certificate addOrUpdateCertificate(Certificate certificate);

    boolean deleteCertificate(int id);

}
