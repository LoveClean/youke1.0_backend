package com.media.ops.backend.dao.mapper;

import org.apache.ibatis.annotations.Select;

import com.media.ops.backend.dao.entity.Member;

public interface MemberMapper {
    int deleteByPrimaryKey(String id);

    int insert(Member record);

    int insertSelective(Member record);

    Member selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Member record);

    int updateByPrimaryKey(Member record);
    

    /**
     * 手机号是否已存在
     *
     * @param mobile
     * @return true：是，false：不存在
     */
    @Select("SELECT IF(COUNT(id)>0,'true','false') FROM t_member WHERE mobile = #{0} and del_flag=0 ")
    boolean mobileExist(String mobile);
}