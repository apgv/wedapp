'use strict';

/* http://docs.angularjs.org/guide/dev_guide.e2e-testing */

describe('my app', function() {

  beforeEach(function() {
    browser().navigateTo('../../app/index.html');
  });

  it('should automatically redirect to /home when location hash/fragment is empty', function() {
    expect(browser().location().url()).toBe("/home");
  });

  describe('registration layout	', function() {

    beforeEach(function() {
      browser().navigateTo('#/registration');
    });

    it('should render registration when user navigates to /registration', function() {
			expect(element('#registration-form input').count()).toBe(10);
			expect(element('#registration-form textarea').count()).toBe(2);
			expect(element('#registration-form button').count()).toBe(2);
    });
  });

	describe('registration input validation', function() {
		beforeEach(function() {
			browser().navigateTo('#/registration');
		});

		it('should be able to send data if form input is valid', function() {
			expect(element('#save-guest').attr('disabled')).toBe('disabled');

			input('guest1.firstName').enter('pedro');
			input('guest1.lastName').enter('garcia');
			input('guest1.attending').select(true);

			expect(input('guest1.firstName').val()).toBe('pedro');
			expect(input('guest1.lastName').val()).toBe('garcia');

			expect(element('#save-guest').attr('disabled')).toBe(undefined);
		});

		it('should be able to send data if form input is valid', function() {
			expect(element('#save-guest').attr('disabled')).toBe('disabled');

			input('guest1.firstName').enter('pedro');
			input('guest1.lastName').enter('garcia');

			expect(element('#save-guest').attr('disabled')).toBe('disabled');
		});

	});

});
