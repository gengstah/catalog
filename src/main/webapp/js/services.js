'use strict';

/* Services */

var services = angular.module('CatalogServices', ['ngResource']);

services.factory('Header', ['$resource', 
	function($resource) {
		return $resource('/api/service/header');
 	}
]);

services.factory('Section', ['$resource', 
	function($resource) {
		return $resource('/api/service/section/:header');
	}
]);

services.factory('Car', ['$resource', 
	function($resource) {
		return $resource('/api/service/car/:year/:make/:model/:submodel/:engine');
	}
]);

services.factory('AutoPart', ['$resource', 
	function($resource) {
		return $resource('/api/service/part/:section/:car');
 	}
]);

services.factory('Attribute', ['$resource', 
 	function($resource) {
 		return $resource('/api/service/attr/:path/:id', {}, {
 			findAttributesByAutoPart: { 
 				method: 'GET',
 				params: { 
					path: 'part'
				},
				isArray: true
 			},
 			findDefaultAttributesOfSection: {
 				method: 'GET',
 				params: { 
					path: 'section'
				},
				isArray: true
 			}
 		});
 	}
 ]);

services.factory('AuthService', ['Session',
	function (Session) {
		var authService = {};

		authService.isAuthenticated = function () {
			return !!Session.user;
		};

		authService.isAuthorized = function (authorizedRoles) {
			if(!angular.isArray(authorizedRoles)) authorizedRoles = [authorizedRoles];

			return (authService.isAuthenticated() && 
				authorizedRoles.indexOf(Session.user.userRole) !== -1);
		};

		return authService;
	}
]);

services.service('Session',
	function() {
		this.create = function(user) {
			this.user = user;
		};

		this.destroy = function() {
			this.user = null;
		};

		return this;
	}
);

services.service('HeaderService',
	function() {
		this.setHeaders = function(headers) {
			this.headers = headers;
		};
		
		this.getHeaders = function() {
			return this.headers;
		};
		
		return this;
	}	
);

services.factory('AuthInterceptor', ['$rootScope', '$q', 'AUTH_EVENTS',
	function($rootScope, $q, events) {
		return {
			responseError: function(response) {
			
				$rootScope.$broadcast({
					401: events.notAuthenticated,
					403: events.notAuthorized,
        			419: events.sessionTimeout,
        			440: events.sessionTimeout
				}[response.status], response);
				
				return $q.reject(response);
				
			}
		};
	}
]);