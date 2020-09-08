package com.dianda.auth.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author huachao
 * @since 2020-08-17
 */
@Data
	@EqualsAndHashCode(callSuper = false)
public class ResCompany implements Serializable {

private static final long serialVersionUID=1L;

        private String companyName;

        private Long parentId;

        private Long addUser;

        private LocalDateTime addDate;

        private Long updateUser;

        private LocalDateTime updateDate;

        private Boolean isDeleted;


		}