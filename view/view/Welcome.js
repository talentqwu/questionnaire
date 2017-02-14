// @Bind #calendar.onReady
function calendarOnReady(self, arg) {
	Welcome.setView(view);
	WelcomeEvent.calendarOnReady(self, arg);
}

// @Bind #calendar.onSelect
function calendarOnSelect(self, arg) {
	WelcomeEvent.calendarOnSelect(self, arg);
}

// @Bind #calendar.onEventClick
function calendarOnEventClick(self, arg) {
	WelcomeEvent.calendarOnEventClick(self, arg);
}

// @Bind #calendar.onDateDblClick
function calendarOnDateDblClick(self, arg) {
	WelcomeEvent.calendarOnDateDblClick(self, arg);
}

// @Bind #calendar.onEventResizeStop
function calendarOnEventResizeStop(self, arg) {
	WelcomeEvent.calendarOnEventResizeStop(self, arg);
}

// @Bind #calendar.onEventDragStop
function calendarOnEventDragStop(self, arg) {
	WelcomeEvent.calendarOnEventDragStop(self, arg);
}

// @Bind #btnSaveAdd.onClick
function btnSaveAddOnClick(self, arg) {
	WelcomeEvent.btnSaveAddOnClick(self, arg);
}

// @Bind #btnCancelAdd.onClick
function btnCancelAddOnClick(self, arg) {
	view.get("#panelAddEvent").hide();
}

// @Bind #btnSaveEdit.onClick
function btnSaveEditOnClick(self, arg) {
	WelcomeEvent.btnSaveEditOnClick(self, arg);
}

//@Bind #btnDeleteEdit.onClick
function btnDeleteEditOnClick(self, arg) {
	WelcomeEvent.btnDeleteEditOnClick(self, arg);
}

// @Bind #btnCancelEdit.onClick
function btnCancelEditOnClick(self, arg) {
	view.get("#panelEditEvent").hide();
	window._editEvent = null;
}

// @Bind #panelAddEvent.onBlur
// @Bind #panelEditEvent.onBlur
function panelAddEventOnBlur(self, arg) {
	self.hide();
}

// @Bind #panelAddEvent.beforeHide
// @Bind #panelEditEvent.beforeHide
function panelAddEventBeforeHide(self, arg) {
	view.get("#calendar").unselect();
}