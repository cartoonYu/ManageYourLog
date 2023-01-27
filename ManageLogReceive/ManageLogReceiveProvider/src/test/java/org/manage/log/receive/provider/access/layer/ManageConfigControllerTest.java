package org.manage.log.receive.provider.access.layer;

import com.google.common.collect.ImmutableList;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.manage.log.base.test.BaseTest;
import org.manage.log.common.model.config.constants.LogContentFormatType;
import org.manage.log.common.model.log.constants.LogRecordIndexSort;
import org.manage.log.common.model.log.constants.LogRecordSort;
import org.manage.log.common.model.log.constants.OperatorSort;
import org.manage.log.receive.facade.dto.config.execute.UploadLogContentFormatConfigDto;
import org.manage.log.receive.facade.dto.config.query.LogConfigDto;
import org.manage.log.receive.facade.dto.config.execute.UploadLogConfigDto;
import org.manage.log.receive.facade.dto.config.execute.UploadLogIndexConfigDto;

import java.util.List;

/**
 * @author cartoon
 * @since 2022/11/16 20:26
 */
public class ManageConfigControllerTest extends BaseTest {

    private static final String mockConfigName = "test";

    @Test
    @Order(1)
    public void testAdd(){
        UploadLogIndexConfigDto indexConfig = new UploadLogIndexConfigDto();
        indexConfig.setRuleName("test")
                .setLogRecordIndexSort(LogRecordIndexSort.ID.getSortDescription())
                .setValueIndexKey("userId")
                .setDescription("test");

        UploadLogContentFormatConfigDto contentFormatConfig = new UploadLogContentFormatConfigDto();
        contentFormatConfig.setRuleName("test")
                .setType(LogContentFormatType.REGULAR_EXPRESSION_MATCH.getType())
                .setValue("test")
                .setExecuteSequence(0L);

        UploadLogConfigDto uploadLogConfigDto = new UploadLogConfigDto();
        uploadLogConfigDto.setRuleName(mockConfigName)
                        .setOperatorSort(OperatorSort.USER.getSortDescription())
                                .setIndexConfigList(ImmutableList.of(indexConfig))
                                        .setLogRecordSort(LogRecordSort.OPERATE.getSortDescription())
                                            .setContentTemplate("test content template, userId: #{userId}")
                                                .setContentFormatConfigList(ImmutableList.of(contentFormatConfig))
                                                    .setDescription("test");
        Assertions.assertTrue(Boolean.parseBoolean(post("/config/add", uploadLogConfigDto)));
    }

    @Test
    @Order(1)
    public void testAddWithoutIndex(){

        UploadLogConfigDto uploadLogConfigDto = new UploadLogConfigDto();
        uploadLogConfigDto.setRuleName("testWithoutIndex")
                .setOperatorSort(OperatorSort.USER.getSortDescription())
                .setLogRecordSort(LogRecordSort.OPERATE.getSortDescription())
                .setContentTemplate("test content template")
                .setDescription("test");
        Assertions.assertTrue(Boolean.parseBoolean(post("/config/add", uploadLogConfigDto)));
    }

    @Test
    @Order(1)
    public void testAddWithoutContentFormat(){

        UploadLogIndexConfigDto indexConfig = new UploadLogIndexConfigDto();
        indexConfig.setRuleName("test1")
                .setLogRecordIndexSort(LogRecordIndexSort.ID.getSortDescription())
                .setValueIndexKey("userId")
                .setDescription("test");

        UploadLogConfigDto uploadLogConfigDto = new UploadLogConfigDto();
        uploadLogConfigDto.setRuleName("testWithoutContentFormat")
                .setOperatorSort(OperatorSort.USER.getSortDescription())
                .setIndexConfigList(ImmutableList.of(indexConfig))
                .setLogRecordSort(LogRecordSort.OPERATE.getSortDescription())
                .setContentTemplate("test content template, userId: #{userId}")
                .setDescription("test");
        Assertions.assertTrue(Boolean.parseBoolean(post("/config/add", uploadLogConfigDto)));
    }



    @Test
    @Order(2)
    public void testGetByName(){
        ImmutablePair<String, String> requestData = ImmutablePair.of("name", mockConfigName);
        try {
            LogConfigDto logConfigDto = get("/config/getByName", ImmutableList.of(requestData), LogConfigDto.class);
            baseAssert(logConfigDto);
        } catch (Exception e){
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    @Order(2)
    public void testGetAll(){
        try {
            List<LogConfigDto> logConfigDtoList = getList("/config/getAll", LogConfigDto.class);
            logConfigDtoList.forEach(this::baseAssert);
        } catch (Exception e){
            Assertions.fail(e.getMessage());
        }
    }

    private void baseAssert(LogConfigDto logConfigDto){
        Assertions.assertNotNull(logConfigDto.getRuleName());
        Assertions.assertNotNull(logConfigDto.getOperatorSort());
        Assertions.assertNotNull(logConfigDto.getIndexConfigList());
        Assertions.assertNotNull(logConfigDto.getLogRecordSort());
        Assertions.assertNotNull(logConfigDto.getDescription());
    }
}
