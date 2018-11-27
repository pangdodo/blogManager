package com.xz.service;

import java.util.List;

import com.xz.entity.Sport_Badminton_S;
import com.xz.entity.Sport_Badminton_sign;
import com.xz.entity.Trole;

public interface Sport_Badminton_Service extends IService<Sport_Badminton_S>{ 
	
	
	
	List<Sport_Badminton_S> selectBadmintonById(Integer id);//根据id查询所有的比赛项目

	
	

}
