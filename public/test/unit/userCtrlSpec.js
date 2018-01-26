'use strict';

/* jasmine specs for controllers go here */

describe('UserCtrl', function() {
	var scope, ctrl, $httpBackend, cookies, location;

	beforeEach(module('myApp.services'));

	beforeEach(inject(function(_$httpBackend_, $rootScope, $cookies, $location, $controller) {
		$httpBackend = _$httpBackend_;
		cookies = $cookies;
		location = $location;

		scope = $rootScope.$new();
		ctrl = $controller(UserCtrl, {$scope: scope});
	}));

	afterEach(function() {
		$httpBackend.verifyNoOutstandingExpectation();
		$httpBackend.verifyNoOutstandingRequest();
	});

	it('should get data from xhr, log in user and set cookie', function() {
		$httpBackend.expectPOST('/users/login').
			respond({"username": "testuser", "role": "ROLE"});

		scope.login();
		$httpBackend.flush();

		expect(scope.user.username).toEqual('testuser');
		expect(scope.user.role).toEqual('ROLE');
		expect(location.path()).toEqual("/guestlist");
	});

	it('should log out user and reset user and delete cookie', function() {
		$httpBackend.expectPOST('/users/logout').
			respond(200);

		scope.user = {'username': 'username', 'role': 'role'};
		cookies.ME_COLOMBIANO_WEDAPP = scope.user.username;

		scope.logout();
		$httpBackend.flush();

		expect(scope.user).toEqual({});
		expect(cookies['ME_COLOMBIANO_WEDAPP']).toBeUndefined();
		expect(location.path()).toEqual('/home');
	});

	it('should delete cookie if logout fails', function() {
		$httpBackend.expectPOST('/users/logout').
			respond(500);

		scope.user = {'username': 'username', 'role': 'role'};
		cookies.ME_COLOMBIANO_WEDAPP = scope.user.username;

		scope.logout();
		$httpBackend.flush();

		expect(scope.user).toEqual({});
		expect(cookies['ME_COLOMBIANO_WEDAPP']).toBeUndefined();
		expect(location.path()).toEqual('/home');
	});

	describe('isInSession()', function() {

		it('should return "true" if username is set in cookie', function() {
			cookies.ME_COLOMBIANO_WEDAPP = "username";
			expect(scope.isInSession()).toBe(true);

		});

		it('should return "false" if cookie don\'t exist', function() {
			expect(scope.isInSession()).toBe(false);
		});

		it('should return "false" if cookie is missing "username"', function() {
			cookies.ME_COLOMBIANO_WEDAPP = "";
			expect(scope.isInSession()).toBe(false);
		});

	});

	describe('getUsername()', function() {

		it('should return username from cookie', function() {
			cookies.ME_COLOMBIANO_WEDAPP = "username";
			expect(scope.getUsername()).toEqual("username");
		});

		it('should return undefined if cookie is missing', function() {
			expect(scope.getUsername()).toBeUndefined();
		})
	});

	describe('changePassword()', function() {

		it('should change password for user clear user in scope and show success message', function() {
			$httpBackend.expectPOST('/users/changepassword').
				respond({"username": "testuser", "role": "ROLE"});

			scope.user = ({"username": "testuser", "oldPass": "pass", "newPass": "newpass", "newPassRepeated": "newpass"});

			scope.changePassword();
			$httpBackend.flush();

			expect(cookies.ME_COLOMBIANO_WEDAPP).toEqual("testuser");
			expect(scope.user).toEqual({});
			expect(scope.alerts.length).toBe(1);
			expect(scope.alerts[0].content).toEqual('Passord er endret');
		});

		it('should show error message if new passwords don\'t match', function() {
			scope.user = ({"username": "testuser", "role": "ROLE", "oldPass": "pass", "newPass": "newpass1", "newPassRepeated": "newpass2"});

			scope.changePassword();

			expect(scope.alerts.length).toBe(1);
			expect(scope.alerts[0].content).toEqual('De nye passordene er ikke like');
		});

		it('should show error message if old password is empty', function() {
			scope.user = ({"username": "testuser", "role": "ROLE", "oldPass": "", "newPass": "newpass", "newPassRepeated": "newpass"});

			scope.changePassword();

			expect(scope.alerts.length).toBe(1);
			expect(scope.alerts[0].content).toEqual('Alle felt må fylles ut');
		});

		it('should show error message if old password is missing', function() {
			scope.user = ({"username": "testuser", "role": "ROLE", "newPass": "newpass", "newPassRepeated": "newpass"});

			scope.changePassword();

			expect(scope.alerts.length).toBe(1);
			expect(scope.alerts[0].content).toEqual('Alle felt må fylles ut');
		});

		it('should show error message if new password is empty', function() {
			scope.user = ({"username": "testuser", "role": "ROLE", "oldPass": "pass", "newPass": "", "newPassRepeated": "newpass2"});

			scope.changePassword();

			expect(scope.alerts.length).toBe(1);
			expect(scope.alerts[0].content).toEqual('Vennligst fyll ut nytt passord');
		});

		it('should show error message if new password repeated is empty', function() {
			scope.user = ({"username": "testuser", "role": "ROLE", "oldPass": "pass", "newPass": "newpass", "newPassRepeated": ""});

			scope.changePassword();

			expect(scope.alerts.length).toBe(1);
			expect(scope.alerts[0].content).toEqual('Vennligst bekreft nytt passord');
		});

		it('should show error message received from back if password change fails', function() {
			$httpBackend.expectPOST('/users/changepassword').
				respond(400, {"error_message": "error"});

			scope.user = ({"username": "testuser", "oldPass": "pass", "newPass": "newpass", "newPassRepeated": "newpass"});

			scope.changePassword();
			$httpBackend.flush();

			expect(scope.alerts.length).toBe(1);
			expect(scope.alerts[0].content).toEqual('error');
		});

	});

	describe('passwordMatches()', function() {

		it('should return "true" if passwords matches', function() {
			expect(scope.passwordMatches('pass', 'pass')).toBe(true);
		});

		it('should return "false" if passwords don\'t match', function() {
			expect(scope.passwordMatches('pass', 'wrong')).toBe(false);
		});

		it('should return "false" if passwords is empty', function() {
			expect(scope.passwordMatches('', '')).toBe(false);
		});

		it('should return "false" if passwords are undefined', function() {
			expect(scope.passwordMatches(undefined, undefined)).toBe(false);
		});
	});

	describe('addErrorMessage()', function() {

		it('should add a message to alert array', function() {
			scope.addErrorMessage("msg");
			expect(scope.alerts.length).toBe(1);
			expect(scope.alerts[0].type).toEqual('error');
			expect(scope.alerts[0].title).toEqual('Hmmm');
			expect(scope.alerts[0].content).toEqual('msg');
		});

		it('should only contain one message at the time', function() {
			scope.addErrorMessage("msg1");
			scope.addErrorMessage("msg2");
			expect(scope.alerts.length).toBe(1);
		});

	});

	describe('addSuccessMessage()', function() {

		it('should add a message to alert array', function() {
			scope.addSuccessMessage("msg");
			expect(scope.alerts.length).toBe(1);
			expect(scope.alerts[0].type).toEqual('success');
			expect(scope.alerts[0].title).toEqual('Johoo!');
			expect(scope.alerts[0].content).toEqual('msg');
		});

		it('should only contain one message at the time', function() {
			scope.addSuccessMessage("msg1");
			scope.addSuccessMessage("msg2");
			expect(scope.alerts.length).toBe(1);
		});

	});

});