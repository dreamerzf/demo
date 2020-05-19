$(document).ready(function (){
    var http = window.location.protocol;
    var host = window.location.host;
    bindCombobox();
    var mainGrid=getGrid("generatorGrid");
    var javaGrid=getJavaGrid("generatorJavaGrid")
    $("#searchButton").click(function (e) {
        mainGrid.setDataSource(bindGrid());
        javaGrid.setDataSource(bindGrid());
    });
    $("#generatorCreateModal").on("show.bs.modal",function (e) {
        var codeList=$("#generatorGrid").data("kendoGrid")._data;

    });
    $("#generatorCreateOKButton").click(function (e) {
        var colList=JSON.stringify($("#generatorJavaGrid").data("kendoGrid")._data);
        $.ajax({
            url: "/demo/generator/generatorCode",
            type: "POST",
            dataType: "json",
            data:encodeURI("moduleName="+$("#moduleName").val()+
                            "&moduleComment="+$("#moduleComment").val()+
                            "&className="+$("#className").val()+
                            "&classComment="+$("#classComment").val()+
                            "&projectName="+$("#projectName").val()+
                            "&colList="+colList
            ),

            success: function(result) {
               if(result.success){
                   window.open(http+"//"+host+'/demo/generator/download?zipName='+$("#className").val());
                   $.globalMessenger().post({
                       message: result.message,//提示信息
                       type: 'success',//消息类型。error、info、success
                       hideAfter: 2,//多长时间消失
                       showCloseButton:true,//是否显示关闭按钮
                       hideOnNavigate: true //是否隐藏导航
                   });

                   $("#generatorCreateModal").modal("hide");
               }else{
                   $.globalMessenger().post({
                       message: result.message,//提示信息
                       type: 'error',//消息类型。error、info、success
                       hideAfter: 2,//多长时间消失
                       showCloseButton:true,//是否显示关闭按钮
                       hideOnNavigate: true //是否隐藏导航
                   });
               }
            }


        })
    })
})
function bindCombobox() {
    $.ajax({
        url: "/demo/generator/getTables",
        type: "POST",
        dataType: "json",
        cache:false,
        success: function(result) {
            $("#tableNameSearch").kendoComboBox({
                dataTextField:"tableComment",
                dataValueField:"tableName",
                dataSource:result
            });
        }


    })
}
function bindGrid() {
    var dataSource=new kendo.data.DataSource({
        transport:{
            read:{
                url:"/demo/generator/getAllColumns",
                dataType:"json",
                method:"POST",
                cache:false
            },
            parameterMap:function (options,operation) {
                return "tableName="+$("#tableNameSearch").val()+
                    "&current="+options.page+
                    "&size="+options.pageSize;
            }
        },
        serverPaging:true,
        pageSize:20,
        schema:{
            data:function (response) {

                return response.records;
            },
            total:function (response) {
                return response.total;
            }
        }
    });
    return dataSource;
}
function getGrid(id) {
    $("#"+id).kendoGrid({
        excel:{
            fileName:"代码生成.et",
            filterable:true,
            allPages:false
        },
        // toolbar:[{template:kendo.template($("#generatorGridToolbar").html())},],
        columns:[
            {
                title:"数据库表相关信息",
                columns:[{
                    field:"tableName",
                    title:"表英文"
                },{
                    field:"columnName",
                    title:"列英文"
                },{
                    field:"columnComment",
                    title:"列中文"
                },{
                    field:"dataType",
                    title:"列数据类型"
                },{
                    field:"columnKey",
                    title:"约束类别"
                }]
            },{
            title:"java代码信息",
                columns:[
                    {
                        field:"entityName",
                        title:"java类英文"
                    },{
                        field:"fieldName",
                        title:"java属性英文"
                    },{
                        field:"fieldDesc",
                        title:"java属性中文"
                    },{
                        field:"fieldType",
                        title:"java属性类别"
                    }
                ]
            },
        ],
        dataSource:[],
        selectable:"row",
        resizable:true,
        sortable:true,
        filterable:true,
        reorderable:true,
        columnMenu:true,
        pageable:{
            numeric:true,
            refresh:true,
            pageSizes:[10,20,50,100,200,"ALL"],
            buttonCount:5,
            page:1,
            input:true
        },
        serverPaging:true,
        serverSorting:true
    });
    var grid=$("#"+id).data("kendoGrid");
    var x=0;
    for(var i=0;i<grid.columns.length;i++){
        for (var j=0;j<grid.columns[i].columns.length;j++){
            grid.thead.find("tr:eq(1)").find("th:eq("+(j+x)+")").attr("title",grid.columns[i].columns[j].title);

        }
        x=x+grid.columns[i].columns.length;
    }
    return $("#"+id).data("kendoGrid");
}
function getJavaGrid(id) {
    $("#"+id).kendoGrid({
        excel:{
            fileName:"代码生成.et",
            filterable:true,
            allPages:false
        },
        // toolbar:[{template:kendo.template($("#generatorGridToolbar").html())},],
        columns:[
            {
            title:"java代码信息",
                columns:[
                    {
                        field:"entityName",
                        title:"java类英文"
                    },{
                        field:"fieldName",
                        title:"java属性英文"
                    },{
                        field:"fieldDesc",
                        title:"java属性中文"
                    },{
                        field:"fieldType",
                        title:"java属性类别"
                    }
                ]
            },
        ],
        dataSource:[],
        editable:true,
        selectable:"row",
        resizable:true,
        sortable:true,
        filterable:true,
        reorderable:true,
        columnMenu:true,
        pageable:{
            numeric:true,
            refresh:true,
            pageSizes:[10,20,50,100,200,"ALL"],
            buttonCount:5,
            page:1,
            input:true
        },
        serverPaging:true,
        serverSorting:true
    });
    var grid=$("#"+id).data("kendoGrid");
    var x=0;
    for(var i=0;i<grid.columns.length;i++){
        for (var j=0;j<grid.columns[i].columns.length;j++){
            grid.thead.find("tr:eq(1)").find("th:eq("+(j+x)+")").attr("title",grid.columns[i].columns[j].title);

        }
        x=x+grid.columns[i].columns.length;
    }
    return $("#"+id).data("kendoGrid");
}