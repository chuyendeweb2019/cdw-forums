package vn.cdw.cdwforums.model;

import java.util.List;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonManagedReference;
public class SearchAjaxResponse {
	String msg;
	
	@JsonIgnore
	List<?> result;
	
	public String getMsg() {
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<?> getResult() {
		return result;
	}
	public void setResult(List<?> result) {
		this.result = result;
	}
	public SearchAjaxResponse(String msg, List<?> result) {
		super();
		this.msg = msg;
		this.result = result;
	}
	public SearchAjaxResponse() {
		super();
	}

    
}
