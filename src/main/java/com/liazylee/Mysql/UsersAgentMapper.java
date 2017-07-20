package com.liazylee.Mysql;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * Created by li on 7/11/17.
 */
public interface UsersAgentMapper {
    @Select("SELECT * FROM usersagent")
    List<Map<String, Object>> find(Map<String, Object> user);
    @Select("SELECT * FROM usersagent where isAcitve=#{isAcitve} and dateCreated >= now()-interval 1 day order by dateCreated desc")
    List<Map<String, Object>> callback_find(Map<String, Object> user);

    @Select("SELECT * FROM usersagent where udid=#{udid} and dateCreated >= now()-interval 1 day order by dateCreated desc limit 1")
    List<Map<String, Object>> find_one(Map<String, Object> user);

    @Insert("INSERT INTO usersagent(udid,multipleurl,isAcitve,iscallback,appid,dateCreated) VALUES(#{udid}, #{multipleurl}, #{isAcitve},#{iscallback}, #{appid}, #{dateCreated})")
    void insert(Map<String, Object> user);

    @Update("UPDATE usersagent SET isAcitve=#{isAcitve}  where udid=#{udid} and dateCreated >= now()-interval 1 day order by dateCreated desc limit 1 ")
    void update_one(Map<String, Object> user);

    @Update("UPDATE usersagent SET iscallback=#{iscallback}  where udid=#{udid}   ")
    void callback_update_one(Map<String, Object> user);

}
