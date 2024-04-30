package com.main.ra.controller.admin;

import com.main.ra.model.Enum.UserStatus;
import com.main.ra.model.dto.UserDto;
import com.main.ra.model.dto.response.MessageResponse;
import com.main.ra.model.dto.response.PageDataResponse;
import com.main.ra.model.dto.request.PageableRequest;
import com.main.ra.model.entity.UserRoleEntity;
import com.main.ra.service.Impl.PageableServiceImpl;
import com.main.ra.service.Impl.UserRoleServiceImpl;
import com.main.ra.service.Impl.UserServiceImpl;
import com.main.ra.util.MessageLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api.myservice.com/v1/admin/users")
public class UserApi {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private UserRoleServiceImpl userRoleService;
    @Autowired
    private MessageLoader loader;
    @Autowired
    private PageableServiceImpl pageableService;

    @GetMapping
    public ResponseEntity findAllProduct(
            @RequestParam Integer page,
            @RequestParam Integer size,
            @RequestParam String sort
    ){
        Sort sortType = pageableService.validateSortType(sort);
        PageableRequest pageableRequest = new PageableRequest(page,size,sortType);
        Page<UserDto> list = userService.findAll(pageableRequest);
        PageDataResponse<UserDto> products = new PageDataResponse<>(list.getContent(), list.getTotalPages(), list.getNumber(),list.getSize(),sort);
        return ResponseEntity.ok(products);
    }

    @PostMapping("/{userId}/role/{roleId}")
    public ResponseEntity addUserRole(
            @PathVariable Long userId,
            @PathVariable Long roleId
    ){
        List<UserRoleEntity> roles = userRoleService.updateUserRole(userId,roleId);
        if (!roles.isEmpty()){
            return ResponseEntity.ok(new MessageResponse(loader.getMessage("success.AddRole")));
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/{userId}/role/{roleId}")
    public ResponseEntity deleteUserRole(
            @PathVariable Long userId,
            @PathVariable Long roleId
    ){
        if (userRoleService.deleteRole(userId,roleId)){
            return ResponseEntity.ok(new MessageResponse(loader.getMessage("success.DeleteRole")));
        } else {
            return new ResponseEntity<MessageResponse>
                    (MessageResponse.builder()
                        .message(loader.getMessage("failure.DeleteRole"))
                            .build()
                    ,HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity changeUserStatus(
            @PathVariable Long userId
    ){
        UserStatus status = userService.switchStatus(userId);
        return ResponseEntity.ok(new MessageResponse(status + " user success"));
    }

    @GetMapping("/search")
    public ResponseEntity findUserByUserName(
            @RequestHeader String search,
            @RequestParam Integer page,
            @RequestParam Integer size,
            @RequestParam String sort
    ){
        Sort sortType = pageableService.validateSortType(sort);
        PageableRequest pageableRequest = new PageableRequest(page,size,sortType);
        Page<UserDto> list = userService.findAllByUserName(search, pageableRequest);
        PageDataResponse<UserDto> users = new PageDataResponse<>(list.getContent(), list.getTotalPages(), list.getNumber(), list.getSize(),sort);
        return ResponseEntity.ok(users);
    }

}
