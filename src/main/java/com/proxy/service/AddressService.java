package com.proxy.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.proxy.bean.Address;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class AddressService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    public List<Address> loadAddresses()throws Exception{
        log.info("AddressService :: Entry Method : loadAddresses()");
        List<Address> addressList = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try (FileReader reader = new FileReader("src/main/resources/static/addresses.json"))
        {
            try {
                JsonNode jsonArray = objectMapper.readTree(reader);
                for (JsonNode element : jsonArray) {
                    Address address = objectMapper.treeToValue(element, Address.class);
                    addressList.add(address);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }catch(FileNotFoundException fileNotFoundException){
            System.out.println(fileNotFoundException.getMessage());
        }catch(Exception ioe){
            ioe.printStackTrace();
        }
        log.info("AddressService :: Exit Method : loadAddresses() -> Entry Params : addressList size :: {}", addressList.size());
        return addressList;
    }

    public String prettyPrintAllAddresses(List<Address> addresses) throws Exception {
        StringBuilder prettyPrintAddress = new StringBuilder();
        for(Address address:addresses) {
            prettyPrintAddress.append(prettyPrintAddress(address));
        }
        return prettyPrintAddress.toString();
    }

    public String prettyPrintAddressType(List<Address> addresses,String typeOfAddress) throws Exception {
        log.info("AddressService :: Entry Method : prettyPrintAddressType() -> Entry Params : addressList{} size, typeOfAddress :: {}", addresses.size(),typeOfAddress);
        StringBuilder prettyPrintAddress = new StringBuilder();
        for(Address address:addresses) {
            if (address.getType().getName().equalsIgnoreCase(typeOfAddress))
                prettyPrintAddress.append(prettyPrintAddress(address));
        }
        log.info("AddressService :: Exit Method : prettyPrintAddressType() -> Entry Params : typeOfAddress :: {}", typeOfAddress);
        return prettyPrintAddress.toString();
    }

    public String prettyPrintAddress(Address address) throws Exception {
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        System.out.print(gson.toJson(address));
        return gson.toJson(address);
    }

    public String printValidAddresses(List<Address> addresses) throws Exception {
        StringBuilder prettyPrintValidAddress = new StringBuilder();
        for(Address address:addresses) {
            if(validateAddress(address)) {
                prettyPrintValidAddress.append(prettyPrintAddress(address));
            }
        }
        return prettyPrintValidAddress.toString();
    }

    public boolean validateAddress(Address address){
        String regex = "\\d+";
        boolean isValidAddress = false;
        try {
            log.info("AddressService :: Entry Method : validateAddress() -> Entry Params : address and country name :: {}", address.getCountry().getName());
            if((!(address.getCountry().getName().equalsIgnoreCase("South Africa"))&&!(address.getCountry().getCode().equalsIgnoreCase("ZA")))&&
                    address.getPostalCode().matches(regex)&&address.getCountry()!=null&&(!(address.getAddressLineDetail().getLine1()!=null||address.getAddressLineDetail().getLine2()!=null))){
                isValidAddress = true;
            }
            if((address.getCountry().getName().equalsIgnoreCase("South Africa")&&address.getCountry().getCode().equalsIgnoreCase("ZA")&&address.getProvinceOrState().getName()!=null)&&
            address.getPostalCode().matches(regex)&&address.getCountry()!=null&&(address.getAddressLineDetail().getLine1()!=null||address.getAddressLineDetail().getLine2()!=null)){
                    isValidAddress = true;
            }
        }catch (Exception ex){
            System.out.println(address.getType().getName()+" in "+address.getCityOrTown()+" in "+address.getCountry().getName() +" is an invalid address.");
        }
        log.info("AddressService :: Entry Method : validateAddress() -> Entry Params : isValidAddress :: {}", isValidAddress);
        return isValidAddress;
    }
}