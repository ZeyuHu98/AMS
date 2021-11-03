package services.support;

import java.util.List;
import java.util.Map;

public interface BaseServices {
	
	void setDto(Map<String,Object> dto);
	
	default Map<String,String> findByID(){
		return null;
	}
	
	default List<Map<String,String>> query() throws Exception{
		return null;
	}
	
	default Map<String,String> ueryByIDq() throws Exception{
		return null;
	}
}
