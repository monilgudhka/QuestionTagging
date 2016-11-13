angular.module("Dashboard", ["QuestionTaggingApp"])

.controller("DashboardController", ["$scope", "Dashboard", function(scope, dashboard){
    scope.widgetList;
    scope.init = function(){
        dashboard.init(function(response){
            scope.widgetList = response;
        });
    };
}]);