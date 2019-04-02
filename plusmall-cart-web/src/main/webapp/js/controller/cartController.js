app.controller('cartController',function ($scope,cartService) {

    //查询购物车列表
    $scope.findCartList=function () {
        cartService.findCartList().success(
            function (callback) {
                $scope.cartList = callback;
            }
        );
    }
});