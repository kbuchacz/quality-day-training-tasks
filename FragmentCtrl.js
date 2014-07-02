angular.module('templateVsEvent').controller('FragmentCtrl', function ($scope)
{
    $scope.formVisible = false;

    console.log('FragmentCtrl');
    $scope.$on('event:showForm', function ()
    {
        console.log('$on event:showForm');
        $scope.formVisible = true;
    });

});
