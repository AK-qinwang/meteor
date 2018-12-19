<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
</head>
<link rel="stylesheet" type="text/css" href="${request.contextPath}/static/easyui.css" />
<link rel="stylesheet" type="text/css" href="${request.contextPath}/static/my.css" />
<link rel="stylesheet" type="text/css" href="${request.contextPath}/static/icon.css" />
<link rel="stylesheet" type="text/css" href="${request.contextPath}/static/validatebox.css" />
<script type="text/javascript" src="${request.contextPath}/static/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/meteor.user.validatebox.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/jquery.messager.js"></script>
<script type="text/javascript">

    function userLogin() {
        if($("#loginForm").form('validate')){
            var params = $("#loginForm").serialize();
            alert(params);
            $.ajax({
                type:'post',
                data:params,
                url:'login/loginView',
                success:function (data) {
                    alert("注册成功");
                }
            })
        }else{
            alert("请检查页面输入项111111");
        }
    }
</script>
<body>
    <div class="formClass">
        <form name="loginForm" id="loginForm"  novalidate="true" action="">
            <p>
                <label for="loginName">
                    登录名:
                </label>
                <label>
                    <input type="text" class="easyui-validatebox" required="true"  name="loginName" id="loginName" value="" maxlength="6"/>
                </label>
            </p>
            <p>
                <label for="password">
                    密&nbsp;&nbsp;&nbsp;码:
                </label>
                <label>
                    <input type="password" id="password" onpaste="false"oncontextmenu="return false"
                           oncopy="return false" oncut="return false" autocomplete="off" class="easyui-validatebox" name="password" required="true" value="" validType="checkLoginName[3,6,'登录名不合法']">
                </label>
            </p>
            <p>
                <a href="javascript:void(0)"  class="buttonstyle2 basered" onclick="userLogin()">登录</a>
            </p>
        </form>
    </div>

</body>
</html>