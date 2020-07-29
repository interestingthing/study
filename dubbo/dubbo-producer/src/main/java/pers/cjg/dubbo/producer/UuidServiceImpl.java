package pers.cjg.dubbo.producer;

import com.alibaba.dubbo.config.annotation.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pers.cjg.dubbo.service.UuidService;

import java.util.UUID;

/**
 * @Author: chenjingang@guazi.com  2020-07-28 17:59
 */
@Component
@Service(interfaceClass = UuidService.class)
@Slf4j
public class UuidServiceImpl implements UuidService {

    @Override
    public String generateId() {

        log.error("dubbo-producer......");
        log.error("dubbo-producer......");
        log.error("dubbo-producer......");

        return UUID.randomUUID().toString();
    }
}
