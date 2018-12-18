package com.xz.service;

import java.util.List;

import com.xz.entity.Sport_Badminton_S;
import com.xz.entity.Sport_Badminton_sign;
import com.xz.entity.Trole;


/**
 * 
 * @author xumin 2018.11.19
 *
 */
public interface Sport_Badminton_sign_Service extends IService<Sport_Badminton_sign>{ 
	
	
	
	
	List<Sport_Badminton_sign> selectBadminton_signById(Integer id);//根据id查询报名人员
	
	
	List<Sport_Badminton_sign> selectbaominByBadmintonId(Integer id);//根据比赛ID查询报名人员
	
	

}
