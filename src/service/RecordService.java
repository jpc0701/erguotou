package service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.ibatis.session.SqlSession;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import dao.RecordMapper;

public class RecordService {
	
	//获取最近的一次投注情况
	public JSONObject getLstestStatus() {
		JSONObject result = new JSONObject();
		JSONObject records = new JSONObject();
		long querytime = new Date().getTime();
		int count = 1;
		while (count < 129) {
			querytime -= (count * 1800 * 1000);
			count *= 2;
			records = this.getRecordsToNow(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(querytime)));
			if (records.size() >= 1) 
				break;
		}
		if (records.size() < 1) {
			result.put("status", -2);
		} else {
			JSONObject first = records.getJSONArray("data").getJSONObject(0);
			result.put("data", first);
			int status = 0;
			if (first.getIntValue("price") != 0 && first.getIntValue("profit") > 0) {
				status = 1;
			}else if (first.getIntValue("price") != 0 && first.getIntValue("profit") < 0) {
				status = -1;
			}
			result.put("status", status);
		}
		return result;
	}
	
	//获取最近24小时内的数据
	public JSONObject get24hRecords() {
		return this.getRecordsToNow(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(new Date().getTime() - 24 * 3600 * 1000)));
	}
	
	//获取指定数量的数据
	public JSONObject getRecords(int limit) {
		JSONObject result = new JSONObject();
		SqlSession sqlSession = SqlSessionSingleton.getSqlSession();
		result.put("data", (JSONArray) JSON.toJSON(sqlSession.getMapper(RecordMapper.class).slectRecordsByLimit(limit)));
		sqlSession.close();
		return result;
	}
	
	//获取从指定时间到现在的数据
	public JSONObject getRecordsToNow(String startDate) {
		return this.getRecordsByDate(startDate, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	}
	
	//获取指定时间范围的数据
	public JSONObject getRecordsByDate(String startDate, String endDate) {
		JSONObject result = new JSONObject();
		SqlSession sqlSession = SqlSessionSingleton.getSqlSession();
		result.put("data", (JSONArray) JSON.toJSON(sqlSession.getMapper(RecordMapper.class).slectRecordsByDate(startDate, endDate)));
		sqlSession.close();
		return result;
	}
}
