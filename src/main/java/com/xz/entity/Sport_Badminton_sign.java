package com.xz.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 
 * @author xumin 2018.11.19  比赛项目报名实体类
 *
 */
@Table(name = "t_sport_badminton_sign")
public class Sport_Badminton_sign {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Integer id;
	  private Integer  Badminton_id;
	  
	  private String name;
	  private String p_id;
	 
	  private String Phone_id;
	  private String address;
	  private String Weixin_id;
	  public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getBadminton_id() {
		return Badminton_id;
	}
	public void setBadminton_id(Integer badminton_id) {
		Badminton_id = badminton_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getP_id() {
		return p_id;
	}
	public void setP_id(String p_id) {
		this.p_id = p_id;
	}
	public String getPhone_id() {
		return Phone_id;
	}
	public void setPhone_id(String phone_id) {
		Phone_id = phone_id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getWeixin_id() {
		return Weixin_id;
	}
	public void setWeixin_id(String weixin_id) {
		Weixin_id = weixin_id;
	}
	
	  
	  
	
}
