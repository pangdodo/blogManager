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
    private String game_item;
    private String game_items;
	private String game_pay;
    private String game_pays;
	private String game_content;
    
    private String game_state;
    
    private String creater;
    
    public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public String getGame_items() {
		return game_items;
	}

	public void setGame_items(String game_items) {
		this.game_items = game_items;
	}

	public String getGame_pay() {
		return game_pay;
	}

	public void setGame_pay(String game_pay) {
		this.game_pay = game_pay;
	}

	public String getGame_pays() {
		return game_pays;
	}

	public void setGame_pays(String game_pays) {
		this.game_pays = game_pays;
	}



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

	public String getGame_item() {
		return game_item;
	}

	public void setGame_item(String game_item) {
		this.game_item = game_item;
	}


    
}
