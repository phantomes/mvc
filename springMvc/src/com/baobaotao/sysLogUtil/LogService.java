package com.baobaotao.sysLogUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baobaotao.dao.SysLogDao;
import com.baobaotao.domain.SysLog;

@Service
public class LogService {

	@Autowired
	private SysLogDao sysLogDao;
	public void addLog(SysLog log) {
		sysLogDao.addLog(log);
	}
}
