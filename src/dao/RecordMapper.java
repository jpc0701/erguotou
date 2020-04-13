package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pojo.Record;

public interface RecordMapper {
	//获取指定日期范围的记录
	public List<Record> slectRecordsByDate(@Param("startDate")String startDate, @Param("endDate")String endDate);
	//获取指定数量的最新记录
	public List<Record> slectRecordsByLimit(int limit);
}
