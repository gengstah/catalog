'use strict';

/* Filters */

var filters = angular.module('CatalogFilters', []);

filters.filter('slice', function() {
	return function(arr, start, end) {
		return arr.slice(start, end);
	}
});