angular.module("QuestionTaggingApp", [])


.factory("database", ["$http", function($http){
    return {
        get: function(url, callback){
            $http.get(url)
            .then(function(response){
                callback(response.data);
            });
        },
        init: function(callback){
            this.get("data/QuestionTaggingApp.json", function(response){
                callback(response["navigation"]);
            });
        }
    };
}])

        
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

        
.controller("MainController", ["$scope", "database", function(scope, database){
    scope.navigationList = [];
    scope.navigation = undefined;
    
    scope.init = function(){
        database.init(function(navigationList){
            navigationList.forEach(function(data){
                if(angular.isDefined(data["url"])){
                    database.get(data["url"], function(response){
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