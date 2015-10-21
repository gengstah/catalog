'use strict';

/* Services */

var services = angular.module('KCVLendingServices', ['ngResource']);

services.factory('Auth', ['$resource', 
	function($resource) {
		return $resource('/api/service/user/:action', {}, {
			authenticate: { 
				method: 'POST', 
				params: { 
					action: 'authenticate'
				}, 
				headers : { 
					'Content-Type': 'application/x-www-form-urlencoded' 
				}
			},
			logout: { 
				method: 'GET', 
				params: { 
					action: 'logout'
				}
			},
			changePassword: {
				method: 'POST',
				params: { 
					action: 'changepassword'
				},
				headers : { 
					'Content-Type': 'application/x-www-form-urlencoded' 
				}
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

services.service('UserDetailService',
	function() {
		this.setUser = function(user) {
			this.user = user;
		};
		
		this.getUser = function() {
			return this.user;
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

services.factory('Admin', ['$resource', 
	function($resource) {
		return $resource('/api/service/admin/:module/:path/:id', { }, {
			pendingUsers: {
				method: 'GET',
				params: { 
					module: 'user',
					path: 'pending'
				},
				isArray: true
			},
			activeUsers: {
				method: 'GET',
				params: { 
					module: 'user',
					path: 'active'
				},
				isArray: true
			},
			disabledUsers: {
				method: 'GET',
				params: { 
					module: 'user',
					path: 'disabled'
				},
				isArray: true
			},
			approveUser: {
				method: 'POST',
				params: { 
					module: 'user',
					path: 'approve'
				}
			},
			rejectUser: {
				method: 'POST',
				params: { 
					module: 'user',
					path: 'reject'
				}
			},
			deleteUser: {
				method: 'POST',
				params: { 
					module: 'user',
					path: 'close'
				}
			},
			disableUser: {
				method: 'POST',
				params: { 
					module: 'user',
					path: 'disable'
				}
			}
		});
	}
]);