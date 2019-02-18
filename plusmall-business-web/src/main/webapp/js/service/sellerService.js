app.service('sellerService',function ($http) {
    //添加
    this.add=function (sellerEntity) {
        return $http.post('../seller/add.do',sellerEntity);
    }
});