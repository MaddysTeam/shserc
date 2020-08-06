package com.dianda.resource.entity;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author huachao
 * @since 2020-08-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ResourceInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableField("id")
	private String id;

	@TableField("resourceName")
	private String resourceName;

	@TableField("addUserId")
	private String addUserId;

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getAddUserId() {
		return addUserId;
	}

	public void setAddUserId(String addUserId) {
		this.addUserId = addUserId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}