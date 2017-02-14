// @Bind view.onReady
function viewOnReady(self, arg) {
	Service.setView(view);
}

//@Bind #gridService.onDataRowDoubleClick
function gridServiceOnDataRowDoubleClick(self, arg) {
	ServiceEvent.gridServiceOnDataRowDoubleClick(self, arg);
}

// @Bind #btnCreate.onClick
function btnCreateOnClick(self, arg) {
	ServiceEvent.btnCreateOnClick(self, arg);
}

// @Bind #btnDelete.onClick
function btnDeleteOnClick(self, arg) {
	ServiceEvent.btnDeleteOnClick(self, arg);
}

// @Bind #btnCancel.onClick
function btnCancelOnClick(self, arg) {
	ServiceEvent.btnCancelOnClick(self, arg);
}

// @Bind #btnRefresh.onClick
function btnRefreshOnClick(self, arg) {
	ServiceEvent.btnRefreshOnClick(self, arg);
}

// @Bind #dialogService.onShow
function dialogServiceOnShow(Self, arg) {
	ServiceEvent.dialogServiceOnShow(self, arg);
}

// @Bind #dialogService.onClose
// @Bind #dialogService.onHide
function dialogServiceOnClose(Self, arg) {
	ServiceEvent.dialogServiceOnClose(self, arg);
}

// @Bind #btnDialogOK.onClick
function btnDialogOKOnClick(self, arg) {
	ServiceEvent.btnDialogOKOnClick(self, arg);
}

// @Bind #btnDialogCancel.onClick
function btnDialogCancelOnClick(self, arg) {
	ServiceEvent.btnDialogCancelOnClick(self, arg);
}