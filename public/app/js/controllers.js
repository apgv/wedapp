'use strict';

/* Controllers */

function WishlistCtrl($scope, Wish) {
	$scope.error_message = {
		"type": "error",
		"title": "Oops!",
		"content": "En feil har oppstått. Vennligst prøv igjen senere."
	};

	$scope.query_success = true;

	$scope.wishlist = Wish.query({}, function(data, headers) {
	}, function(httpErrorResponse) {
		$scope.query_success = false;
	});
}
WishlistCtrl.$inject = ['$scope', 'Wish'];

function HotelCtrl($scope, Hotel) {
	$scope.error_message = {
		"type": "error",
		"title": "Oops!",
		"content": "En feil har oppstått. Vennligst prøv igjen senere."
	};

	$scope.query_success = true;

	$scope.hotels = Hotel.query({}, function(data, headers) {
	}, function(httpErrorResponse) {
		$scope.query_success = false;
	});


}
HotelCtrl.$inject = ['$scope', 'Hotel'];

function RegistrationCtrl($scope, Guest) {
	$scope.pristineGuest = {};

	$scope.success_message = {
		"type": "success",
		"title": "Johoo!",
		"content": "Ditt svar er mottatt."
	};
	$scope.error_message = {
		"type": "error",
		"title": "Oops!",
		"content": "En feil har oppstått. Vennligst prøv igjen senere."
	};

	$scope.alerts = [];

	$scope.invalidForm = function() {
		if (($scope.guestIsValid($scope.guest1) && ($scope.guest2 === undefined || $scope.guestIsBlank($scope.guest2)))) {
			return false;
		}

		if ($scope.guestIsValid($scope.guest2) && ($scope.guest1 === undefined || $scope.guestIsBlank($scope.guest1))) {
			return false;
		}

		return !($scope.guestIsValid($scope.guest1) && $scope.guestIsValid($scope.guest2));
	};

	$scope.guestIsBlank = function(guest) {
		return (guest.firstName === undefined || guest.firstName === "") &&
			(guest.lastName === undefined || guest.lastName === "") && guest.attending === undefined;
	};

	$scope.guestIsValid = function(guest) {
		if (guest === undefined) {
			return false;
		}

		if (guest.firstName === undefined || guest.lastName === undefined || guest.attending === undefined) {
			return false;
		}

		return (guest.firstName.length > 0 && guest.lastName.length > 0) &&
			(guest.attending === 'true' || guest.attending === 'false');
	};

	$scope.saveGuest = function() {
		$scope.alerts = [];
		$scope.dataToSend = [];

		if ($scope.guestIsValid($scope.guest1)) {
			$scope.guest1.attending = $scope.guest1.attending === 'true';
			$scope.dataToSend.push($scope.guest1);
		}

		if ($scope.guestIsValid($scope.guest2)) {
			$scope.guest2.attending = $scope.guest2.attending === 'true';
			$scope.dataToSend.push($scope.guest2);
		}

		if ($scope.dataToSend.length > 0) {
			Guest.save($scope.dataToSend, function(data, headers) {
				$scope.resetForm();
				$scope.alerts.push($scope.success_message);
			}, function(httpErrorResponse) {
				$scope.alerts.push($scope.error_message);
			});
		}
	};

	$scope.resetForm = function() {
		$scope.alerts = [];
		$scope.guest1 = angular.copy($scope.pristineGuest);
		$scope.guest2 = angular.copy($scope.pristineGuest);
	};
}
RegistrationCtrl.$inject = ['$scope', 'Guest'];

function UserCtrl($scope, $cookies, $location, User) {
	$scope.pristineUser = {};
	$scope.user = {};
	$scope.alerts = [];

	$scope.addErrorMessage = function(errorMessage) {
		$scope.alerts = [];
		$scope.alerts.push({"type": "error", "title": "Hmmm", "content": errorMessage});
	};

	$scope.addSuccessMessage = function(successMessage) {
		$scope.alerts = [];
		$scope.alerts.push({"type": "success", "title": "Johoo!", "content": successMessage});
	};

	$scope.login = function() {
		$scope.user = User.login($scope.user, function() {
			$cookies.ME_COLOMBIANO_WEDAPP = $scope.user.username;
			$location.path('/guestlist');
		}, function(httpErrorResponse) {
			$scope.addErrorMessage("Brukernavn og/eller passord er feil");
		});
	};

	$scope.logout = function() {
		$scope.loggedOutUser = User.logout(function() {
			$scope.user = angular.copy($scope.pristineUser);
			delete $cookies['ME_COLOMBIANO_WEDAPP'];
			$location.path('/home');
		}, function(httpErrorResponse) {
			$scope.user = angular.copy($scope.pristineUser);
			delete $cookies['ME_COLOMBIANO_WEDAPP'];
			$location.path('/home');
		});
	};

	$scope.isInSession = function() {
		if (angular.isDefined($cookies.ME_COLOMBIANO_WEDAPP)) {
			return angular.isString($cookies.ME_COLOMBIANO_WEDAPP) && $cookies.ME_COLOMBIANO_WEDAPP.length > 0;
		}

		return false;
	};

	$scope.getUsername = function() {
		return $cookies.ME_COLOMBIANO_WEDAPP;
	};

	$scope.changePassword = function() {
		if ((!angular.isString($scope.user.oldPass) || $scope.user.oldPass.length === 0)) {
			$scope.addErrorMessage('Alle felt må fylles ut');
			return;
		}

		if (angular.isUndefined($scope.user.newPass) || $scope.user.newPass.length === 0) {
			$scope.addErrorMessage('Vennligst fyll ut nytt passord');
			return;
		}

		if (angular.isUndefined($scope.user.newPassRepeated) || $scope.user.newPassRepeated.length === 0) {
			$scope.addErrorMessage('Vennligst bekreft nytt passord');
			return;
		}

		if (!$scope.passwordMatches($scope.user.newPass, $scope.user.newPassRepeated)) {
			$scope.addErrorMessage('De nye passordene er ikke like');
		}

		if ($scope.passwordMatches($scope.user.newPass, $scope.user.newPassRepeated)) {
			$scope.user = User.changePassword($scope.user, function() {
				$cookies.ME_COLOMBIANO_WEDAPP = $scope.user.username;
				$scope.user = angular.copy($scope.pristineUser);
				$scope.addSuccessMessage("Passord er endret");
			}, function(httpErrorResponse) {
				$scope.addErrorMessage(httpErrorResponse.data.error_message);
			});
		}
	};

	$scope.passwordMatches = function(pass, passRepeated) {
		if (angular.isDefined(pass)) {
			return angular.equals(pass, passRepeated) && pass.length > 0;
		}

		return false;
	}
}
UserCtrl.$inject = ['$scope', '$cookies', '$location', 'User'];

function GuestListCtrl($scope, Guest) {
	$scope.alerts = [];

	$scope.error_message = {
		"type": "error",
		"title": "Hmmm",
		"content": "En feil har oppstått. Vennligst prøv igjen senere."
	};

	$scope.guests = Guest.query({}, function(data, headers) {
	}, function(httpErrorResponse) {
		$scope.alerts.push($scope.error_message);
	});

	$scope.gridOptions = {
		data: 'guests',
		showFooter: true,
		multiSelect: false,
		enableColumnResize: true,
		filterOptions: {filterText: '', useExternalFilter: false},
		columnDefs: [
			{field: 'firstName', displayName: 'Fornavn', width: 100},
			{field: 'lastName', displayName: 'Etternavn', width: 100},
			{field: 'relation', displayName: 'Relasjon', width: 100},
			{field: 'about', displayName: 'Kort om', width: 683}
		]
	};
}
GuestListCtrl.$inject = ['$scope', 'Guest'];

function GuestListAllCtrl($scope, Guest) {
	$scope.alerts = [];

	$scope.addSuccessMessage = function(successMessage) {
		$scope.alerts = [];
		$scope.alerts.push({"type": "success", "title": "Johoo!", "content": successMessage});
	};

	$scope.addErrorMessage = function(errorMessage) {
		$scope.alerts = [];
		$scope.alerts.push({"type": "error", "title": "Hmmm", "content": errorMessage});
	};

	$scope.guests = Guest.queryAll({}, function(data, headers) {
	}, function(httpErrorResponse) {
		$scope.addErrorMessage('En feil har oppstått. Vennligst prøv igjen senere.')
	});

	$scope.saveUpdate = function() {
		Guest.saveUpdate($scope.mySelectedItems[0], function(data, headers) {
			$scope.addSuccessMessage("Lagring vellykket")
		}, function(httpErrorResponse) {
			$scope.addErrorMessage('En feil har oppstått. Vennligst prøv igjen senere.')
		});
	};

	$scope.delete = function() {
		Guest.deleteGuests($scope.mySelectedItems[0], function(data, headers) {
			$scope.mySelectedItems.forEach(function(guest) {
				var indexToDelete = $scope.guests.indexOf(guest);

				if(indexToDelete > -1) {
					$scope.guests.splice(indexToDelete, 1);
				}
			});

			$scope.addSuccessMessage("Sletting vellykket")
		}, function(httpErrorResponse) {
			$scope.addErrorMessage('En feil har oppstått. Vennligst prøv igjen senere.')
		});
	};

	$scope.mySelectedItems = [];

	$scope.gridOptions = {
		data: 'guests',
		showFooter: true,
		enableColumnResize: true,
		enableCellSelection: true,
		multiSelect: false,
		selectedItems: $scope.mySelectedItems,
		filterOptions: {filterText: '', useExternalFilter: false},
		columnDefs: [
			{field: 'firstName', displayName: 'Fornavn', enableCellEdit: true, width: 100},
			{field: 'lastName', displayName: 'Etternavn', enableCellEdit: true, width: 100},
			{field: 'email', displayName: 'E-post', enableCellEdit: true, width: 100},
			{field: 'attending', displayName: 'Kommer', enableCellEdit: true, width: 66},
			{field: 'note', displayName: 'Notat', enableCellEdit: true, width: 120},
			{field: 'relation', displayName: 'Relasjon', enableCellEdit: true, width: 100},
			{field: 'about', displayName: 'Kort om', enableCellEdit: true, width: 487},
			{field: 'created', displayName: 'Registrert dato', enableCellEdit: false, width: 110}
		]
	};
}
GuestListAllCtrl.$inject = ['$scope', 'Guest'];