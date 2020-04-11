package com.akahori.auth.repository;

import com.akahori.auth.model.UserModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepository {
    public UserModel selectByUser(String username);

    public void save(UserModel userModel);
}