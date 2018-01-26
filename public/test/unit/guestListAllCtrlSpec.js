'use strict';

describe('GuestListAllCtrl', function() {
	var scope, ctrl, $httpBackend;

	beforeEach(module('myApp.services'));

	beforeEach(inject(function(_$httpBackend_, $rootScope, $controller) {
		$httpBackend = _$httpBackend_;

		$httpBackend.expectGET('/guests/all').
			respond(
				[
					{
						"id": "1",
						"firstName": "pedro",
						"lastName": "garcia",
						"email": "pedro@test.com",
						"attending": true,
						"note": "hola",
						"relation": "family",
						"about": "oneliner",
						"created": "2013-03-02T19:50:30.760+01:00"
					},
					{
						"id": "2",
						"firstName": "estha",
						"lastName": "andersen",
						"email": "estha@test.com",
						"attending": true,
						"note": "hola",
						"relation": "family",
						"about": "oneliner",
						"created": "2013-03-02T19:50:30.760+01:00"
					}
				]
			);

		scope = $rootScope.$new();
		ctrl = $controller(GuestListAllCtrl, {$scope: scope});
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
		expect(scope.guests[1].email).toEqual('estha@test.com');
		expect(scope.guests[1].attending).toBe(true);
		expect(scope.guests[1].note).toEqual('hola');
		expect(scope.guests[1].relation).toEqual('family');
		expect(scope.guests[1].about).toEqual('oneliner');
	});

	it('should save update and show success message', function() {
		$httpBackend.expectPOST('/guests/update').
			respond(200);

		scope.saveUpdate();

		$httpBackend.flush();

		expect(scope.alerts.length).toBe(1);
		expect(scope.alerts[0].type).toEqual('success');
		expect(scope.alerts[0].title).toEqual('Johoo!');
		expect(scope.alerts[0].content).toEqual('Lagring vellykket');
	});

	it('should show error message when save update fails', function() {
		$httpBackend.expectPOST('/guests/update').
			respond(500);

		scope.saveUpdate();

		$httpBackend.flush();

		expect(scope.alerts.length).toBe(1);
		expect(scope.alerts[0].type).toEqual('error');
		expect(scope.alerts[0].title).toEqual('Hmmm');
		expect(scope.alerts[0].content).toEqual('En feil har oppstått. Vennligst prøv igjen senere.');
	});

	it('should delete guest and show success message', function() {
		$httpBackend.expectPOST('/guests/delete').
			respond(200);

		scope.delete();

		$httpBackend.flush();

		expect(scope.alerts.length).toBe(1);
		expect(scope.alerts[0].type).toEqual('success');
		expect(scope.alerts[0].title).toEqual('Johoo!');
		expect(scope.alerts[0].content).toEqual('Sletting vellykket');
	});

	it('should show error message if delete fails', function() {
		$httpBackend.expectPOST('/guests/delete').
			respond(500);

		scope.delete();

		$httpBackend.flush();

		expect(scope.alerts.length).toBe(1);
		expect(scope.alerts[0].type).toEqual('error');
		expect(scope.alerts[0].title).toEqual('Hmmm');
		expect(scope.alerts[0].content).toEqual('En feil har oppstått. Vennligst prøv igjen senere.');
	});

});
