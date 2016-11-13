angular.module("QuestionTaggingApp", ["Backend"])
        
.directive("main", ["$compile", function(compile){
    return {
        link: function(scope, element, attrs){
            scope.$watch("navigation", function(newValue, oldValue){
                if(angular.isDefined(newValue) && angular.isDefined(newValue["page"])){
                    element.children("page").hide();
                    if(angular.isUndefined(newValue["element"])){
                        var pageElem = angular.element('<page url="'+newValue["page"]+'"></page>');
                        element.append(pageElem);
                        compile(pageElem)(scope);
                        newValue["element"] = pageElem;
                    }
                    newValue["element"].show();
                }
            });
        }
    };
}])

        
.directive("page", [function(){
    return {
        templateUrl: function(element, attr){
            return attr["url"];
        }
    };
}])

    
.controller("DashboardController", ["$scope", "Dashboard", function(scope, dashboard){
    scope.widgetList;
    scope.init = function(){
        dashboard.init(function(response){
            scope.widgetList = response;
        });
    };
}])
    
.controller("MainController", ["$scope", "Navigation", "Database", function(scope, Navigation, Database){
    scope.navigationList = [];
    scope.navigation = undefined;
    
    scope.init = function(){
        Navigation.init(function(navigationList){
            navigationList.forEach(function(data){
                if(angular.isDefined(data["url"])){
                    Database.get(data["url"], function(response){
                        response[data["tag"]].forEach(function(item){
                            scope.navigationList.push(item);
                        });
                    });
                }else{
                    scope.navigationList.push(data);
                }
            });
            scope.select(scope.navigationList[0]);
        });
    };
        
    scope.select = function(navigation){
        scope.navigation = navigation;
    };
}]);