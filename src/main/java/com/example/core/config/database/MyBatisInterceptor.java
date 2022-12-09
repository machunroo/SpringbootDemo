package com.example.core.config.database;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.HashMap;
import java.util.Map;

@Intercepts({
				@Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class }),
				@Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }) 
			})
public class MyBatisInterceptor implements Interceptor {

	@Override
	public Object intercept(final Invocation invocation) throws Exception {

		String method = invocation.getMethod().getName();
		Object[] args = invocation.getArgs();
		Object param = args[1];

		Map<String, Object> paramMap = (Map<String, Object>) args[1];

		System.out.println("method: " + method);
		System.out.println("args: " + args);
		System.out.println("param: " + param);
		System.out.println("paramMap: " + paramMap);

		if( paramMap == null ) {
			paramMap = new HashMap<>();
		}
		paramMap.put("addParammeter", "추가추가");
		args[1] = paramMap;

		return invocation.proceed();
	}
}
