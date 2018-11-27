package com.xz.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.xz.util.JsonUtils;

import lombok.Data;

/**
 * wechat mp properties
 *
 * @author Binary Wang(https://github.com/binarywang)
 */
@Data
@ConfigurationProperties(prefix = "wx.mp")
public class WxMpProperties {
private Map<String,String> config;
	
	private List<MpConfig> configs = new ArrayList<MpConfig>();
	
	public WxMpProperties() {
		if(configs != null) {
			mapToList();
		}
	}
	
	public void mapToList()
	{
		if(config!= null) {
			
			MpConfig mpCon = new MpConfig();
			mpCon.setAppId(config.get("appId"));
			mpCon.setSecret(config.get("secret"));
			mpCon.setToken(config.get("token"));
			mpCon.setAesKey(config.get("aesKey"));
			
			configs.add(mpCon);

		}
	}
    
    @Data
    public static class MpConfig {
        /**
         * 设置微信公众号的appid
         */
    	private String appId;

        /**
         * 设置微信公众号的app secret
         */
        private String secret;

        /**
         * 设置微信公众号的token
         */
        private String token;

        /**
         * 设置微信公众号的EncodingAESKey
         */
        private String aesKey;
    }

    @Override
    public String toString() {
        return JsonUtils.toJson(this);
    }
}
