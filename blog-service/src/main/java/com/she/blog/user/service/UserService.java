package com.she.blog.user.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.she.blog.user.dao.entity.UserEntity;
import com.she.blog.user.dao.mapper.UserMapper;
import com.she.blog.user.model.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = this.getUserByUserName(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        return new User(userEntity.getUserName(), userEntity.getPassword(), new ArrayList<>());
    }


    public UserEntity getUserByUserName(String userName){
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",userName);
        List<UserEntity> userEntities = userMapper.selectList(queryWrapper);
        if(CollectionUtils.isEmpty(userEntities)){
            return null;
        }
        return userEntities.get(0);
    }

    public List<UserDto> getAllUser() {
        List<UserDto> resultList = new ArrayList<>();
        List<UserEntity> userEntities = userMapper.selectList(new QueryWrapper<>());
        if (CollectionUtils.isEmpty(userEntities)) {
            return resultList;
        }
        for (UserEntity userEntity : userEntities) {
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(userEntity, userDto);
            resultList.add(userDto);
        }
        return resultList;
    }


}
