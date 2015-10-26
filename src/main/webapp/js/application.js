'use strict';

// Declare app level module which depends on filters, and services
angular.module('Catalog', [
	'ui.router',
	'ngCookies',
	'ui.utils',
	'CatalogFilters',
	'CatalogServices',
	'CatalogDirectives',
	'CatalogControllers'
]).
config(['$stateProvider', '$urlRouterProvider', '$locationProvider', '$httpProvider', 
	function($stateProvider, $urlRouterProvider, $locationProvider, $httpProvider) {
		$urlRouterProvider.otherwise("/");

		$stateProvider
			.state('home', {
				url: '/', 
				templateUrl: 'templates/home.html'
			})
			.state('autoPartsSections', {
				url: '/autoPartsSections', 
				templateUrl: 'templates/auto-parts-sections.html'
			});
		
		$locationProvider.hashPrefix('!');
		
		$httpProvider.interceptors.push(['$injector', function ($injector) {
			return $injector.get('AuthInterceptor');
		}]);
	}
]).
run(['$rootScope',
	function($rootScope) {
		
	}
]).
constant('USER_ROLES', {
	all: '*',
	admin: 'ROLE_ADMIN',
	encoder: 'ROLE_ENCODER',
	member: 'ROLE_MEMBER'
}).
constant('AUTH_EVENTS', {
	loginSuccess: 'auth-login-success',
  	loginFailed: 'auth-login-failed',
  	logoutSuccess: 'auth-logout-success',
  	sessionTimeout: 'auth-session-timeout',
  	notAuthenticated: 'auth-not-authenticated',
  	notAuthorized: 'auth-not-authorized'
}).
constant('DATA', {
	pageSize: 20
});