package com.main.ra.controller.user;

import com.main.ra.model.dto.UserDto;
import com.main.ra.model.dto.request.UserRequest;
import com.main.ra.model.dto.response.DataResponse;
import com.main.ra.model.dto.response.MessageResponse;
import com.main.ra.model.entity.UserEntity;
import com.main.ra.util.FileServiceImpl;
import com.main.ra.service.Impl.MapperUtilServiceImpl;
import com.main.ra.service.Impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.Collections;

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

    @GetMapping
    public ResponseEntity getAccount(
         @RequestHeader Long userId
    ){
        UserEntity user = userService.findById(userId);
        if (user != null){
            UserDto userDto = mapper.convertEntityToDTO(user, UserDto.class);
            return ResponseEntity.ok(DataResponse.builder().data(Collections.singletonList(userDto)).build());
        } else {
            return new ResponseEntity<>(MessageResponse.builder().message("exception.UserNotFound").build(),HttpStatus.NOT_FOUND);
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

}
