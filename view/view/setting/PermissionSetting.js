// @Bind #btnCreate.onClick
function btnCreateOnClick(self, arg) {
	view.get("#dataSetUser.data").insert({password : md5('123654')});
}

// @Bind #btnDelete.onClick
function btnDeleteOnClick(self, arg) {
	dorado.MessageBox.confirm("您真的想删除当前数据吗?", function(){
		view.get("#dataSetUser.data").remove();
		view.get('#actionUpdate').execute();
	});
}

// @Bind #btnSave.onClick
function btnSaveOnClick(self, arg) {
	dorado.MessageBox.confirm("您真的要保存吗?",function(){
		view.get("#actionUpdate").execute();
	})
}

// @Bind #btnCancel.onClick
function btnCancelOnClick(self, arg) {
	dorado.MessageBox.confirm("您真的要取消当前操作吗？",function(){
		view.get("#dataSetUser").getData().cancel();
	});
}

// @Bind #btnQuery.onClick
function btnQueryOnClick(self, arg) {
	var data = view.get("#formCondition.entity");
	with (view.get("#dataSetUser")){
		set("parameter", data);
		flushAsync();
	}
}

// @Bind #btnSetPassword.onClick
function btnSetPasswordOnClick(self, arg) {
	var datas = view.get('#dataSetUser.data');
	if (datas.current)
		view.get('#dialogSetPassword').show();
	else
		dorado.widget.NotifyTipManager.notify('请先选择一笔数据！');
}

// @Bind #btnDialogOK.onClick
function btnDialogOKOnClick(self, arg) {
	var data = view.get("#formChangePassword.entity");
	if (data.newPassword == data.confirmNewPassword) {
		var datas = view.get('#dataSetUser.data');
		datas.current.set('password', md5(data.newPassword));
		view.get('#dialogSetPassword').close();
	} else
		dorado.widget.NotifyTipManager.notify('请两次输入一致的密码！');
}

// @Bind #btnDialogCancel.onClick
function btnDialogCancelOnClick(self, arg) {
	view.get('#dialogSetPassword').close();
}

// @Bind #gridUser.#roleStr.onRenderCell
function gridUserRoleStrOnRenderCell(self, arg) {
	if (!arg.rowType) {
		var text = '';
		if (arg.data.get('roleStr')) {
			var datas = view.get('#dataSetRole.data');
			var roleNames = arg.data.get('roleStr').split(',');
			roleNames.each(function(name){
				datas.each(function(data){
					if (data.get('key') == name)
						text += data.get('value') + ',';
				});
			});
			if (text.length > 1)
				text = text.substr(0, text.length - 1);
		}
		arg.dom.innerHTML = text;
	}
}

// @Bind #dropDownPermission.onOpen
function dropDownPermissionOnOpen(self, arg) {
	var datas = view.get('#dataSetUser.data'), selection = [];
	if (datas.current.get('roleStr')) {
		var roles = datas.current.get('roleStr').split(',');
		view.get('#dataSetRole.data').each(function(data) {
			roles.each(function(role) {
				if (data.get('key') == role)
					selection.push(data);
			});
		});
	}
	view.get('#gridRole').set('selection', selection);
}

// @Bind #dropDownPermission.onClose
function dropDownPermissionOnClose(self, arg) {
	var selection = view.get('#gridRole.selection'), roleStr = '';
	selection.each(function(role) {
		roleStr += role.get('key') + ',';
	});
	if (roleStr.length > 1)
		roleStr = roleStr.substr(0, roleStr.length - 1);
	var datas = view.get('#dataSetUser.data');
	datas.current.set('roleStr', roleStr);
}