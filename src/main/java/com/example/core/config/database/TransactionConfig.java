package com.example.core.config.database;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.interceptor.MatchAlwaysTransactionAttributeSource;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class TransactionConfig {
    private static final String AOP_TRANSACTION_METHOD_NAME = "*";
    private static final String AOP_TRANSACTION_EXPRESSION = "execution(* com.example..service..*Service.*(..))";

    @Autowired
    private TransactionManager transactionManager;

    @Bean
    public TransactionInterceptor transactionAdvice() {
        List<RollbackRuleAttribute> rollbackRules = new ArrayList<RollbackRuleAttribute>();
        rollbackRules.add(new RollbackRuleAttribute(Exception.class));
        rollbackRules.add(new RollbackRuleAttribute(Error.class));

        RuleBasedTransactionAttribute transactionAttribute = new RuleBasedTransactionAttribute();
        transactionAttribute.setRollbackRules(rollbackRules);
        transactionAttribute.setName(AOP_TRANSACTION_METHOD_NAME);

        MatchAlwaysTransactionAttributeSource attributeSource = new MatchAlwaysTransactionAttributeSource();
        attributeSource.setTransactionAttribute(transactionAttribute);

        return new TransactionInterceptor(transactionManager, attributeSource);
    }

    @Bean
    public Advisor transactionAdviceAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(AOP_TRANSACTION_EXPRESSION);
        return new DefaultPointcutAdvisor(pointcut, transactionAdvice());
    }
}
