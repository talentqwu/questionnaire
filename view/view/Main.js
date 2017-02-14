// @Bind view.onReady
function viewOnReady(self, arg) {
	Main.setView(view);
	MainEvent.viewOnReady(self, arg);
}

// @Bind ^main-tree.onDataRowClick
function tagMainTreeOnDataRowClick(self, arg) {
	MainEvent.tagMainTreeOnDataRowClick(self, arg);
}

// @Bind ^main-tree.onNodeAttached
function tagMainTreeOnNodeAttached(self, arg) {
	MainEvent.tagMainTreeOnNodeAttached(self, arg);
}