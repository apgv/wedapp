'use strict';

/* jasmine specs for controllers go here */

describe('RegistrationCtrl', function() {
	var scope, ctrl, $httpBackend;

	beforeEach(module('myApp.services'));

	beforeEach(inject(function(_$httpBackend_, $rootScope, $controller) {
		$httpBackend = _$httpBackend_;

		scope = $rootScope.$new();
		ctrl = $controller(RegistrationCtrl, {$scope: scope});
	}));

	afterEach(function() {
		$httpBackend.verifyNoOutstandingExpectation();
		$httpBackend.verifyNoOutstandingRequest();
	});

	describe('guestIsValid()', function() {

		it('should return "true" when validating a valid guest', function() {
			var guest = {"firstName": "pedro", "lastName": "garcia", "email": "pedro@test.no", "attending": "true", "note": "allergi"};
			expect(scope.guestIsValid(guest)).toBe(true);
		});

		it('should return "false" when guest is invalid', function() {
			var guest = {"firstName": "pedro", "lastName": "garcia", "email": "pedro@test.no", "attending": "false", "note": "allergi"};
			expect(scope.guestIsValid(guest)).toBe(true);
		});

		it('should return "false" when guest "first name" is missing or undefined', function() {
			var guest = {"firstName": "", "lastName": "garcia", "email": "pedro@test.no", "attending": "true", "note": "allergi"};
			expect(scope.guestIsValid(guest)).toBe(false);

			guest.firstName = undefined;
			expect(scope.guestIsValid(guest)).toBe(false);
		});

		it('should return "false" when guest "last name" is missing or undefined', function() {
			var guest = {"firstName": "pedro", "": "garcia", "email": "pedro@test.no", "attending": "true", "note": "allergi"};
			expect(scope.guestIsValid(guest)).toBe(false);

			guest.lastName = undefined;
			expect(scope.guestIsValid(guest)).toBe(false);
		});

		it('should return "false" when guest "attending" is missing or undefined', function() {
			var guest = {"firstName": "pedro", "lastName": "garcia", "email": "pedro@test.no", "attending": "", "note": "allergi"};
			expect(scope.guestIsValid(guest)).toBe(false);

			guest.attending = undefined;
			expect(scope.guestIsValid(guest)).toBe(false);
		});

		it('should return "false when guest "firstName" and "lastName" are blank and "attending is undefined', function() {
			var guest = {"firstName": "", "lastName": "", "email": "pedro@test.no", "attending": undefined, "note": "allergi"};
			expect(scope.guestIsValid(guest)).toBe(false);
		});
	});

	describe('guestIsBlank()', function() {

		it('should return "true" when guest "firstName" and "lastName" is blank and "attending" is undefined', function() {
			var guest = {"firstName": "", "lastName": "", "email": "pedro@test.no", "attending": undefined, "note": "allergi"};
			expect(scope.guestIsBlank(guest)).toBe(true);
		});

		it('should return "true" when guest "firstName", "lastName" and "attending" is undefined', function() {
			var guest = {"firstName": undefined, "lastName": undefined, "email": "pedro@test.no", "attending": undefined, "note": "allergi"};
			expect(scope.guestIsBlank(guest)).toBe(true);
		});

		it('should return "false" when only guest "firstName" is blank and "attending" is undefined', function() {
			var guest = {"firstName": "", "lastName": "garcia", "email": "pedro@test.no", "attending": undefined, "note": "allergi"};
			expect(scope.guestIsBlank(guest)).toBe(false);
		});
	});

	describe('invalidForm()', function() {

		it('should return "false" when guest1 is valid and guest2 is undefined', function() {
			scope.guest1 = {"firstName": "pedro", "lastName": "garcia", "email": "pedro@test.no", "attending": "true", "note": "allergi"};
			expect(scope.invalidForm()).toBe(false);
		});

		it('should return "true" when guest1 is valid and guest2 is invalid', function() {
			scope.guest1 = {"firstName": "pedro", "lastName": "garcia", "email": "pedro@test.no", "attending": "true", "note": "allergi"};
			scope.guest2 = {"firstName": "", "lastName": "garcia", "email": "pedro@test.no", "attending": "true", "note": "allergi"};
			expect(scope.invalidForm()).toBe(true);
		});

		it('should return "false" when both guest1 and guest2 are valid', function() {
			scope.guest1 = {"firstName": "pedro", "lastName": "garcia", "email": "pedro@test.no", "attending": "true", "note": "allergi"};
			scope.guest2 = {"firstName": "pedro", "lastName": "garcia", "email": "pedro@test.no", "attending": "true", "note": "allergi"};
			expect(scope.invalidForm()).toBe(false);
		});

		it('should return "false" when guest1 is undefined and guest2 is valid', function() {
			scope.guest2 = {"firstName": "pedro", "lastName": "garcia", "email": "pedro@test.no", "attending": "true", "note": "allergi"};
			expect(scope.invalidForm()).toBe(false);
		});

		it('should return "false" when guest1 is valid and guest2 "firstName" and "lastName" is blank and "atttending" is undefined', function() {
			scope.guest1 = {"firstName": "pedro", "lastName": "garcia", "email": "pedro@test.no", "attending": "true", "note": "allergi"};
			scope.guest2 = {"firstName": "", "lastName": "", "email": "pedro@test.no", "attending": undefined	, "note": "allergi"};
			expect(scope.invalidForm()).toBe(false);
		});

		it('should return "false" when guest1 "firstName" and "lastName" is blank and "atttending" is undefined and guest2 is valid', function() {
			scope.guest1 = {"firstName": "", "lastName": "", "email": "pedro@test.no", "attending": undefined	, "note": "allergi"};
			scope.guest2 = {"firstName": "pedro", "lastName": "garcia", "email": "pedro@test.no", "attending": "true", "note": "allergi"};
			expect(scope.invalidForm()).toBe(false);
		});
	});

	describe('resetForm()', function() {

		it('should reset all values for guests', function() {
			scope.alerts = { "type": "success", "title": "Johoo!", "content": "Ditt svar er mottatt."};
			scope.guest1 = {"firstName": "pedro", "lastName": "garcia", "email": "pedro@test.no", "attending": "true", "note": "allergi"};
			scope.guest2 = {"firstName": "pedro", "lastName": "garcia", "email": "pedro@test.no", "attending": "true", "note": "allergi"};

			scope.resetForm();

			expect(scope.alerts).toEqual([]);
			expect(scope.guest1).toEqual({});
			expect(scope.guest2).toEqual({});
		});
	});

	describe('saveGuest()', function() {

		it('should convert "attending" string to a boolean, save guest1, set success message and clear the scope', function() {
			$httpBackend.expectPOST('/guests').
				respond(201);

			expect(scope.alerts.length).toBe(0);

			scope.guest1 = {"firstName": "pedro", "lastName": "garcia", "email": "pedro@test.no", "attending": "true", "note": "allergi"};

			scope.saveGuest();

			expect(scope.guest1.attending === true).toBe(true);

			$httpBackend.flush();

			expect(scope.dataToSend.length).toBe(1);
			expect(scope.guest1).toEqual({});
			expect(scope.guest2).toEqual({});
			expect(scope.alerts.length).toBe(1);
			expect(scope.alerts[0].type).toEqual("success");
		});

		it('should not save guest1 which is invalid', function() {
			scope.guest1 = {"firstName": "", "lastName": "garcia", "email": "pedro@test.no", "attending": "true", "note": "allergi"};

			scope.saveGuest();

			expect(scope.dataToSend.length).toBe(0);
		});

		it('should convert "attending" string to a boolean, save guest2', function() {
			$httpBackend.expectPOST('/guests').
				respond(201);

			scope.guest2 = {"firstName": "pedro", "lastName": "garcia", "email": "pedro@test.no", "attending": "false", "note": "allergi"};

			scope.saveGuest();

			expect(scope.guest2.attending === false).toBe(true);

			$httpBackend.flush();

			expect(scope.dataToSend.length).toBe(1);
		});

		it('should save both guest1 and guest2 which are valid', function() {
			$httpBackend.expectPOST('/guests').
				respond(201);

			scope.guest1 = {"firstName": "pedro", "lastName": "garcia", "email": "pedro@test.no", "attending": "true", "note": "allergi"};
			scope.guest2 = {"firstName": "pedro", "lastName": "garcia", "email": "pedro@test.no", "attending": "true", "note": "allergi"};

			scope.saveGuest();

			$httpBackend.flush();

			expect(scope.dataToSend.length).toBe(2);
		});

		it('should set error message when saving guest fails', function() {
			$httpBackend.expectPOST('/guests').
				respond(500);

			expect(scope.alerts.length).toBe(0);

			scope.guest1 = {"firstName": "pedro", "lastName": "garcia", "email": "pedro@test.no", "attending": "true", "note": "allergi"};

			scope.saveGuest();

			$httpBackend.flush();

			expect(scope.guest1).toEqual({"firstName": "pedro", "lastName": "garcia", "email": "pedro@test.no", "attending": true, "note": "allergi"});

			expect(scope.alerts.length).toBe(1);
			expect(scope.alerts[0].type).toEqual("error");
		})

	});

});