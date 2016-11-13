angular.module("Backend", [])

.factory("Database", ["$http", function($http){
    return {
        get: function(url, callback){
            $http.get(url)
            .then(function(response){
                callback(response.data);
            });
        }
    };
}])

.factory("Dashboard", ["Database", function(Database){
    return {
        init: function(callback){
            Database.get("data/Dashboard.json", function(response){
                callback(response["widgetList"]);
            });
        }
    };
}])

.factory("Navigation", ["Database", function(Database){
    return {
        init: function(callback){
            Database.get("data/QuestionTaggingApp.json", function(response){
                callback(response["navigation"]);
            });
        }
    };
}]);