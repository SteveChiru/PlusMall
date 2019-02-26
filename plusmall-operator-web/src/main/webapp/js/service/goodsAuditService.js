app.service('goodsAuditService',function ($http) {

    //查找
    this.search=function (pageNum, pageSize, searchEntity) {
        return $http.post('../goodsAudit/search.do?pageNum='+pageNum+'&pageSize='+pageSize,searchEntity);
    }

    //更新商品状态
    this.updateStatus=function (ids, status) {
        return $http.get('../goodsAudit/updateStatus.do?ids='+ids+'&status='+status);
    }
})