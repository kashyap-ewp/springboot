package com.employee.transactions.controllers;

import com.employee.transactions.models.Certificate;
import com.employee.transactions.models.ResponseDto;
import com.employee.transactions.services.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/certificates")
public class CertificateController {
    @Autowired
    CertificateService certificateService;

    @PostMapping("/simplet")
    @Transactional(isolation = Isolation.READ_COMMITTED,
    propagation = Propagation.REQUIRED,
            noRollbackFor = RuntimeException.class,
            readOnly=true)
    public ResponseEntity<ResponseDto> addCertificateSimpleT(@RequestBody Certificate certificate)
    {
        addCertificate(certificate);
        throw new RuntimeException();
    }
    public ResponseEntity<ResponseDto> addCertificate(Certificate certificate)
    {
        Certificate c = certificateService.addOrUpdateCertificate(certificate);
        ResponseDto res = new ResponseDto();

        if(c.getId() == 0)
        {
            res.setStatus(false);
            res.setMessage("Data not Saved, Please enter correct details");

            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        else
        {
            res.setStatus(true);
            res.setMessage("Data Saved");
            res.setData(c);

            return new ResponseEntity<>(res, HttpStatus.CREATED);
        }
    }
    @GetMapping("/")
    public ResponseEntity<ResponseDto> getCertificates()
    {
        List<Certificate> certificates = certificateService.getCertificates();
        ResponseDto res = new ResponseDto();

        if(certificates.isEmpty())
        {
            res.setStatus(false);
            res.setMessage("No Data");

            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        else {
            res.setStatus(true);
            res.setData(certificates);

            return new ResponseEntity<>(res, HttpStatus.OK);
        }
    }

    @PutMapping("/")
    public ResponseEntity<ResponseDto> updateCertificate(@RequestBody Certificate certificate)
    {
        Certificate c = certificateService.addOrUpdateCertificate(certificate);
        ResponseDto res = new ResponseDto();

        if(c.getId() == 0)
        {
            res.setStatus(false);
            res.setMessage("Department not found");

            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        else
        {
            res.setStatus(true);
            res.setMessage("Department Saved");
            res.setData(c);

            return new ResponseEntity<>(res, HttpStatus.CREATED);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteEmployee(@PathVariable int id)
    {
        boolean isDeleted = certificateService.deleteCertificate(id);
        ResponseDto res = new ResponseDto();

        if(!isDeleted){
            res.setStatus(false);
            res.setMessage("Certificate not found");

            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        else {
            res.setStatus(true);
            res.setMessage("Certificate Deleted");

            return new ResponseEntity<>(res, HttpStatus.OK);
        }
    }
}
