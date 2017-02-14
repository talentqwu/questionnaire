var Service = function() {};

Service.setView = function(view) {
	Context.setView(view);
}

var ServiceEvent = function() {};

ServiceEvent.gridServiceOnDataRowDoubleClick = function(self, arg) {
	Context.VIEW.get('#dialogService').show();
};

ServiceEvent.btnCreateOnClick = function(self, arg) {
	Context.VIEW.get('#dataSetService.data').insert();
	Context.VIEW.get('#dialogService').show();
};

ServiceEvent.btnDeleteOnClick = function(self, arg) {
	dorado.MessageBox.confirm("您真的想删除当前数据吗?", function() {
		Context.VIEW.get('#dataSetService.data').remove();
		Context.VIEW.get('#actionUpdate').execute();
	});
};

ServiceEvent.btnCancelOnClick = function(self, arg) {
	Context.VIEW.get('#dataSetService.data').cancel();
};

ServiceEvent.btnRefreshOnClick = function(self, arg) {
	Context.VIEW.get('#dataSetService').flushAsync();
};

ServiceEvent.dialogServiceOnShow = function(self, arg) {
	var entity = Context.VIEW.get('#dataSetService').getData('#');
	var selection = [];
	if (entity.get('roleStr')) {
		var roles = entity.get('roleStr').split(',');
		Context.VIEW.get('#dataSetRole.data').each(function(data) {
			roles.each(function(role) {
				if (data.get('key') == role)
					selection.push(data);
			});
		});
	}
	Context.VIEW.get('#gridRole').set('selection', selection);
};

ServiceEvent.dialogServiceOnClose = function(Self, arg) {
	Context.VIEW.get('#dataSetService.data').cancel();
};

ServiceEvent.btnDialogOKOnClick = function(self, arg) {
	var roles = [];
	Context.VIEW.get('#gridRole.selection').each(function(role) {
		roles.push(role.get('key'));
	});
	
	if (roles.length == 0) {
		dorado.MessageBox.alert('请选择服务对应授权的角色！');
		return false;
	} 
	
	Context.VIEW.get('#dataSetService').getData('#').set('roleStr', roles.join(','));
	Context.VIEW.get('#actionUpdate').execute(function(result){
		Context.VIEW.get('#dialogService').close();
	});
};

ServiceEvent.btnDialogCancelOnClick = function(self, arg) {
	Context.VIEW.get('#dialogService').close();
};