package com.proxy.service;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class AddressServiceUnitTest {

    public  static final String POSTAL_ADDRESS = "Postal Address";
    public  static final String PHYSICAL_ADDRESS = "Physical Address";
    public  static final String BUSINESS_ADDRESS = "Business Address";
    @Autowired
    private AddressService addressService;

    @Test
    public void loadAddressesTest() throws Exception {
        assertEquals(3,addressService.loadAddresses().size());
    }

    @Test
    public void prettyPrintAllAddressesTest() throws Exception{
        String test = "{\n" +
                "  \"id\": \"1\",\n" +
                "  \"type\": {\n" +
                "    \"code\": \"1\",\n" +
                "    \"name\": \"Physical Address\"\n" +
                "  },";
        assertTrue(addressService.prettyPrintAllAddresses(addressService.loadAddresses()).contains(test));
    }

    @Test
    public void prettyPrintPhysicalAddressTest() throws Exception{
        assertTrue(addressService.prettyPrintAddressType(addressService.loadAddresses(),PHYSICAL_ADDRESS).contains(PHYSICAL_ADDRESS));
    }
    @Test
    public void prettyPrintBusinessAddressTest() throws Exception{
        assertFalse(addressService.prettyPrintAddressType(addressService.loadAddresses(),BUSINESS_ADDRESS).contains(PHYSICAL_ADDRESS));
    }
    @Test
    public void prettyPrintPostalAddressTest() throws Exception{
        assertTrue(addressService.prettyPrintAddressType(addressService.loadAddresses(),POSTAL_ADDRESS).contains(POSTAL_ADDRESS));
    }
}