package com.dianda.shserc.vo;

import com.dianda.shserc.entity.ResUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper=false)
public class ResUserVo extends BaseVo<ResUser>  implements Serializable {

}
