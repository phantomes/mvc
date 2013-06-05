package com.baobaotao.sysLogUtil;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.baobaotao.domain.SysLog;
import com.baobaotao.domain.User;
import com.baobaotao.service.UserService;


@Aspect
public class SystemLogAspect {

	//int和long的class会自动转化为封装类
	private static final String integerClazz="class java.lang.Integer";
	private static final String longClazz="class java.lang.Long";
	private Logger logger=Logger.getLogger(this.getClass().getName());
	
	@Resource
	private LogService logService;
	
//	@Pointcut("target(com.baobaotao.service.UserService)")  target这种可用，切 到具体的类，执行该类的多少方法，则运行多少次
	@Pointcut("within(com.baobaotao.service..*)")  //同上
	public void myAspect() {
		
	}
	
	@AfterThrowing(pointcut="myAspect()",throwing="e")
	public void	doAfterThrowing(JoinPoint jp,Throwable e){
		System.err.println("出现异常："+e.getMessage());
		System.err.println(e.getClass().getName());
		System.err.println("异常类："+jp.getTarget().getClass().getName());
		System.err.println(""+jp.getSignature().getName()+"方法 throw exception");
//		logger.error("Oops===" + jp.getTarget().getClass().getName() + "中的"  
//	                + jp.getSignature().getName() + "方法抛出" + e.getClass().getName()  
//	                + "异常");
		System.err.println("参数：");
		if (jp.getArgs()!=null&&jp.getArgs().length>0) {
			for (int i = 0; i < jp.getArgs().length; i++) {
				System.err.println(jp.getArgs()[i].toString());
//				logger.error("参数为："+jp.getArgs()[i].toString());
			}
		}
		System.out.println("--------错误异常");
	}
	
//	@After("target(com.baobaotao.service.UserService)") target这种可用，切 到具体的类，执行该类的多少方法，则运行多少次
	@After("within(com.baobaotao.service..*)") //同上
	public void	doAfter(JoinPoint jp){
		System.out.println("----------后置通知");  
        System.out.println("方法所在类:" + jp.getTarget().getClass().getName());  
        System.out.println("" + jp.getSignature().getName() + "方法");  
        String methodName = jp.getSignature().getName();
       
        //操作日志对象
        SysLog log=new SysLog();
        
        //操作参数
        String descArgs="参数";
        if (jp.getArgs()!=null&&jp.getArgs().length>0) {
        	for (int i = 0; i < jp.getArgs().length; i++) {
        		if (jp.getArgs()[i]!=null) {
					descArgs+=jp.getArgs()[i].toString()+",";
				}else {
					descArgs+="null"+",";
				}
        	}
        	System.out.println("-----参数："+descArgs);
		}
        log.setOperateDes(descArgs); //设置日志参数
        
        
        
        HttpSession session=((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        User appUser=(User)session.getAttribute("user");
        if (appUser!=null) {
			System.out.println("用户已存在session中");
			log.setuId(appUser.getUserId()+"");
			log.setuName(appUser.getUserName());
		}
        
        String ip=((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getRemoteAddr();
        log.setuIp(ip);
        log.setOperateTime(new Date());
        logService.addLog(log);
        System.out.println("------保存操作日志");
	}
}
