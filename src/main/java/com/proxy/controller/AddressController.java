package com.proxy.controller;

import com.proxy.bean.Address;
import com.proxy.service.AddressService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService){
        this.addressService = addressService;
    }

    @RequestMapping("/printAllAddresses.do")
    public void prettyPrintAllAddresses() throws Exception {
        Address[] allAddresses = addressService.loadAddresses();
        addressService.prettyPrintAllAddresses(allAddresses);
    }

    @RequestMapping("/printPhysicalAddress.do")
    public void printPhysicalAddress() throws Exception {
        Address[] allAddresses = addressService.loadAddresses();
        addressService.printPhysicalAddress(allAddresses);
    }

    @RequestMapping("/printPostalAddress.do")
    public void printPostalAddress() throws Exception {
        Address[] allAddresses = addressService.loadAddresses();
        addressService.printPostalAddress(allAddresses);
    }
    @RequestMapping("/printBusinessAddress.do")
    public void printBusinessAddress() throws Exception {
        Address[] allAddresses = addressService.loadAddresses();
        addressService.printBusinessAddress(allAddresses);
    }

    @RequestMapping("/printValidAddresses.do")
    public void printValidAddresses() throws Exception {
        Address[] allAddresses = addressService.loadAddresses();
        addressService.printValidAddresses(allAddresses);
    }
}

