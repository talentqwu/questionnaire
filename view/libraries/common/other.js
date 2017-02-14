ActionEvent = function() {};

ActionEvent.onFailure = function(self, arg) {
	dorado.MessageBox.show({
		title : '错误',
		message : arg.error.message,
		icon : 'ERROR',
		buttons : [ 'ok' ]
	});
	arg.processDefault = false;
};