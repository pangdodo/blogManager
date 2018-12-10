package com.xz.web;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.xz.config.WxMpConfiguration;
import com.xz.entity.Sport_Badminton_S;
import com.xz.entity.Sport_Badminton_sign;
import com.xz.model.JqgridBean;
import com.xz.model.PageRusult;
import com.xz.service.Sport_Badminton_Service;
import com.xz.service.Sport_Badminton_sign_Service;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import tk.mybatis.mapper.entity.Example;



/**
 * 
 * @author xumin
 *  微信端展示比赛相关  控制器
 *
 */
@RestController
@RequestMapping("/weixin")
public class WX_badminton_controller {

	@Resource
	private Sport_Badminton_Service sport_Badminton_Service;
	@Resource
	private Sport_Badminton_sign_Service sport_Badminton_sign_Service;
	
	   @RequestMapping("/list")
	  //  @RequiresPermissions(value = {"羽毛球赛事管理"})
	    public String tobadmintonmanage(Map map,@RequestParam(value = "code", required = false, defaultValue = "1") String code,
	    		@RequestParam String appid) {
		 
		   System.out.println("获取到的appid为：" + appid);
		   WxMpService mpService = WxMpConfiguration.getMpServices().get(appid);

	        try {
	            WxMpOAuth2AccessToken accessToken = mpService.oauth2getAccessToken(code);
	            WxMpUser user = mpService.oauth2getUserInfo(accessToken, null);
	            System.out.println("获取到的user为：" + user.toString());
	            map.put("user", user);
	        } catch (WxErrorException e) {
	            e.printStackTrace();
	        }
		   System.out.println("比赛报名项目列表");
		   
		   System.out.println("获取到的code为：" + code);
		   
	        return "power/wxbadmintons";
	    }
	
	
	@RequestMapping("/badmintonbaomin")
	   public String tobadminton() {
			 
		   System.out.println("点击报名开始 ");
	        return "power/wxbadminton_show";
	    }
	
	@RequestMapping("/badmintonbaomin1111")
	  //  @RequiresPermissions(value = {"羽毛球赛事管理"})
	    public Map<String, Object> showbadminton(@RequestParam("id") int id) {
		   
		   System.out.println("显示选择的比赛信息");
		   LinkedHashMap<String, Object> resultmap = new LinkedHashMap<String, Object>();
		   Sport_Badminton_S sport_Badminton_S = sport_Badminton_Service.selectByKey(id);
		   if (sport_Badminton_S == null) {
               resultmap.put("state", "fail");
               resultmap.put("mesg", "无法找到该记录");
               return resultmap;
           }

       resultmap.put("sport_Badminton_S", sport_Badminton_S);
       resultmap.put("state", "success");
       resultmap.put("mesg", "获取成功");
		   
		   
		   
	        return resultmap;
	    }
	
	
	@GetMapping(value = "/lists")
   // @RequiresPermissions(value = {"比赛项目管理"})
  public Map<String, Object> list(JqgridBean jqgridbean
         /*String userName,@RequestParam(value="page",required=false)Integer page*/
		) throws Exception {
	 
	 
	 LinkedHashMap<String, Object> resultmap = new LinkedHashMap<String, Object>();
      //  LinkedHashMap<String, Object> datamap = new LinkedHashMap<String, Object>();
        
        Example sport_Badminton_S = new Example(Sport_Badminton_S.class);
        Example.Criteria criteria = sport_Badminton_S.or();
        
        if (StringUtils.isNotEmpty(jqgridbean.getSearchField())) {
            if ("game_name".equalsIgnoreCase(jqgridbean.getSearchField())) {
                if ("eq".contentEquals(jqgridbean.getSearchOper())) {
                    criteria.andEqualTo("game_name",jqgridbean.getSearchString());
                }
            }
        }

       // PageHelper.startPage(jqgridbean.getPage(), jqgridbean.getLength());
        List<Sport_Badminton_S> badmintonList = sport_Badminton_Service.selectByExample(sport_Badminton_S);
       // PageRusult<Sport_Badminton_S> pageRusult =new PageRusult<Sport_Badminton_S>(badmintonList);

        /*Integer totalrecords = roleService.countByExample(troleExample);//总记录数
        Page pagebean = new Page(jqgridbean.getLength() * ((jqgridbean.getPage() > 0 ? jqgridbean.getPage() : 1) - 1), jqgridbean.getLength(), totalrecords);
        troleExample.setPage(pagebean);
        troleExample.setOrderByClause(jqgridbean.getSidx() + " " + jqgridbean.getSord());
        List<Trole> roleList = roleService.selectByExample(troleExample);*/

      //  resultmap.put("currpage", String.valueOf(pageRusult.getPageNum()));
        //resultmap.put("totalpages", String.valueOf(pageRusult.getPages()));
        //resultmap.put("totalrecords", String.valueOf(pageRusult.getTotal()));
        
        resultmap.put("code", 0);
        resultmap.put("msg", "");
        resultmap.put("count", badmintonList.size());
        resultmap.put("data", badmintonList);
        
        
        //badmintonList.indexOf(sport_Badminton_S)

	 return resultmap;
 
 }
	
	@ResponseBody
	@RequestMapping("/selectBadmintonById")
    //@RequiresPermissions(value = {"编辑比赛信息"})
    public Map<String, Object> selectBadmintonById(Sport_Badminton_S sport_Badminton_S) {
    	
    	System.out.println("微信端报名 显示比赛内容信息 ");
    	
    	System.out.println("微信端报名 显示比赛内容信息 ID==="+sport_Badminton_S.getId());
        LinkedHashMap<String, Object> resultmap = new LinkedHashMap<String, Object>();
        try {
            if (sport_Badminton_S.getId() != null && !sport_Badminton_S.getId().equals(0)) {
            	sport_Badminton_S = sport_Badminton_Service.selectByKey(sport_Badminton_S.getId());
                if (sport_Badminton_S == null) {
                    resultmap.put("state", "fail");
                    resultmap.put("mesg", "无法找到该记录");
                    return resultmap;
                }
            } else {
                resultmap.put("state", "fail");
                resultmap.put("mesg", "无法找到该记录的id");
                return resultmap;
            }

            resultmap.put("sport_Badminton_S", sport_Badminton_S);
            resultmap.put("state", "success");
            resultmap.put("mesg", "获取成功");
            return resultmap;
        } catch (Exception e) {
            e.printStackTrace();
            resultmap.put("state", "fail");
            resultmap.put("mesg", "获取失败，系统异常");
            return resultmap;
        }
    }
	
	 /**
     * @param sport_Badminton_sign
     * 报名比赛
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addupdate_sign")
    //@RequiresPermissions(value = {"比赛项目管理"})
    
    
    public Map<String, Object> addupdatebatminton(Sport_Badminton_sign sport_Badminton_sign ){
    	LinkedHashMap<String, Object> resultmap = new LinkedHashMap<String, Object>();
    	try {
    		
    		System.out.println("进入新建项目方法");

    		if (sport_Badminton_sign.getId() == null) {//新建
    			  Example badminton_signExample = new Example(Sport_Badminton_sign.class);
    			  badminton_signExample.or().andEqualTo("Weixin_id",sport_Badminton_sign.getWeixin_id());
    			  List<Sport_Badminton_sign> badminton_signlist = sport_Badminton_sign_Service.selectByExample(badminton_signExample);
    			  if (badminton_signlist != null && badminton_signlist.size() > 0) {
                      resultmap.put("state", "fail");
                      resultmap.put("mesg", "你已报名该比赛项目");
                      return resultmap;
                  }
    			  sport_Badminton_sign_Service.saveNotNull(sport_Badminton_sign);
              } else {//编辑
            	  Sport_Badminton_sign oldObject = sport_Badminton_sign_Service.selectByKey(sport_Badminton_sign.getId());
                  if (oldObject == null) {
                      resultmap.put("state", "fail");
                      resultmap.put("mesg", "报名异常");
                      return resultmap;
                  } else {
                	  sport_Badminton_sign_Service.updateNotNull(sport_Badminton_sign);
                  }
              }
              resultmap.put("state", "success");
              resultmap.put("mesg", "操作成功");
    		
                 return  resultmap;
    		
    	}
    	catch(Exception e) {
    		e.printStackTrace();
            resultmap.put("state", "fail");
            resultmap.put("mesg", "操作失败，系统异常");
    		return  resultmap;
    		
    	}
    		
    	}
	
}
