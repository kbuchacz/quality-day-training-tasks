(function ()
{
    'use strict';

    module.exports = function (grunt)
    {
        grunt.initConfig({
            open: {
                server: {
                    url: "http://localhost:8000"
                }
            },
            concurrent: {
                test: ['connect', 'karma']
            },
            connect: {
                keepalive: {
                    options: {
                        port: 8000,
                        keepalive: true
                    }
                }
            },
            karma: {
                e2e: {
                    configFile: 'karma-e2e.conf.js',
                    singleRun: true
                }
            }
        });
        grunt.loadNpmTasks('grunt-concurrent');
        grunt.loadNpmTasks('grunt-karma');
        grunt.loadNpmTasks('grunt-open');
        grunt.loadNpmTasks('grunt-contrib-connect');
        grunt.registerTask("default", ["open", "connect:keepalive"]);
        grunt.registerTask("test", ["concurrent"]);
    }
})();
