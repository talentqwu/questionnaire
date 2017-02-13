package com.kam.util;

import java.util.Date;

import org.hibernate.event.SaveOrUpdateEvent;
import org.hibernate.event.def.DefaultSaveOrUpdateEventListener;

import com.bstek.dorado.web.DoradoContext;
import com.kam.qs.entity.AuditEntity;
import com.kam.qs.entity.common.Log;
import com.kam.qs.entity.common.User;
import com.kam.qs.util.Constants;

public class EntityAuditListener extends DefaultSaveOrUpdateEventListener {

	private static final long serialVersionUID = 1L;

	@Override
	public void onSaveOrUpdate(SaveOrUpdateEvent event) {

		if (event.getObject() instanceof AuditEntity || event.getObject() instanceof Log) {
			User currentUser = (User) DoradoContext.getCurrent().getAttribute(
					DoradoContext.SESSION, Constants.CURRENT_USER);
			String userCode = currentUser != null ? currentUser.getCode() : "unknown";
			String userName = currentUser != null ? currentUser.getName() : "unknown";
			
			if (event.getObject() instanceof Log) {
				Log log = (Log)event.getObject();
				log.setDateTime(new Date());
				log.setUserCode(userCode);
				log.setUserName(userName);
			} else if (event.getObject() instanceof AuditEntity) {
				AuditEntity entity = (AuditEntity)event.getObject();
				if (entity.getId() == null) {
					entity.setCreated(new Date());
					entity.setCreator(userCode);
				} else {
					entity.setModified(new Date());
					entity.setModifier(userCode);
				}
			}
		}

		super.onSaveOrUpdate(event);
	}
}
