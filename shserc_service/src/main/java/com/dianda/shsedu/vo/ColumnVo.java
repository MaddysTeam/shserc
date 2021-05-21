package com.dianda.shsedu.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ColumnVo implements Serializable {

	private long id;

	private long parentId;

    private String title;

    private boolean isMenu;

}
