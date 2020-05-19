$(document).ready(function(){
    var http = window.location.protocol;
    var host = window.location.host;
    //加载页面时创建grid控件
    window["addressInfoGrid"]=getGrid("addressInfoGrid");
    //获取单数据源，存储在全局变量里
    window["singleDataSource"] = getSingleDataSource();
    //获取多字段查询数据源，存储在全局变量里
    window["multiDataSource"] = getMultiDataSource();

    //加载页面时读取所有数据
    rederGridData(window["multiDataSource"],1);
    //查询按钮点击事件
    $("#searchButton").click(function(e){
        rederGridData(window["multiDataSource"],1);
    });
    //重置按钮
    $("#resetButton").click(function(e){
                $("#nSearch").val("");
        $("#mSearch").val("");

        rederGridData(window["multiDataSource"],1);
    });
    //下载按钮
    $("#addressInfoExportButton").kendoButton({
        click : function(){
            window["addressInfoGrid"].saveAsExcel();
        }
    });
    //去除下载按钮的k-button
    $("#addressInfoExportButton").removeClass("k-button");

    //双击表格中的数据源查看详情
    $("#addressInfoShowModal").on("show.bs.modal",function(e){
                $("#nCreate").val("");
        $("#mCreate").val("");

        //填充input
        var tr=window["addressInfoGrid"].select();
        var item=window["addressInfoGrid"].dataItem(tr[0]);
        $.ajax({
            url:http+"//"+host+'/demo/addressmanage/getAddressInfo',
            type:"POST",
            data: encodeURI(
                "n=" + item.n 

            ),
            dataType:'json',
            success:function(data){
                if(data.success){
                  $("#nShow").val(data.records[0].n);
            $("#mShow").val(data.records[0].m);
            
                }else{
                    $.globalMessenger().post({
                     message: data.message,//提示信息
                     type: 'error',//消息类型。error、info、success
                     hideAfter: 2,//多长时间消失
                     showCloseButton:true,//是否显示关闭按钮
                     hideOnNavigate: true //是否隐藏导航
                    });
                    return e.preventDefault();

                }

            },
        });
    });
    //新增窗口打开时
    $("#addressInfoCreateModal").on("show.bs.modal",function(e){
                $("#nCreate").val("");
        $("#mCreate").val("");

    });
    //修改框打开编辑时操作
    $("#addressInfoEdit").click(function(e){
        var tr=window["addressInfoGrid"].select();
        if(tr.length==1){
            var item=window["addressInfoGrid"].dataItem(tr[0]);
            $.ajax({
                url:http+"//"+host+'/demo/addressmanage/getAddressInfo',
                type:"POST",
                data: encodeURI(
                    "n=" + item.n 

                ),
                dataType:'json',
                success:function(data){
                    if(data.success){
                        $("#addressInfoEditModal").modal("show");
                                    $("#nEdit").val(data.records[0].n);
            $("#mEdit").val(data.records[0].m);

                    }else{
                        $.globalMessenger().post({
                            message: data.message,//提示信息
                            type: 'error',//消息类型。error、info、success
                            hideAfter: 2,//多长时间消失
                            showCloseButton:true,//是否显示关闭按钮
                            hideOnNavigate: true //是否隐藏导航
                        });
                        return e.preventDefault();
                    }
                },
            });
        }else if(tr.length>1){
            $.globalMessenger().post({
                message: "每次只能选择一条记录",//提示信息
                type: 'error',//消息类型。error、info、success
                hideAfter: 2,//多长时间消失
                showCloseButton:true,//是否显示关闭按钮
                hideOnNavigate: true //是否隐藏导航
            });
            return e.preventDefault();
        }else{
            $.globalMessenger().post({
                message: "请选择一条记录",//提示信息
                type: 'error',//消息类型。error、info、success
                hideAfter: 2,//多长时间消失
                showCloseButton:true,//是否显示关闭按钮
                hideOnNavigate: true //是否隐藏导航
            });
            return e.preventDefault();
        }
    })
    //删除框打开时
    $("#addressInfoDelete").click(function(e){
        var tr=window["addressInfoGrid"].select();
        if(tr.length<1){
            $.globalMessenger().post({
                message: "请至少选择一条记录",//提示信息
                type: 'error',//消息类型。error、info、success
                hideAfter: 2,//多长时间消失
                showCloseButton:true,//是否显示关闭按钮
                hideOnNavigate: true //是否隐藏导航
            });
            return e.preventDefault();
        }else{
            $("#addressInfoDeleteModal").modal("show");
        }
    })
    //新增确认按钮
    $("#addressInfoCreateOKButton").click(function(event){
        $.ajax({
            url:http+"//"+host+'/demo/addressmanage/insert',
            type:"POST",
            dataType:'json',
            data:encodeURI(
                "n=" + $("#nCreate").val() +
                    "&m=" + $("#mCreate").val()
            ),
            success:function(data){
                if(data.success){
                    $.globalMessenger().post({
                        message: data.message,//提示信息
                        type: 'success',//消息类型。error、info、success
                        hideAfter: 2,//多长时间消失
                        showCloseButton:true,//是否显示关闭按钮
                        hideOnNavigate: true //是否隐藏导航
                    });
                    $("#addressInfoCreateModal").modal("hide");
                    rederGridData(window["multiDataSource"],1);
                }else {
                    $.globalMessenger().post({
                        message: data.message,//提示信息
                        type: 'error',//消息类型。error、info、success
                        hideAfter: 2,//多长时间消失
                        showCloseButton:true,//是否显示关闭按钮
                        hideOnNavigate: true //是否隐藏导航
                    });
                }
            }
        });
    });
      //修改确认按钮
    $("#addressInfoEditOKButton").click(function(event){
        $.ajax({
            url:http+"//"+host+'/demo/addressmanage/update',
            type:"PUT",
            dataType:'json',
            data:encodeURI(
                "n=" + $("#nEdit").val() +
                    "&m=" + $("#mEdit").val()
            ),
            success:function(data){
                if(data.success){
                    $.globalMessenger().post({
                        message: data.message,//提示信息
                        type: 'success',//消息类型。error、info、success
                        hideAfter: 2,//多长时间消失
                        showCloseButton:true,//是否显示关闭按钮
                        hideOnNavigate: true //是否隐藏导航
                    });
                    $("#addressInfoEditModal").modal("hide");
                    rederGridData(window["multiDataSource"],1);
                }else{
                    $.globalMessenger().post({
                        message: data.message,//提示信息
                        type: 'error',//消息类型。error、info、success
                        hideAfter: 2,//多长时间消失
                        showCloseButton:true,//是否显示关闭按钮
                        hideOnNavigate: true //是否隐藏导航
                    });
                }

            }
        });
    });

    $("#addressInfoSearch").bind('input propertychange',function(){
        rederGridData(window["singleDataSource"],1);
    });

    //删除确定按钮
    $("#addressInfoDeleteOKButton").click(function(event){
        var ids=new Array();
        var tr=window["addressInfoGrid"].select();
        for(var i=0;i<tr.length;i++){
            var item = window["addressInfoGrid"].dataItem(tr[i]);
            var vo={};
            vo.n=item.n;
            
            ids.push(vo);
        }
        $.ajax({
            url:http+"//"+host+'/demo/addressmanage/delete',
            type:'DELETE',
            dataType:'json',
            contentType: "application/json;charset=UTF-8",
            data:JSON.stringify(ids),
            success:function(data){
                if(data.success){
                    $.globalMessenger().post({
                        message: data.message,//提示信息
                        type: 'success',//消息类型。error、info、success
                        hideAfter: 2,//多长时间消失
                        showCloseButton:true,//是否显示关闭按钮
                        hideOnNavigate: true //是否隐藏导航
                    });
                    $("#addressInfoDeleteModal").modal('hide');
                    rederGridData(window["multiDataSource"],1);
                }else {
                    $.globalMessenger().post({
                        message: data.message,//提示信息
                        type: 'error',//消息类型。error、info、success
                        hideAfter: 2,//多长时间消失
                        showCloseButton:true,//是否显示关闭按钮
                        hideOnNavigate: true //是否隐藏导航
                    });
                }

            }
        });
    });
    $("#addressInfoFilterButton").click(function(){
                $("#nSearch").val("");
        $("#mSearch").val("");

        if($(".c-query-panel").css("display")=="block"){
            $(".c-query-panel").slideUp();
        }else if($(".c-query-panel").css("display")=="none"){
            $(".c-query-panel").slideDown();
        }
        rederGridData(window["multiDataSource"],1);
    });

});
function getGrid(id){
    $("#"+id).kendoGrid({
        excel:{
            fileName:"表格.et",
            filterable:true,
            allPages:false
        },
        toolbar:[
            {template:kendo.template($("#addressInfoGridToolbar").html())},
            ],
        columns:[
            {
                title:"<input type='checkbox' class='grid_checkbox_all'/>",
                width:35,
                template:"<input type='checkbox' class='grid_checkbox'/>"
            },{
            field : "n",
            title:"编号"
        },{
            field : "m",
            title:"信息"
        }
        ],
        change:function(e){
            var items = this.items();
            for(var i=0;i<items.length;i++){
                var $item=$(items[i]);
                var $checkbox=$item.find(".grid_checkbox");
                var checked=$item.hasClass("k-state-selected");
                if(checked){
                    $checkbox.prop("checked",true);
                }else{
                    $checkbox.prop("checked",false);
                }
            }
            var checkedNum=0;
            $(".grid_checkbox").each(function(){
                if($(this).prop("checked")){
                    checkedNum++;
                }
            });
            if(checkedNum == $(".grid_checkbox").length){
                $(".grid_checkbox_all").prop("indeterminate",false);
                $(".grid_checkbox_all").prop("checked",true);
            }else if(checkedNum == 0){
                $(".grid_checkbox_all").prop("indeterminate",false);
                $(".grid_checkbox_all").prop("checked",false);
            }else{
                $(".grid_checkbox_all").prop("indeterminate",true);
            }
        },
        selectalbe : "row",
        resizable : true,
        sortable : true,
        filterable : true,
        reorderable : true,
        columnMenu : true,
        pageable : {
            numeric:true,
            refresh:true,
            pageSizes : [10,20,50,100,200,"ALL"],
            buttonCount : 5,
            page:1,
            input:true
        },
        serverPaging:true,
        serverSorting:true,
        //加载完成每一行增加一个双击事件
        dataBound:function(){
            var data = this.dataSource.data();
            $.each(data,function(i,row){
                $("tr[data-uid='"+row.uid+"']").dblclick(function(){
                    var td=$(this).find("td:eq(0)");
                    var $checkedbox=td.find(".grid_checkbox:eq(0)");
                    var checkedboxs=$(".grid_checkbox");
                    for (var j=0;j<checkedboxs.length;j++){
                        checkedboxs[j].checked=false;
                        $(checkedboxs[j]).closest("tr").removeClass("k-state-selected");
                    }
                    $checkedbox.prop("checked",true);
                    $(this).addClass("k-state-selected");

                    $("#addressInfoShowModal").modal("show");
                });
            });
        }
    });
    $("#"+id).on("click", ".grid_checkbox_all" , function(){
        var checked=this.checked;
        if(checked){
            $("#"+id).getKendoGrid().items().addClass("k-state_selected");
            $(".grid_checkbox").prop("checked",true);
        }else{
            $("#"+id).getKendoGrid().items().removeClass("k-state_selected");
            $(".grid_checkbox").prop("checked",false);
        }
    });
    $("#"+id).on("click",".grid_checkbox",function(){
        var checked=this.checked;
        var row=$(this).closest("tr");
        if(checked){
            row.addClass("k-state-selected");
        }else{
            row.removeClass("k-state-selected");

        }
        var checkedNum=0;
        $(".grid_checkbox").each(function(){
            if($(this).prop("checked")){
                checkedNum++;
            }
        });
        if(checkedNum == $(".grid_checkbox").length){
            $(".grid_checkbox_all").prop("indeterminate",false);
            $(".grid_checkbox_all").prop("checked",true);
        }else if(checkedNum == 0){
            $(".grid_checkbox_all").prop("indeterminate",false);
            $(".grid_checkbox_all").prop("checked",false);
        }else{
            $(".grid_checkbox_all").prop("indeterminate",true);
        }
    });
    $("#"+id).on("click","tr",function(){
        var row=$(this);
        if(event.target!=row.find(".grid_checkbox")[0]){
            var td=$(this).find("td:eq(0)");
            var checked=td.find(".grid_checkbox:eq(0)")[0].checked;
            var $checkedbox=td.find(".grid_checkbox:eq(0)");
            if(checked){
                $checkedbox.prop("checked",false);
                row.removeClass("k-state-selected");
            }else{
                $checkedbox.prop("checked",true);
                row.addClass("k-state-selected");

            }
            var checkedNum=0;
            $(".grid_checkbox").each(function(){
                if($(this).prop("checked")){
                    checkedNum++;
                }
            });
            if(checkedNum == $(".grid_checkbox").length){
                $(".grid_checkbox_all").prop("indeterminate",false);
                $(".grid_checkbox_all").prop("checked",true);
            }else if(checkedNum == 0){
                $(".grid_checkbox_all").prop("indeterminate",false);
                $(".grid_checkbox_all").prop("checked",false);
            }else{
                $(".grid_checkbox_all").prop("indeterminate",true);
            }
        }
    });
    var grid=$("#"+id).data("kendoGrid");
    for(var i=0;i<grid.columns.length;i++){
        grid.thead.find("tr").find("th:eq("+i+")").attr("title",grid.columns[i].title);
    }
    return $("#"+id).data("kendoGrid");
}
function getMultiDataSource(){
    var http = window.location.protocol;
    var host = window.location.host;

    var dataSource = new kendo.data.DataSource({
        transport:{
            read:{
                url:http+"//"+host+"/demo/addressmanage/findAddressInfos",
                dataType:'json',
                method:"POST",
                cache:false
            },
            parameterMap:function(options,operation){
                //options中存储了页面分页信息
                return encodeURI("n=" + $("#nSearch").val() +
                    "&m=" + $("#mSearch").val()+
                "&current="+options.page+
                "&size="+options.pageSize
                );
            },
        },
        serverPaging:true,
        page:1,
        pageSize:10,
        schema:{
            data:function(response){
                return response.records;
            },
            total:function(response){
                return response.total;
            }
        }
    });
   // dataSource.bind("error",datasource_error);
    return dataSource;
}
function getSingleDataSource(){
    var http = window.location.protocol;
    var host = window.location.host;
    var dataSource = new kendo.data.DataSource({
        transport:{
            read:{
                url:http+"//"+host+"/demo/addressmanage/findBySearchText",
                dataType:'json',
                method:"POST",
                cache:false
            },
            parameterMap:function(options,operation){
                //options中存储了页面分页信息
                return encodeURI("searchText="+$("#addressInfoSearch").val()+
                "&current="+options.page+
                "&size="+options.pageSize
                );
            },
        },
        serverPaging:true,
        page:1,
        pageSize:10,
        schema:{
            data:function(response){
                return response.records;
            },
            total:function(response){
                return response.total;
            }
        }
    });
    //后台无响应时，前台提示
    //dataSource.bind("error",datasource_error);
    return dataSource;
}
function rederGridData(dataSource,pageNum){
    if(pageNum!=null&&pageNum!=dataSource._page){
        dataSource.page(pageNum);
    }
    window["addressInfoGrid"].setDataSource(dataSource);
}
function datasource_error(e){
    if(e.status=="error"){
        alert("系统后台无响应");
    }
}