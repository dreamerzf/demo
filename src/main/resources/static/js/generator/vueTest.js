var data={message:"zf真帅",url:"www.baidu.com",alexa:"123445",site:"自学vue"}
var vue=new Vue({
    el:"#vueTest",
    data:data,
    methods:{
      details:function () {
          return this.site + new Date();
      }
    }
});
vue.message="zf不帅";
document.write(vue.url===data.url);
document.write(vue.details()===vue.site+new Date());
console.info(vue);