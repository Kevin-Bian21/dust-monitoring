package cn.bianwenkai.mapper;

import cn.bianwenkai.entity.DustEnvironment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author BianWenKai
 * @DATE 2022/5/9 - 18:17
 **/

@Mapper
public interface DustEnvDataMapper {

    List<DustEnvironment> GetEnvData(int start, int end);

    List<DustEnvironment> GetSingleMonitorLocalData(String monitorLocal);

}
