app.service("searchService",function ($http) {

    //查找
    this.search=function (searchMap) {
        return $http.post('itemsearch/search.do',searchMap);
    }
});