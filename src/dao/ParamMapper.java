package dao;

import pojo.Param;

public interface ParamMapper {
	public Param selectParamByKey(String key);
	public void updateParam(Param param);
}
