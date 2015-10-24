'use strict';

/* Controllers */

var controllers = angular.module('CatalogControllers', []);

controllers.controller('ApplicationController', ['$scope', '$state', 'Car',
	function($scope, $state, Car) {
		var cars = Car.query();
		console.log("%cCarManager#findAllCars: %O", "color: blue", cars);
		
		var car = Car.get({year: 1993, make: 'Nissan', model: 'Sentra', submodel: 'SE', engine: '4 Cyl 1.6L'});
		console.log("%cCarManager#findCar: %O", "color: blue", car);
	}
]);

controllers.controller('HomeController', ['$scope', '$rootScope',
    function($scope, $rootScope) {
		
	}
]);