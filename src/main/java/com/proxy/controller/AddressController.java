package com.proxy.controller;

import com.proxy.bean.Address;
import com.proxy.service.AddressService;
import com.proxy.util.AddressType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService){
        this.addressService = addressService;
    }

    @GetMapping("/printAllAddresses.do")
    public String prettyPrintAllAddresses() throws Exception {
        List<Address> allAddresses = addressService.loadAddresses();
        return addressService.prettyPrintAllAddresses(allAddresses);
    }

    @GetMapping("/printPhysicalAddress.do")
    public String printPhysicalAddress() throws Exception {
        List<Address> allAddresses = addressService.loadAddresses();
        return addressService.prettyPrintAddressType(allAddresses, AddressType.PHYSICAL_ADDRESS);
    }

    @GetMapping("/printPostalAddress.do")
    public String printPostalAddress() throws Exception {
        List<Address> allAddresses = addressService.loadAddresses();
        return addressService.prettyPrintAddressType(allAddresses, AddressType.POSTAL_ADDRESS);
    }
    @GetMapping("/printBusinessAddress.do")
    public String printBusinessAddress() throws Exception {
        List<Address> allAddresses = addressService.loadAddresses();
        return addressService.prettyPrintAddressType(allAddresses, AddressType.BUSINESS_ADDRESS);
    }

    @GetMapping("/printValidAddresses.do")
    public String printValidAddresses() throws Exception {
        List<Address> allAddresses = addressService.loadAddresses();
        return addressService.printValidAddresses(allAddresses);
    }
}