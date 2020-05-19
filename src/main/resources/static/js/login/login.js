$(function(){
    var http = window.location.protocol;
    var host = window.location.host;
    /*判断上次是否勾选记住密码和自动登录*/
    var check1s=localStorage.getItem("check1");
    var check2s=localStorage.getItem("check2");
    var oldName=localStorage.getItem("userName");
    var oldPass=localStorage.getItem("passWord");
    if(check1s=="true"){
        $("#login-username").val(oldName);
        $("#login-password").val(oldPass);
        $("#check1").prop('checked',true);
    }else{
        $("#login-username").val('');
        $("#login-password").val('');
        $("#check1").prop('checked',false);
    }
    if(check2s=="true"){
        $("#check2").prop('checked',true);
        $("#loginFrom").submit();
        //location="https://www.baidu.com?userName="+oldName+"&passWord="+oldPass;//添加退出当前账号功能
    }else{
        $("#check2").prop('checked',false);
    }
    /*拿到刚刚注册的账号*/
    /*if(localStorage.getItem("name")!=null){
        $("#login-username").val(localStorage.getItem("name"));
    }*/
    /*登录*/
    $("#login").click(function(){
        var userName=$("#login-username").val();
        var passWord=$("#login-password").val();
        /*获取当前输入的账号密码*/
        localStorage.setItem("userName",userName)
        localStorage.setItem("passWord",passWord)
        /*获取记住密码  自动登录的 checkbox的值*/
        var check1 = $("#check1").prop('checked');
        var check2 = $('#check2').prop('checked');
        localStorage.setItem("check1",check1);
        localStorage.setItem("check2",check2);
    })
    $("#register").click(function () {
        alert('1111111111');
        $.ajax({
            url:http+"//"+host+'/demo/login/registerIndex',
            type:"GET",
            // data: encodeURI(
            //     "n=" + item.n
            //
            // ),
            dataType:'json',
            success:function(data){
                // if(data.success){
                //     $("#nShow").val(data.records[0].n);
                //     $("#mShow").val(data.records[0].m);
                //
                // }else{
                    $.globalMessenger().post({
                        message: 欢迎注册,//提示信息
                        type: 'info',//消息类型。error、info、success
                        hideAfter: 2,//多长时间消失
                        showCloseButton:true,//是否显示关闭按钮
                        hideOnNavigate: true //是否隐藏导航
                    });
                //     return e.preventDefault();
                //
                // }

            },
        });
    })
    /*$("#check2").click(function(){
        var flag=$('#check2').prop('checked');
        if(flag){
            var userName=$("#login-username").val();
            var passWord=$("#login-password").val();
            $.ajax({
                type:"post",
                url:"http://localhost:8080/powers/pow/regUsers",
                data:{"userName":userName,"passWord":passWord},
                async:true,
                success:function(res){
                    alert(res);
                }
            });
        }
    })*/





    //注册
    /*错误class  form-control is-invalid
    		正确class  form-control is-valid*/
    var flagName=false;
    var flagPas=false;
    var flagPass=false;
    /*验证用户名*/
    var name,passWord,passWords;
    $("#register-username").change(function(){
        name=$("#register-username").val();
        if(name.length<2||name.length>10){
            $("#register-username").removeClass("form-control is-valid")
            $("#register-username").addClass("form-control is-invalid");
            flagName=false;
        }else{
            $("#register-username").removeClass("form-control is-invalid")
            $("#register-username").addClass("form-control is-valid");
            flagName=true;
        }
    })
    /*验证密码*/
    $("#register-password").change(function(){
        passWord=$("#register-password").val();
        if(passWord.length<6||passWord.length>18){
            $("#register-password").removeClass("form-control is-valid")
            $("#register-password").addClass("form-control is-invalid");
            flagPas=false;
        }else{
            $("#register-password").removeClass("form-control is-invalid")
            $("#register-password").addClass("form-control is-valid");
            flagPas=true;
        }
    })
    /*验证确认密码*/
    $("#register-passwords").change(function(){
        passWords=$("#register-passwords").val();
        if((passWord!=passWords)||(passWords.length<6||passWords.length>18)){
            $("#register-passwords").removeClass("form-control is-valid")
            $("#register-passwords").addClass("form-control is-invalid");
            flagPass=false;
        }else{
            $("#register-passwords").removeClass("form-control is-invalid")
            $("#register-passwords").addClass("form-control is-valid");
            flagPass=true;
        }
    })


    $("#regbtn").click(function(){
        if(flagName&&flagPas&&flagPass){
            localStorage.setItem("name",name);
            localStorage.setItem("passWord",passWord);
            location="loginBody.html"
        }else{
            if(!flagName){
                $("#register-username").addClass("form-control is-invalid");
            }
            if(!flagPas){
                $("#register-password").addClass("form-control is-invalid");
            }
            if(!flagPass){
                $("#register-passwords").addClass("form-control is-invalid");
            }
        }
    })

})