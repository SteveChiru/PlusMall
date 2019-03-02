app.controller('sellerController',function ($scope, $controller, sellerService,loginService) {
    $controller('baseController',{$scope:$scope});    //继承

    //商家申请入驻
    $scope.apply=function () {
        sellerService.add($scope.seller).success(
            function (callback) {
                if (callback.success){
                    location.href='shoplogin.html';
                }else {
                    alert(callback.msg);
                }
            }
        );
    }

    //根据sellerId查找单个用户信息
    $scope.findOne=function(){
        loginService.loginName().success(
            function (callback) {
                $scope.loginName=callback.loginName;
                sellerService.findOne($scope.loginName).success(
                    function (callback) {
                        $scope.seller=callback;
                    }
                )
            }
        );
    }

    //更新信息
    $scope.update=function () {
        sellerService.update($scope.seller).success(
            function (callback) {
                if (callback.success){
                    top.location.href='/shoplogin.html';
                } else{
                    alert(callback.msg);
                }
            }
        )
    }

    //输入原始密码完成后，检测密码是否正确
    $scope.checkOrgPwd=function () {
        sellerService.checkOrgPwd($scope.orgPwd).success(
            function (callback) {
                if (!callback.success){
                    alert("密码输入错误，请重新输入");
                    $scope.orgPwd="";
                }
            }
        )
    }

    //输入新密码后，保证新密码和原始密码不一样
    $scope.checkNewPwd=function () {
        if ($scope.newPwd==$scope.orgPwd){
            alert("新密码不能和原始密码一样，请重新设置");
            $scope.newPwd="";
        }
    }

    //输入确认新密码后，要保证确认新密码和新密码是一致的
    $scope.checkConfirmNewPwd=function () {
        if ($scope.confirmNewPwd!=$scope.newPwd){
            alert("确认密码与新设置密码不一致，请重新输入");
            $scope.confirmNewPwd="";
        }
    }
    
    //更新密码
    $scope.updateConfirmNewPwd=function () {
        sellerService.updateConfirmNewPwd($scope.confirmNewPwd).success(
            function (callback) {
                if (callback.success){
                    top.location.href='/pages/index.html';
                }
            }
        )
    }
});