'use strict';

/* Controllers */

var controllers = angular.module('CatalogControllers', []);

controllers.controller('ApplicationController', ['$scope', '$state',
	function($scope, $state) {
		
	}
]);

controllers.controller('HomeController', ['$scope', '$rootScope',
    function($scope, $rootScope) {
		
	}
]);
	
controllers.controller('HeaderController', ['$scope', '$rootScope', 'AUTH_EVENTS', function($scope, $rootScope, events) {
	$scope.logout = function logout() {
		$rootScope.$broadcast(events.logoutSuccess);
	};
}]);