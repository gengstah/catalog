'use strict';

/* Controllers */

var controllers = angular.module('KCVLendingControllers', []);

controllers.controller('ApplicationController', ['$scope', 'USER_ROLES', 'AuthService', 'AUTH_EVENTS', '$state', 'Session', 'Auth', 'Admin',
	function($scope, roles, AuthService, events, $state, Session, Auth, Admin) {
		$scope.menuUrl = '/templates/header.html';
		$scope.user = Session.user;
		$scope.userRoles = roles;
		$scope.isAuthenticated = AuthService.isAuthenticated;
		$scope.isAuthorized = AuthService.isAuthorized;

		$scope.setCurrentUser = function (user) {
			$scope.user = user;
		};

		$scope.$on(events.notAuthenticated, function() {
			$state.go('home');
		});

		$scope.$on(events.loginSuccess, function() {
			$scope.setCurrentUser(Session.user);
			$state.go('users');
		});

		$scope.$on(events.loginFailed, function() {
			
		});

		$scope.$on(events.logoutSuccess, function() {
			Auth.logout(function() {
				Session.destroy();
				$state.go('home');
			});
		});
	}
]);

controllers.controller('HomeController', ['$scope', '$rootScope', 'AUTH_EVENTS', 'Auth', 'Session', 'DATA', 'USER_ROLES',
    function($scope, $rootScope, events, Auth, Session, DATA, roles) {
		$scope.credentials = {};
		
		$scope.login = function login(credentials) {
			Auth.authenticate($.param({email: $scope.credentials.email, password: $scope.credentials.password}), function(user) {
				delete $scope.loginError;
				console.log(user);
				if(user.userRole != roles.admin) {
					$rootScope.$broadcast(events.loginFailed);
					console.log('User ' + user.contact.email1 + ' is not admin');
				} else {
					$scope.credentials = {};
					Session.create(user);
					$rootScope.$broadcast(events.loginSuccess);
				}
			}, function(error) {
				$scope.loginError = JSON.parse(error.headers('X-KCVLendingServiceApi-Exception'));
				console.log($scope.loginError);
				$rootScope.$broadcast(events.loginFailed);
			});
		};
	}
]);

controllers.controller('UserController', ['$scope', 'Admin', '$state', 'UserDetailService',
	function($scope, Admin, $state, UserDetailService) {
		Admin.pendingUsers(function(pendingUsers) {
			$scope.pendingUsers = pendingUsers;
		});
		
		Admin.activeUsers(function(activeUsers) {
			$scope.activeUsers = activeUsers;
		});
		
		Admin.disabledUsers(function(disabledUsers) {
			$scope.disabledUsers = disabledUsers;
		});
		
		$scope.showUserDetails = function showUserDetails(user) {
			console.log(user);
			UserDetailService.setUser(user);
			$state.go('user', { "user" : user });
		};
	}
]);
	
controllers.controller('HeaderController', ['$scope', '$rootScope', 'AUTH_EVENTS', function($scope, $rootScope, events) {
	$scope.logout = function logout() {
		$rootScope.$broadcast(events.logoutSuccess);
	};
}]);

controllers.controller('UserDetailsController', ['$scope', 'Admin', 'UserDetailService', '$state',
    function($scope, Admin, UserDetailService, $state) {
		$scope.user = UserDetailService.getUser();
		
		$scope.approveUser = function approveUser() {
			Admin.approveUser({id: $scope.user.id}, {}, function() {
				$state.go('users');
			});
		};
		
		$scope.rejectUser = function rejectUser() {
			Admin.rejectUser({id: $scope.user.id}, {}, function() {
				$state.go('users');
			});
		};
		
		$scope.deleteUser = function deleteUser() {
			Admin.deleteUser({id: $scope.user.id}, {}, function() {
				$state.go('users');
			});
		};
		
		$scope.disableUser = function disableUser() {
			Admin.disableUser({id: $scope.user.id}, {}, function() {
				$state.go('users');
			});
		};
		
		$scope.back = function back() {
			$state.go('users');
		};
	}
]);

/*controllers.controller('LenderController', ['$scope', 'Lender', 'Session', '$rootScope', 'AUTH_EVENTS',
	function($scope, Lender, Session, $rootScope, events) {
		$scope.lenderSignUp = lenderSignUp;
		
		$scope.lender = {
			users: [{
				username: '',
				password: '',
				userContact : {
					firstName: '',
					lastName: '',
					phone1: '',
					email1: '',
					contactAddress: {
						addressLine1: '',
						city: '',
						state: '',
						zipcode: ''
					}
				}
			}]
		};
		
		function lenderSignUp(lender) {
			console.log(lender);
			delete lender.users[0].password2;
			Lender.register(lender, function(lenderResponse) {
				$scope.lender = {
					users: [{
						username: '',
						password: '',
						userContact : {
							firstName: '',
							lastName: '',
							phone1: '',
							email1: '',
							contactAddress: {
								addressLine1: '',
								city: '',
								state: '',
								zipcode: ''
							}
						}
					}]
				};
				Session.create(lenderResponse.users[0]);
				$rootScope.$broadcast(events.loginSuccess);
				delete $scope.lenderSignUpErrors;
			}, function(error) {
				$scope.lenderSignUpErrors = JSON.parse(error.headers('X-KCVLendingServiceApi-Exception'));
			});
		}
	}
]);

controllers.controller('BorrowerController', ['$scope', 'Borrower', 'Session', '$rootScope', 'AUTH_EVENTS',
	function($scope, Borrower, Session, $rootScope, events) {
		$scope.borrowerSignUp = borrowerSignUp;
		
		$scope.borrower = {
			users: [{
				username: '',
				password: '',
				userContact : {
					firstName: '',
					lastName: '',
					phone1: '',
					email1: '',
					contactAddress: {
						addressLine1: '',
						city: '',
						state: '',
						zipcode: ''
					}
				}
			}]
		};
		
		function borrowerSignUp(borrower) {
			console.log(borrower);
			delete borrower.users[0].password2;

			Borrower.register(borrower, function(borrowerResponse) {
				$scope.borrower = {
					users: [{
						username: '',
						password: '',
						userContact : {
							firstName: '',
							lastName: '',
							phone1: '',
							email1: '',
							contactAddress: {
								addressLine1: '',
								city: '',
								state: '',
								zipcode: ''
							}
						}
					}]
				};
				Session.create(borrowerResponse.users[0]);
				$rootScope.$broadcast(events.loginSuccess);
				delete $scope.borrowerSignUpErrors;
			}, function(error) {
				$scope.borrowerSignUpErrors = JSON.parse(error.headers('X-KCVLendingServiceApi-Exception'));
			});
		}
	}
]);

controllers.controller('ProfileController', ['$scope', 'Session', 'USER_ROLES', 'Lender', 'Borrower', '$interval',
	function($scope, Session, roles, Lender, Borrower, $interval) {
		$scope.updateProfile = updateProfile;

		if(Session.user.userRole === roles.lender) {
			Lender.get(function(lender) {
				$scope.profile = lender;
			});
		} else if(Session.user.userRole === roles.borrower) {
			Borrower.get(function(borrower) {
				$scope.profile = borrower;
			});
		}

		function updateProfile(profile) {
			if(Session.user.userRole === roles.lender) {
				var lender = new Lender(profile);
				lender.$save(function (lenderResponse) {
					$scope.profile.name = lenderResponse.name;
					$scope.profile.desc = lenderResponse.desc;
					$scope.profileUpdateSuccess = true;
					$interval(function() {
						delete $scope.profileUpdateSuccess;
					}, 5000, 1);
				});
			} else if(Session.user.userRole === roles.borrower) {
				var borrower = new Borrower(profile);
				borrower.$save(function (borrowerResponse) {
					$scope.profile.name = borrowerResponse.name;
					$scope.profile.desc = borrowerResponse.desc;
					$scope.profileUpdateSuccess = true;
					$interval(function() {
						delete $scope.profileUpdateSuccess;
					}, 5000, 1);
				});
			}
		}
	}
]);

controllers.controller('PasswordController', ['$scope', 'Session', 'Auth',
	function($scope, Session, Auth) {
		$scope.changePassword = changePassword;
		
		function changePassword(oldPassword, newPassword) {
			var username = Session.user.username;
			
			Auth.changePassword($.param({username: username, oldPassword: oldPassword, newPassword: newPassword}), function(user) {
				$scope.oldPassword = '';
				$scope.newPassword = '';
				$scope.newPassword2 = '';
			}, function(error) {
				console.log(JSON.parse(error.headers('X-KCVLendingServiceApi-Exception')));
			});
		}
		
		$scope.oldPassword = '';
		$scope.newPassword = '';
		$scope.newPassword2 = '';
	}
]);

controllers.controller('ContactController', ['$scope', 'Contact', '$state', '$stateParams',
	function($scope, Contact, $state, $stateParams) {
		$scope.saveContact = saveContact;
		$scope.deleteContact = deleteContact;


		if($stateParams.id !== undefined) {
			Contact.get({ id : $stateParams.id }, function(contact) {
				$scope.contact = contact;
			});
		}

		function saveContact(contactParameter) {
			var contact = new Contact(contactParameter);
			contact.$save(function(contactResponse) {
				$state.go('profile');
				delete $scope.profileContactFormErrors;
			}, function(error) {
				$scope.profileContactFormErrors = JSON.parse(error.headers('X-KCVLendingServiceApi-Exception'));
			});
		}

		function deleteContact(contactParameter) {
			var contact = new Contact(contactParameter);
			contact.$remove({ id : contactParameter.id }, function() {
				$state.go('profile');
			});
		}
	}
]);

controllers.controller('LoanRequestFormController', ['$scope', 
	function($scope) {
		
	}
]);

controllers.controller('LenderCriteriaFormController', ['$scope', 'LocationCounty',
	function($scope, LocationCounty) {
		LocationCounty.get(function(data) {
			$locations = data;
		});
	}
]);*/