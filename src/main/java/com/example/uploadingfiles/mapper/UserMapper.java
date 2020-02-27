package com.example.uploadingfiles.mapper;

import java.util.List;

import com.example.uploadingfiles.data.UserEntity;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMapper {
	
	// @Select("SELECT * FROM pub_users")
	// List<UserEntity> getAll();
	
	// @Select("SELECT * FROM pub_users WHERE user_id = #{id}")
	// UserEntity getOne(Long id);

    @Insert("INSERT INTO pub_users(user_id,user_name,user_password,user_email,login_time,login_ip,login_out_time,isLogin) "+
            "VALUES(#{userId}, #{userName}, #{userPassword},#{userEmail},#{loginTime},#{loginIp},#{loginOutTime},#{isLogin})")
	void insert(UserEntity user);

	// @Update("UPDATE users SET userName=#{userName},nick_name=#{nickName} WHERE id =#{id}")
	// void update(UserEntity user);

	// @Delete("DELETE FROM users WHERE user_id =#{id}")
	// void delete(Long id);

}