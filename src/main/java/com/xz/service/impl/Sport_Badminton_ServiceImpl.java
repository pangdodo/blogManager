package com.xz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xz.entity.Sport_Badminton_S;
import com.xz.entity.Trole;
import com.xz.mapper.Sport_Badminton_S_Mapper;
import com.xz.service.Sport_Badminton_Service;
import com.xz.service.TroleService;

@Service("Sport_Badminton_Service")
public class Sport_Badminton_ServiceImpl  extends BaseService<Sport_Badminton_S> implements Sport_Badminton_Service{
	
	@Autowired
	private Sport_Badminton_S_Mapper  sport_Badminton_S_Mapper;
	
	
	
	@Override
	public List<Sport_Badminton_S> selectBadmintonById(Integer id){
		
		return  sport_Badminton_S_Mapper.selectBadmintonById(id);
		
		
	}
	
	

}
