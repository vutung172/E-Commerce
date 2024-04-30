package com.main.ra.service.Impl;

import com.main.ra.exception.BaseException;
import com.main.ra.model.Enum.UserStatus;
import com.main.ra.model.dto.UserDetailAdapter;
import com.main.ra.model.dto.UserDto;
import com.main.ra.model.dto.request.SignUpRequest;
import com.main.ra.model.dto.request.PageableRequest;
import com.main.ra.model.dto.response.PageDataResponse;
import com.main.ra.model.entity.RoleEntity;
import com.main.ra.model.entity.UserEntity;
import com.main.ra.repository.UserRepository;
import com.main.ra.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@NoArgsConstructor
@AllArgsConstructor
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleServiceImpl userRoleService;
    @Autowired
    private RoleServiceImpl roleService;
    @Autowired
    private PageableServiceImpl pageableService;
    @Autowired
    private MapperUtilServiceImpl mapper;
    @Autowired
    private PasswordEncoder encoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findUserEntitiesByUserName(username).orElse(null);
        if (user != null){
            UserDetailAdapter userAdapter = new UserDetailAdapter();
            userAdapter.setUser(user);
            return userAdapter;
        }
        throw new UsernameNotFoundException("exception.UserNotFound");
    }

    public UserEntity addUser(SignUpRequest req){
        UserEntity existedUser = userRepository.findUserEntitiesByUserName(req.getUserName()).orElse(null);
        UserEntity phoneExistedUser = userRepository.findUserEntitiesByPhone(req.getPhone()).orElse(null);
        if (existedUser == null && phoneExistedUser == null){
            UserEntity user = mapper.convertDTOToEntity(req, UserEntity.class);
            user.setPassword(encoder.encode(req.getPassword()));
            UserEntity userDB = userRepository.save(user);
            req.getRoles().forEach(r -> {
                RoleEntity role = roleService.findByRoleName(r);
                if (role != null){
                    userRoleService.addUserRole(userDB.getId(),role.getId());
                }
            });
            return userDB;
        } else if (existedUser != null){
            throw new BaseException("exception.UserExisted",HttpStatus.BAD_REQUEST);
        } else {
            throw  new BaseException("exception.PhoneExisted",HttpStatus.BAD_REQUEST);
        }
    }

    public UserStatus switchStatus(Long userId){
        UserEntity user = userRepository.findById(userId).orElse(null);
        if (user != null){
            user.setStatus(!user.getStatus());
            if (userRepository.save(user).getStatus()){
                return UserStatus.ACTIVE;
            } else {
                return UserStatus.BLOCK;
            }
        } else {
            throw  new BaseException("exception.UserNotFound",HttpStatus.NOT_FOUND);
        }
    }

    public Page<UserDto> findAllByUserName(String key, PageableRequest pageableRequest){
        try{
            String searchKey = "%".concat(key.concat("%")).replace(" ","");
            return userRepository.findAllByUserNameLikeIgnoreCase(searchKey, pageableRequest)
                    .map(u -> mapper.convertUserEntityToUserDto(u));
        }catch (Exception e){
            throw new BaseException("exception.pageable.PageNotFound",HttpStatus.FORBIDDEN);
        }
    }

    public UserEntity findByUsernameAndPassword(String userName, String password){
        return userRepository.findUserEntitiesByUserNameAndPassword(userName,password).orElse(null);
    }

    public Page<UserDto> findAll(PageableRequest pageableRequest){
        try{
            return userRepository.findAll(pageableRequest).map(u -> mapper.convertUserEntityToUserDto(u));
        }catch (Exception e){
            throw new BaseException("exception.pageable.PageNotFound",HttpStatus.FORBIDDEN);
        }
    }
}
