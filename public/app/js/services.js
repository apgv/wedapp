'use strict';

/* Services */


// Demonstrate how to register services
// In this case it is a simple value service.
var myModule = angular.module('myApp.services', ['ngResource', 'ngCookies', 'ngGrid']);

myModule.value('version', '1.5');

myModule.factory('Wish', function($resource) {
	return $resource('/wishlist', {}, {
		query: {method: 'GET', params: {}, isArray: true}
	});
});

myModule.factory('Hotel', function($resource) {
	return $resource('/hotels', {}, {
		query: {method: 'GET', params: {}, isArray: true}
	});
});

myModule.factory('Guest', function($resource) {
	return $resource('/guests/:action', {}, {
		query: {method: 'GET', params: {action: 'attending'}, isArray: true},
		queryAll: {method: 'GET', params: {action: 'all'}, isArray: true},
		save: {method: 'POST', params: {}, isArray: true},
		saveUpdate: {method: 'POST', params: {action: 'update'}, isArray: true},
		deleteGuests: {method: 'POST', params: {action: 'delete'}, isArray: true}
	});
});

myModule.factory('User', function($resource) {
	return $resource('/users/:action', {}, {
		login: {method: 'POST', params: {action: 'login'}},
		logout: {method: 'POST', params: {action: 'logout'}},
		changePassword: {method: 'POST', params: {action: 'changepassword'}}
	})
});
