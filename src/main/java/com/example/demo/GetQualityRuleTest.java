package com.example.demo;

import com.aliyuncs.dataphin.DataphinAcsClient;
import com.aliyuncs.dataphin.model.v20200830.quality.GetQualityRuleRequest;
import com.aliyuncs.dataphin.model.v20200830.quality.GetQualityRuleResponse;
import com.aliyuncs.exceptions.ClientException;
import org.junit.jupiter.api.Assertions;

public class GetQualityRuleTest {

    private static final Long TENANT_ID = 300023201L;
    // private static final String USER_ID = "1"; // 请根据实际情况修改

    private final DataphinAcsClient client;

    public GetQualityRuleTest(DataphinAcsClient client) {
        this.client = client;
    }

    public GetQualityRuleResponse getQualityRule() throws ClientException {
        GetQualityRuleRequest request = new GetQualityRuleRequest();

        request.setOpTenantId(TENANT_ID);
        // request.setOpUserId(USER_ID);
        request.setRuleId(5068005L);

        GetQualityRuleResponse response = client.getAcsResponse(request);

        Assertions.assertEquals(response.getCode(), "OK");
        Assertions.assertNotNull(response.getData());

        System.out.println("获取质量规则成功，规则ID: 5068005");
        return response;
    }
}

