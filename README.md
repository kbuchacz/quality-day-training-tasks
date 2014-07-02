quality-day-training-tasks
==========================

Various tasks and problems to solve

##AngularJS Template vs Event

If your controller is attached to some tag inside included fragment (ng-include) and it has to react to an event raised outside of that fragment (i.e. view controller),
then randomly it may fail.
It's because template is loaded via AJAX request and sometimes it may take long to load it and events from View controller may be raised before that.

This is extract from index.html:

    <div ng-view></div>

    <div>
        <div ng-include="'fragment.html'"></div>
    </div>

Source of fragment.html:

    <div ng-controller="FragmentCtrl">
        <form ng-show="formVisible">This form should be visible</form>
        <form ng-show="!formVisible">This form should NOT be visible</form>
    </div>

The default view is `home.html` controlled by ViewCtrl, which raises the `event:showForm` event.
Included `fragment.html` has 2 forms inside where one should be show when the other should be hidden. It is controlled by `formVisible` variable
that ischanged by FragmentCtrl when `event:showForm` event is raised.

To run the app in the browser :

    grunt

To execute tests:

    grunt test

The solution to the problem is to move `ng-controller="FragmentCtrl"` from `fragment.html` to `index.html`.

index.html:

    <div ng-view></div>

    <div ng-controller="FragmentCtrl">
        <div ng-include="'fragment.html'"></div>
    </div>

fragment.html:

    <div>
        <form ng-show="formVisible">This form should be visible</form>
        <form ng-show="!formVisible">This form should NOT be visible</form>
    </div>
