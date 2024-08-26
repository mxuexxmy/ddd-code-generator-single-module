package com.mxue.generator.single.controller;

import com.mxue.generator.single.service.SysGeneratorService;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Zhang Liqiang
 * @email 18945085165@163.com
 * @date 2021/11/30
 * @description:
 **/
@RestController
@RequestMapping("/pro")
public class ProjectGeneratorController {

    @Autowired
    private SysGeneratorService sysGeneratorService;

    @RequestMapping("/generator")
    public void generator(String groupId,
                          String artifact,
                          String version,
                          String packageName,
                          String springBootVersion,
                          HttpServletResponse response) throws IOException {
        if (StringUtils.isBlank(groupId)) {
            groupId = "com.mxue.generator.single";
        }
        if (StringUtils.isBlank(artifact)) {
            artifact = "generator-single";
        }
        if (StringUtils.isBlank(version)) {
            version = "1.0.0";
        }
        if (StringUtils.isBlank(packageName)) {
            packageName = "com.mxue.generator.single";
        }
        if (StringUtils.isBlank(springBootVersion)) {
            springBootVersion = "2.5.6";
        }
        byte[] data = sysGeneratorService.generatorCode3(groupId,
                artifact,
                version,
                packageName,
                springBootVersion);
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"code.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(data, response.getOutputStream());
    }
}
