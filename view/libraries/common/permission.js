var PermissionHelper = function() {};

PermissionHelper.getRoles = function(user) {
	var roles = [];

	user.permissions.each(function(permission) {
			roles.push(permission.role);
	});

	return roles;
};

PermissionHelper.isSystem = function(user) {
	var flag = false;

	user.permissions.each(function(permission) {
		if (permission.role == ENUM.Role.SYSTEM)
			flag = true;
	});

	return flag;
};

PermissionHelper.checkAuthorize = function(user, role) {
	var authorize = false;

	user.permissions.each(function(permission) {
		if (permission.role == role) {
			authorize = true;
		}
	});

	return authorize;
};