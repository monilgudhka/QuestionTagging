<html>
    <head>
        <title>Dashboard</title>
        <link rel="stylesheet" href="plugin/material-design-lite/material.min.css">
        <link rel="stylesheet" href="style/main.css">
    </head>
    <body ng-app="QuestionTaggingApp">
        <div class="home-layout mdl-layout mdl-js-layout mdl-layout--fixed-drawer mdl-layout--fixed-header" ng-controller="MainController" ng-init="init()">
            <div class="drawer mdl-layout__drawer mdl-color--blue-grey-900 mdl-color-text--blue-grey-50">
                <nav class="navigation mdl-navigation mdl-color--blue-grey-800">
                    <a class="mdl-navigation__link pointer" ng-repeat="nav in navigationList" ng-click="select(nav)">
                        <i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">{{nav["icon"]}}</i>
                        {{nav["title"]}}
                    </a>
                </nav>
            </div>
            <header class="demo-header mdl-layout__header mdl-color--grey-100 mdl-color-text--grey-600">
                <div class="mdl-layout__header-row">
                    <span class="mdl-layout-title">{{navigation["title"]}}</span>
                </div>
            </header>
            <main class="mdl-layout__content mdl-color--grey-100" url="dashboard">
            </main>
        </div>
    </body>

    <script src="plugin/jquery/dist/jquery.js"></script>
    <script src="plugin/angular/angular.js"></script>
    <script src="plugin/material-design-lite/material.min.js"></script>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <script src="script/main.js"></script>
</html>
