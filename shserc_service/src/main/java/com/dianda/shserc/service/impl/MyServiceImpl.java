package com.dianda.shserc.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dianda.shserc.entity.ResComment;
import com.dianda.shserc.entity.ResUser;
import com.dianda.shserc.entity.ResourceOperation;
import com.dianda.shserc.mapper.ResCommentMapper;
import com.dianda.shserc.mapper.ResUserMapper;
import com.dianda.shserc.mapper.ResourceMapper;
import com.dianda.shserc.service.IMyService;
import com.dianda.shserc.vo.*;
import com.dianda.shserc.vo.mappers.ICommentVoMapperImpl;
import com.dianda.shserc.vo.mappers.IResourceOperationVoMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author huachao
 * @title MyServiceImpl
 * @Description 描述
 * @Date 2020/8/15 11:18
 * @Copyright 2019-2020
 */

@Service
@Validated
public class MyServiceImpl extends ServiceImpl<ResUserMapper, ResUser> implements IMyService {
	
	@Resource
	ResUserMapper mapper;
	
	@Resource
	ResourceMapper resourceMapper;
	
	@Resource
	ResCommentMapper commentMapper;
	
	@Override
	public ResUserVo findResourceOperationCount( long id ) {
		ResUserVo resUserVo = new ResUserVo ( );
		resUserVo.setId ( id );
		
		List<ResourceOperation> operationList = mapper.selectOperationCount ( id );
		if ( operationList.size ( ) > 0 ) {
			resUserVo.setViewCount ( operationList.get ( 0 ).getOperIntResult ( ) );
			resUserVo.setFavoriteCount ( operationList.get ( 1 ).getOperIntResult ( ) );
			resUserVo.setCommentCount ( operationList.get ( 2 ).getOperIntResult ( ) );
			resUserVo.setDownloadCount ( operationList.get ( 3 ).getOperIntResult ( ) );
		}
		
		return resUserVo;
	}
	
	@Override
	public ResourceOperationVoList findFavorites( long id ) {
		QueryWrapper<ResourceOperation> wrapper=new QueryWrapper<> (  );
		wrapper.eq ( "user_id", id );
		List<ResourceOperation> operationList= resourceMapper.selectFavorites (wrapper );
		List<ResourceOperationVo> operationVoList =new ArrayList<> (  );
		for(ResourceOperation resourceOperation : operationList){
			operationVoList.add ( IResourceOperationVoMapper.INSTANCE.mapFrom ( resourceOperation ));
		}
		
		ResourceOperationVoList resourceOperationVoList=new ResourceOperationVoList ();
		resourceOperationVoList.setListData ( operationVoList );
		
		
		return resourceOperationVoList;
	}
	
	@Override
	public ResourceOperationVoList findDownloads( long id ) {
		QueryWrapper<ResourceOperation> wrapper=new QueryWrapper<> (  );
		wrapper.eq ( "user_id", id );
		List<ResourceOperation> operationList= resourceMapper.selectDownloads (wrapper );
		List<ResourceOperationVo> operationVoList =new ArrayList<> (  );
		for(ResourceOperation resourceOperation : operationList){
			operationVoList.add ( IResourceOperationVoMapper.INSTANCE.mapFrom ( resourceOperation ));
		}
		
		ResourceOperationVoList resourceOperationVoList=new ResourceOperationVoList ();
		resourceOperationVoList.setListData ( operationVoList );
		
		return resourceOperationVoList;
	}
	
	@Override
	public CommentVoList findComments( long id ) {
		QueryWrapper<ResComment> wrapper=new QueryWrapper<> (  );
		wrapper.eq ("user_id", id   );
		List<ResComment> commentList=commentMapper.selectList (wrapper  );
		
		List<CommentVo> listData =new ArrayList<> (  );
		for(ResComment resComment : commentList){
			listData.add ( ICommentVoMapperImpl.INSTANCE.mapFrom ( resComment ));
		}
		
		CommentVoList  commentVoList=new CommentVoList ();
		commentVoList.setListData ( listData );
		
		return commentVoList;
	}
	
}
