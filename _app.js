angular.module('templateVsEvent', ['ngRoute']).config(function ($routeProvider)
{
    $routeProvider.when('/', {templateUrl: 'home.html', controller: 'ViewCtrl'});
    $routeProvider.otherwise({redirectTo: '/'});
});
