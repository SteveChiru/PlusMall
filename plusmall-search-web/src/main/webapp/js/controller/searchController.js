app.controller('searchController',function($scope,searchService){

    //搜索
    $scope.search=function(){
        searchService.search( $scope.searchMap ).success(
            function(callback){
                $scope.resultMap=callback;//搜索返回的结果
            }
        );
    }
});
