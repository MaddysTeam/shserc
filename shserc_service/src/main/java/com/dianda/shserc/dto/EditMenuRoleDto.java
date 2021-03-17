package com.dianda.shserc.dto;

import com.dianda.shserc.validators.NotNull;
import lombok.Data;

import java.util.Map;

@Data
public class EditMenuRoleDto {

  @NotNull
  private Map<Long,Long> menuRoleMap;

}
