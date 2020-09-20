package com.dianda.shserc.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author huachao
 * @since 2020-08-12
 */
@Data
	@EqualsAndHashCode(callSuper = false)
public class ResRole implements Serializable {

private static final long serialVersionUID=1L;

        private String roleName;

        private String description;


		}