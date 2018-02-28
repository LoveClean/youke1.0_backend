package com.media.ops.backend.dao.mapper;

import com.media.ops.backend.dao.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(@Param("id") Integer id, @Param("account") String account);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(@Param("id") Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    ////////////////////以上是自动生成的////////////////////////////
    

 	int checkAccount(String account);
 	
 	int checkEmail(String email);
 	
 	User selectLogin(@Param("account")String account, @Param("password")String password);

 	String selectQuestionByAccount(String account);
 	
 	int checkAnswer(@Param("account")String account, @Param("question")String question, @Param("answer")String answer);

 	int updatePasswordByAccount(@Param("account")String account,@Param("passwordNew")String passwordNew);

 	int checkPassword(@Param("password")String password, @Param("userId")Integer userId);

 	int checkEmailByUserId(@Param("email")String email, @Param("userId")Integer userId);
}