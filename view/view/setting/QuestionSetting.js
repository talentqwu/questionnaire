// @Bind view.onReady
function viewOnReady(self, arg) {
	view.get('#gridQuestion').enableAutoRefresh();
}

// @Bind #btnCreate.onClick
function btnCreateOnClick(self, arg) {
	var treeGrid = view.get('#gridQuestion');
	var datas = view.get("#dataSetQuestion.data");
	var currentNode = treeGrid.get("currentNode");
	var newEntity = null;
	if (currentNode) {
		var currentEntity = currentNode.get("data");
		newEntity = currentEntity.createBrother({
			name : '<新试题>',
			parentId : currentEntity.get('parentId')
		});
	} else
		newEntity = datas.insert({
			name : '<新试题>'
		});
	treeGrid.set('currentEntity', newEntity);
}

// @Bind #btnCreateSub.onClick
function btnCreateSubOnClick(self, arg) {
	var treeGrid = view.get('#gridQuestion');
	var currentNode = treeGrid.get("currentNode");
	if (currentNode) {
		if (currentNode.get('level') == 2)
			dorado.widget.NotifyTipManager.notify('最多只能创建两层试题！');
		else {
			var currentEntity = currentNode.get("data");
			if (currentEntity.get('id')) {
				currentNode.expandAsync(function() {
					var newEntity = currentEntity.createChild("children", {
						name : '<新试题>',
						parentId : currentEntity.get('id')
					});
					treeGrid.set('currentEntity', newEntity);
				});
			} else
				dorado.widget.NotifyTipManager.notify('请先保存父试题再创建子试题！');
		}
	} else
		dorado.widget.NotifyTipManager.notify('请先创建父试题再创建子试题！');
}


// @Bind #btnSetAnswer.onClick
function btnSetAnswerOnClick(self, arg) {
	view.get('#dialogAnswer').show();
}