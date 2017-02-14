var Context = function() {};

Context.setView = function(view) {
	Context.VIEW = view;
};

var ContextHelper = function() {};

ContextHelper.setAttributes = function(parameter) {
	new dorado.widget.AjaxAction({
		service : 'qs.main#setContextAttributes',
		parameter : parameter
	}).execute();
};

ContextHelper.getAttributes = function(parameter) {
	return new dorado.widget.AjaxAction({
		async : false,
		service : 'qs.main#getContextAttributes',
		parameter : parameter
	}).execute();
};
