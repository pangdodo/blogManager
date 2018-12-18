package com.xz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xz.entity.Sport_Badminton_sign;

import com.xz.mapper.Sport_Badminton_sign_Mapper;

import com.xz.service.Sport_Badminton_sign_Service;

/**
 * 
 * @author xumin  2018.11.19 羽毛球报名查询服务
 *
 */


@Service("Sport_Badminton_sign_Service")
public class Sport_Badminton_sign_ServiceImpl  extends BaseService<Sport_Badminton_sign> implements Sport_Badminton_sign_Service{
	
	@Autowired
	private Sport_Badminton_sign_Mapper  sport_Badminton_sign_Mapper;
	
	
	
	@Override
	public List<Sport_Badminton_sign> selectBadminton_signById(Integer id){
		
		return  sport_Badminton_sign_Mapper.selectBadminton_signById(id);
		
		
	}
	
	@Override
	public List<Sport_Badminton_sign> selectbaominByBadmintonId(Integer id){
		
		return  sport_Badminton_sign_Mapper.selectbaominByBadmintonId(id);
		
		
	}
	
	

}
