package com.kam.qs.dao.common;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.kam.qs.entity.common.CalendarEvent;
import com.kam.util.DoradoHibernateDao;

@Repository
public class CalendarEventDao extends DoradoHibernateDao<CalendarEvent, String> {

	public List<CalendarEvent> getByUserId(String userId, Date startTime, Date endTime) {
		String hql = "FROM CalendarEvent a "
				+ "   WHERE a.user.id=? "
				+ "         AND startTime >=? "
				+ "         AND endTime<=?";
		return find(hql, userId, startTime, endTime);
	}
}
