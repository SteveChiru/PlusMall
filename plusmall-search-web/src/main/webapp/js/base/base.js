var app = angular.module('plusmall',[]);

//$sce服务写成过滤器
app.filter('trustHtml',['$sce',function ($sce) {
    return function (callback) {
        return $sce.trustAsHtml(callback);
    }
}]);