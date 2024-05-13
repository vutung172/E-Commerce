package com.main.ra.service.Impl;

import com.main.ra.exception.BaseException;
import com.main.ra.model.dto.request.NewAddressRequest;
import com.main.ra.model.entity.AddressEntity;
import com.main.ra.model.entity.UserEntity;
import com.main.ra.repository.AddressRepository;
import com.main.ra.repository.UserRepository;
import com.main.ra.service.AddressService;
import com.main.ra.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MapperUtilServiceImpl mapper;


    public AddressEntity add(Long userId,NewAddressRequest request) {
        UserEntity user = userRepository.findById(userId).orElse(null);
        if (user != null){
            AddressEntity address = mapper.convertDTOToEntity(request, AddressEntity.class);
            address.setUserId(userId);
            return addressRepository.save(address);
        } else {
            throw  new BaseException("exception.UserNotFound", HttpStatus.NOT_FOUND);
        }
    }

    public boolean delete(Long userId, Long addressId){
        AddressEntity address = addressRepository.findAddressEntitiesByUserIdAndId(userId,addressId).orElse(null);
        if (address != null){
            addressRepository.delete(address);
            return true;
        } else {
            throw new BaseException("exception.AddressNotFound",HttpStatus.NOT_FOUND);
        }

    }

    public List<AddressEntity> findAll(Long userId){
        return addressRepository.findAllByUserId(userId);
    }
    public AddressEntity findById(Long userId, Long addressId){
        return addressRepository.findAddressEntitiesByUserIdAndId(userId,addressId).orElse(null);
    }
}
