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

// @Bind #gridBatch.onDataRowClick
function gridBatchOnDataRowClick(self, arg) {
	view.get('#dataSetSubTask').set('parameter', {
		batch : arg.data.get('id')
	}).flushAsync();
}

// @Bind #dialogPrize.onShow
function dialogPrizeOnShow(self, arg) {
	var vasible = true, readOnly = false;
	var task = view.get('#dataSetTask.data').current;
	if (task.get('status') != ENUM.TaskStatus.EDITING) {
		vasible = false;
		readOnly = true;
	}
	view.get('#btnConfirmPrize').set('vasible', vasible);
	view.get('#btnCancelPrize').set('vasible', vasible);
	view.get('#formPrize').set('readOnly', readOnly);
	view.get('#dataSetPrize').set('parameter', {
		taskId : task.get('id')
	}).flushAsync();
}

// @Bind #gridBatch.onDataRowDoubleClick
function gridBatchOnDataRowDoubleClick(self, arg) {
	view.get('#dialogBatch').show();
}

// @Bind #dialogBatch.onShow
function dialogBatchOnShow(self, arg) {
	var vasible = true, readOnly = false;
	var task = view.get('#dataSetTask.data').current;
	if (task.get('status') != ENUM.TaskStatus.EDITING) {
		vasible = false;
		readOnly = true;
	}
	view.get('#btnConfirmBatch').set('vasible', vasible);
	view.get('#btnCancelBatch').set('vasible', vasible);
	view.get('#formBatch').set('readOnly', readOnly);
}

// @Bind #girdTask.onDataRowClick
function gridTaskOnDataRowClick(self, arg) {
	view.get('#dataSetBatch').set('parameter', {
		taskId : arg.data.get('id')
	}).flushAsync();
}

// @Bind #btnCreateBatch.onClick
function btnCreateBatchOnClick(self, arg) {
	var task = view.get('#dataSetTask.data').current;
	if (task) {
		view.get('#dataSetBatch').insert({task : task});
		view.get('#dialogBatch').show();
	} else
		dorado.widget.NotifyTipManager.notify('请先创建或选择任务再创建批次！');
}

// @Bind #btnSetPrize.onClick
function btnSetPrizeOnClick(self, arg) {
	var task = view.get('#dataSetTask.data').current;
	if (task) {
		view.get('#dataSetBatch').insert({task : task});
		view.get('#dialogPrize').show();
	} else
		dorado.widget.NotifyTipManager.notify('请先创建或选择任务再创建批次！');
}

// @Bind #btnCreateTask.onClick
function btnCreateTaskOnClick(self, arg) {
	var repository =view.get('dataTypeRepository');
	var statistics = dorado.DataUtil.convertIfNecessary({}, 
			repository, repository.get('Statistics', 'always'));
	var entity = view.get('#dataSetTask').insert({
		status : ENUM.TaskStatus.EDITING,
		statistics : statistics
	});

	view.get('#dialogTask').show();
}

// @Bind #btnChangeTaskDate.onClick
function btnChangeTaskDateOnClick(self, arg) {
	view.get('#dialogChangeDate').show();
}

// @Bind #btnChangeBatchDate.onClick
function btnChangeBatchDateOnClick(self, arg) {
	view.get('#dialogChangeDate').show();
}