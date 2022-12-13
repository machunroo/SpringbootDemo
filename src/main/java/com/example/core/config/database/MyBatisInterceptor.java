package com.example.core.config.database;

import com.example.core.util.StringUtil;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Intercepts({
				@Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class }),
				@Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }) 
			})
public class MyBatisInterceptor implements Interceptor {

//	@Override
//	public Object intercept(final Invocation invocation) throws Exception {
//		System.out.println("MyBatisInterceptor intercept --------------------------- sss");
//		System.out.println(invocation.getMethod().getReturnType());
//		System.out.println("MyBatisInterceptor intercept --------------------------- eee");
//
//		String method = invocation.getMethod().getName();
//
//		Object[] args = invocation.getArgs();
//		Object param = args[1];
//
//		System.out.println("method: " + method);
//		System.out.println("args: " + args);
//		System.out.println("param: " + param);
//
//		Map<String, Object> paramMap = (Map<String, Object>) args[1];
//		System.out.println("paramMap: " + paramMap);
//
//		if( paramMap == null ) {
//			paramMap = new HashMap<>();
//		}
//		paramMap.put("addParammeter", "추가추가");
//		args[1] = paramMap;
//
//		return invocation.proceed();
//	}

	@SuppressWarnings("unchecked")
	@Override
	public Object intercept(final Invocation invocation) throws Exception {

		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

		Object[] args = invocation.getArgs();
		MappedStatement mappedStatement = (MappedStatement) args[0];

//		logger.info("▶▶ Query ID ▶▶ " + mappedStatement.getId());

		if (isDuplicated(stackTrace) ) {
			return invocation.proceed();
		}

		try {
			if (args[1] == null) {
				Map<String, String> map = new HashMap<String, String>();
				args[1] = map;
			}
			if (args[1] instanceof String) {

				BoundSql boundSql = mappedStatement.getBoundSql(null);
				List<ParameterMapping> paramMapping = boundSql.getParameterMappings();

				Map<String, String> map = new HashMap<String, String>();
				String key = "";
				for (ParameterMapping parameterMapping : paramMapping) {
					key = parameterMapping.getProperty();
					if ( !"".equals(key) && key != null ) {
						map.put(paramMapping.get(0).getProperty(), (String) args[1]);
					} else {
						map.put("value", (String) args[1]);
					}
				}

				args[1] = map;
			}
			if (args[1] instanceof Map) {
				Iterator<String> mapItor = ((Map<String, Object>) args[1]).keySet().iterator();
				while (mapItor.hasNext()) {

					String keyVal = mapItor.next();
					if (((Map<String, Object>) args[1]).get(keyVal) instanceof String) {

						String tmpVal = (String) ((Map<String, Object>) args[1]).get(keyVal);
						if (keyVal.trim().indexOf("DATE") > -1) {
							tmpVal = StringUtil.nullToEmptyString(tmpVal).replaceAll("/", "");
						}
					}
				}

				addSessionInfo((Map<String, Object>) args[1]);
			}
		} catch (Exception e) {
//			logger.info(e.getMessage());
		}
//		} catch (BzdException e) {
//			logger.info(e.getMessage());
//		}

        int resultCount = -1;
        long queryStartedTime = 0;
        long queryFinishedTime = 0;
        Object obj;

		try {
            queryStartedTime = System.currentTimeMillis();
            obj = invocation.proceed();
            queryFinishedTime = System.currentTimeMillis() - queryStartedTime;

            if (obj instanceof Integer) {
                resultCount = (Integer) obj;
            } else if (obj instanceof List) {
                resultCount = (((List) obj)).size();
            }
		} catch (Exception e) {
            throw e;
        } finally {
//            try {
//                sqlLogging(mappedStatement, args[1], queryFinishedTime, resultCount);
//            } catch (Exception e) {
//                logger.error(e.getMessage());
//            }
		}

		return obj;
	}

	private boolean isDuplicated(StackTraceElement[] stackTrace) {
		int intercepCount = 0;
		String interceptorClassName = this.getClass().getName();
		for (StackTraceElement e : stackTrace) {
			if (e.getClassName().equals(interceptorClassName)) {
				intercepCount++;
			}
		}
		return intercepCount != 1;
	}

	private void addSessionInfo(final Map<String, Object> map) throws Exception {
//		if (ClientHolder.getLoginInfo() == null) {
//			RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
//			if (requestAttributes != null) {
//				HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
//				HttpSession httpSession = request.getSession();
//				if (httpSession.isNew() || httpSession.getAttribute(Constant.SESSION_LOGIN_INFO) == null) {
//					LoginInfo loginInfo = new LoginInfo();
//					loginInfo.setGateCd(Config.getString("bzd.gateCd.default"));
//					loginInfo.setLangCd(Config.getString("bzd.langCd.default"));
//					map.put("ses", loginInfo);
//				} else {
//					LoginInfo loginInfo = (LoginInfo) httpSession.getAttribute(Constant.SESSION_LOGIN_INFO);
//					ClientHolder.clear();
//					ClientHolder.setLoginInfo(loginInfo);
//					map.put("ses", ClientHolder.getLoginInfo());
//				}
//			} else {
//				LoginInfo loginInfo = new LoginInfo();
//				loginInfo.setGateCd(Config.getString("bzd.gateCd.default"));
//				loginInfo.setLangCd(Config.getString("bzd.langCd.default"));
//				map.put("ses", loginInfo);
//			}
//		} else {
//			map.put("ses", ClientHolder.getLoginInfo());
//		}

//		map.put("_databaseId", "mysql");
//
//		map.put("defaultGateCd", Config.getString("defaultGateCd"));
//		map.put("defaultLangCd", Config.getString("defaultLangCd"));
//		map.put("systemDateTime", Config.getString("systemDateTime"));
//		map.put("gridDateFormat", Config.getString("gridDateFormat"));
//		map.put("dateToString", Config.getString("dateToString"));
//		map.put("timeToString", Config.getString("timeToString"));
//		map.put("dateTimeToString", Config.getString("dateTimeToString"));
//		map.put("dboStart", Config.getString("dboStart"));
//		map.put("dboEnd", Config.getString("dboEnd"));
//		map.put("mergeAs", Config.getString("mergeAs"));
//		map.put("nvl", Config.getString("nvl"));
//		map.put("length", Config.getString("length"));
//		map.put("substr", Config.getString("substr"));

	}

	@Override
	public Object plugin(final Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(final Properties properties) {
//		logger.info("Calling setProperties()");
	}

    @SuppressWarnings({"unchecked", "rawtypes"})
    private void sqlLogging(MappedStatement mappedStatement, Object parameters, long queryFinishedTime, int resultCount) {
//
//        if (!isSqlLogging(mappedStatement)) {
//            return;
//        }
//
//        if (Config.getBoolean("eversrm.sql.logging.personalInfo.encrypt", false)) {
//
//            String[] encryptCol = {"email", "telNum", "cellNum", "faxNum", "EMAIL", "TEL_NUM", "CELL_NUM", "FAX_NUM"};
//
//            if (parameters instanceof List) {
//                for (int i = 0; i < ((List) parameters).size(); i++) {
//                    Map t = (Map) ((List) parameters).get(i);
//                    for (Object key : t.keySet()) {
//                        if (key instanceof String) {
//                            for (int j = 0; j < encryptCol.length; j++) {
//                                if (((String) key).indexOf(encryptCol[j]) > -1) {
//                                    if (t.get(key) instanceof String) {
//                                        t.put(key, (((String) (t.get(key))).length() > 0 ? "******" : ""));
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            } else if (parameters instanceof Map) {
//                Map t = (Map) parameters;
//                for (Object key : t.keySet()) {
//                    if (key instanceof String) {
//                        for (int j = 0; j < encryptCol.length; j++) {
//                            if (((String) key).indexOf(encryptCol[j]) > -1) {
//                                if (t.get(key) instanceof String) {
//                                    t.put(key, (((String) (t.get(key))).length() > 0 ? "******" : ""));
//                                }
//                            }
//                        }
//                    }
//                }
//            } else if (parameters instanceof String) {
//                String tmpStr = (String) parameters;
//                if (tmpStr.indexOf("EMAIL") > -1) {
//                    tmpStr = tmpStr.length() > 0 ? "******" : "";
//                }
//                parameters = tmpStr;
//            }
//        }
//
//        BoundSql boundSql = mappedStatement.getBoundSql(parameters);
//        BzdSqlLogger sqlLogging = new BzdSqlLogger(mappedStatement.getId(), boundSql.getSql(), parameters);
//        sqlLogging.logging(boundSql.getParameterMappings(), queryFinishedTime, resultCount);
    }

    private boolean isSqlLogging(MappedStatement mappedStatement) {

//        try {
//            if (!Config.getBoolean("bzd.system.common.sql.logging", true)) {
//                if (mappedStatement.getId().indexOf("tcms") != -1) {
//                    return false;
//                }
//            }
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//        }
        return true;
    }
}
