package cjg1.async.fix.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: chenjingang@guazi.com  2020-06-23 17:11
 */
@Service
@Order(2)
@Slf4j
public class ServiceA {
    //    @Resource
//    ServiceA serviceA;
//    @Lazy
    @Resource
    ServiceB serviceB;

    @Async
//    @Transactional
    public void async() {
        log.warn("A子线程:{}", Thread.currentThread().getId());
    }

    public ServiceA() {
        System.out.println("AAAAAAAAA");

    }

    public ServiceB getServiceB() {
        return serviceB;
    }
}
