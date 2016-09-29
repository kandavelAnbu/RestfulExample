angular.module('app')
    .controller('hospUpdate', function ($scope, $http, $window) {
        $scope.onupdate = function onupdate() {

            var baseUrl = 'http://localhost:8181/RESTfulExample/rest/myapp/hosUpdate';

            var hospital = {};

            hospital.h_id = $scope.h_id;
            hospital.name = $scope.name;

            var request = {
                h_id: hospital.h_id,
                name: hospital.name
            };

            $http.put(baseUrl, request)
            .success(function(){
                $window.location.reload();
                $window.alert("Updated Successfully");
            })
            .error(function(){
            });
        }
    });
