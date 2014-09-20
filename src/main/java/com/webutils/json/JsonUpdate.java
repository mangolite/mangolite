package com.webutils.json;

import com.utils.JsonUtil;

import foodev.jsondiff.JacksonDiff;
/**
 * @author <a mailto:lalit.tanwar07@gmail.com> Lalit Tanwar</a>
 * @version 1.0
 * @lastModified Sep 3, 2014
 */
public class JsonUpdate {
	private JacksonDiff diff = new JacksonDiff();

	private Object origObject;
	private String origJson;

	public JsonUpdate(Object object) {
		this.origObject = object;
		this.origJson = JsonUtil.toJson(object);
	}

	public Object getOrigObject() {
		return origObject;
	}

	public void setOrigObject(Object origObject) {
		this.origObject = origObject;
		this.origJson = JsonUtil.toJson(this.origObject);
	}

	public String diff() {
		return diff.diff(origJson, JsonUtil.toJson(this.origObject));
	}
	
//	public JsonPatch diff(){
//		return diff2.diff(origJson, JsonUtil.toJson(this.origObject));
//	}

}
