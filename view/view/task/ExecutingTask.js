// @Bind #btnCreateTask.onClick
function btnCreateTaskOnClick(self, arg) {
	var entity = view.get('#dataSetTask').insert({
		status : ENUM.TaskStatus.EDITING,
		statistics : {},
		enableShare : true
	});

	view.get('#dialogTask').show();
}

// @Bind #btnDeleteTask.onClick
function btnDeleteTaskOnClick(self, arg) {
	view.get('#dataSetTask.data').current.remove();
	view.get('#actionUpdateTask').set('parameter', {
		action : ENUM.TaskAction.DELETE
	}).execute();
}

// @Bind #btnPublishTask.onClick
function btnPublishTaskOnClick(self, arg) {
	// TODO
	dorado.widget.NotifyTipManager.notify('功能尚未开发！');
}


//@Bind #btnReditTask.onClick
function btnReditTaskOnClick(self, arg) {
	// TODO
	dorado.widget.NotifyTipManager.notify('功能尚未开发！');
}

//@Bind #btnCopyTask.onClick
function btnCopyTaskOnClick(self, arg) {
	// TODO
	dorado.widget.NotifyTipManager.notify('功能尚未开发！');
}

//@Bind #btnSetPrize.onClick
function btnSetPrizeOnClick(self, arg) {
	var task = view.get('#dataSetTask.data').current;
	if (task) {
		view.get('#dataSetBatch').insert({task : task});
		view.get('#dialogPrize').show();
	} else
		dorado.widget.NotifyTipManager.notify('请先创建或选择任务再创建批次！');
}

//@Bind #btnChangeTaskDate.onClick
function btnChangeTaskDateOnClick(self, arg) {
	view.get('#dialogChangeDate').show();
}

//@Bind #gridTask.onDataRowClick
function gridTaskOnDataRowClick(self, arg) {
	view.get('#dataSetBatch').set('parameter', {
		taskId : arg.data.get('id')
	}).flushAsync();
}

// @Bind #gridTask.onDataRowDoubleClick
function gridTaskOnDataRowDoubleClick(self, arg) {
	view.get('#dialogTask').show();
}

//@Bind #btnCreateBatch.onClick
function btnCreateBatchOnClick(self, arg) {
	var task = view.get('#dataSetTask.data').current;
	if (task) {
		view.get('#dataSetBatch').insert({
			task : task
		});
		view.get('#dialogBatch').show();
	} else
		dorado.widget.NotifyTipManager.notify('请先创建或选择任务再创建批次！');
}

// @Bind #btnDeleteBatch.onClick
function btnDeleteBatchOnClick(self, arg) {
	// TODO
	dorado.widget.NotifyTipManager.notify('功能尚未开发！');
}

//@Bind #btnChangeBatchDate.onClick
function btnChangeBatchDateOnClick(self, arg) {
	view.get('#dialogChangeDate').show();
}

//@Bind #gridBatch.onDataRowClick
function gridBatchOnDataRowClick(self, arg) {
	view.get('#dataSetSubTask').set('parameter', {
		batch : arg.data.get('id')
	}).flushAsync();
}

//@Bind #gridBatch.onDataRowDoubleClick
function gridBatchOnDataRowDoubleClick(self, arg) {
	view.get('#dialogBatch').show();
}

// @Bind #btnShowParticipatorList.onClick
function btnShowParticipatorListOnClick(self, arg) {
	var subTask = view.get('#dataSetSubTask.data').current;
	if (subTask) {
		view.get('#dataSetParticipator').set('parameter', {
			subTaskId : subTask.get('id')
		}).flushAsync();
		view.get('#dialogParticipatorList').show();
	} else
		dorado.widget.NotifyTipManager.notify('请先选择一个单位再查看参与者明细！');
}

// @Bind #btnRefreshSubTask.onClick
function btnRefreshSubTaskOnClick(self, arg) {
	// TODO
	dorado.widget.NotifyTipManager.notify('功能尚未开发！');
}

// @Bind #gridSubTask.onDataRowDoubleClick
function gridSubTaskOnDataRowDoubleClick(self, arg) {
	view.get('#btnShowParticipatorList').fireEvent('onClick');
}

// @Bind #btnConfirmTask.onClick
function btnConfirmTaskOnClick(self, arg) {
	view.get('#actionUpdateTask').set('parameter', {
		action : ENUM.TaskAction.SAVE
	}).execute(function() {
		view.get('#dialogTask').close();
	});
}

// @Bind #btnCancelTask.onClick
function btnCancelTaskOnClick(self, arg) {
	view.get('#dialogTask').close();
}

// @Bind #dialogTask.onShow
function dialogTaskOnShow(self, arg) {
	var visible = true, readOnly = false;
	var task = view.get('#dataSetTask.data').current;
	if (task.get('status') != ENUM.TaskStatus.EDITING) {
		visible = false;
		readOnly = true;
	}
	view.get('#btnConfirmTask').set('visible', visible);
	view.get('#btnCancelTask').set('visible', visible);
	view.get('#formTask').set('readOnly', readOnly);
}

// @Bind #dialogTask.onClose
// @Bind #dialogTask.onHide
function dialogTaskOnClose(self, arg) {
	view.get('#dataSetTask.data').cancel();
}

// @Bind #btnConfirmPrize.onClick
function btnConfirmPrizeOnClick(self, arg) {
	// TODO
	dorado.widget.NotifyTipManager.notify('功能尚未开发！');
}

// @Bind #btnCancelPrize.onClick
function btnCancelPrizeOnClick(self, arg) {
	// TODO
	dorado.widget.NotifyTipManager.notify('功能尚未开发！');
}

// @Bind #dialogPrize.onShow
function dialogPrizeOnShow(self, arg) {
	var visible = true, readOnly = false;
	var task = view.get('#dataSetTask.data').current;
	if (task.get('status') != ENUM.TaskStatus.EDITING) {
		visible = false;
		readOnly = true;
	}
	view.get('#btnConfirmPrize').set('visible', visible);
	view.get('#btnCancelPrize').set('visible', visible);
	view.get('#formPrize').set('readOnly', readOnly);
	view.get('#dataSetPrize').set('parameter', {
		taskId : task.get('id')
	}).flushAsync();
}

// @Bind #btnConfirmBatch.onClick
function btnConfirmBatchOnClick(self, arg) {
	var selection = view.get('#gridChooseUnit').get('selection');
	var batch = view.get('#dataSetBatch.data').current;
	selection.each(function(unit) {
		batch.get('units').insert(dorado.Core.clone(unit));
	});
	view.get('#actionUpdateBatch').set('parameter', {
		action : ENUM.BatchAction.SAVE
	}).execute(function() {
		view.get('#dialogBatch').close();
	});
}

//@Bind #btnCancelBatch.onClick
function btnCancelBatchOnClick(self, arg) {
	view.get('#dialogBatch').close();
}

// @Bind #dialogBatch.onShow
function dialogBatchOnShow(self, arg) {
	var visible = true, readOnly = false;
	var batch = view.get('#dataSetBatch.data').current;
	var task = view.get('#dataSetTask.data').current;
	if (task.get('status') != ENUM.TaskStatus.EDITING) {
		visible = false;
		readOnly = true;
	}
	view.get('#btnConfirmBatch').set('visible', visible);
	view.get('#btnCancelBatch').set('visible', visible);
	view.get('#formBatch').set('readOnly', readOnly);
}

// @Bind #dialogBatch.onClose
// @Bind #dialogBatch.onHide
function dialogBatchOnClose(self, arg) {
	view.get('#dataSetBatch.data').cancel();
}

// @Bind #btnConfirmChangeDate.onClick
function btnConfirmChangeDateOnClick(self, arg) {
	// TODO
	dorado.widget.NotifyTipManager.notify('功能尚未开发！');
}

// @Bind #btnCancelChangeDate.onClick
function btnCancelChangeDateOnClick(self, arg) {
	// TODO
	dorado.widget.NotifyTipManager.notify('功能尚未开发！');
}

// @Bind #btnCreateSubTask.onClick
function btnCreateSubTaskOnClick(self, arg) {
	var batch = view.get('#dataSetBatch.data').current;
	if (batch) {
		var task = view.get('#dataSetTask.data').current;
		if (task.get('status') == ENUM.TaskStatus.EDITING) {
			view.get('#dataSetChoseUnit').set('parameter', task.get('id')).flushAsync();
			view.get('#dialogChooseUnit').show();
		} else 
			dorado.widget.NotifyTipManager.notify('任务已经发布，不能再变更参与单位！');
	} else
		dorado.widget.NotifyTipManager.notify('请先创建任务批次再添加参与单位！');
}

// @Bind #btnDeleteSubTask.onClick
function btnDeleteSubTaskOnClick(self, arg) {
	var selection = view.get('#gridSubTask.selection');
	if (selection.length > 0) {
		var batch = view.get('#dataSetBatch.data').current;
		selection.each(function(subTask){
			subTask.set('batch', {id : batch.get('id')});
			subTask.setState(dorado.Entity.STATE_DELETED);
		});
		view.get('#actionUpdateSubTask').set('parameter', {
			action : ENUM.SubTaskAction.DELETE
		}).execute();
	} else
		dorado.widget.NotifyTipManager.notify('请先勾选需要删除的单位！');
}

// @Bind #btnConfirmChooseUnit.onClick
function btnConfirmChooseUnitOnClick(self, arg) {
	var selection = view.get('#gridChooseUnit.selection');
	if (selection.length > 0) {
		var batch = view.get('#dataSetBatch.data').current;
		var datas = view.get('#dataSetSubTask.data');
		selection.each(function(unit) {
			datas.insert({
				unit : unit,
				batch : batch,
				statistics : {}
			});
		});
		view.get('#actionUpdateSubTask').set('parameter', {
			action : ENUM.SubTaskAction.SAVE
		}).execute(function() {
			view.get('#dialogChooseUnit').close();
		});
	} else
		dorado.widget.NotifyTipManager.notify('请选择参与单位！');
}

// @Bind #btnCancelChooseUnit.onClick
function btnCancelChooseUnitOnClick(self, arg) {
	view.get('#dialogChooseUnit').close();
}