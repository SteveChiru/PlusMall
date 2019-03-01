app.service('sellerService',function ($http) {
    //添加
    this.add=function (sellerEntity) {
        return $http.post('../seller/add.do',sellerEntity);
    }

    //根据sellerId查找单个用户信息
    this.findOne=function (sellerId) {
        return $http.get('../seller/findOne.do?sellerId='+sellerId);
    }

    //更新
    this.update=function (sellerEntity) {
        return $http.post('../seller/update.do',sellerEntity);
    }
});