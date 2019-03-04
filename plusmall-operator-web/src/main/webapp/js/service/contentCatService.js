app.service('contentCatService',function ($http) {

    //查找
    this.search=function (pageNum, pageSize) {
        return $http.get('../contentCat/search.do?pageNum='+pageNum+'&pageSize='+pageSize);
    }

    //删除
    this.delete=function (ids) {
        return $http.get('../contentCat/delete.do?ids='+ids);
    }

    //添加
    this.add=function (contentCat) {
        return $http.post('../contentCat/add.do',contentCat);
    }

    //根据id查找单个广告分类
    this.findOne=function (id) {
        return $http.get('../contentCat/findOne.do?id='+id);
    }

    //更新
    this.update=function (contentCat) {
        return $http.post('../contentCat/update.do?',contentCat);
    }
})