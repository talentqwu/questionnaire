// @Bind #btnCreate.onClick
function btnCreateOnClick(self, arg) {
	view.get('#dataSetAward.data').insert();
}

// @Bind #btnDelete.onClick
function btnDeleteOnClick(self, arg) {
	dorado.MessageBox.confirm("您真的想删除当前数据吗?", function(){
		view.get("#dataSetAward.data").remove();
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
		view.get("#dataSetAward").getData().cancel();
	});
}