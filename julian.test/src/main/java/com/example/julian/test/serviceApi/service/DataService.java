package com.example.julian.test.serviceApi.service;

import com.example.julian.test.serviceApi.entities.Product;
import com.example.julian.test.serviceApi.repository.DataEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DataService {

    private static final String DATA_TOKEN = ",";
    private static final String PRODUCT_COST_MESSAGE = "The following product has this cost receipt";
    private static final String PRODUCT_TRACE_CALCULATION = "%s, %s";
    private static final String PRODUCT_NOT_FOUND = "Following Product Not Found: %s";

    @Autowired
    private final DataEntityRepository repository;

    public void processProductUpload(final MultipartFile file) {

        final BufferedReader br;
        final List<String> result = new ArrayList<>();
        List<Product> products = repository.findAll();
        try {
            String line;
            InputStream is = file.getInputStream();
            br = new BufferedReader(new InputStreamReader(is));
            br.readLine().split(DATA_TOKEN);
            while ((line = br.readLine()) != null) {
                String[] productData = line.replaceAll("\\s+","").split(DATA_TOKEN);
                String productName = productData[0];
                BigDecimal productPrice = new BigDecimal(productData[1]);
                repository.save(new Product(productName, productPrice));
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public Map<String, BigDecimal> processOrder(final MultipartFile file) throws Exception {

        final BufferedReader br;
        final List<String> result = new ArrayList<>();
        Map<String, BigDecimal> productCosts = new HashMap<>();
        List<Product> products = repository.findAll();
        try {
            String line;
            InputStream is = file.getInputStream();
            br = new BufferedReader(new InputStreamReader(is));
            br.readLine().split(DATA_TOKEN);
            while ((line = br.readLine()) != null) {
                String[] productData = line.replaceAll("\\s+", "").split(DATA_TOKEN);
                String productName = productData[0];
                BigDecimal productQuantity = new BigDecimal(productData[1]);
                Product product = products.stream().filter(
                        e -> e.getName().equalsIgnoreCase(productName)
                ).collect(Collectors.toList()).get(0);
                if(product != null){
                    BigDecimal orderCost = product.getPrice().multiply(productQuantity).setScale(2, RoundingMode.HALF_UP);
                    productCosts.put(productName, orderCost);
                    System.out.println(PRODUCT_COST_MESSAGE);
                    System.out.println(String.format(PRODUCT_TRACE_CALCULATION, productName, orderCost));
                } else {
                    String stackTrace = String.format(PRODUCT_NOT_FOUND, productName);
                    System.out.println(stackTrace);
                    throw new Exception(stackTrace);
                }

            }
            return  productCosts;

        } catch (IOException e) {
            System.err.println(e.getMessage());
            throw e;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw e;
        }
    }

}
