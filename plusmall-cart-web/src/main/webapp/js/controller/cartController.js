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
                //设置默认地址
                for(var i=0;i< $scope.addressList.length;i++){
                    if($scope.addressList[i].isDefault=='1'){
                        $scope.address=$scope.addressList[i];
                        break;
                    }
                }
            }
        );
    }

    //选择地址
    $scope.selectAddress=function (address) {
        $scope.address=address;
    }

    //判断是否是当前选中的地址
    $scope.isSelectedAddress=function (address) {
        if (address==$scope.address){
            return true;
        } else {
            return false;
        }
    }

    $scope.order={paymentType:'1'};
    //选择支付方式
    $scope.selectPayType=function (type) {
        $scope.order.paymentType = type;
    }

});