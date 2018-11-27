package com.xz.web;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.xz.entity.Sport_Badminton_S;
import com.xz.entity.Sport_Badminton_sign;
import com.xz.model.JqgridBean;
import com.xz.model.PageRusult;
import com.xz.service.Sport_Badminton_sign_Service;

import tk.mybatis.mapper.entity.Example;

@Controller
@RequestMapping("/badminton_sign")
public class Sport_Badminton_sign_controller {
	@Resource
	private Sport_Badminton_sign_Service sport_Badminton_sign_Service;
	
	 @RequestMapping("/tobadmintonmanage")
	  //  @RequiresPermissions(value = {"羽毛球赛事管理"})
	    public String tobadmintonmanage() {
		 
		   System.out.println("羽毛球项目创建管理");
	        return "power/badmintonsign";
	    }
	
	    @ResponseBody
	    @RequestMapping(value = "/list")
	   // @RequiresPermissions(value = {"比赛项目管理"})
	    public Map<String, Object> list(JqgridBean jqgridbean
	             /*String userName,@RequestParam(value="page",required=false)Integer page*/
	) throws Exception {
			 
			 
			 LinkedHashMap<String, Object> resultmap = new LinkedHashMap<String, Object>();
		        LinkedHashMap<String, Object> datamap = new LinkedHashMap<String, Object>();
		        
		        Example sport_Badminton_sign = new Example(Sport_Badminton_sign.class);
		        Example.Criteria criteria = sport_Badminton_sign.or();
		        
		        if (StringUtils.isNotEmpty(jqgridbean.getSearchField())) {
		            if ("name".equalsIgnoreCase(jqgridbean.getSearchField())) {
		                if ("eq".contentEquals(jqgridbean.getSearchOper())) {
		                    criteria.andEqualTo("name",jqgridbean.getSearchString());
		                }
		            }
		        }

		        PageHelper.startPage(jqgridbean.getPage(), jqgridbean.getLength());
		        List<Sport_Badminton_sign> badminton_signList = sport_Badminton_sign_Service.selectByExample(sport_Badminton_sign);
		        PageRusult<Sport_Badminton_sign> pageRusult =new PageRusult<Sport_Badminton_sign>(badminton_signList);

		        /*Integer totalrecords = roleService.countByExample(troleExample);//总记录数
		        Page pagebean = new Page(jqgridbean.getLength() * ((jqgridbean.getPage() > 0 ? jqgridbean.getPage() : 1) - 1), jqgridbean.getLength(), totalrecords);
		        troleExample.setPage(pagebean);
		        troleExample.setOrderByClause(jqgridbean.getSidx() + " " + jqgridbean.getSord());
		        List<Trole> roleList = roleService.selectByExample(troleExample);*/

		        resultmap.put("currpage", String.valueOf(pageRusult.getPageNum()));
		        resultmap.put("totalpages", String.valueOf(pageRusult.getPages()));
		        resultmap.put("totalrecords", String.valueOf(pageRusult.getTotal()));
		        resultmap.put("datamap", badminton_signList);

			 return resultmap;
		 
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
