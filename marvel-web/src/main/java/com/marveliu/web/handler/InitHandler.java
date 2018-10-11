package com.marveliu.web.handler;

import com.marveliu.web.dao.entity.Resource;
import com.marveliu.web.dao.entity.Role;
import com.marveliu.web.domain.ao.SysConfig;
import com.marveliu.web.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author Marveliu
 * @Date 2018/7/18 8:59 PM
 **/

@Slf4j
@Component
public class InitHandler implements CommandLineRunner {

    @Autowired
    private SysConfig sysConfig;

    @Autowired
    private RoleService roleService;

    @Override
    public void run(String... args) throws Exception {
        log.info(String.format("%s initHandler start.", sysConfig.getAppName()));
        initUpload();
        initDb();

    }

    /**
     * 初始化上传路径
     *
     * @return
     */
    private boolean initUpload() {
        try {
            File dir = new File(sysConfig.getUploadPath());
            if (!dir.exists()) {
                dir.mkdir();
                log.info("初始化上传文件地址：" + dir.getAbsolutePath());
            }
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return false;
    }

    /**
     * 初始数据库
     *
     * @return
     */
    private boolean initDb() {
        try {
            if (ObjectUtils.isEmpty(roleService.getDAO().findDistinctByCode("role_guest"))) {
                Role role = new Role();
                role.setCode("role_guest");
                role.setName("访客角色");
                role.setAvailable(Boolean.TRUE);

                Set<Resource> resources = new HashSet<>();

                Resource resource = new Resource();
                resource.setUrl("/account/login");
                resource.setMethod("POST");
                resource.setCode("ACCOUNT_LOGIN");
                resource.setName("用户登录");
                resource.setType(2);
                resources.add(resource);

                Resource resource1 = new Resource();
                resource1.setUrl("/user/role/*");
                resource1.setMethod("GET");
                resource1.setCode("USER_ROLE_APPID");
                resource1.setName("获取对应用户角色");
                resource1.setType(2);
                resources.add(resource1);

                role.setResources(resources);
                roleService.save(role);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return false;
    }
}
