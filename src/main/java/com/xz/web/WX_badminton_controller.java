package com.xz.web;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xz.entity.Sport_Badminton_S;
import com.xz.entity.Sport_Badminton_sign;
import com.xz.model.JqgridBean;
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
@Controller
@RequestMapping("/wx")
public class WX_badminton_controller {

	@Resource
	private Sport_Badminton_Service sport_Badminton_Service;
	@Resource
	private Sport_Badminton_sign_Service sport_Badminton_sign_Service;
	
	

	
	@RequestMapping("/list")
	  //  @RequiresPermissions(value = {"羽毛球赛事管理"})
	    public String tobadmintonmanage(Map  map ,@RequestParam(value = "code", required = false, defaultValue = "1") String code) {
		    
		   //map.put("code",code);
		  //System.out.println("取到威信啊啊啊code啊啊==="+code);
		   
		  // System.out.println("取到威信啊啊map==="+map.toString());
		   //System.out.println("比赛报名项目列表");
		   
		   WxMpInMemoryConfigStorage config = new WxMpInMemoryConfigStorage();
			config.setAppId("wxc0d62b8d4a6b6f0a"); // 设置微信公众号的appid
			config.setSecret("8857e2f29ae07acd6b19b5ff290a06fd"); // 设置微信公众号的app corpSecret
			config.setToken(null); // 设置微信公众号的token
			config.setAesKey(null); // 设置微信公众号的EncodingAESKey

			WxMpService wxService = new WxMpServiceImpl();// 实际项目中请注意要保持单例，不要在每次请求时构造实例，具体可以参考demo项目
			wxService.setWxMpConfigStorage(config);
			
			// 用户的openid及相关信息在下面地址获得
			try {
				WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxService.oauth2getAccessToken(code);
				WxMpUser wmUser = wxService.oauth2getUserInfo(wxMpOAuth2AccessToken, null);
				//model.addAttribute("wmUser", wmUser);
				map.put("openid",code);
				map.put("weixinname",wmUser.getNickname());
				System.out.println(wmUser.toString());
			} catch (WxErrorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   
		   
	        return "power/wxbadmintons";
	    }
	
	
	@RequestMapping("/tobadmintonbaomin")
	   public String tobadminton() {
			 
		   //
		   System.out.println("点击报名开始 ");
	        return "power/wxbadminton_baomin";
	    }
	 
	@RequestMapping("/towxbadminton_seebaomin")
	   public String toseebadmintonbaomin() {
			 
		   //
		   System.out.println("跳转查看报名情况 ");
	        return "power/wxbadminton_seebaomin";
	    }
	
	@ResponseBody
	@RequestMapping("/wxseebaominBybaId")
	   public  Map<String, Object> wxseebaominBybaId(@RequestParam("id") int id) {
		
		  System.out.println("查看报名情况数据显示页面 ");
		 LinkedHashMap<String, Object> resultmap = new LinkedHashMap<String, Object>();
	
			  //Example badminton_signExample = new Example(Sport_Badminton_sign.class);
			  //badminton_signExample.or().andEqualTo("Weixin_id",sport_Badminton_sign.getWeixin_id());
			  List<Sport_Badminton_sign> badminton_signlist = sport_Badminton_sign_Service.selectbaominByBadmintonId(id);
			  if (badminton_signlist != null && badminton_signlist.size() > 0)
			  {
              
                 //resultmap.put("data", badminton_signlist);
                  //新建8个list用于存放8种类型比赛的人员信息（姓名（微信名称））
                // List<String> list1=new ArrayList<String>();//男单
                 //List<String> list2=new ArrayList<String>();//女单
                 //List<String> list3=new ArrayList<String>();//男双
                // List<String> list4=new ArrayList<String>();//女双
                // List<String> list5=new ArrayList<String>();//混双
                // List<String> list6=new ArrayList<String>();//男团
                // List<String> list7=new ArrayList<String>();//女团
                // List<String> list8=new ArrayList<String>();//混团
				  // List<String> list1=new ArrayList<String>();//男单
				    String sitem1="";//男单
	               String sitem2="";//女单
	               String sitem3="";//男双
	               String sitem4="";//女双
	               String sitem5="";//混双
	               String sitem6="";//男团
	               String  sitem7="";//女团
	               String sitem8="";//混团
                 
                 
                 for(int i=0;i < badminton_signlist.size();i++)
                 {
                	 Sport_Badminton_sign sport_Badminton_sign  =  badminton_signlist.get(i);
                	 
                	  
                	
                	   if(sport_Badminton_sign.getGame_item()!=null)
                		   
                		   
                	   {
                		   System.out.println("有单项赛===="+sport_Badminton_sign.getGame_item());
                		   String [ ] line=sport_Badminton_sign.getGame_item().split(","); 
                		   int itemsize=line.length; 
                		   if(itemsize>0)
                     	  {
                			   
                			   System.out.println("有单项赛");
                     		  //单项赛统计
                			   String s="";
                     		  for(int m=0;m<itemsize;m++) {
                     			  s=line[m];
                         		  if(s.equals("1"))
                         		  { 
                         			  
                         			  sitem1=sitem1+"  "+sport_Badminton_sign.getName()+"("+sport_Badminton_sign.getWeixin_name()+")";
                         			  
                         			 System.out.println("sitem1==="+sitem1);
                         			  
                         		  }
                         		  if(s.equals("2"))
                         		  {
                         			  sitem2=sitem2+"  "+sport_Badminton_sign.getName()+"("+sport_Badminton_sign.getWeixin_name()+")"; 
                         			 System.out.println("sitem2==="+sitem2);
                         		  }
                         		  if(s.equals("3"))
                         		  {
                         			  sitem3=sitem3+"  "+sport_Badminton_sign.getName()+"("+sport_Badminton_sign.getWeixin_name()+")";
                         			 System.out.println("sitem3==="+sitem3);
                         		  } 
                         		  if(s.equals("4"))
                         		  {
                         			  sitem4=sitem4+"  "+sport_Badminton_sign.getName()+"("+sport_Badminton_sign.getWeixin_name()+")";
                         			 System.out.println("sitem4==="+sitem4);
                         		  }  
                         		  if(s.equals("5"))
                         		  {
                         			  sitem5=sitem5+"  "+sport_Badminton_sign.getName()+"("+sport_Badminton_sign.getWeixin_name()+")";
                         			 System.out.println("sitem5==="+sitem5);
                         		  } 
                         		  
                         	
                     		  
                     		  //System.out.println(ss);
                     		  
                     		  
                     		  }
                     	 
                     	 
                     	// System.out.println("读取报名信息_姓名："+sport_Badminton_sign.getName());
                     	 //System.out.println("读取报名信息_微信:"+sport_Badminton_sign.getWeixin_id());
                     	// System.out.println("读取报名信息_参加单项:"+sport_Badminton_sign.getGame_item());
                     	// System.out.println("读取报名信息_参加团体:"+sport_Badminton_sign.getGame_items());
                         }
                		   
                		   
                	   }
                	  if(sport_Badminton_sign.getGame_items()!=null)
                	  {
                		  
                	   String [ ] lines=sport_Badminton_sign.getGame_items().split(",");
                   	   int itemssize=lines.length;  
                   	   
                 	  if(itemssize>0)
             		  {
                 		  System.out.println("有团体赛--------------");
                 		 String ss="";
             		  //团体赛统计
             			  for(int j=0;j<itemssize;j++) {
             				  ss=lines[j];
             				  
             				  
             				  if(ss.equals("1"))
                     		  { 
             					  sitem6=sitem6+"  "+sport_Badminton_sign.getName()+"("+sport_Badminton_sign.getWeixin_name()+")";  
             					 System.out.println("sitem6==="+sitem6);
                     		  }
                     		  if(ss.equals("2"))
                     		  {
                     			  sitem7=sitem7+"  "+sport_Badminton_sign.getName()+"("+sport_Badminton_sign.getWeixin_name()+")"; 
                     			 System.out.println("sitem7==="+sitem7);
                     		  }
                     		  if(ss.equals("3"))
                     		  {
                     			  sitem8=sitem8+"  "+sport_Badminton_sign.getName()+"("+sport_Badminton_sign.getWeixin_name()+")";
                     			 System.out.println("sitem8==="+sitem8);
                     		  } 
             		  }
             		  
             		  
         	  }
         	 
                	  }
                	   
                	  //String[] array=new String[itemsize];
                	 
                 
               }
                 String resultvale="";
                 if(sitem1!="")
                 {
                	 if(resultvale=="")
                	 {               		 
                		 resultvale= "男单参赛名单："+sitem1; 
                	 }
                	 else
                	 {
                		 resultvale=resultvale+","+"男单参赛名单!"+sitem1;                		 
                	 }	 
                 }
                 if(sitem2!="")
                 {
                	 if(resultvale=="")
                	 {             		 
                		 resultvale= "女单参赛名单："+sitem2; 
                	 }
                	 else
                	 {
                		 resultvale=resultvale+","+"女单参赛名单!"+sitem2;         		 
                	 }
        	 
                 }
                 if(sitem3!="")
                 {
                	 if(resultvale=="")
                	 {             		 
                		 resultvale= "男双参赛名单："+sitem3; 
                	 }
                	 else
                	 {
                		 resultvale=resultvale+","+"男双参赛名单!"+sitem3;         		 
                	 }
        	 
                 }
                 if(sitem4!="")
                 {
                	 if(resultvale=="")
                	 {             		 
                		 resultvale= "男双参赛名单："+sitem4; 
                	 }
                	 else
                	 {
                		 resultvale=resultvale+","+"男双参赛名单!"+sitem4;         		 
                	 }
        	 
                 }
                 if(sitem5!="")
                 {
                	 if(resultvale=="")
                	 {             		 
                		 resultvale= "混双参赛名单："+sitem5; 
                	 }
                	 else
                	 {
                		 resultvale=resultvale+","+"混双参赛名单!"+sitem5;         		 
                	 }
        	 
                 }
                 if(sitem6!="")
                 {
                	 if(resultvale=="")
                	 {             		 
                		 resultvale= "男团参赛名单："+sitem6; 
                	 }
                	 else
                	 {
                		 resultvale=resultvale+","+"男团参赛名单!"+sitem6;         		 
                	 }
        	 
                 }
                 if(sitem7!="")
                 {
                	 if(resultvale=="")
                	 {             		 
                		 resultvale= "女团参赛名单："+sitem7; 
                	 }
                	 else
                	 {
                		 resultvale=resultvale+","+"女团参赛名单!"+sitem7;         		 
                	 }
        	 
                 }
                 if(sitem8!="")
                 {
                	 if(resultvale=="")
                	 {             		 
                		 resultvale= "混团参赛名单："+sitem8; 
                	 }
                	 else
                	 {
                		 resultvale=resultvale+","+"混团参赛名单!"+sitem8;         		 
                	 }
        	 
                 }
                 
                 System.out.println("最终参赛名单======"+resultvale);
                 resultmap.put("state", "success");
                 resultmap.put("mesg", "读取报名信息成功");
                 resultmap.put("items", resultvale); 
                 
			  }
			  else 
            {   //编辑
       	              
                   resultmap.put("state", "fail");
                   resultmap.put("mesg", "读取报名信息异常");
             }
             
       
		
            
		 return  resultmap;
		   //
		  // System.out.println("根据比赛ID的到报名情况 ");
	       // return "power/wxbadminton_seebaomin";
	    }
	
	@RequestMapping("/badmintonbaomin")
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
	
	
	/**
	@ResponseBody
	@RequestMapping("/authorize")
    public String authorize(@RequestParam("returnUrl") String returnUrl){
        String url = "http://xxx.natapp.cn/wechat/userInfo";
        String redirectURL = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_USERINFO, URLEncoder.encode(returnUrl));
        log.info("【微信网页授权】获取code,redirectURL={}", redirectURL);
        return "redirect:" + redirectURL;
    }
	**/
	@ResponseBody
    //@GetMapping("/lists")
	@RequestMapping("/lists")
   // @RequiresPermissions(value = {"比赛项目管理"})
 
 public Map<String, Object> list(JqgridBean jqgridbean
         /*String userName,@RequestParam(value="page",required=false)Integer page*/
) throws Exception {
	 
	// System.out.println("取到用户openID============"+code);
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
    		
    		System.out.println("进入报名方法");

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
              resultmap.put("mesg", "恭喜你报名成功，比赛取得好成绩！");
    		
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
