app.controller('loginController',function ($scope, loginService) {
    //读取当前登录人的信息
    $scope.showLoginName=function () {
        loginService.loginName().success(
            function (callback) {
                $scope.loginName=callback.loginName;
            }
        );
    }
})