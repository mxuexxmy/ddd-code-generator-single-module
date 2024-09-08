package com.mxue.generator.single.utils;

import com.mxue.generator.single.controller.SysGeneratorController;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Zhang Liqiang
 * @email 18945085165@163.com
 * @date 2021/11/30
 * @description:
 **/
public class GenUtilsCommon {


    /**
     * 列名转换成Java属性名
     */
    public static String columnToJava(String columnName) {
        return WordUtils.capitalizeFully(columnName, new char[]{'_'}).replace("_", "");
    }

    /**
     * 表名转换成Java类名
     */
    public static String tableToJava(String tableName, String[] tablePrefixArray) {
        if (null != tablePrefixArray && tablePrefixArray.length > 0) {
            for (String tablePrefix : tablePrefixArray) {
                if (tableName.startsWith(tablePrefix)) {
                    tableName = tableName.replaceFirst(tablePrefix, "");
                }
            }
        }
        return columnToJava(tableName);
    }

    /**
     * 获取配置信息
     */
    public static Configuration getConfig() {
        try {
            Configuration config = new PropertiesConfiguration("generator.properties");
            if (!com.mysql.cj.util.StringUtils.isNullOrEmpty(SysGeneratorController.packageName)) {
                config.setProperty("package", SysGeneratorController.packageName);
            }
            if (!com.mysql.cj.util.StringUtils.isNullOrEmpty(SysGeneratorController.moduleName)) {
                config.setProperty("moduleName", SysGeneratorController.moduleName);
            }
            if (!com.mysql.cj.util.StringUtils.isNullOrEmpty(SysGeneratorController.tablePrefix)) {
                config.setProperty("tablePrefix", SysGeneratorController.tablePrefix);
            }
            return config;
        } catch (ConfigurationException e) {
            throw new AdminException("获取配置文件失败，", e);
        }
    }

    public static List<String> getTemplates(boolean isComplex, boolean isFirst) {
        List<String> templates = new ArrayList<String>();
        templates.add("template/Command.java.vm");
        templates.add("template/Controller.java.vm");
        templates.add("template/Converter.java.vm");
        templates.add("template/DO.java.vm");
        templates.add("template/DTO.java.vm");
        templates.add("template/DTOAssembler.java.vm");
        templates.add("template/Entity.java.vm");
        templates.add("template/EntityId.java.vm");
        templates.add("template/Factory.java.vm");
        templates.add("template/Mapper.java.vm");
        templates.add("template/Mapper.xml.vm");
        templates.add("template/Repository.java.vm");
        templates.add("template/RepositoryImpl.java.vm");
        templates.add("template/Service.java.vm");
        templates.add("template/ServiceFacade.java.vm");
        templates.add("template/ServiceFacadeImpl.java.vm");
        templates.add("template/ServiceImpl.java.vm");
        templates.add("template/UpdateSpecification.java.vm");

        templates.add("template/project/pom/parent/pom.xml.vm");
        templates.add("template/project/pom/children/starter/pom.xml.vm");
        templates.add("template/project/pom/children/interfaces/pom.xml.vm");
        templates.add("template/project/pom/children/infrastructure/pom.xml.vm");
        templates.add("template/project/pom/children/domain/pom.xml.vm");
        templates.add("template/project/pom/children/application/pom.xml.vm");

        templates.add("template/project/application.yml.vm");
        templates.add("template/project/application-dev.yml.vm");
        templates.add("template/project/ApplicationRunner.java.vm");
        templates.add("template/project/logback-spring.xml.vm");

        templates.add("template/AbsEntity.java.vm");
        templates.add("template/AbstractSpecification.java.vm");
        templates.add("template/BaseDO.java.vm");
        templates.add("template/Specification.java.vm");
        templates.add("template/StatusEnum.java.vm");
        templates.add("template/AbstractController.java.vm");
        templates.add("template/Result.java.vm");
        templates.add("template/Page.java.vm");


        templates.add("template/AndSpecification.java.vm");
        templates.add("template/NotSpecification.java.vm");
        templates.add("template/OrSpecification.java.vm");
        templates.add("template/ValueObject.java.vm");


        templates.add("template/utils/validator/group/AddGroup.java.vm");
        templates.add("template/utils/validator/group/Group.java.vm");
        templates.add("template/utils/validator/group/UpdateGroup.java.vm");
        templates.add("template/utils/validator/Assert.java.vm");
        templates.add("template/utils/validator/ValidatorUtils.java.vm");

        templates.add("template/utils/exception/AppException.java.vm");
        templates.add("template/utils/exception/AppExceptionHandler.java.vm");
        templates.add("template/PageConverter.java.vm");
        templates.add("template/Query.java.vm");

        templates.add("template/utils/xss/SQLFilter.java.vm");
        templates.add("template/CommonConstant.java.vm");
        templates.add("template/SwaggerConfig.java.vm");

        return templates;
    }


    /**
     * 获取文件名
     */
    public static String getFileName(String template, String className, String packageName, String moduleName, String fatherName) {
        String packagePath = "main" + File.separator + "java" + File.separator;
        if (StringUtils.isNotBlank(packageName)) {
            packagePath += packageName.replace(".", File.separator) + File.separator + moduleName + File.separator;
        }
        if (template.contains("Command.java.vm")) {
            return packagePath
                    + "interfaces" + File.separator
                    + "web" + File.separator
                    + "command" + File.separator
                    + className + "Command.java";
        }
        if (template.contains("template/Controller.java.vm")) {
            return packagePath
                    + "interfaces" + File.separator
                    + "web" + File.separator
                    + className + "Controller.java";
        }
        if (template.contains("template/Converter.java.vm")) {
            return packagePath
                    + "infrastructure" + File.separator
                    + "persistence" + File.separator
                    + "mybatis" + File.separator
                    + "converter" + File.separator
                    + className + "Converter.java";
        }
        if (template.contains("template/DO.java.vm")) {
            return packagePath
                    + "infrastructure" + File.separator
                    + "persistence" + File.separator
                    + "mybatis" + File.separator
                    + "entity" + File.separator
                    + className + "DO.java";
        }
        if (template.contains("DTO.java.vm")) {
            return packagePath
                    + "interfaces" + File.separator
                    + "facade" + File.separator
                    + "dto" + File.separator
                    + className + "DTO.java";
        }
        if (template.contains("DTOAssembler.java.vm")) {
            return packagePath
                    + "interfaces" + File.separator
                    + "facade" + File.separator
                    + "assembler" + File.separator
                    + className + "DTOAssembler.java";
        }
        if (template.contains("template/Entity.java.vm")) {
            return packagePath
                    + "domain" + File.separator
                    + "model" + File.separator
                    + "entity" + File.separator
                    + className + ".java";
        }
        if (template.contains("EntityId.java.vm")) {
            return packagePath
                    + "domain" + File.separator
                    + "model" + File.separator
                    + "types" + File.separator
                    + className + "Id.java";
        }
        if (template.contains("Factory.java.vm")) {
            return packagePath
                    + "domain" + File.separator
                    + "factory" + File.separator
                    + className + "Factory.java";
        }
        if (template.contains("Mapper.java.vm")) {
            return packagePath
                    + "infrastructure" + File.separator
                    + "persistence" + File.separator
                    + "mybatis" + File.separator
                    + "mapper" + File.separator
                    + className + "Mapper.java";
        }
        if (template.contains("Mapper.xml.vm")) {
            return packagePath
                    + "infrastructure" + File.separator
                    + "persistence" + File.separator
                    + "mybatis" + File.separator
                    + "mapper" + File.separator
                    + "xml" + File.separator
                    + className + "Mapper.xml";
        }
        if (template.contains("Repository.java.vm")) {
            return packagePath
                    + "domain" + File.separator
                    + "repository" + File.separator
                    + className + "Repository.java";
        }
        if (template.contains("RepositoryImpl.java.vm")) {
            return packagePath
                    + "infrastructure" + File.separator
                    + "persistence" + File.separator
                    + "mybatis" + File.separator
                    + "repository" + File.separator
                    + "impl" + File.separator
                    + className + "RepositoryImpl.java";
        }
        if (template.contains("Service.java.vm")) {
            return packagePath
                    + "application" + File.separator
                    + className + "Service.java";
        }
        if (template.contains("ServiceFacade.java.vm")) {
            return packagePath
                    + "interfaces" + File.separator
                    + "facade" + File.separator
                    + className + "ServiceFacade.java";
        }
        if (template.contains("ServiceFacadeImpl.java.vm")) {
            return packagePath
                    + "interfaces" + File.separator
                    + "facade" + File.separator
                    + "impl" + File.separator
                    + className + "ServiceFacadeImpl.java";
        }
        if (template.contains("ServiceImpl.java.vm")) {
            return packagePath
                    + "application" + File.separator
                    + "impl" + File.separator
                    + className + "ServiceImpl.java";
        }
        if (template.contains("UpdateSpecification.java.vm")) {
            return packagePath
                    + "domain" + File.separator
                    + "specification" + File.separator
                    + className + "UpdateSpecification.java";
        }

        if (template.contains("ServiceFacadeAll.java.vm")) {
            return packagePath
                    + "interfaces" + File.separator
                    + "facade" + File.separator
                    + className + "AllServiceFacade.java";
        }

        if (template.contains("CommandAll.java.vm")) {
            return packagePath
                    + "interfaces" + File.separator
                    + "web" + File.separator
                    + "command" + File.separator
                    + className + "AllCommand.java";
        }

        if (template.contains("ServiceFacadeImplAll.java.vm")) {
            return packagePath
                    + "interfaces" + File.separator
                    + "facade" + File.separator
                    + "impl" + File.separator
                    + className + "AllServiceFacadeImpl.java";
        }

        return null;
    }

    /**
     * 自动部署文件
     */
    public static void genetatorAuto(String fileName, StringWriter sw, boolean sqlAuto) throws Exception {
        String absolutePath = null;
        if (fileName.endsWith("java") || fileName.endsWith("xml")) {
//            ### DDD各模块生成的代码的base路径
            if (fileName.indexOf("/interfaces/") >= 0 || fileName.indexOf("\\interfaces\\") >= 0) {
                absolutePath = SysGeneratorController.interfacesPath + "/src/" + fileName;
            } else if (fileName.indexOf("/application/") >= 0 || fileName.indexOf("\\application\\") >= 0) {
                absolutePath = SysGeneratorController.applicationPath + "/src/" + fileName;
            } else if (fileName.indexOf("/domain/") >= 0 || fileName.indexOf("\\domain\\") >= 0) {
                absolutePath = SysGeneratorController.domainPath + "/src/" + fileName;
            } else if (fileName.indexOf("/infrastructure/") >= 0 || fileName.indexOf("\\infrastructure\\") >= 0) {
                absolutePath = SysGeneratorController.infrastructurePath + "/src/" + fileName;
            } else {
                absolutePath = SysGeneratorController.adminUrl + "/src/" + fileName;
            }

        }

        File file = new File(absolutePath);
        // 1、代码清理
        if (file.exists()) {
            file.delete();
        }

        final File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            // 2、创建父目录
            parentFile.mkdirs();
        }
        file.createNewFile();

        // 3、代码生成
        OutputStream out = new FileOutputStream(file);
        IOUtils.write(sw.toString(), out, "UTF-8");
        IOUtils.closeQuietly(sw);
        IOUtils.closeQuietly(out);
    }
}
