var Welcome = function() {};

Welcome.setView = function(view) {
	Context.setView(view);
};

var WelcomeEvent = function() {};

WelcomeEvent.calendarOnReady = function(self, arg) {
	self.set("eventStore", new dorado.widget.DataSetEventStore({
		dataSet : Context.VIEW.get("#dataSetEvent"),
		bindingConfig : {
			title : "title",
			startTime : "startTime",
			endTime : "endTime",
			allDay : "allDay", 
			backgroundColor : "backgroundColor",
			color : "color"
		}
	}));
};

WelcomeEvent.calendarOnSelect = function(self, arg) {
	var addEventPanel = Context.VIEW.get("#panelAddEvent");
	var addForm = Context.VIEW.get("#formAddEvent");

	addForm.set('entity', {
		title : "",
		startTime : arg.startTime,
		endTime : arg.endTime,
		allDay : arg.allDay,
		color : '#ffffff',
		backgroundColor : '#6767d4'
	});

	addEventPanel.show({
		anchorTarget : arg.placeholder,
		position : arg.position,
		align : "innerleft",
		vAlign : "top"
	});

	addForm.refreshData();
};

WelcomeEvent.calendarOnEventClick = function(self, arg) {
	var dsEvents = Context.VIEW.get("#dataSetEvent");
	dsEvents.getData().setCurrent(arg.event.entity);

	var event = arg.event;
	var editEventPanel = Context.VIEW.get("#panelEditEvent");
	var editEventForm = Context.VIEW.get("#formEditEvent");
	editEventForm.set('entity', {
		title : event.title,
		startTime : event.startTime,
		endTime : event.endTime,
		allDay : event.allDay,
		color : event.color,
		backgroundColor : event.backgroundColor
	});

	window._editEvent = arg.event;

	editEventPanel.show({
		anchorTarget : arg.placeholder,
		align : "innerleft",
		vAlign : "top"
	});
};

WelcomeEvent.calendarOnDateDblClick = function(self, arg) {
	self.set({
		currentView : "day",
		date : arg.date
	});
};

WelcomeEvent.calendarOnEventResizeStop = function(self, arg) {
	if (arg.changed) {
		Context.VIEW.get("#dataSetEvent").getData("#").set({
			startTime: arg.startTime,
			endTime : arg.endTime
		});
		Context.VIEW.get("#actionUpdate").execute();
	}
};

WelcomeEvent.calendarOnEventDragStop = function(self, arg) {
	if (arg.changed) {
		Context.VIEW.get("#dataSetEvent").getData("#").set({
			startTime: arg.startTime,
			endTime : arg.endTime
		});
		Context.VIEW.get("#actionUpdate").execute();
	}
};

WelcomeEvent.btnSaveAddOnClick = function(self, arg) {
	var addEventPanel = Context.VIEW.get("#panelAddEvent");
	var addForm = Context.VIEW.get("#formAddEvent");
	var eventEntity = addForm.get("entity");
	var title = eventEntity.get("title");

	if (title) {
		var dataSet = Context.VIEW.get("#dataSetEvent");
		var entity = dataSet.insert(eventEntity.toJSON());
		Context.VIEW.get("#actionUpdate").execute();
		addEventPanel.hide();
	}
};

WelcomeEvent.btnSaveEditOnClick = function(self, arg) {
	var editEventPanel = Context.VIEW.get("#panelEditEvent");
	var eventEntity = Context.VIEW.get("#formEditEvent.entity");
	var title = eventEntity.get("title");

	Context.VIEW.get("#dataSetEvent").getData("#").set(eventEntity.toJSON());
	Context.VIEW.get("#actionUpdate").execute();

	window._editEvent.set({
		title : title,
		startTime : eventEntity.get("startTime"),
		endTime : eventEntity.get("endTime"),
		allDay : eventEntity.get("allDay"),
		backgroundColor : eventEntity.get("backgroundColor"),
		color : eventEntity.get('color')
	});

	window._editEvent = null;
	editEventPanel.hide();
};

WelcomeEvent.btnDeleteEditOnClick = function(self, arg) {
    dorado.MessageBox.confirm("您真的想删除当前数据吗?", function(){
    	Context.VIEW.get("#dataSetEvent").getData("#").remove();
    	Context.VIEW.get("#actionUpdate").execute();
    	window._editEvent = null;
    	Context.VIEW.get("#panelEditEvent").hide();
    });
};