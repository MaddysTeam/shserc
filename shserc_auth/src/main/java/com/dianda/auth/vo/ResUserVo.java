package com.dianda.auth.vo;

import com.dianda.auth.entity.ResUser;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class ResUserVo implements Serializable {
	 Integer current;
	 Integer size;
	 Long total;
	 List<ResUser> userList;
}
