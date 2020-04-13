package service;

import org.apache.ibatis.session.SqlSession;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import dao.ParamMapper;
import pojo.Param;

public class ParamService {
	
	public JSONObject getNormalParams() {
		String[] keys = {"bet_price", "max_index", "base_price", "unit_price", "bet_type", "plan_base_price", "plan_unit_price"};
		return this.getParams(keys);
	}
	
	//获取指定key的参数
	public JSONObject getParamByKey(String key) {
		SqlSession sqlSession = SqlSessionSingleton.getSqlSession();
		JSONObject result = sqlSession.getMapper(ParamMapper.class).selectParamByKey(key).toJSON();
		sqlSession.close();
		return result;		
	}
	
	//批量获取参数
	public JSONObject getParams(String[] keys) {
		JSONObject result = new JSONObject();
		JSONArray temp = new JSONArray();
		SqlSession sqlSession = SqlSessionSingleton.getSqlSession();
		for (String key : keys) {
			temp.add(sqlSession.getMapper(ParamMapper.class).selectParamByKey(key).toJSON());
		}
		sqlSession.close();
		result.put("data", temp);
		return result;
	}
	
	//更新参数
	public void updateParam(Param param) {
		SqlSession sqlSession = SqlSessionSingleton.getSqlSession();
		sqlSession.getMapper(ParamMapper.class).updateParam(param);
		sqlSession.close();
	}
	
}
