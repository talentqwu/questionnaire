// @Bind #btnCreate.onClick
function btnCreateOnClick(self, arg) {
	view.get('#dataSetTemplate.data').insert();
	view.get('#dialogTemplate').show();
}

// @Bind #btnAddQuestion.onClick
function btnAddQuestionOnClick(self, arg) {
	view.get('#dialogChoseQuestion').show();
}

// @Bind #gridTemplate.onDataRowDoubleClick
function gridTemplateOnDataRowDoubleClick(self, arg) {
//	arg.data.get('questions');
	view.get('#dialogTemplate').show();
}