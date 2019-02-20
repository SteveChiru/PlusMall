app.service('itemCatService',function ($http) {

    //通过parentId查找商品分类
    this.searchByParentId=function (parentId) {
        return $http.get('../itemcat/searchByParentId.do?parentId='+parentId);
    }

    //通过id查找单个分类数据
    this.findOne=function (id) {
        return $http.get('../itemcat/findOne.do?id='+id);
    }
})