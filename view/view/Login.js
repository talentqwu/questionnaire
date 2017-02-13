// @Bind view.onReady
function viewOnReady(self, arg) {
	self.get('#dialogLogin').show();
}

// @Bind #btnReset.onClick
function btnResetOnClick(self, arg) {
	view.get('#formLogin.entity').clearData();
}

// @Bind #btnLogin.onClick
function btnLoginOnClick(self, arg) {
	var entity = view.get('#formLogin.entity');
	if (entity.get('userName') && entity.get('password')) {
		view.get('#actionLogin').set('parameter', entity.toJSON()).execute();
	} else {
		dorado.widget.NotifyTipManager.notify("请输入用户及密码！");
	}
}

// @Bind #formLogin.#password.onKeyDown
function formLoginPasswordOnKeyDown(self, arg) {
	if (arg.keyCode == 13) {
		view.get('#btnLogin').fireEvent('onClick');
	}
}

// @Bind #actionLogin.onSuccess
function actionLoginOnSuccess(self, arg) {
	if (arg.result) {
		top.location.href = "./view.Main.d";
	} else 
		dorado.widget.NotifyTipManager.notify("登陆失败！");
}