package cn.bianwenkai.mapper;

import cn.bianwenkai.entity.DustEnvironment;
import cn.bianwenkai.entity.WarningData;
import cn.bianwenkai.vo.CommonVo;
import cn.bianwenkai.vo.MonitorDataVo;
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

    List<MonitorDataVo> AllMonitorData(CommonVo commonVo);

    //记录预警的数据
    int EarlyWarningRecord(WarningData warningData);

    //统计预警数据
    int NumberOfEarlyWarningRecord();

}
