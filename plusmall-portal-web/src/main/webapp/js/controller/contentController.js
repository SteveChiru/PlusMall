//广告控制层（运营商后台）
app.controller("contentController",function($scope,contentService){
    $scope.contentList=[];//广告集合
    $scope.findByCategoryId=function(categoryId){
        contentService.findByCategoryId(categoryId).success(
            function(callback){
                $scope.contentList[categoryId]=callback;
            }
        );
    }

    //搜索跳转
    $scope.search=function () {
        location.href="http://localhost:9390/search.html#?keywords="+$scope.keywords;
    }
});
