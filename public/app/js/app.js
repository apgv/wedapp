'use strict';


// Declare app level module which depends on filters, and services
angular.module('myApp', ['myApp.filters', 'myApp.services', 'myApp.directives', '$strap.directives']).
	config(['$routeProvider', function($routeProvider) {
		$routeProvider.when('/home', {templateUrl: 'partials/home.html'});
		$routeProvider.when('/registration', {templateUrl: 'partials/registration.html', controller: RegistrationCtrl});
		$routeProvider.when('/wishlist', {templateUrl: 'partials/wishlist.html', controller: WishlistCtrl});
		$routeProvider.when('/directions', {templateUrl: 'partials/directions.html'});
		$routeProvider.when('/sleep', {templateUrl: 'partials/sleeping-arrangments.html'});
		$routeProvider.when('/contact', {templateUrl: 'partials/contact.html'});
		$routeProvider.when('/guestlist', {templateUrl: 'partials/guestlist-attending.html', controller: GuestListCtrl});
		$routeProvider.when('/guestlistall', {templateUrl: 'partials/guestlist-all.html', controller: GuestListAllCtrl});
		$routeProvider.when('/login', {templateUrl: 'partials/login.html'});
		$routeProvider.when('/settings', {templateUrl: 'partials/user-settings.html'});
		$routeProvider.otherwise({redirectTo: '/home'});
	}]);
