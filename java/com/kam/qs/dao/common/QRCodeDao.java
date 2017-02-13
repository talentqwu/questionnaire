package com.kam.qs.dao.common;

import org.springframework.stereotype.Repository;

import com.kam.qs.entity.common.QRCode;
import com.kam.util.DoradoHibernateDao;

@Repository
public class QRCodeDao extends DoradoHibernateDao<QRCode, String> {

}
