package com.proxy.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.proxy.bean.Address;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;


@Service
public class AddressService {

    public Address[] loadAddresses()throws Exception{
        StringBuilder newFile = new StringBuilder();
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream inputStream = classloader.getResourceAsStream("static/addresses.json");
            if(!(inputStream==null)){
                InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                BufferedReader reader = new BufferedReader(streamReader);
                for (String line; (line = reader.readLine()) != null;) {
                    newFile.append(line);
                }
            }
        }catch(FileNotFoundException fileNotFoundException){
            System.out.println(fileNotFoundException.getMessage());
        }catch(Exception ioe){
            ioe.printStackTrace();
        }
        return objectMapper.readValue(newFile.toString(), Address[].class);
    }

    public void prettyPrintAllAddresses(Address[] addresses) throws Exception {
        for(Address address:addresses) {
            prettyPrintAddress(address);
        }
    }

    public void printPostalAddress(Address[] addresses) throws Exception {
        for(Address address:addresses) {
            if (address.getType().getName().equalsIgnoreCase("Postal Address"))
                prettyPrintAddress(address);
        }
    }

    public void printPhysicalAddress(Address[] addresses) throws Exception {
        for(Address address:addresses) {
            if (address.getType().getName().equalsIgnoreCase("Physical Address"))
                prettyPrintAddress(address);
        }
    }

    public void printBusinessAddress(Address[] addresses) throws Exception {
        for(Address address:addresses) {
            if (address.getType().getName().equalsIgnoreCase("Business Address"))
                prettyPrintAddress(address);
        }
    }

    public void printValidAddresses(Address[] addresses) throws Exception {
        for(Address address:addresses) {
            if(validateAddress(address)) {
                prettyPrintAddress(address);
            }
        }
    }

    public void prettyPrintAddress(Address address) throws Exception {
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        System.out.print(gson.toJson(address));
    }

    public boolean validateAddress(Address address){
        String regex = "\\d+";
        boolean isValidAddress = false;
        try {
            if(address.getPostalCode().matches(regex)&&address.getCountry()!=null&&address.getAddressLineDetail().getLine1()!=null||address.getAddressLineDetail().getLine2()!=null){
                if(address.getCountry().getCode().equalsIgnoreCase("ZA")&&address.getProvinceOrState()!=null){
                    isValidAddress=true;
                }
            }
        }catch (Exception ex){
            System.out.println(address.getCountry().getName() +" address is invalid ");
        }
        return isValidAddress;
    }
}