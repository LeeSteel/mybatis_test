package com.demo.mybatis.mapper;

import com.demo.mybatis.domain.TRole;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @version V1.0
 * @Title:
 * @Package
 * @Description:
 * @author: 李钢 2580704698@qq.com
 * @date: 2021/7/29 23:15
 * @Copyright: Copyright (c) 2019
 */
public interface RoleMapper {

    TRole getRole(Long id);

    @Select("select id, role_name as roleName, note from t_role where id=#{id}")
    TRole getRole2(Long id);

    int insertRole(TRole role);

    int deleteRole(Long id);

    int updateRole(TRole role);


    List<TRole> findRoles(String roleName);
}
