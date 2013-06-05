package com.baobaotao.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.baobaotao.domain.SysLog;


@Repository
public class SysLogDao {

	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void addLog(SysLog log) {
		String sql="insert into t_sys_log (userid, username, operatetime, operatedes, userip) values(?,?,?,?,?)";
		Object [] args={log.getuId(),log.getuName(),log.getOperateTime(),log.getOperateDes(),log.getuIp()};
		jdbcTemplate.update(sql, args);
	}
}
