<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>layui</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="${basePath!}/static/layui/css/layui.css"  media="all">
  <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
  
  <style type="text/css">
  .body1{ margin:10px;
        border:10px}  <!--body顶格，body最顶部不留空白-->
</style>


  <script src="${basePath!}/static/layui/layui.js" charset="utf-8"></script>
</head>
<body class="body1">


    <form class="layui-form" action="" id="addbaominformid">
        <label hidden="true" id="editbaominlabelid"></label>
        <input id="editbaomid" name="id" value="" hidden/>
        <div class="layui-form-item">
            <label class="layui-form-label">微信号</label>
            <div class="layui-input-block">
                <input type="text" id="Weixin_id" name="Weixin_id"  autocomplete="off" placeholder="" value="" class="layui-input">
            </div>
             
        </div>
        
        <div class="layui-form-item">
            
             <label class="layui-form-label">姓名</label>
            <div class="layui-input-block">
                <input type="text" id="name" name="name" autocomplete="off" placeholder="" class="layui-input">
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
                <input type="text" id="Badminton_id" name="Badminton_id"  autocomplete="off" placeholder="" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="addbaominsubmitfilter">立即提交</button>
                <button id="reset" type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>

    </form>


          <script>
          
           //监听提交，羽毛球报名表单提交在此操作（xumin 2018.11.17）
        form.on('submit(addbaominsubmitfilter)', function(data) {
            alert("提交报名");
            //为了防止form中的id值被重置后置空,将编辑的id存放在label中
            $("#editbaominid").val($("#editbaominlabelid").html() );
            $("#editbaominlabelid").html("");

            $.ajax({
                type: "POST",
                url:"badminton_sign/addupdate_sign",
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
                        layer.open({
                            skin: 'layui-layer-molv',
                            type:1,
                            area:"10%",
                            content:data.mesg,
                            shadeClose:true,
                            end: function(){
                                layer.close();
                                
                            }
                        });

                    }
                }
            });



            return false;
        });

          </scripy>
    
</body>
</html>