package com.example.julian.test.serviceApi.controller;

import com.example.julian.test.serviceApi.common.entities.BaseResponseBody;
import com.example.julian.test.serviceApi.entities.OrderInvoice;
import com.example.julian.test.serviceApi.service.DataService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Map;

@Controller
@AllArgsConstructor
public class DataController {

    private static final String UPLOAD_PRICES_ENDPOINT = "/upload-products";
    private static final String TRANSACTION_ENDPOINT = "/upload-transactions";

    private static final String FILE_ERROR_MESSAGE = "File is Empty";

    @Autowired
    private final DataService service;

    @PostMapping(UPLOAD_PRICES_ENDPOINT)
    public ResponseEntity<String> uploadProducts(@RequestParam("file") MultipartFile file) throws Exception {

        try {
            service.processProductUpload(file);
        } catch (Exception e) {
            throw new Exception("INTERNAL SERVER ERROR DURING PRODUCT UPLOAD");
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping(TRANSACTION_ENDPOINT)
    public ResponseEntity<BaseResponseBody> uploadTransactions(@RequestParam("file") MultipartFile file) throws Exception {

        try {
            Map<String, BigDecimal> costs = service.processOrder(file);
            return ResponseEntity.ok().body(new OrderInvoice(costs));
        } catch (Exception e) {
            throw new Exception("INTERNAL SERVER ERROR DURING PRODUCT UPLOAD");
        }
    }

}
