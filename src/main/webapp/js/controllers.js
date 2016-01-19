'use strict';

/* Controllers */

var controllers = angular.module('CatalogControllers', []);

controllers.controller('ApplicationController', ['$scope', '$state', 'Car', 'Header', 'Section', 'AutoPart', 'Attribute', 'CarService', 'HeaderService',
	function($scope, $state, Car, Header, Section, AutoPart, Attribute, CarService, HeaderService) {
	
		/*Car.query(function(cars) {
			CarService.setCars(cars);
			console.log("%cCarManager#findAllCars: %O", "color: green", cars);
		});
		
		Car.get({ year: 1993, make: 'Nissan', model: 'Sentra', submodel: 'SE', engine: '4 Cyl 1.6L' }, function(car) {
			console.log("%cCarManager#findCar: %O", "color: green", car);
		});
		
		Header.query(function(headers) {
			HeaderService.setHeaders(headers);
			console.log("%cHeaderManager#findAllHeaders: %O", "color: green", headers);
		});
		
		Section.query(function(sectionNames) {
			console.log("%cSectionManager#findAllDistinctSectionName: %O", "color: green", sectionNames);
		});
		
		Section.query({ header: 1 }, function(sections) {
			console.log("%cSectionManager#findSectionsByHeader: %O", "color: green", sections);
		});
		
		AutoPart.query(function(autoPartNames) {
			console.log("%cAutoPartManager#findAllDistinctAutoPartName: %O", "color: green", autoPartNames);
		});
		
		AutoPart.query({ section: 229 }, function(autoPartsBySection) {
			console.log("%cAutoPartManager#findAutoPartsBySection: %O", "color: green", autoPartsBySection);
		});
		
		AutoPart.query({ section: 229, car: 1 }, function(autoPartsBySectionAndCar) {
			console.log("%cAutoPartManager#findAutoPartsBySectionAndCar: %O", "color: green", autoPartsBySectionAndCar);
		});
		
		Attribute.query(function(attributes) {
			console.log("%cAttributeManager#findAllAttribute: %O", "color: green", attributes);
		});
		
		Attribute.findAttributesByAutoPart({ id: 1 }, function(attributesByAutoPart) {
			console.log("%cAttributeManager#findAttributesByAutoPart: %O", "color: green", attributesByAutoPart);
		});
		
		Attribute.findDefaultAttributesOfSection({ id: 4 }, function(defaultAttributesOfSection) {
			console.log("%cAttributeManager#findDefaultAttributesOfSection: %O", "color: green", defaultAttributesOfSection);
		});*/
		
		$scope.removeCar = function removeCar() {
			$scope.car = null;
			CarService.destroyCar();
		};
		
		Car.query(function(cars) {
			CarService.setCars(cars);
						
			var displayCars = {};
			for(var carIndex = 0; carIndex < cars.length;carIndex++) {
				var car = cars[carIndex];
				
				if(displayCars[car.year] == undefined) displayCars[car.year] = {};
				if(displayCars[car.year][car.make] == undefined) displayCars[car.year][car.make] = {};
				if(displayCars[car.year][car.make][car.model] == undefined) displayCars[car.year][car.make][car.model] = {};
				if(displayCars[car.year][car.make][car.model][car.submodel] == undefined) displayCars[car.year][car.make][car.model][car.submodel] = {};
				if(displayCars[car.year][car.make][car.model][car.submodel][car.engine] == undefined) displayCars[car.year][car.make][car.model][car.submodel][car.engine] = {};
			}
			
			$scope.cars = displayCars;
						
			$scope.toggleMake = function toggleMake(year) {
				if(year == "") $('#make').attr('disabled', 'disabled'); 
				else $('#make').removeAttr('disabled');
				
				$('#model').attr('disabled', 'disabled');
				$('#submodel').attr('disabled', 'disabled');
				$('#engine').attr('disabled', 'disabled');
			};
			
			$scope.toggleModel = function toggleModel(make) {
				if(make == "") $('#model').attr('disabled', 'disabled'); 
				else $('#model').removeAttr('disabled');
				
				$('#submodel').attr('disabled', 'disabled');
				$('#engine').attr('disabled', 'disabled');
			};
			
			$scope.toggleSubmodel = function toggleSubmodel(model) {
				if(model == "") $('#submodel').attr('disabled', 'disabled'); 
				else $('#submodel').removeAttr('disabled');
				
				$('#engine').attr('disabled', 'disabled');
			};
			
			$scope.toggleEngine = function toggleEngine(submodel) {
				if(submodel == "") $('#engine').attr('disabled', 'disabled'); 
				else $('#engine').removeAttr('disabled');
			};
			
			$scope.findCar = function findCar(year, make, model, submodel, engine) {
				console.log("year: %O", year);
				console.log("make: %O", make);
				console.log("model: %O", model);
				console.log("submodel: %O", submodel);
				console.log("engine: %O", engine);
				if(year && make && model && submodel && engine) {
					Car.get({ year: year, make: make, model: model, submodel: submodel, engine: engine }, function(car) {
						console.log("%cCarManager#findCar: %O", "color: green", car);
						CarService.setCar(car);
						$scope.car = car;
						$state.go("autoPartsSections");
						$scope.year = "";
						$scope.make = "";
						$scope.model = "";
						$scope.submodel = "";
						$scope.engine = "";
					});
				}
			};
			
		});
		
	}
]);

controllers.controller('HeaderController', ['$scope', '$rootScope', 'CarService',
	function($scope, $rootScope, CarService) {
  		
  	}
]);

controllers.controller('HomeController', ['$scope', '$state', 'Car', 'CarService',
    function($scope, $state, Car, CarService) {
		
	}
]);

controllers.controller('AutoPartsSectionController', ['$scope', '$rootScope', '$state', 'HeaderService', 'CarService', 'SectionService',
	function($scope, $rootScope, $state, HeaderService, CarService, SectionService) {
		if(CarService.getCar() == null || CarService.getCar() == undefined) $state.go("home");
  		var columnCount = 3;
  		var headers = HeaderService.getHeaders();
  		
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
  		$scope.car = CarService.getCar();
  		$scope.showAutoParts = function showAutoParts(section) {
  			if(section) {
  				SectionService.setSection(section);
  				$state.go("autoParts");
  			}
  		};
  	}
]);

controllers.controller('AutoPartsController', ['$scope', '$rootScope', '$state', 'AutoPart', 'CarService', 'SectionService',
	function($scope, $rootScope, $state, AutoPart, CarService, SectionService) {
		if(SectionService.getSection() == null || SectionService.getSection() == undefined) $state.go("autoPartsSections");
		
		if(CarService.getCar())
			AutoPart.query({ section: SectionService.getSection().id, car: CarService.getCar().id, page: 1, size: 20 }, function(autoPartsBySectionAndCar) {
				console.log("%cAutoPartManager#findAutoPartsBySectionAndCar: %O", "color: green", autoPartsBySectionAndCar);
				$scope.autoParts = autoPartsBySectionAndCar;
				$scope.car = CarService.getCar();
			});
		else
			AutoPart.query({ section: SectionService.getSection().id, page: 1, size: 20 }, function(autoPartsBySection) {
				console.log("%cAutoPartManager#findAutoPartsBySection: %O", "color: green", autoPartsBySection);
				$scope.autoParts = autoPartsBySection;
			});
	}
]);