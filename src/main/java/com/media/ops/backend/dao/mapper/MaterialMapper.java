package com.media.ops.backend.dao.mapper;

import com.media.ops.backend.dao.entity.Material;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MaterialMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Material record);

    int insertSelective(Material record);

    Material selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Material record);

    int updateByPrimaryKey(Material record);
    
    List<Material> selectByGroupId(Integer groupId);
    
    List<Material> selectList();
    
    List<Material> selectListByIds(@Param("materialIds") List<Integer> materialIds);
    
    List<Material> selectByNameTypeGroupId(@Param("materialName") String materialName,@Param("materialType") String materialType,@Param("groupId") Integer groupId);

    int checkExistDelivery(@Param("admaterialId")Integer admaterialId);

    @Select("SELECT COUNT(*) FROM tbmaterial WHERE del_flag=0")
    int sumMaterial();

    List<Material> selectList2();

    List<Material> selectMusicList();

    List<Material> selectByNameTypeGroupId2(@Param("materialName") String materialName,@Param("materialType") String materialType,@Param("groupId") Integer groupId);
}