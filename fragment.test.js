describe('Fragment', function ()
{
    'use strict';

    for (var i = 0; i < 100; i++) {
        describe('On enter /', function ()
        {
            beforeEach(function ()
            {
                browser().navigateTo('/');
            });
            it('should show first form', function ()
            {
                expect(element('form:visible').text()).toEqual('This form should be visible');
            });
        });
    }
});
