app.controller('cartController',function ($scope,cartService) {

    //查询购物车列表
    $scope.findCartList=function () {
        cartService.findCartList().success(
            function (callback) {
                $scope.cartList = callback;
                $scope.totalValue=cartService.sum($scope.cartList);     //求合计数
            }
        );
    }

    //添加商品到购物车
    $scope.addGoodsToCartList=function(itemId,num){
        cartService.addGoodsToCartList(itemId,num).success(
            function(callback){
                if(callback.success){
                    $scope.findCartList();//刷新列表
                }else{
                    alert(callback.msg);//弹出错误提示
                }
            }
        );
    }

    $scope.findAddresslist=function () {
        cartService.findAddressList().success(
            function (callback) {
                $scope.addressList = callback;
            }
        );
    }

});