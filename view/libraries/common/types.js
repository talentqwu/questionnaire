var TYPE = function(o) {
	// handle null in old IE
	if (o === null)
		return 'null';

	// handle DOM elements
	if (o && (o.nodeType === 1 || o.nodeType === 9))
		return 'element';

	var s = Object.prototype.toString.call(o);
	var type = s.match(/\[object (.*?)\]/)[1].toLowerCase();

	// handle NaN and Infinity
	if (type === 'number') {
		if (isNaN(o))
			return 'nan';
		if (!isFinite(o))
			return 'infinity';
	}

	return type;
};

TYPE.isNull = function(o) {
	return TYPE(o) === "Null".toLowerCase();
};

TYPE.isUndefined = function(o) {
	return TYPE(o) === "Undefined".toLowerCase();
};

TYPE.isObject = function(o) {
	return TYPE(o) === "Object".toLowerCase();
};

TYPE.isArray = function(o) {
	return TYPE(o) === "Array".toLowerCase();
};

TYPE.isString = function(o) {
	return TYPE(o) === "String".toLowerCase();
};

TYPE.isNumber = function(o) {
	return TYPE(o) === "Number".toLowerCase();
};

TYPE.isBoolean = function(o) {
	return TYPE(o) === "Boolean".toLowerCase();
};

TYPE.isFunction = function(o) {
	return TYPE(o) === "Function".toLowerCase();
};

TYPE.isRegExp = function(o) {
	return TYPE(o) === "RegExp".toLowerCase();
};

TYPE.isElement = function(o) {
	return TYPE(o) === "Element".toLowerCase();
};

TYPE.isNaN = function(o) {
	return TYPE(o) === "NaN".toLowerCase();
};

TYPE.isInfinite = function(o) {
	return TYPE(o) === "Infinite".toLowerCase();
};
