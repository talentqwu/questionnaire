var TreeNode = function() {};

TreeNode.setView = function(view) {
	Context.setView(view);
};

var TreeNodeEvent = function() {};

TreeNodeEvent.editorCategoryOnTextEdit = function(self, arg) {
	Context.VIEW.get('#dataSetTreeNode').set({
		'parameter' : self.get('value')
	}).flushAsync();
};

TreeNodeEvent.btnRefreshOnClick = function(self, arg) {
	var category = Context.VIEW.get('#editorCategory.value');
	if (category) {
		Context.VIEW.get('#dataSetTreeNode').set({
			'parameter' : category
		}).flushAsync();
	}
};

TreeNodeEvent.treeGridTreeNodeOnDataRowDoubleClick = function(self, arg) {
	Context.VIEW.get('#dialog').show();
};

TreeNodeEvent.btnDialogOKOnClick = function(self, arg) {
	var roles = [];
	Context.VIEW.get('#gridRole.selection').each(function(role) {
		roles.push(role.get('key'));
	});
	var roleStr =  roles.length == 0 ? null : roles.join(',');
	Context.VIEW.get('#dataSetForUpdate').getData('#').set('roleStr', roleStr);
	Context.VIEW.get('#actionUpdateSingle').execute(function(result){
		var newEntity = Context.VIEW.get('#dataSetForUpdate').getData('#');
		var entity = Context.VIEW.get('#treeGridTreeNode.currentEntity');
		entity.fromJSON(newEntity.toJSON());
		Context.VIEW.get('#dialog').close();
	});
};

TreeNodeEvent.btnDialogCancelOnClick = function(self, arg) {
	Context.VIEW.get('#dialog').close();
};

TreeNodeEvent.dialogOnShow = function(self, arg) {
	var currentNode = Context.VIEW.get("#treeGridTreeNode.currentNode");
	var entity = currentNode.get('data'), parent = null;
	var newEntity = dorado.Core.clone(entity);
	var parentNode = currentNode.get('parent');
	if (!(parentNode === Context.VIEW.get("#treeGridTreeNode.root")))
		parent = dorado.Core.clone(parentNode.get('data'));
	if (entity.get('id')) 
		newEntity.set('parent', parent);

	var dataSetForUpdate = Context.VIEW.get('#dataSetForUpdate');
	dataSetForUpdate.clear();
	var temp = dataSetForUpdate.get('data').insert(newEntity);
	var selection = [];
	if (temp.get('roleStr')) {
		var roles = temp.get('roleStr').split(',');
		Context.VIEW.get('#dataSetRole.data').each(function(data) {
			roles.each(function(role) {
				if (data.get('key') == role)
					selection.push(data);
			});
		});
	} 
	Context.VIEW.get('#gridRole').set('selection', selection);
};

TreeNodeEvent.dialogOnClose = function(self, arg) {
	Context.VIEW.get('#treeGridTreeNode.currentEntity').cancel(true);
};