// @Bind view.onReady
function viewOnReady(self, arg) {
	TreeNode.setView(view);
}

//@Bind #editorCategory.onTextEdit
function editorCategoryOnTextEdit(self, arg) {
	TreeNodeEvent.editorCategoryOnTextEdit(self, arg);
}

// @Bind #btnRefresh.onClick
function btnRefreshOnClick(self, arg) {
	TreeNodeEvent.btnRefreshOnClick(self, arg);
}

// @Bind #treeGridTreeNode.onDataRowDoubleClick
function treeGridTreeNodeOnDataRowDoubleClick(self, arg) {
	TreeNodeEvent.treeGridTreeNodeOnDataRowDoubleClick(self, arg);
}

// @Bind #btnDialogOK.onClick
function btnDialogOKOnClick(self, arg) {
	TreeNodeEvent.btnDialogOKOnClick(self, arg);
}

// @Bind #btnDialogCancel.onClick
function btnDialogCancelOnClick(self, arg) {
	TreeNodeEvent.btnDialogCancelOnClick(self, arg);
}

// @Bind #dialog.onShow
function dialogOnShow(self, arg) {
	TreeNodeEvent.dialogOnShow(self, arg);
}

// @Bind #dialog.onClose
// @Bind #dialog.onHide
function dialogOnClose(self, arg) {
	TreeNodeEvent.dialogOnClose(self, arg);
}