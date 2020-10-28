package cjg1;

import cjg1.async.fix.bean.ServiceA;
import cjg1.async.fix.bean.ServiceB;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author: chenjingang@guazi.com  2020-06-22 17:14
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
//@MyEnableAsync
@EnableAsync
@EnableTransactionManagement
@Slf4j
public class SpringBootStudyApplication {


    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(SpringBootStudyApplication.class, args);
        //print env profile info
        String[] activeProfiles = ctx.getEnvironment().getActiveProfiles();
        for (String profile : activeProfiles) {
            log.info("order-test starting by profile:  {} =============================== {} ====== {} ====== {}", profile, profile, profile, profile);
        }
        log.info("startup successfully");
        ServiceB beanB = ctx.getBean(ServiceB.class);
        System.out.println(beanB);
        System.out.println(beanB.getServiceA());
        ServiceA beanA = ctx.getBean(ServiceA.class);
        System.out.println(beanA);
        System.out.println(beanA.getServiceB());
    }



}
