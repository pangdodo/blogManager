package com.xz.web;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.xz.entity.Sport_Badminton_S;
import com.xz.entity.Trole;
import com.xz.entity.Trolemenu;
import com.xz.entity.Tuser;
import com.xz.entity.Tuserrole;
import com.xz.model.JqgridBean;
import com.xz.model.PageRusult;
import com.xz.service.Sport_Badminton_Service;

import tk.mybatis.mapper.entity.Example;

@Controller
@RequestMapping("/badminton_s")
public class Sport_Badminton_S_controller {
	
	@Resource
	private Sport_Badminton_Service sport_Badminton_Service;
	
	 @RequestMapping("/tobadmintonmanage")
	    @RequiresPermissions(value = {"羽毛球赛事管理"})
	    public String tobadmintonmanage() {
		 
		   System.out.println("羽毛球项目创建管理");
	        return "power/badminton";
	    }
	
	 @ResponseBody
	    @RequestMapping(value = "/list")
	   // @RequiresPermissions(value = {"比赛项目管理"})
	 
	 public Map<String, Object> list(JqgridBean jqgridbean,HttpSession session
             /*String userName,@RequestParam(value="page",required=false)Integer page*/
) throws Exception {
		 
		 
		 LinkedHashMap<String, Object> resultmap = new LinkedHashMap<String, Object>();
	        LinkedHashMap<String, Object> datamap = new LinkedHashMap<String, Object>();
	        
	        Example sport_Badminton_S = new Example(Sport_Badminton_S.class);
	        Example.Criteria criteria = sport_Badminton_S.or();
	        
	        if (StringUtils.isNotEmpty(jqgridbean.getSearchField())) {
	            if ("game_name".equalsIgnoreCase(jqgridbean.getSearchField())) {
	                if ("eq".contentEquals(jqgridbean.getSearchOper())) {
	                    criteria.andEqualTo("game_name",jqgridbean.getSearchString());
	                }
	            }
	        }
	        Tuser currentUser=(Tuser) session.getAttribute("currentUser");
	        
	        String cuserroles=currentUser.getRoles();
	        
	        List<Sport_Badminton_S> badmintonList = null;
	        if(cuserroles.contains("系统管理员"))
	        {
	        	badmintonList = sport_Badminton_Service.selectByExample(sport_Badminton_S);
	        }
	        else
	        {
	        	badmintonList = sport_Badminton_Service.selectBadmintonByCreater(currentUser.getUserName());
	        }
	        //判断如果是管理员，能看到所有比赛，否则能看到自己创建的比赛
	        System.out.println("用户角色信息======"+cuserroles);
	        
	        PageHelper.startPage(jqgridbean.getPage(), jqgridbean.getLength());
	        //List<Sport_Badminton_S> badmintonList = sport_Badminton_Service.selectByExample(sport_Badminton_S);
	       // List<Sport_Badminton_S> badmintonList = sport_Badminton_Service.selectBadmintonByCreater(currentUser.getUserName());
	        PageRusult<Sport_Badminton_S> pageRusult =new PageRusult<Sport_Badminton_S>(badmintonList);

	        /*Integer totalrecords = roleService.countByExample(troleExample);//总记录数
	        Page pagebean = new Page(jqgridbean.getLength() * ((jqgridbean.getPage() > 0 ? jqgridbean.getPage() : 1) - 1), jqgridbean.getLength(), totalrecords);
	        troleExample.setPage(pagebean);
	        troleExample.setOrderByClause(jqgridbean.getSidx() + " " + jqgridbean.getSord());
	        List<Trole> roleList = roleService.selectByExample(troleExample);*/

	        resultmap.put("currpage", String.valueOf(pageRusult.getPageNum()));
	        resultmap.put("totalpages", String.valueOf(pageRusult.getPages()));
	        resultmap.put("totalrecords", String.valueOf(pageRusult.getTotal()));
	        resultmap.put("datamap", badmintonList);

		 return resultmap;
	 
	 }
	 
	 
	 
	    /**
	     * 新建比赛项目
	     * @param sport_Badminton_S
	     * @return
	     */
	 
	 @RequestMapping("/tonewupdatebatminton")
	    //@RequiresPermissions(value = {"羽毛球赛事管理"})
	    public String tonewupdate() {
		 
		   System.out.println("跳转比赛项目创建页面");
		   
		    //需要获取当前登录人员信息
		   
	        return "power/newupdatebadminton";
	    }
	 
	 
	 @RequestMapping("/toupdatebatminton")
	    //@RequiresPermissions(value = {"羽毛球赛事管理"})
	    public String toupdate() {
		 
		   System.out.println("跳转到修改比赛项目内容页面");
	        return "power/updatebadminton";
	    }
	 
	    @ResponseBody
	    @RequestMapping(value = "/addupdatebatminton")
	    //@RequiresPermissions(value = {"比赛项目管理"})
	    public Map<String, Object> addupdatebatminton(Sport_Badminton_S sport_Badminton_S ){
	    	LinkedHashMap<String, Object> resultmap = new LinkedHashMap<String, Object>();
	    	try {
	    		
	    		System.out.println("进入新建项目方法");
	    		
	    		if (sport_Badminton_S.getId() == null) {//新建
	    			  Example badmintonExample = new Example(Sport_Badminton_S.class);
	    			  badmintonExample.or().andEqualTo("game_name",sport_Badminton_S.getGame_name());
	    			  List<Sport_Badminton_S> badmintonlist = sport_Badminton_Service.selectByExample(badmintonExample);
	    			  if (badmintonlist != null && badmintonlist.size() > 0) {
	                      resultmap.put("state", "fail");
	                      resultmap.put("mesg", "当前项目名已存在");
	                      return resultmap;
	                  }
	    			  sport_Badminton_Service.saveNotNull(sport_Badminton_S);
	              } else {//编辑
	            	  Sport_Badminton_S oldObject = sport_Badminton_Service.selectByKey(sport_Badminton_S.getId());
	                  if (oldObject == null) {
	                      resultmap.put("state", "fail");
	                      resultmap.put("mesg", "当前项目名不存在");
	                      return resultmap;
	                  } else {
	                	  sport_Badminton_Service.updateNotNull(sport_Badminton_S);
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
	    
	    

	    @ResponseBody
	    @RequestMapping(value = "/selectBadmintonById")
	    //@RequiresPermissions(value = {"编辑比赛信息"})
	    public Map<String, Object> selectBadmintonById(Sport_Badminton_S sport_Badminton_S) {
	    	
	    	System.out.println("进入比赛编辑界面 ");
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
	 
	    @ResponseBody
	    @RequestMapping(value = "/deletbadmintonbyid")
	    
	    public Map<String, Object> deletbadminton(Sport_Badminton_S sport_Badminton_S) {
	        LinkedHashMap<String, Object> resultmap = new LinkedHashMap<String, Object>();
	        try {
	            if (sport_Badminton_S.getId() != null && !sport_Badminton_S.getId().equals(0)) {
	            	
	            	System.out.println("删除选中的项目ID==="+sport_Badminton_S.getId());
	            	Sport_Badminton_S sport_Badminton_S1 = sport_Badminton_Service.selectByKey(sport_Badminton_S.getId());
	                if (sport_Badminton_S1 == null) {
	                    resultmap.put("state", "fail");
	                    resultmap.put("mesg", "删除失败,无法找到该记录");
	                    return resultmap;
	                } else {
	                    //还需删除用户角色中间表
	                    Example badmintonexample = new Example(Sport_Badminton_S.class);
	                    badmintonexample.or().andEqualTo("id",sport_Badminton_S.getId());
	                    //sport_Badminton_Service.deleteByExample(badmintonexample);
	                    //Example trolemenuexample = new Example(Trolemenu.class);
	                   // trolemenuexample.or().andEqualTo("id",trole.getId());
	                   // sport_Badminton_Service.deleteByExample(trolemenuexample);

	                    sport_Badminton_Service.delete(sport_Badminton_S.getId());
	                }
	            } else {
	                resultmap.put("state", "fail");
	                resultmap.put("mesg", "删除失败");
	            }


	            resultmap.put("state", "success");
	            resultmap.put("mesg", "删除成功");
	            return resultmap;
	        } catch (Exception e) {
	            e.printStackTrace();
	            resultmap.put("state", "fail");
	            resultmap.put("mesg", "删除失败，系统异常");
	            return resultmap;
	        }
	    }

}
