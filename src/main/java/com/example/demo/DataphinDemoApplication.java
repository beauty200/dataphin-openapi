package com.example.demo;

import com.aliyuncs.dataphin.DataphinAcsClient;
import com.aliyuncs.dataphin.model.v20200830.quality.GetQualityRuleResponse;
import com.aliyuncs.dataphin.model.v20200830.quality.GetQualityTemplateResponse;
// import com.aliyuncs.dataphin.model.v20200830.metadata.ListTableColumnsResponse;
// import com.aliyuncs.exceptions.ClientException;
import java.text.SimpleDateFormat;
import java.util.List;
// import java.util.Date;
import java.util.Map;

public class DataphinDemoApplication {

    public static void main(String[] args) {
        DataphinAcsClient client = DataphinAcsClient.create(
                "dataphin",
                "dataphin-openapi.poc.lydaas.com",
                "kIEi7xLwOEAwBnWeEJ56HO9g",
                "kS4QFIyavNtw3WFHzHgI4qFrPegohu"
        );

        // RunTriggerNodeTest triggerNodeService = new RunTriggerNodeTest(client);
        // ListTableColumns listTableColumns = new ListTableColumns(client);
        ListTablesTest listTablesTest = new ListTablesTest(client);
        GetQualityRuleTest getQualityRuleTest = new GetQualityRuleTest(client);

        // String columnFilter = args.length > 0 ? args[0] : "id";

        try {
            // triggerNodeService.triggerNode();
            // List<ListTableColumnsResponse.Column> columns = listTableColumns.getPhysicalColumns(columnFilter);
            // System.out.println("列信息(" + columnFilter + "): " + columns);
            listTablesTest.listTables();
            GetQualityRuleResponse response = getQualityRuleTest.getQualityRule();
            
            System.out.println("========== 质量规则响应信息 ==========");
            System.out.println("响应码: " + response.getCode());
            System.out.println("请求ID: " + response.getRequestId());
            
            if (response.getData() != null) {
                GetQualityRuleResponse.QualityRuleDTO data = response.getData();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                
                System.out.println("\n========== 质量规则详细信息 ==========");
                
                // 基本信息
                System.out.println("【基本信息】");
                System.out.println("  租户ID: " + data.getTenantId());
                System.out.println("  规则ID: " + data.getRuleId());
                System.out.println("  规则名称: " + data.getRuleName());
                System.out.println("  规则描述: " + data.getRuleDescription());
                System.out.println("  规则状态: " + data.getRuleStatus());
                System.out.println("  规则强弱: " + data.getRuleStrength());
                
                // 分类信息
                System.out.println("\n【分类信息】");
                if (data.getCatalogs() != null && !data.getCatalogs().isEmpty()) {
                    System.out.println("  规则分类: " + data.getCatalogs());
                }
                if (data.getCatalogNames() != null && !data.getCatalogNames().isEmpty()) {
                    System.out.println("  分类名称: " + data.getCatalogNames());
                }
                
                // 模板信息
                System.out.println("\n【模板信息】");
                System.out.println("  模板类型: " + data.getTemplateType());
                System.out.println("  模板ID: " + data.getTemplateId());
                System.out.println("  模板名称: " + data.getTemplateName());
                System.out.println("  模板范围: " + data.getTemplateScope());
                
                // 监控信息
                System.out.println("\n【监控信息】");
                System.out.println("  规则所属监控ID: " + data.getWatchId());
                if (data.getWatch() != null) {
                    System.out.println("  监控对象: " + data.getWatch());
                }
                
                // 校验信息
                System.out.println("\n【校验信息】");
                System.out.println("  是否启动异常归档: " + data.getEnableErrorArchive());
                if (data.getValidateCondition() != null) {
                    System.out.println("  校验条件: " + data.getValidateCondition());
                }
                if (data.getValidateObject() != null) {
                    System.out.println("  校验对象: " + data.getValidateObject());
                }
                
                // 试运行信息
                System.out.println("\n【试运行信息】");
                System.out.println("  试运行规则任务ID: " + data.getTryRunRuleTaskId());
                System.out.println("  试运行规则任务状态: " + data.getTryRunRuleTaskStatus());
                System.out.println("  试运行规则校验结果: " + data.getTryRunRuleValidateResult());
                
                // 调度信息
                System.out.println("\n【调度信息】");
                if (data.getScheduleBindList() != null && !data.getScheduleBindList().isEmpty()) {
                    System.out.println("  调度绑定列表: " + data.getScheduleBindList());
                }
                if (data.getScheduleSettings() != null && !data.getScheduleSettings().isEmpty()) {
                    System.out.println("  调度设置: " + data.getScheduleSettings());
                }
                
                // 表单属性
                System.out.println("\n【表单属性】");
                if (data.getFormProperties() != null && !data.getFormProperties().isEmpty()) {
                    List<GetQualityTemplateResponse.QualityFormPropertyDTO> formProperties = data.getFormProperties();
                    System.out.println("  表单属性数量: " + formProperties.size());
                    for (int i = 0; i < formProperties.size(); i++) {
                        GetQualityTemplateResponse.QualityFormPropertyDTO property = formProperties.get(i);
                        System.out.println("  属性 " + (i + 1) + ":");
                        System.out.println("    组件类型: " + property.getComponentType());
                        System.out.println("    属性名称: " + property.getPropertyName());
                        System.out.println("    属性值: " + property.getPropertyValue());
                    }
                } else {
                    System.out.println("  表单属性列表: 空");
                }
                
                // 属性值映射
                System.out.println("\n【属性值映射】");
                if (data.getAttributeWithValuesMap() != null && !data.getAttributeWithValuesMap().isEmpty()) {
                    System.out.println("  属性值映射:");
                    for (Map.Entry<String, ?> entry : data.getAttributeWithValuesMap().entrySet()) {
                        System.out.println("    " + entry.getKey() + ": " + entry.getValue());
                    }
                }
                
                // 审计信息
                System.out.println("\n【审计信息】");
                System.out.println("  创建者: " + data.getCreator());
                System.out.println("  创建者名称: " + data.getCreatorName());
                if (data.getGmtCreate() != null) {
                    System.out.println("  创建时间: " + sdf.format(data.getGmtCreate()));
                }
                System.out.println("  修改者: " + data.getModifier());
                System.out.println("  修改者名称: " + data.getModifierName());
                if (data.getGmtModified() != null) {
                    System.out.println("  修改时间: " + sdf.format(data.getGmtModified()));
                }
            }
            System.out.println("=====================================");
        } catch (Exception e) {
            System.err.println("获取质量规则失败: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

