// @Bind #pilotUnit.onSubControlAction
function pilotUnitOnSubControlAction(self, arg) {
	var datas = view.get("#dataSetUnit.data");
	var action = view.get("#actionUpdate");
	switch(arg.code){
		case "+":{
			datas.insert();
			view.get("#dialogUnit").show();
			arg.processDefault=false;
			break;
		}
		case "-":{
			dorado.MessageBox.confirm("您真的想删除当前数据吗?",function(){
				datas.remove();
				actionUpdate.execute();
			});
			arg.processDefault=false;
			break;
		}
	}
}

// @Bind #btnUnitModify.onClick
function btnUnitModifyOnClick(self, arg) {
	var data=view.get("#dataSetUnit.data");
	if(data.current){
		view.get("#dialogLiaisons").show();
	}else{
		dorado.MessageBox.alert("当前表没有信息可供编辑!");
	}
}

// @Bind #btnUnitQuery.onClick
function btnUnitQueryOnClick(self, arg) {
	var data = view.get("#formUnitCondition.entity");
	with (view.get("#dataSetUnit")){
		set("parameter", data);
		flushAsync();
	}
}

// @Bind #btnUnitSave.onClick
function btnUnitSaveOnClick(self, arg) {
	var dialog=view.get("#dialogUnit");
	var action=view.get("#actionUpdate");
	dorado.MessageBox.confirm("您真的要保存吗?",function(){
		action.execute(function(){
			dialog.hide();
		});
	})
}

// @Bind #btnUnitCancel.onClick
function btnUnitCancelOnClick(self, arg) {
	var data=view.get("#dataSetUnit").getData();
	var dialog=view.get("#dialogUnit");
	dorado.MessageBox.confirm("您真的要取消当前操作吗？",function(){
		data.cancel();
		dialog.hide();
	});
}

//@Bind #btnAddLiaisons.onClick
function btnAddLiaisonsOnClick(self, arg) {
	view.get('#dialogSelectLiaisons').show();
}

// @Bind #btnRemoveLiaisons.onClick
function btnRemoveLiaisonsOnClick(self, arg) {
	
}

// @Bind #btnCloseLiaisonsDialog.onClick
function btnCloseLiaisonsDialogOnClick(self, arg) {
	view.get("#dialogSelectLiaisons").hide();
}

// @Bind #btnConfirmLiaisons.onClick
function btnConfirmLiaisonsOnClick(self, arg) {
	var data = view.get('#dataSetLiaisons.data');
	if (data.current) {
		var liaisonses = view.get('#dataSetUnit.data.current.liaisonses');
		var liaisons = dorado.Core.clone(data.current, true);
		var found = false;
		liaisonses.each(function(item){
			if (item.get('id') == liaisons.get('id'))
				found = true;
		});
		if (found) 
			dorado.widget.NotifyTipManager.notify('该联络员已经添加！');
		else {
			liaisonses.insert(liaisons);
			view.get('#dialogSelectLiaisons').hide();
		}
	}
}

// @Bind #btnLiaisonsQuery.onClick
function btnLiaisonsQueryOnClick(self, arg) {
	var data = view.get("#formLiaisonsCondition.entity");
	with (view.get("#dataSetLiaisons")){
		set("parameter", data);
		flushAsync();
	}
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
	view.get('#btnConfirmLiaisons').fireEvent('onClick');
}