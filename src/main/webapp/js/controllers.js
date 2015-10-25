'use strict';

/* Controllers */

var controllers = angular.module('CatalogControllers', []);

controllers.controller('ApplicationController', ['$scope', '$state', 'Car', 'Header', 'Section', 'AutoPart', 'Attribute', 'HeaderService',
	function($scope, $state, Car, Header, Section, AutoPart, Attribute, HeaderService) {
		var cars = Car.query();
		var car = Car.get({ year: 1993, make: 'Nissan', model: 'Sentra', submodel: 'SE', engine: '4 Cyl 1.6L' });
		console.log("%cCarManager#findAllCars: %O", "color: green", cars);
		console.log("%cCarManager#findCar: %O", "color: green", car);
		
		var headers = Header.query();
		HeaderService.setHeaders(headers);
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

controllers.controller('HomeController', ['$scope', '$rootScope', 'Header', 'HeaderService',
    function($scope, $rootScope, Header, HeaderService) {
		var columnCount = 3;
		Header.query(function(headers) {			
			var displayHeaders = [];
			for(var headerIndex = 0;headerIndex < headers.length;headerIndex++) {
				var header = headers[headerIndex];
				var displayHeader = { id: header.id, name: header.name, sections: header.sections, columns: [] };
				var sectionPerColumn = Math.ceil(header.sections.length / columnCount);
				
				for(var sectionIndex = 0;sectionIndex < header.sections.length;sectionIndex += sectionPerColumn) {
					var sectionColumn = { start: sectionIndex, end: Math.min(sectionIndex + sectionPerColumn, header.sections.length) };
					displayHeader.columns.push(sectionColumn);
				}
				displayHeaders.push(displayHeader);
			}
			
			$scope.headers = displayHeaders;
		});
	}
]);