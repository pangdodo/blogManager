package com.xz.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name = "t_config")
public class Tconfig {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	private String appid;
	
	private String secret;
	
	private String token;
	
	@Column(name = "mchId")
	private String mchId;
	
	@Column(name = "mchKey")
	private String mchKey;
	
	@Column(name = "subAppId")
	private String subAppId;
	
	@Column(name = "subMchId")
	private String subMchId;
	
	@Column(name = "keyPath")
	private String keyPath;
	
}
