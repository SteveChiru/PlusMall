app.service('itemCatService',function ($http) {

    //查找
    this.search=function (pageNum, pageSize,parentId) {
        return $http.get('../itemcat/search.do?pageNum='+pageNum+'&pageSize='+pageSize+'&parentId='+parentId);
    }
    
    //删除
    this.delete=function (ids) {
        return $http.get('../itemcat/delete.do?ids='+ids);
    }
    
    //新增
    this.add=function (itemCat) {
        return $http.post('../itemcat/add.do',itemCat);
    }

    //根据Id查找
    this.findOne=function (id) {
        return $http.get('../itemcat/findOne.do?id='+id);
    }

    //更新
    this.update=function (itemCat) {
        return $http.post('../itemcat/update.do',itemCat);
    }

    //查找所有商品分类数据
    this.findAll=function () {
        return $http.get('../itemcat/findAll.do');
    }
});