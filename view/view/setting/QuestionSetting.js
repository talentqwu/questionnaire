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
			question : '<新试题>',
			parentId : currentEntity.get('parentId')
		});
	} else
		newEntity = datas.insert({
			question : '<新试题>'
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
						question : '<新试题>',
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

// @Bind #gridQuestion.onDataRowClick
function gridQuestionOnDataRowClick(self, arg) {
	var questionId = arg.data.get('data.id');
	view.get('#dataSetAnswer').set('parameter', questionId).flushAsync();
}

// @Bind #btnSetAnswer.onClick
function btnSetAnswerOnClick(self, arg) {
	var treeGrid = view.get('#gridQuestion');
	var currentNode = treeGrid.get("currentNode");
	if (currentNode) {
		var currentEntity = currentNode.get("data");
		if (currentEntity.get('id')) 
			view.get('#dialogAnswer').show();
		else
			dorado.widget.NotifyTipManager.notify('请先保存试题再编辑答案！');
	} else
		dorado.widget.NotifyTipManager.notify('请先选择一道试题！');
}