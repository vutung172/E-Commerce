package com.main.ra.service.Impl;

import com.main.ra.model.dto.UserDto;
import com.main.ra.model.entity.UserEntity;
import com.main.ra.service.MapperUtilService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class MapperUtilServiceImpl implements MapperUtilService {
    public <E, D> D convertEntityToDTO(E entity, Class<D> dtoClass) {
        try {
            D dto = dtoClass.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(entity, dto); // Using Apache Commons BeanUtils for property copying
            return dto;
        } catch (Exception ex) {
            // Handle exception accordingly
            ex.printStackTrace();
            return null;
        }
    }

    public <D, E> E convertDTOToEntity(D dtoClass, Class<E> entity) {
        try {
            E entityBase = entity.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(dtoClass, entityBase); // Using Apache Commons BeanUtils for property copying
            return entityBase;
        } catch (Exception ex) {
            // Handle exception accordingly
            ex.printStackTrace();
            return null;
        }
    }

    public UserDto convertUserEntityToUserDto(UserEntity user){
        UserDto userDto = convertEntityToDTO(user, UserDto.class);
        user.getRoles().forEach(ur -> userDto.getRoles().add(ur.getRole().getRoleName()));
        return userDto;
    }
}
