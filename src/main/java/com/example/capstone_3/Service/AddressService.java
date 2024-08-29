package com.example.capstone_3.Service;

import com.example.capstone_3.Api.ApiException;
import com.example.capstone_3.DTO.AddressDTO;
import com.example.capstone_3.Model.Address;
import com.example.capstone_3.Model.Customer;
import com.example.capstone_3.Repository.AddressRepository;
import com.example.capstone_3.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
    private final CustomerRepository customerRepository;

    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public void addAddress(AddressDTO addressDTO) {
        Customer customer = customerRepository.findCustomerById(addressDTO.getCustomerId());
        if (customer == null) {
            throw new ApiException("Customer not found");
        }
        Address address = new Address(null , addressDTO.getCity(), addressDTO.getStreet(), addressDTO.getBuildingNumber(),customer);
        addressRepository.save(address);
    }

    public void updateAddress(Integer id,Address address) {
        Address a = addressRepository.findAddressById(id);
        if (a == null) {
            throw new ApiException("Address not found");
        }
        a.setCity(address.getCity());
        a.setStreet(address.getStreet());
        a.setBuildingNumber(address.getBuildingNumber());
        addressRepository.save(a);
    }

    public void deleteAddress(Integer id) {
        Address a = addressRepository.findAddressById(id);
        if (a == null) {
            throw new ApiException("Address not found");
        }
        addressRepository.delete(a);
    }
}
