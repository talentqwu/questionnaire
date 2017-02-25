package com.kam.qs.dao.task;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kam.qs.entity.task.SubTask;
import com.kam.util.DoradoHibernateDao;

@Repository
public class SubTaskDao extends DoradoHibernateDao<SubTask, String> {

	public List<Object[]> getByBatch(String batchId) {
		String hql = "SELECT s,"
				+ "          s.unit,"
				+ "          s.statistics,"
				+ "          s.unit.region,"
				+ "          s.unit.industry"
				+ "   FROM SubTask s"
				+ "   WHERE s.batch.id = ?"
				+ "   ORDER BY s.unit.name";
		return find(hql, batchId);
	}

	public void deleteAll(SubTask subTask) {
		String hql = null, subTaskId = subTask.getId();
		// 删除参与者每题得分
		hql = "DELETE FROM Score s WHERE s.sheet.id IN (SELECT sh.id FROM Sheet sh WHERE sh.participator.id IN (SELECT p.id FROM Participator p WHERE p.subTask.id = ?))";
		// 删除参与者答卷
		hql = "DELETE FROM Sheet sh WHERE sh.participator.id IN (SELECT p.id FROM Participator p WHERE p.subTask.id = ?)";
		createQuery(hql, subTask.getId()).executeUpdate();
		// 获取与二维码相关的参与者ID
		hql = "SELECT p.qrcode.id FROM Participator p WHERE p.subTask.id = ? AND p.qrcode IS NOT NULL";
		List<String> qrcodeIds = find(hql, subTaskId);
		// 删除子任务参与者
		hql = "DELETE FROM Participator p WHERE p.subTask.id = ? AND p.parent.id IS NOT NULL";
		createQuery(hql, subTask.getId()).executeUpdate();
		hql = "DELETE FROM Participator p WHERE p.subTask.id = ?";
		createQuery(hql, subTask.getId()).executeUpdate();
		// 删除参与者二维码
		if (qrcodeIds != null && qrcodeIds.size() > 0) {
			hql = "DELETE FROM QRCode qr WHERE qr.id IN (:qrcodeIds)";
			createQuery(hql).setParameterList("qrcodeIds", qrcodeIds).executeUpdate();
		}
		
		delete(subTask);
		
//		if (statisticsId != null) {
//			// 删除子任务统计明细
//			hql = "DELETE FROM StatisticsDetail sd WHERE sd.statistics.id = ?)";
//			createQuery(hql, statisticsId).executeUpdate();
//			// 删除子任务统计
//			hql = "DELETE FROM Statistics s WHERE s.id = ?";
//			createQuery(hql, statisticsId).executeUpdate();
//		}

		// 删除子任务LOG
		hql = "DELETE FROM Log l WHERE l.entityId = ?";
		createQuery(hql, subTaskId).executeUpdate();
	}
	
	public void deleteAll(String subTaskId) {
		deleteAll(get(subTaskId));
	}
}
