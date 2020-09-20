package com.dianda.shserc.util.mapper;

import org.mapstruct.InheritConfiguration;

import java.util.List;

public interface BasicMapper<Source, Target> {
	@InheritConfiguration
	Source mapFrom( Target target );
	
	@InheritConfiguration
	Target mapTo( Source source );
	
	@InheritConfiguration
	List<Source> mapFrom2( List<Target> targets );
	
	@InheritConfiguration
	List<Target> mapTo( List<Source> sources );
}
