package pers.cjg.dubbo.consumer;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.cjg.dubbo.service.UuidService;

/**
 * @Author: chenjingang@guazi.com  2020-07-28 18:20
 */
@RestController
public class UuidController {

    @Reference
    private UuidService uuidService;

    /**
     * 测试日志trace
     * @return
     */
    @GetMapping("/uuid")
    public String uuid() {
        return uuidService.generateId();
    }
}
