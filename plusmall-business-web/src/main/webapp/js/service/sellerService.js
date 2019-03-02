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

    //验证密码是否正确
    this.checkOrgPwd=function (orgPwd) {
        return $http.get('../seller/checkOrgPwd.do?orgPwd='+orgPwd);
    }

    //更新新密码
    this.updateConfirmNewPwd=function (confirmNewPwd) {
        return $http.get('../seller/updateNewPwd.do?newPwd='+confirmNewPwd);
    }
});