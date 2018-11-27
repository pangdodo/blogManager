package com.xz.mapper;

import java.util.List;


import com.xz.entity.Sport_Badminton_sign;
import com.xz.util.MyMapper;

public interface Sport_Badminton_sign_Mapper extends MyMapper<Sport_Badminton_sign> {
	
	List<Sport_Badminton_sign> selectBadminton_signById(Integer id);//根据id查询报名人员信息
	
	

}
