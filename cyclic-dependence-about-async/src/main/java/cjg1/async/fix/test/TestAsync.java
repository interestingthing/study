package cjg1.async.fix.test;

import cjg1.async.fix.bean.ServiceA;
import cjg1.async.fix.bean.ServiceB;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Map;

/**
 * @Author: chenjingang@guazi.com  2020-06-30 21:27
 */
@RestController
@Slf4j
public class TestAsync {

    @Resource
    ServiceB serviceB;
    @Resource
    ServiceA serviceA;

    @RequestMapping("test")
    public Map testAsync() {
        log.error("注入的对象:{},{}", serviceA, serviceB);
        log.warn("父线程:{}", Thread.currentThread().getId());
        serviceB.async();
        serviceA.async();

        return Collections.emptyMap();
    }

}
