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
@Order(1)
@Slf4j
public class ServiceB {
    @Resource
//    @Lazy
    ServiceA serviceA;
//    @Resource
//    @Lazy
//    ServiceB serviceB;

    @Async
//    @Transactional
    public void async() {
        log.warn("B子线程:{}", Thread.currentThread().getId());
        serviceA.async();
    }

    public ServiceB() {
        System.out.println("BBBBBBBB");
    }

    public ServiceA getServiceA() {
        return serviceA;
    }
}
