package com.main.ra.controller.user;

import com.main.ra.exception.BaseException;
import com.main.ra.model.dto.AddressDto;
import com.main.ra.model.dto.UserDto;
import com.main.ra.model.dto.request.NewAddressRequest;
import com.main.ra.model.dto.request.UserRequest;
import com.main.ra.model.dto.response.DataResponse;
import com.main.ra.model.dto.response.MessageResponse;
import com.main.ra.model.entity.AddressEntity;
import com.main.ra.model.entity.UserEntity;
import com.main.ra.service.Impl.AddressServiceImpl;
import com.main.ra.util.FileServiceImpl;
import com.main.ra.service.Impl.MapperUtilServiceImpl;
import com.main.ra.service.Impl.UserServiceImpl;
import com.main.ra.util.MessageLoader;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api.myservice.com/v1/user/account")
@CrossOrigin
@Validated
public class AccountApi {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private MapperUtilServiceImpl mapper;
    @Autowired
    private FileServiceImpl fileService;
    @Autowired
    private AddressServiceImpl addressService;
    @Autowired
    private MessageLoader loader;

    @GetMapping
    public ResponseEntity getAccount(
         @RequestHeader Long userId
    ){
        UserEntity user = userService.findById(userId);
        if (user != null){
            UserDto userDto = mapper.convertEntityToDTO(user, UserDto.class);
            return ResponseEntity.ok(DataResponse.builder().data(Collections.singletonList(userDto)).build());
        } else {
            return new ResponseEntity<>(MessageResponse.builder().message(loader.getMessage("exception.UserNotFound")).build(),HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(consumes = {"multipart/form-data","application/*",MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity updateAccount(
            @RequestHeader Long userId,
            @RequestPart @Valid UserRequest request,
            @RequestPart("file") MultipartFile file
    ){
        request.setFile(file);
        UserEntity userEntity = userService.update(userId,request);
        UserDto userDto = mapper.convertEntityToDTO(userEntity, UserDto.class);
        return ResponseEntity.ok(new DataResponse<>(Collections.singletonList(userDto)));
    }

    @PostMapping("/address")
    public ResponseEntity addNewAddress(
            @RequestHeader Long userId,
            @RequestBody @Valid NewAddressRequest request
    ){
        AddressEntity address = addressService.add(userId,request);
        if (address != null){
            AddressDto dto = mapper.convertEntityToDTO(address, AddressDto.class);
            return ResponseEntity.ok(dto);
        } else {
            throw new BaseException("exception.AddressCreatedFailure",HttpStatus.FORBIDDEN);
        }

    }

    @DeleteMapping("/address/{id}")
    public ResponseEntity deleteAddress(
            @RequestHeader Long userId,
            @PathVariable Long id
    ){
        if (addressService.delete(userId,id)){
            return ResponseEntity.ok(MessageResponse.builder().message(loader.getMessage("success.DeleteCart")).build());
        } else {
            throw new BaseException("exception.AddressDeletedFailure",HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/address")
    public ResponseEntity getAllUserAddress(
            @RequestHeader Long userId
    ){
        List<AddressEntity> addresses = addressService.findAll(userId);
        List<AddressDto> dto = addresses.stream().map(a -> mapper.convertEntityToDTO(a, AddressDto.class)).toList();
        return ResponseEntity.ok(new DataResponse<>(dto));
    }

    @GetMapping("/address/{id}")
    public ResponseEntity getAddressById(
            @RequestHeader Long userId,
            @PathVariable Long id
    ){
        AddressEntity address = addressService.findById(userId,id);
        if (address != null){
            AddressDto addressDto =  mapper.convertEntityToDTO(address, AddressDto.class);
            return ResponseEntity.ok(new DataResponse<>(Collections.singletonList(addressDto)));
        } else {
            throw  new BaseException("exception.AddressNotFound",HttpStatus.NOT_FOUND);
        }
    }

}
