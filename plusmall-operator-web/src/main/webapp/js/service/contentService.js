app.service('contentService',function ($http) {

    //查找
    this.search=function (pageNum, pageSize) {
        return $http.get('../content/search.do?pageNum='+pageNum+'&pageSize='+pageSize);
    }

    //删除
    this.delete=function (ids) {
        return $http.get('../content/delete.do?ids='+ids);
    }

    //添加
    this.add=function (content) {
        return $http.post('../content/add.do',content);
    }

    //根据id查找单个广告
    this.findOne=function (id) {
        return $http.get('../content/findOne.do?id='+id);
    }

    //更新广告
    this.update=function (content) {
        return $http.post('../content/update.do',content);
    }
})