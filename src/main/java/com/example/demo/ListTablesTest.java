package com.example.demo;

import com.aliyuncs.dataphin.DataphinAcsClient;
import com.aliyuncs.dataphin.model.v20200830.metadata.ListTablesRequest;
import com.aliyuncs.dataphin.model.v20200830.metadata.ListTablesResponse;
import com.aliyuncs.exceptions.ClientException;
import org.junit.jupiter.api.Assertions;

public class ListTablesTest {

    private final DataphinAcsClient acsClient;

    public ListTablesTest(DataphinAcsClient acsClient) {
        this.acsClient = acsClient;
    }

    public void listTables() throws ClientException {
        ListTablesRequest listTableRequest = new ListTablesRequest();

        ListTablesRequest.TableQuery tableQuery = new ListTablesRequest.TableQuery();
        tableQuery.setEnv("PROD");
        tableQuery.setNeedPartitions(true);
        tableQuery.setNeedColumns(true);
        tableQuery.setMetaTableType("PHYSICAL_TABLE");

        ListTablesRequest.PaginationCriteria paginationCriteria = new ListTablesRequest.PaginationCriteria();
        paginationCriteria.setPageSize(10);
        paginationCriteria.setPage(1);

        tableQuery.setPaginationCriteria(paginationCriteria);
        listTableRequest.setTableQuery(tableQuery);

        ListTablesResponse listTableResponse = acsClient.getAcsResponse(listTableRequest);

        Assertions.assertNotNull(listTableResponse.getData());

        if (listTableResponse.getData() != null 
                && listTableResponse.getData().getData() != null 
                && listTableResponse.getData().getData().size() > 0) {
            Assertions.assertNotNull(listTableResponse.getData().getData().get(0).getName());
            System.out.println("表名: " + listTableResponse.getData().getData().get(0).getName());
        }
    }
}

