package com.example.demo;
import com.aliyuncs.dataphin.DataphinAcsClient;
import com.aliyuncs.dataphin.model.v20200830.metadata.ListTableColumnsRequest;
import com.aliyuncs.dataphin.model.v20200830.metadata.ListTableColumnsResponse;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ListTableColumns {
    private final DataphinAcsClient acsClient;

    public ListTableColumns(DataphinAcsClient acsClient) {
        this.acsClient = Objects.requireNonNull(acsClient, "acsClient 未初始化");
    }

    /**
     * 测试获取物理表的列信息
     */
    public List<ListTableColumnsResponse.Column> getPhysicalColumns(String columnNameFilter) throws Exception {
        ListTableColumnsRequest tableRequest = new ListTableColumnsRequest();
        tableRequest.setOpTenantId(300023201L);
        tableRequest.setTableName("m_prv_dataphin_vdm_node_dev");
        ListTableColumnsRequest.ColumnQuery columnQuery = new ListTableColumnsRequest.ColumnQuery();
        columnQuery.setEnv("PROD");
        columnQuery.setProjectId(7155117168639360L);
        tableRequest.setTableQuery(columnQuery);

        ListTableColumnsResponse queryTableResponse = acsClient.getAcsResponse(tableRequest);
        Objects.requireNonNull(queryTableResponse.getData(), "列信息为空");
        List<ListTableColumnsResponse.Column> columns = queryTableResponse.getData();
        if (columnNameFilter == null || columnNameFilter.trim().isEmpty()) {
            return columns;
        }
        return columns.stream()
                .filter(column -> columnNameFilter.equals(column.getName()))
                .collect(Collectors.toList());
    }
}

