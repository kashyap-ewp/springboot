package com.example.department.controllers;

import com.example.department.models.Certificate;
import com.example.department.models.Employee;
import com.example.department.models.ResponseDto;
import com.example.department.services.CertificateService;
import com.example.department.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/certificates")
public class CertificateController {
    @Autowired
    CertificateService certificateService;

    @PostMapping("/")
    public ResponseEntity<ResponseDto> addCertificate(@RequestBody Certificate certificate)
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
