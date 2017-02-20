// @Bind #btnUnitCreateBrother.onClick
function btnUnitCreateBrotherOnClick(self, arg) {
	var treeGrid = view.get('#gridUnit');
	var datas = view.get("#dataSetUnit.data");
	var currentNode = treeGrid.get("currentNode");
	var newEntity = null;
	if (currentNode) {
		var currentEntity = currentNode.get("data");
		newEntity = currentEntity.createBrother({name: '<新单位>'});
	} else
		newEntity = datas.insert({name: '<新单位>'});
	treeGrid.set('currentEntity', newEntity);
}

// @Bind #btnUnitCreateChild.onClick
function btnUnitCreateChildOnClick(self, arg) {
	var treeGrid = view.get('#gridUnit');
	var currentNode = treeGrid.get("currentNode");
	if (currentNode.get('level') == 2) 
		dorado.widget.NotifyTipManager.notify('最多只能创建两层单位！');
	else if (currentNode) {
		var currentEntity = currentNode.get("data");
		currentNode.expandAsync(function() {
			var newEntity = currentEntity.createChild("children", {name: '<新单位>'});
			treeGrid.set('currentEntity', newEntity);
		});
	} else
		dorado.widget.NotifyTipManager.notify('请先创建父单位再创建子单位！');
}

// @Bind #btnUnitDelete.onClick
function btnUnitDeleteOnClick(self, arg) {
	var treeGrid = view.get('#gridUnit');
	var currentNode = treeGrid.get("currentNode");
	if (currentNode.get('hasChild'))
		dorado.widget.NotifyTipManager.notify('请先删除子单位再删除父单位！');
	else
		dorado.MessageBox.confirm("您真的想删除当前数据吗?",function(){
			var currentEntity = treeGrid.get("currentEntity");
			currentEntity.remove();
		});
}

//@Bind #btnUnitSave.onClick
function btnUnitSaveOnClick(self, arg) {
	dorado.MessageBox.confirm("您真的要保存吗?",function(){
		view.get("#actionUpdate").execute();
	})
}

// @Bind #btnUnitQuery.onClick
function btnUnitQueryOnClick(self, arg) {
	var data = view.get("#formUnitCondition.entity");
	with (view.get("#dataSetUnit")){
		set("parameter", data);
		flushAsync();
	}
}

// @Bind #btnUnitCancel.onClick
function btnUnitCancelOnClick(self, arg) {
	var treeGrid = view.get('#gridUnit');
	dorado.MessageBox.confirm("您真的要取消当前操作吗？",function(){
		var currentEntity = treeGrid.get("currentEntity");
		currentEntity.cancel();
	});
}

// @Bind #gridUnit.#liaisonses.onRenderCell
function gridUnitLiaisonsesOnRenderCell(self, arg) {
	if (!arg.rowType) {
		var text = '';
		var liaisonses = arg.data.get('liaisonses');
		if (liaisonses) {
			liaisonses.each(function(liaisons){
				text += liaisons.get('name') + '(' + liaisons.get('telphone') + '),';
			});
			if (text.length > 1)
				text = text.substr(0, text.length - 1);
		}
		arg.dom.innerHTML = text;
	}
}

// @Bind #gridSelectLiaisons.onDataRowDoubleClick
function gridSelectLiaisonsOnDataRowDoubleClick(self, arg) {
	var dropDown = dorado.widget.DropDown.findDropDown(self);
	dropDown.close(dorado.Core.clone(arg.data));
}