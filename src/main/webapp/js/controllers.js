'use strict';

/* Controllers */

var controllers = angular.module('CatalogControllers', []);

controllers.controller('ApplicationController', ['$scope', '$state', 'Car', 'Header', 'Section', 'AutoPart', 'Attribute',
	function($scope, $state, Car, Header, Section, AutoPart, Attribute) {
		var cars = Car.query();
		var car = Car.get({ year: 1993, make: 'Nissan', model: 'Sentra', submodel: 'SE', engine: '4 Cyl 1.6L' });
		console.log("%cCarManager#findAllCars: %O", "color: green", cars);
		console.log("%cCarManager#findCar: %O", "color: green", car);
		
		var headers = Header.query();
		console.log("%cHeaderManager#findAllHeaders: %O", "color: green", headers);
		
		var sectionNames = Section.query();
		var sections = Section.query({ header: 1 });
		console.log("%cSectionManager#findAllDistinctSectionName: %O", "color: green", sectionNames);
		console.log("%cSectionManager#findSectionsByHeader: %O", "color: green", sections);
		
		var autoPartNames = AutoPart.query();
		var autoPartsBySection = AutoPart.query({ section: 229 });
		var autoPartsBySectionAndCar = AutoPart.query({ section: 229, car: 1 });
		console.log("%cAutoPartManager#findAllDistinctAutoPartName: %O", "color: green", autoPartNames);
		console.log("%cAutoPartManager#findAutoPartsBySection: %O", "color: green", autoPartsBySection);
		console.log("%cAutoPartManager#findAutoPartsBySectionAndCar: %O", "color: green", autoPartsBySectionAndCar);
		
		var attributes = Attribute.query();
		var attributesByAutoPart = Attribute.findAttributesByAutoPart({ id: 1 });
		var defaultAttributesOfSection = Attribute.findDefaultAttributesOfSection({ id: 4 });
		console.log("%cAttributeManager#findAllAttribute: %O", "color: green", attributes);
		console.log("%cAttributeManager#findAttributesByAutoPart: %O", "color: green", attributesByAutoPart);
		console.log("%cAttributeManager#findDefaultAttributesOfSection: %O", "color: green", defaultAttributesOfSection);
	}
]);

controllers.controller('HomeController', ['$scope', '$rootScope',
    function($scope, $rootScope) {
		
	}
]);