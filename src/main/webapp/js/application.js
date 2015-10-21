'use strict';

// Declare app level module which depends on filters, and services
angular.module('KCVLending', [
	'ui.router',
	'ngCookies',
	'ui.utils',
	'KCVLendingFilters',
	'KCVLendingServices',
	'KCVLendingDirectives',
	'KCVLendingControllers'
]).
config(['$stateProvider', '$urlRouterProvider', '$locationProvider', '$httpProvider', 'USER_ROLES', 
	function($stateProvider, $urlRouterProvider, $locationProvider, $httpProvider, roles) {
		$urlRouterProvider.otherwise("/");

		$stateProvider
			.state('home', {
				url: '/', 
				templateUrl: 'templates/home.html',
				data: {
					authorizedRoles: [roles.all]
				}
			})
			.state('users', {
				url: '/users', 
				templateUrl: 'templates/users.html',
				data: {
					authorizedRoles: [roles.admin]
				}
			})
			.state('user', {
				url: '/user', 
				templateUrl: 'templates/user-details.html',
				data: {
					authorizedRoles: [roles.admin]
				}
			})
			/*
			.state('about', {
				url: '/about', 
				templateUrl: 'templates/about.html',
				data: {
					authorizedRoles: [roles.all]
				}
			})
			.state('contact', {
				url: '/contact', 
				templateUrl: 'templates/contact.html',
				data: {
					authorizedRoles: [roles.all]
				}
			})
			.state('feedback', {
				url: '/feedback', 
				templateUrl: 'templates/feedback.html',
				data: {
					authorizedRoles: [roles.all]
				}
			})
			.state('privacy', {
				url: '/privacy', 
				templateUrl: 'templates/privacy.html',
				data: {
					authorizedRoles: [roles.all]
				}
			})
			.state('lenderCriteriaForm', {
				url: '/lenderCriteriaForm', 
				templateUrl: 'templates/lender-criteria-form.html',
				data: {
					authorizedRoles: [roles.lender]
				}
			})
			.state('loanRequestsList', {
				url: '/loanRequestsList', 
				templateUrl: 'templates/lender-loan-request-list.html',
				data: {
					authorizedRoles: [roles.lender]
				}
			})
			.state('loanRequestForm', {
				url: '/loanRequestForm', 
				templateUrl: 'templates/borrower-loan-request-form.html',
				data: {
					authorizedRoles: [roles.borrower]
				}
			})
			.state('profile', {
				url: '/profile', 
				templateUrl: 'templates/profile.html',
				data: {
					authorizedRoles: [roles.lender, roles.borrower]
				}
			})
			.state('signup', {
				url: '/signup', 
				templateUrl: 'templates/signup.html',
				data: {
					authorizedRoles: [roles.all]
				}
			})
			.state('lenderSignUp', {
				url: '/signup/lender', 
				templateUrl: 'templates/signup-lender.html',
				data: {
					authorizedRoles: [roles.all]
				}
			})
			.state('borrowerSignUp', {
				url: '/signup/borrower', 
				templateUrl: 'templates/signup-borrower.html',
				data: {
					authorizedRoles: [roles.all]
				}
			})
			.state('changePassword', {
				url: '/changePassword', 
				templateUrl: 'templates/change-password.html',
				data: {
					authorizedRoles: [roles.lender, roles.borrower]
				}
			})
			.state('addProfileContact', {
				url: '/profile/contact', 
				templateUrl: 'templates/add-contact.html',
				data: {
					authorizedRoles: [roles.lender, roles.borrower]
				}
			})
			.state('updateProfileContact', {
				url: '/profile/contact/:id', 
				templateUrl: 'templates/update-contact.html',
				data: {
					authorizedRoles: [roles.lender, roles.borrower]
				}
			})*/;
		
		$locationProvider.hashPrefix('!');
		
		$httpProvider.interceptors.push(['$injector', function ($injector) {
			return $injector.get('AuthInterceptor');
		}]);
	}
]).
run(['$rootScope', 'AUTH_EVENTS', 'AuthService', 'USER_ROLES', 'Auth', 'Session',
		function($rootScope, events, AuthService, roles, Auth, Session) {
	Auth.get(function(user) {
		if(user != null) {
			Session.create(user);
			$rootScope.$broadcast(events.loginSuccess);
		}
	});

	$rootScope.$on('$stateChangeStart', function (event, next) {
		var authorizedRoles = next.data.authorizedRoles;
		if (authorizedRoles.indexOf(roles.all) === -1 && !AuthService.isAuthorized(authorizedRoles)) {
			event.preventDefault();
			if (AuthService.isAuthenticated()) {
				$rootScope.$broadcast(events.notAuthorized);
			} else {
				$rootScope.$broadcast(events.notAuthenticated);
			}
		}
	});
}]).
constant('USER_ROLES', {
	all: '*',
	lender: 'ROLE_LENDER',
	borrower: 'ROLE_BORROWER',
	admin: 'ROLE_ADMIN'
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