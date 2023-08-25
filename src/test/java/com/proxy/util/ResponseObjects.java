package com.proxy.util;

import com.proxy.bean.Address;
import com.proxy.bean.Country;
import com.proxy.bean.Type;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class ResponseObjects {

    private static final HttpHeaders headers = new HttpHeaders();

    public static ResponseEntity<List<Address>> getListOfAddresses() {
        headers.setContentType(MediaType.APPLICATION_JSON);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(getAddressList());
    }

    public static Address address1(){
        Address address = new Address();
        Country country= new Country();
        Type type = new Type();
        type.setCode("1");
        type.setName("Physical Address");
        country.setCode("ZA");
        country.setName("South Africa");
        address.setId("1");
        address.setPostalCode("12345");
        address.setCityOrTown("City 1");
        address.setCountry(country);
        address.setType(type);
        return address;
    }

    public static Address address2(){
        Address address = new Address();
        Country country= new Country();
        Type type = new Type();
        type.setCode("2");
        type.setName("Postal Address");
        country.setCode("LB");
        country.setName("Lebanon");
        address.setId("2");
        address.setPostalCode("67894");
        address.setCityOrTown("City 2");
        address.setCountry(country);
        address.setType(type);
        return address;
    }

    public static Address address3(){
        Address address = new Address();
        Country country= new Country();
        Type type = new Type();
        type.setCode("3");
        type.setName("Business Address");
        country.setCode("EG");
        country.setName("Egypt");
        address.setId("3");
        address.setPostalCode("67894");
        address.setCityOrTown("City 3");
        address.setCountry(country);
        address.setType(type);
        return address;
    }

    public static List<Address> getAddressList() {
        List<Address> addressList = new ArrayList<>();
        addressList.add(address1());
        addressList.add(address2());
        addressList.add(address3());
        return addressList;
    }
}