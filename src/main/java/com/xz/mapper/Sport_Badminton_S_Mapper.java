package com.xz.mapper;

import java.util.List;

import com.xz.entity.Sport_Badminton_S;
import com.xz.util.MyMapper;

public interface Sport_Badminton_S_Mapper  extends MyMapper<Sport_Badminton_S>{
	
	
	List<Sport_Badminton_S> selectBadmintonById(Integer id);//根据id查询所有的比赛项目
	List<Sport_Badminton_S> selectBadmintonByCreater(String creater);//根据id查询所有的比赛项目
	
	
	

}
