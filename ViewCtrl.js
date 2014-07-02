angular.module('templateVsEvent').controller('ViewCtrl', function ($rootScope)
{
    console.log('broadcast event:showForm');
    $rootScope.$broadcast('event:showForm');
});
