angular.module('app',[]).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market';

    $scope.fillTable = function() {
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null,
                page: $scope.page
            }
        }).then(function (response) {
            $scope.ProductsList = response.data;
        });
    };

    $scope.nextPage = function () {
        $scope.page = $scope.page + 1;
        $scope.fillTable();
    }

    $scope.previousPage = function () {
        if ($scope.page > 0) {
            $scope.page = $scope.page - 1;
        }
        $scope.fillTable();
    }

    $scope.submitCreateNewProduct = function() {
        $http.post(contextPath + '/products', $scope.newProduct)
            .then(function (response) {
                console.log('sended:');
                console.log($scope.newProduct);
                console.log('received');
                console.log(response.data);
                $scope.newProduct = null;
                $scope.fillTable();
            });
    };

    $scope.deleteProductById = function(id) {
        $http.delete(contextPath + '/products/' + id)
            .then(function (response) {
                $scope.fillTable();
            });
    };

    $scope.page = 0;
    $scope.fillTable();
})