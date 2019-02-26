app.service('goodsAuditService',function ($http) {

    //查找
    this.search=function (pageNum, pageSize, searchEntity) {
        return $http.post('../goodsAudit/search.do?pageNum='+pageNum+'&pageSize='+pageSize,searchEntity);
    }
})