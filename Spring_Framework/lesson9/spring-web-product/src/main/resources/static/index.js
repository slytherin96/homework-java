angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8191/app/api/v1';

    //$scope.loadProducts = function () {
  //      $http.get(contextPath + '/products')
  //          .then(function (response) {
 //               $scope.ProductsList = response.data;
 //           });
 //   };
     $scope.loadProducts = function (pageIndex = 1) {
             $http({
                 url: contextPath + '/products',
                 method: 'GET',
                 params: {
                     title_part: $scope.filter ? $scope.filter.title_part : null,
                     min_cost: $scope.filter ? $scope.filter.min_cost : null,
                     max_cost: $scope.filter ? $scope.filter.max_cost : null
                 }
             }).then(function (response) {
              $scope.ProductsList = response.data.content;
             });
         };

    $scope.deleteProduct = function (productId) {
        $http.delete(contextPath + '/products/' + productId)
            .then(function (response) {
                $scope.loadProducts();
            });
    }

    $scope.changeScore = function (productId, delta) {
        $http({
            url: contextPath + '/products/change_score',
            method: 'POST',
            params: {
                productId: productId,
                delta: delta
            }
        }).then(function (response) {
            $scope.loadProducts();
        });
    }

     $scope.createProductJson = function () {
         console.log($scope.newProductJson);
         $http.post(contextPath + '/products', $scope.newProductJson)
             .then(function (response) {
                 $scope.loadProducts();
             });
     }

    $scope.loadProducts();
});