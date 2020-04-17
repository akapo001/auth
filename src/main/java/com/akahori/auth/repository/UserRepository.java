package com.akahori.auth.repository;

import com.akahori.auth.model.UserModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;

@Mapper
public interface UserRepository {

    @Select("SELECT id, name, password, enabled FROM t_user where id = #{id}")
    public UserModel selectByUser(String username);

    @Insert("INSERT INTO t_user(id,name,password,enabled) VALUES (#{id}, #{name}, #{password}, #{enabled})")
    public void save(UserModel userModel);
}