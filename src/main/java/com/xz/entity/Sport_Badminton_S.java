package com.xz.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 
 * @author xumin
 * 羽毛球竞赛项目创建，简化输入内容
 *
 */

@Table(name = "T_sport_Badminton_s_game")
public class Sport_Badminton_S {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Integer id;

    private String game_name;
    
	private String game_content;
    
    private String game_state;

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGame_name() {
		return game_name;
	}

	public void setGame_name(String game_name) {
		this.game_name = game_name;
	}

	public String getGame_content() {
		return game_content;
	}

	public void setGame_content(String game_content) {
		this.game_content = game_content;
	}

	public String getGame_state() {
		return game_state;
	}

	public void setGame_state(String game_state) {
		this.game_state = game_state;
	}


    
}
