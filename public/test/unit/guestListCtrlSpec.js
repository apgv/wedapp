'use strict';

describe('GuestListCtrl', function() {
	var scope, ctrl, $httpBackend;

	beforeEach(module('myApp.services'));

	beforeEach(inject(function(_$httpBackend_, $rootScope, $controller) {
		$httpBackend = _$httpBackend_;

		$httpBackend.expectGET('/guests/attending').
			respond(
				[
					{"firstName": "pedro","lastName": "garcia","relation": "family","about": "lala"},
					{"firstName": "estha","lastName": "andersen","relation": "family","about": "hola"}
				]
			);

		scope = $rootScope.$new();
		ctrl = $controller(GuestListCtrl, {$scope: scope});
	}));

	afterEach(function() {
		$httpBackend.verifyNoOutstandingExpectation();
		$httpBackend.verifyNoOutstandingRequest();
	});

	it('should return data from xhr', function() {
		expect(scope.guests.length).toBe(0);

		$httpBackend.flush();

		expect(scope.guests.length).toBe(2);
		expect(scope.guests[1].firstName).toEqual('estha');
		expect(scope.guests[1].lastName).toEqual('andersen');
		expect(scope.guests[1].relation).toEqual('family');
		expect(scope.guests[1].about).toEqual('hola');
	});

});
