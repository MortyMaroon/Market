angular.module('app',[]).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market/api/v1';

    $scope.fillTable = function(pageIndex = 1) {
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                title: $scope.filter ? $scope.filter.title : null,
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null,
                page: pageIndex
            }
        }).then(function (response) {
            $scope.ProductsPage = response.data;

            let minPageIndex = pageIndex - 2;
            if (minPageIndex < 1) {
                minPageIndex = 1;
            }

            let maxPageIndex = pageIndex + 2;
            if (maxPageIndex > $scope.ProductsPage.totalPages) {
                maxPageIndex = $scope.ProductsPage.totalPages;
            }

            $scope.PaginationArray = $scope.generatePageIndexes(minPageIndex, maxPageIndex);
        });
    };

    $scope.fillCart = function() {
        $http({
            url: contextPath + '/cart',
            method: 'GET'
        }).then(function (response) {
            $scope.ProductsInCart = response.data;
        });
    };

    $scope.deleteProductFromCart = function(id) {
        $http({
            url: contextPath + '/cart/delete/',
            method: 'GET',
            params: {
                id : id
            }
        }).then(function (response) {
            $scope.fillCart();
        });
    }

    $scope.addProductInCart = function(id) {
        $http({
            url: contextPath + '/cart/add/',
            method: 'GET',
            params: {
                id : id
            }
        }).then(function (response) {
            $scope.fillCart();
        });
    }

    $scope.generatePageIndexes = function(startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
            arr.push(i)
        }
        return arr;
    }

    $scope.submitCreateNewProduct = function() {
        $http.post(contextPath + '/products', $scope.newProduct)
            .then(function (response) {
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

    $scope.fillTable();
    $scope.fillCart();
})