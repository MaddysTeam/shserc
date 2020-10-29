package com.dianda.shserc.entity;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import com.dianda.shserc.util.cache.dictionary.DictionaryCache;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * dictionary  entity
 *
 * @author huachao
 * @since 2020-10-05
 */
@Data
@EqualsAndHashCode( callSuper = false )
public class Dictionary implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private long id;
	
	private String name;
	
	@TableField( "parentId" )
	private Long parentId;
	
	private String description;
	
	private String code;
	
	private Long sort;
	
	private String value;
	
	public static void dictTranslate( Dictionary dic , DictionaryCache cache ) {
	}
	
}