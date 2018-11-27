package com.xz.mapper;

import java.util.HashMap;
import java.util.List;

import com.xz.entity.Tmenu;
import com.xz.util.MyMapper;

public interface TmenuMapper extends MyMapper<Tmenu> {

    List<Tmenu> selectMenusByRoleId(Integer roleid);

    List<Tmenu> selectByParentIdAndRoleId(HashMap<String,Object> paraMap);

}