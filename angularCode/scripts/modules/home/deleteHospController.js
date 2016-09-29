angular.module('app')
    .controller('hospDelete', function ($scope, $http, $window) {
        $scope.ondelete = function ondelete() {

            var baseUrl = 'http://localhost:8181/RESTfulExample/rest/myapp/' + $scope.h_id;

            $http.delete(baseUrl)
            .success(function(){
                $window.location.reload();
                $window.alert("Deleted Successfully");
            })
            .error(function(){
            });
        }
    });
