package com.liazylee.Mysql;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * Created by li on 7/11/17.
 */
public interface UsersMapper {


    @Select("SELECT * FROM users  WHERE udid = #{udid} ")
    List<Map<String, Object>> find_one(String udid);


    @Insert("INSERT INTO users(udid,appid,dateCreated) VALUES(#{udid}, #{appid}, #{dateCreated})")
    void insert(Map<String, Object> user);


}
