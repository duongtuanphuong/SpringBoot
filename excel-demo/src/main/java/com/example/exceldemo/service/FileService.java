package com.example.exceldemo.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import com.example.exceldemo.model.Customer;
import com.example.exceldemo.repository.CustomerRepository;
import com.example.exceldemo.ultils.ExcelUltils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
    @Autowired
    CustomerRepository customerRepository;

    public void store(MultipartFile file) {
        try {
            List<Customer> lstCustomers = ExcelUltils.parseExcelFile(file.getInputStream());
            // Save Customers to DataBase
            customerRepository.saveAll(lstCustomers);
        } catch (IOException e) {
            throw new RuntimeException("FAIL! -> message = " + e.getMessage());
        }
    }

}
