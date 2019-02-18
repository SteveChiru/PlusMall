app.service('operatorSellerService',function ($http) {

    //查找商家
    this.search=function (pageNum, pageSize,searchEntity) {
        return $http.post('../seller/search.do?pageNum='+pageNum+'&pageSize='+pageSize,searchEntity);
    }

    //查找单个商家信息
    this.findOne=function (strId) {
        return $http.get('../seller/findOne.do?strId='+strId);
    }

    //更新商家信息
    this.update=function (seller) {
        return $http.post('../seller/update.do',seller);
    }

    //更新商家状态
    this.updateStatus=function (strId,status) {
        return $http.get('../seller/updateStatus.do?strId='+strId+'&status='+status);
    }
});