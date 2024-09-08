# ddd-code-generator

#### 介绍

一款基于DDD开发模式的工程框架及代码生成器

- ddd-generator是源自人人开源项目的代码生成器的改造升级
  DDD分层代码生成器，可在线生成entity、xml、dao、service、html、js、sql代码、可以直接运行项目框架，减少70%以上的开发任务

#### 本地部署

- 通过git下载源码
- 修改application.yml，更新MySQL账号和密码、数据库名称
- Eclipse、IDEA运行 DddCodeGeneratorApplication.java，则可启动项目
- 项目访问路径：http://localhost:8080/

#### DDD代码生成说明

使用本代码生成有以下规则必须遵守，不然生成的代码无法运行：

- 表的主键必须命名为id且为varchar类型：例子 `id` varchar(32) NOT NULL
- 有7个属性字段必须包含其中包含tenant_id、status、del_flag、created_by、created_time、updated_by、updated_time

除了以上的8个字段属性之外自己可以根据实际的情况来添加，建表实例：

```sql
DROP TABLE
    IF
    EXISTS `table`;
CREATE TABLE `table`
(
    `id`                VARCHAR(40) CHARACTER
                            SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
    `enabled_mark`      INT NULL DEFAULT NULL COMMENT '是否启用(0:未启用；1:启用)',
    `description`       VARCHAR(500) CHARACTER
                            SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
    `delete_mark`       INT NULL DEFAULT NULL COMMENT '删除标识(0:正常；1:删除)',
    `created_user`      VARCHAR(20) CHARACTER
                            SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人编码',
    `created_user_name` VARCHAR(50) CHARACTER
                            SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
    `created_time`      DATETIME NULL DEFAULT NULL COMMENT '创建时间',
    `updated_user`      VARCHAR(20) CHARACTER
                            SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人编码',
    `updated_user_name` VARCHAR(50) CHARACTER
                            SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
    `updated_time`      DATETIME NULL DEFAULT NULL COMMENT '修改时间',
    `delete_user`       VARCHAR(20) CHARACTER
                            SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '删除人编码',
    `delete_user_name`  VARCHAR(50) CHARACTER
                            SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '删除人',
    `delete_time`       DATETIME NULL DEFAULT NULL COMMENT '删除时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER 
SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '表的注释' ROW_FORMAT = Dynamic;

SET
FOREIGN_KEY_CHECKS = 1;
```

**DDD主子表代码生成说明**

使用主子表代码生成有以下规则必须遵守，不然生成的代码无法运行：

- （1）主子表生成的时候必须选择两个以上的表来进行生成
- （2）要在选择的表中选定一个主表
- （3）需要设置子表对于主表的外键属性名称，注意界面中的关联属性要和SQL中的外键属性一致
- （4）每一个子表中都必须包含该外键属性

具体的建表例子请参考sql/complex.sql[一个主表，五个子表的例子]
