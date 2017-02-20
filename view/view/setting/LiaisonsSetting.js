// @Bind #btnCreate.onClick
function btnCreateOnClick(self, arg) {
	view.get("#dataSetLiaisons.data").insert();
	view.get("#dialogLiaisons").show();
}

// @Bind #btnDelete.onClick
function btnDeleteOnClick(self, arg) {
	dorado.MessageBox.confirm("您真的想删除当前数据吗?", function(){
		view.get("#dataSetLiaisons.data").remove();
		actionUpdate.execute();
	});
}

// @Bind #btnQuery.onClick
function btnQueryOnClick(self, arg) {
	var data = view.get("#formCondition.entity");
	with (view.get("#dataSetLiaisons")){
		set("parameter", data);
		flushAsync();
	}
}

// @Bind #btnDialogConfirm.onClick
function btnDialogConfirmOnClick(self, arg) {
	var dialog=view.get("#dialogLiaisons");
	var action=view.get("#actionUpdate");
	dorado.MessageBox.confirm("您真的要保存吗?",function(){
		action.execute(function(){
			dialog.hide();
		});
	})
}

// @Bind #btnDialogCancel.onClick
function btnDialogCancelOnClick(self, arg) {
	dorado.MessageBox.confirm("您真的要取消当前操作吗？",function(){
		view.get("#dataSetLiaisons").getData().cancel();
		view.get("#dialogLiaisons").hide();
	});
}

// @Bind #gridLiaisons.onDataRowDoubleClick
function gridLiaisonsOnDataRowDoubleClick(self, arg) {
	view.get("#dialogLiaisons").show();
}