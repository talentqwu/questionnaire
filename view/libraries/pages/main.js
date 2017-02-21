var Main = function() {};

Main.setView = function(view) {
	Context.setView(view);
};

Main.createRefreshButton = function(tabControl) {
	tabControl.addRightToolButton(new dorado.widget.SimpleIconButton({
		iconClass : 'fa fa-refresh',
		tip : '刷新当前页面',
		onClick : function() {
			var currentTab = Context.VIEW.get('#tabControl.currentTab');
			if (currentTab.get('name') != 'Welcome')
				currentTab.doGetControl().reload();
		}
	}));
};

Main.createCloseAllButton = function(tabControl) {
	tabControl.addRightToolButton(new dorado.widget.SimpleIconButton({
		iconClass : 'fa fa-times-circle',
		id : 'btnCloseAllTabs',
		tip : '关闭所有页面',
		onClick : function() {
			var tabControl = Context.VIEW.get('#tabControl');
			var welcomeTab = tabControl.getTab('Welcome');
			tabControl.set('currentTab', welcomeTab);
			tabControl.closeOtherTabs(welcomeTab);
		}
	}));
};

Main.createLogoutButton = function(tabControl) {
	tabControl.addRightToolButton(new dorado.widget.SimpleIconButton({
		iconClass : 'fa fa-user',
		id : 'btnLogout',
		tip : '当前用户：' + Context.VIEW.CONTEXT.currentUser.name,
		onClick : function() {
			dorado.MessageBox.confirm('您确定要登出系统吗？', function() {
				new dorado.widget.AjaxAction({
					async : false,
					service : 'qs.main#logout'
				}).execute();
				document.location = "view.Login.d";
			});
		}
	}));
};

var MainEvent = function() {};

MainEvent.viewOnReady = function(self, arg) {
	var tabControl = Context.VIEW.get('#tabControl');
	var results = ContextHelper.getAttributes({
		keys : [ Constants.CURRENT_USER ]
	});
	Context.VIEW.CONTEXT = {
		currentUser : results.CURRENT_USER
	};
	
	var showSetting = false, showManage = false, showTask = false, showAnalysis = false;
	if (Context.VIEW.CONTEXT.currentUser.permissions)
		Context.VIEW.CONTEXT.currentUser.permissions.each(function(permission) {
			switch(permission.role) {
			case ENUM.Role.SYSTEM:
				showManage = true;
				break;
			case ENUM.Role.ADMIN:
				showSetting = true;
				break;
			case ENUM.Role.PUBLISH:
				showTask = true;
				break;
			case ENUM.Role.ANALYSIS:
				showAnalysis = true;
				break;
			}
		});

	Context.VIEW.get('#stSetting').set('visible', showSetting);
	Context.VIEW.get('#stManage').set('visible', showManage);
	Context.VIEW.get('#stTask').set('visible', showTask);
	Context.VIEW.get('#stAnalysis').set('visible', showAnalysis);
	var accNavigator = Context.VIEW.get('#accNavigator');
	accNavigator.get('sections').each(function(section) {
		if (section.get('visible')) {
			accNavigator.set('currentSection', section);
			return false;
		}
	});

	var tab = {
		$type : 'dorado.widget.tab.IFrameTab',
		caption : '日程表',
		iconClass : 'fa fa-calendar',
		name : 'Welcome',
		closeable : false,
		path : 'view.Welcome.d'
	};

	tab = tabControl.addTab(tab);
	tabControl.set("currentTab", tab);

	Main.createRefreshButton(tabControl);
	Main.createCloseAllButton(tabControl)
	Main.createLogoutButton(tabControl)
};

MainEvent.tagMainTreeOnDataRowClick = function(self, arg) {
	var data = self.get('currentNode.data');
	var treeId = self.get('id');
	if (treeId == 'treeBudget' || treeId == 'treeReport')
		if (data.get('hasChild'))
			return;
	if (treeId == 'treeBudget' && !data.get('hasChild'))
		ContextHelper.setAttributes({
			'CURRENT_TREE_NODE_ID' : data.get('id')
		});

    var tab = {
		$type : 'dorado.widget.tab.IFrameTab',
		caption : data.get('label'),
		name : data.get('label'),
		iconClass : self.get('currentNode.iconClass'),
		closeable : true,
		path : data.get('url') ? data.get('url') : 'http://www.baidu.com',
		tags : 'tab-' + treeId
	};

	// 如果当前节点有指定的Path则打开新的tab
	if (tab.path) {
		var tabControl = Context.VIEW.get("#tabControl");
		var currentTab = tabControl.get('currentTab');
		// 根据name查找是否已经打开过当前的Tab。
		// 如果没有打开过，则需要添加一个新的Tab
		currentTab = tabControl.getTab(tab.name);
		tab = currentTab ? currentTab : tabControl.addTab(tab);
		// 设定当前的Tab为制定的tab
		tabControl.set("currentTab", tab);
	}
};

MainEvent.tagMainTreeOnNodeAttached = function(self, arg) {
	var setNodeIconClass = function(self, arg, iconClass) {
		var node = arg.node, hasChild = node.get("data.hasChild");
		if (!hasChild) {
			node.set("iconClass", iconClass);
		}
	};
	
	switch (self.get('id')) {
	case 'treeSetting':
		setNodeIconClass(self, arg, "fa fa-cog");
		break;
	case 'treeAnalysis':
		setNodeIconClass(self, arg, "fa fa-file-excel-o");
		break;
	case 'treeManage':
		setNodeIconClass(self, arg, "fa fa-file-o");
		break;
	case 'treeTask':
		setNodeIconClass(self, arg, "fa fa-flag");
		break;
	}
};