package com.media.ops.backend.dao.mapper;

import com.media.ops.backend.dao.entity.Device;
import com.media.ops.backend.dao.entity.User;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(@Param("id") Integer id, @Param("account") String account);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(@Param("id") Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    ////////////////////以上是自动生成的////////////////////////////
    

 	int checkAccount(@Param("account")String account, @Param("type")Integer type);
 	
 	int checkEmail(@Param("email")String email, @Param("type")Integer type);
 	
 	int checkPhone(@Param("phone")String phone, @Param("type")Integer type);
 	
 	int checkAccountNotSelf(@Param("uid")Integer uid,@Param("account")String account, @Param("type")Integer type);
 	
 	int checkEmailNotSelf(@Param("uid")Integer uid,@Param("email")String email, @Param("type")Integer type);
 	
 	int checkPhoneNotSelf(@Param("uid")Integer uid,@Param("phone")String phone, @Param("type")Integer type);
 	
 	
 	User selectByAccount(@Param("account")String account, @Param("password")String password);
 	User selectByPhone(@Param("phone")String phone, @Param("password")String password);
 	User selectByEmail(@Param("email")String email, @Param("password")String password);

 	String selectQuestionByAccount(String account);
 	
 	int checkAnswer(@Param("account")String account, @Param("question")String question, @Param("answer")String answer);

 	int updatePasswordByAccount(@Param("account")String account,@Param("passwordNew")String passwordNew);

 	int checkPassword(@Param("password")String password, @Param("userId")Integer userId);

 	int checkEmailByUserId(@Param("email")String email, @Param("userId")Integer userId);
 	
 	User findByMobile(String mobile);
 	
 	List<User> selectList();
 	
 	int updateStatusById(@Param("account")String account, @Param("status")Integer status);
 	
 	User selectByAccountEmail(@Param("account") String account,@Param("email") String email);
 	
 	User selectByName(@Param("account") String account);
 	
 	List<User> selectPlayerList();
 	
}