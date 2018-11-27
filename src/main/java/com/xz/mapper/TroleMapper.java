package com.xz.mapper;

import java.util.List;

import com.xz.entity.Trole;
import com.xz.util.MyMapper;

public interface TroleMapper extends MyMapper<Trole> {

    List<Trole> selectRolesByUserId(Integer userid);

}