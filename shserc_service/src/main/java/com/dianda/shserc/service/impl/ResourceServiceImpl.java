package com.dianda.shserc.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dianda.shserc.bean.ResourceSelectParams;
import com.dianda.shserc.common.Constant;
import com.dianda.shserc.dto.EditResourceDto;
import com.dianda.shserc.entity.ResourceOperation;
import com.dianda.shserc.dto.mappers.IEditResourceMapper;
import com.dianda.shserc.entity.Resource;
import com.dianda.shserc.exceptions.ExceptionType;
import com.dianda.shserc.exceptions.GlobalException;
import com.dianda.shserc.mapper.ResourceMapper;
import com.dianda.shserc.service.IResourceService;
import com.dianda.shserc.util.basic.ObjectUtil;
import com.dianda.shserc.util.basic.StringUtil;
import com.dianda.shserc.util.cache.dictionary.DictionaryCache;
import com.dianda.shserc.util.logger.system.SystemLog;
import com.dianda.shserc.vo.ResourceVo;
import com.dianda.shserc.vo.ResourceVoList;
import com.dianda.shserc.vo.mappers.IResourceVoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * resource service
 *
 * @author huachao
 * @since 2020-10-01
 */

@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements IResourceService {

	@javax.annotation.Resource
	ResourceMapper mapper;


	DictionaryCache cache;

	@Autowired
	public ResourceServiceImpl(DictionaryCache cache) {
		this.cache = cache;
		this.cache.setCacheFromService(0);
	}


	@Override
	public ResourceVoList find(ResourceSelectParams params) {
		QueryWrapper<Resource> wrapper = new QueryWrapper<>();
		long userId = params.getUserId();
		long deformityId = params.getDeformityId();
		long stateId = params.getStateId();
		long stageId = params.getStageId();
		long subjectId = params.getSubjectId();
		long importSourceId = params.getImportSourceId();
		long domainId = params.getDomainId();
		long gradeId = params.getGradeId();
		String phrase = params.getPhrase();

		// filter fields

		if (userId > 0) {
			wrapper = wrapper.eq("user_id", userId);
		}
		if (deformityId > 0) {
			wrapper = wrapper.eq("deformity_Id", deformityId);
		}
		if (stateId > 0) {
			wrapper = wrapper.eq("state_Id", stateId);
		}
		if (stageId > 0) {
			wrapper = wrapper.eq("stage_id", stageId);
		}
		if (subjectId > 0) {
			wrapper = wrapper.eq("subject_id", subjectId);
		}
		if (importSourceId > 0) {
			wrapper = wrapper.eq("import_source_id", importSourceId);
		}
		if (domainId > 0) {
			wrapper = wrapper.eq("domain_id", domainId);
		}
		if (gradeId > 0) {
			wrapper = wrapper.eq("grade_id", stageId);
		}
		if (!StringUtil.IsNullOrEmpty(phrase))
			wrapper = wrapper.like("title", phrase);

		// TODO: will implement later
		Map<String, String> orderPhrases = params.getOrderPhrases();
		if (!ObjectUtil.isNull(orderPhrases) && orderPhrases.size() > 0) {
			for (Map.Entry<String, String> entry : orderPhrases.entrySet()) {
				String diretion = entry.getValue();
				String orderPhrase = entry.getKey();
				if (diretion.equals(Constant.OrderDirection.ASC)) {
					wrapper = wrapper.orderByAsc(orderPhrase);
				} else if (diretion.equals(Constant.OrderDirection.DESC)) {
					wrapper = wrapper.orderByDesc(orderPhrase);
				}
			}
		}

		// get paged list data

		int current = params.getCurrent();
		int size = params.getSize();
		IPage<Resource> page = new Page<>(current, size);
		page = mapper.selectResources(page, wrapper);
		List<Resource> resources = page.getRecords();
		List<ResourceVo> resourceVoList = new ArrayList<>();

		// translate

		for (Resource res : resources) {
			Resource.dictTranslate(res, cache); // 翻译字典
			resourceVoList.add(IResourceVoMapper.INSTANCE.mapFrom(res));
		}

		// get vo list data

		ResourceVoList voList = new ResourceVoList();
		voList.setCurrent(current);
		voList.setSize(size);
		voList.setTotal(page.getTotal());

		if (!ObjectUtil.isNull(resources) && resources.size() > 0)
			voList.setListData(resourceVoList);

		return voList;
	}

	@Override
	public ResourceVo edit(EditResourceDto resourceDto) {
		if (ObjectUtil.isNull(resourceDto))
			return null;

		// execute user mapping from dto
		Resource resource = IEditResourceMapper.INSTANCE.mapFrom(resourceDto);
		if (resource.isNewOne()) {
			mapper.insert(resource);
		} else {
			mapper.updateById(resource);
		}

		ResourceVo vo = null; // ResourceVoMapper.INSTANCE.mapFrom ( resource );
		return vo;
	}

	@Override
	public ResourceVo getById(long id) {
		try {
			Resource resource = mapper.selectById(id);
			Resource.dictTranslate(resource, cache); // 翻译字典
			return IResourceVoMapper.INSTANCE.mapFrom(resource);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = GlobalException.class)
	@SystemLog()
	public boolean addViewCount(ResourceOperation dto) {
		if (ObjectUtil.isNull(dto))
			return false;

		Resource resource = mapper.selectById(dto.getResourceId());
		if (ObjectUtil.isNull(resource)) {
			return false;
		}

		resource.addViewCount();
		//mapper.updateById ( resource );
		//mapper.addViewCount ( dto );
		//throw new GlobalException ( ExceptionType.SERVER_ERROR ); // for unit test only
		return mapper.updateById(resource) + mapper.addViewCount(dto) == 2;
	}

	@Override
	public boolean addPraiseCount(ResourceOperation param) {
		return false;
	}

	@Override
	public boolean addDownloadCount(ResourceOperation param) {
		return false;
	}

}
