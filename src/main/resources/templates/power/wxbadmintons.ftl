<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>三秦sport</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="${basePath!}/static/layui/css/layui.css"  media="all">
  
  <style>
  
  .layui-table-view .layui-table {width:100%}
  </style>
  
  <script src="${basePath!}/static/layui/layui.js" charset="utf-8"></script>
<!-- 这个是所有jquery插件的基础，首先第一个引入 -->
<script src="${basePath!}/static/js/jquery.min.js" type="text/javascript"></script>
<script src="${basePath!}/static/jqgrid/js/jquery.jqGrid.min.js" type="text/javascript"></script>

<style>

 .layui-input
 {
 float:left; width:99%; border:1px solid #006666; padding:0px; margin:0px
 }
 
 .layui-table-cell {
	height: auto;
}
</style>

</head>
<body>         
  
  
   <#--2018.11.  xumin  比赛列表  -->
      <table class="layui-hide"  id="badmtlist" lay-filter="badmtlist"></table> 
      
        
         
         <#--带有 class="layui-fluid" 的容器中，那么宽度将不会固定，而是 100% 适应-->
<div id="selectdeformdivid" hidden=""  style="margin: 15px;" >
    <form  action="" id="addeditformid">
   
        <label hidden="true" id="editlabelid"></label>
        
       
            
            <div  id="badminton_name" align='center'>  
            </div>
            <div  id="badminton_content">
            </div>
     
        <div >
            <div align='center'>
             
                <input class="layui-btn"  type="button" onclick="baomin();" value="报名" name="报名"></input>   
            </div>
        </div>

    </form>
</div>
         
      
      <#--2018.11.20  xumin  选择比赛增加报名信息表单  -->
<div id="addbaominformdivid" hidden="" style="margin: 15px;">
<br><br><br><br><br><br>
 <form class="layui-form" action="" id="addbaominformid">
  <br>
        <label hidden="true" id="editbaominlabelid"></label>
        <input id="editbaomid" name="id" value="" hidden/>
        <div class="layui-form-item">
            <label class="layui-form-label">微信号</label>
            <div class="layui-input-block">
                <input type="text" id="Weixin_id" name="Weixin_id"  autocomplete="off" placeholder="" value="" class="layui-input" >
            
              </div>
        </div>
        
        <div class="layui-form-item">
            
             <label class="layui-form-label">姓名</label>
            <div class="layui-input-block">
                <input type="text" id="name" name="name" autocomplete="off" placeholder="" class="layui-input" >
            </div>
        </div>
        
          <div class="layui-form-item">
             <label class="layui-form-label">身份证号</label>
            <div class="layui-input-block">
                <input type="text" id="p_id" name="p_id"  autocomplete="off" placeholder="" class="layui-input">
            </div>
        </div>
        
        <div class="layui-form-item">
            
             <label class="layui-form-label">联系电话</label>
            <div class="layui-input-block">
                <input type="text" id="Phone_id" name="Phone_id"  autocomplete="off" placeholder="" class="layui-input">
            </div>
        </div>
        
         <div class="layui-form-item">
            
             <label class="layui-form-label">联系地址</label>
            <div class="layui-input-block">
                <input type="text" id="address" name="address" autocomplete="off" placeholder="" class="layui-input">
            </div>
        </div>
           <div class="layui-form-item">
            
             <label class="layui-form-label">比赛项目ID</label>
            <div class="layui-input-block">
             <input id="badminton_id" name="badminton_id" class="layui-input" value="" />
             
             
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                 <input type="button" class="layui-btn layui-btn-primary" onclick="baominsubmit();" value="报名" name="报名"></input>   
                <button id="reset" type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>

    </form>
<div>

      
      
      
      
                               

 
<script id="barDemo" type="text/html">
  <a class="layui-btn layui-btn-xs" lay-event="edit">详情</a>
</script>


<script> 

//监听报名按钮
 var layerid;
 
 //2018.11.20  xumin  增加报名选择比赛项目报名函数
       var layerid;//当前弹层id;这个id可以定义多个，主要的目的是为了在回调函数关闭弹层时使用的
       
         function baomin()
        {
        
         // alert(document.getElementById("badminton_id").value);
          
          //document.getminton_id").value=id;ElementById("Bad
        
           layerid=layer.open(
           
           {//开启表单弹层
               // skin: 'layui-layer-molv',
                
                type: 1,
                title:'报名比赛',
                content: $('#addbaominformdivid') //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
            });
               layer.full(layerid);
        };
 
//监听提交，羽毛球报名表单提交在此操作（xumin 2018.11.17）
      
 
      function baominsubmit()
      {     
          alert("报名提交");
          
           $.ajax({
                type: "POST",
                url:"${basePath!}/wx/addupdate_sign",
                data:$('#addbaominformid').serialize(),
                async: false,
                error: function(request) {
                    layer.alert("与服务器连接失败/(ㄒoㄒ)/~~");
                },
                success: function(data) {
                    if(data.state=='fail'){
                        layer.alert(data.mesg);
                    }
                    if(data.state=='success'){
                    
                   // layer.alert(data.sport_Badminton_S.id);
                    //$("#baominbadminton_id").val(data.sport_Badminton_S.id);
                        layer.open({
                            skin: 'layui-layer-molv',
                            type:1,
                            area:"10%",
                            content:data.mesg,
                            shadeClose:true,
                            end: function(){
                              //  layer.close(layerid);
                                //jQuery("#list2").jqGrid().trigger("reloadGrid");//重新加载数据
                               // $("#reset").click();//重置表单
                            }
                        });

                    }
                }
            });



           // return false;
  
          
          
          
      };







   layui.use('table', function(){
    var table = layui.table;
  
    table.render({
    elem: '#badmtlist'
    ,url:'${basePath!}/wx/lists'
    ,cellMinWidth: 100 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
    ,cols: [[
       {type:'numbers',title: '排序',}    
      ,{field:'game_name', title: '可报名比赛项目（点击查看详细和报名）'}
      

     
    ]]
   
  });
  
  //监听行单击事件（单击事件为：rowDouble）
  table.on('row(badmtlist)', function(obj){
    var data = obj.data;
    
    //layer.alert(JSON.stringify("${basePath!}/wx/selectBadmintonById?id="+data.id), {
     // title: '当前行数据：'
    //});
 
    if (data.id) {
               

                //请求后台，获取该记录的详细记录，并填充进表单
                $.ajax({
                    type: "GET",
                    url:"${basePath!}/wx/selectBadmintonById",
                    data:{id:data.id},
                    async: false,
                    error: function(request) {
                        layer.alert("与服务器连接失败/(ㄒoㄒ)/~~");
                    },
                    success: function(data) {
                        if(data.state=='fail'){
                            layer.alert(data.mesg);
                            return false;
                        }
                        if(data.state=='success'){
                            //向表单填充数据
                            
                            $("#badminton_id").val(data.sport_Badminton_S.id);////给报名表单赋值比赛项目ID
                            $("#badminton_name").html(data.sport_Badminton_S.game_name);//显示比赛项目信息
                            $("#badminton_content").html(data.sport_Badminton_S.game_content);//显示比赛项目信息

                            //开启编辑表单所在的弹层。注意编辑和新建的表单是一套模板
                            layerid=layer.open({
                                //skin: 'layui-layer-molv',
                                area:'[100%,100%]',
                                type: 1,
                                title:'报名比赛',
                                content: $('#selectdeformdivid') //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
                            });
                           layer.full(layerid);
                        }
                    }
                });


            } else {
                layer.alert("请选择要编辑的记录");
            }
    
    
           });

      });
      
        //监听行单击事件结束
</script>

</body>
</html>