package com.example.demo;

import com.aliyuncs.dataphin.DataphinAcsClient;
import com.aliyuncs.dataphin.model.v20200830.sop.node.RunTriggerNodeRequest;
import com.aliyuncs.dataphin.model.v20200830.sop.node.RunTriggerNodeResponse;
import com.aliyuncs.exceptions.ClientException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RunTriggerNodeTest {

    private final DataphinAcsClient client;

    public RunTriggerNodeTest(DataphinAcsClient client) {
        this.client = client;
    }

    public void triggerNode() throws ClientException {
        RunTriggerNodeRequest runTriggerNodeRequest = new RunTriggerNodeRequest();

        runTriggerNodeRequest.setNodeId("n_7002350646032990208");

        LocalDate yesterday = LocalDate.now().minusDays(1);
        String bizDate = yesterday.format(DateTimeFormatter.ISO_LOCAL_DATE);
        runTriggerNodeRequest.setBizDate(bizDate);

        runTriggerNodeRequest.setProjectId(6865331514222208L);
        runTriggerNodeRequest.setEnv("PROD");
        runTriggerNodeRequest.setOpTenantId(300023201L);

        RunTriggerNodeResponse runTriggerNodeResponse = client.getAcsResponse(runTriggerNodeRequest);
        System.out.println("触发节点返回码: " + runTriggerNodeResponse.getCode());
    }
}