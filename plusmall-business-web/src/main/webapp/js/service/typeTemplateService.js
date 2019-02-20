app.service('typeTemplateService',function ($http) {

    //根据Id查找单个模板数据
    this.findOne=function (id) {
        return $http.get('../typeTemplate/findOne.do?id='+id);
    }
})