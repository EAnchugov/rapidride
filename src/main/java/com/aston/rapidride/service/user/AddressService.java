package com.aston.rapidride.service.user;

import com.aston.rapidride.dto.mapper.user.AddressMapper;
import com.aston.rapidride.dto.user.AddressDto;
import com.aston.rapidride.repository.user.AddressRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AddressMapper addressMapper;

    public List<AddressDto> getAllAddresses() {
        return addressRepository.findAll()
                .stream()
                .map(addressMapper::entityToAddress)
                .toList();
    }

    public AddressDto getAddressById(Long id) {
        return addressRepository.findById(id)
                .map(addressMapper::entityToAddress)
                .orElseThrow(() -> new EntityNotFoundException("Address " + id + " is not found"));
    }
}
