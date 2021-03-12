package com.dianda.shserc.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dianda.shserc.bean.ResourceSelectParams;
import com.dianda.shserc.common.Constant;
import com.dianda.shserc.dto.EditResourceDto;
import com.dianda.shserc.entity.ResUser;
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
import com.dianda.shserc.validators.NotNull;
import com.dianda.shserc.vo.ResRealVo;
import com.dianda.shserc.vo.ResourceVo;
import com.dianda.shserc.vo.ResourceVoList;
import com.dianda.shserc.vo.mappers.IResourceVoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
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
@Validated
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
		Map<String, String> orderPhrases = params.getOrderPhrases();
		String phrase = params.getSearchPhrase();

		// filter fields

		wrapper = wrapper.eq("is_deleted", 0);
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
		if (!StringUtil.isNullOrEmpty(phrase))
			wrapper = wrapper.like("title", phrase);

		// order phrase
		if (!ObjectUtil.isNull(orderPhrases) && orderPhrases.size() > 0) {
			for (Map.Entry<String, String> entry : orderPhrases.entrySet()) {
				String direction = entry.getValue();
				String orderPhrase = entry.getKey();
				if (direction.equals(Constant.OrderDirection.ASC)) {
					wrapper = wrapper.orderByAsc(orderPhrase);
				} else if (direction.equals(Constant.OrderDirection.DESC)) {
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
	public boolean edit(@Valid @NotNull EditResourceDto resourceDto) {
		// execute user mapping from dto
		Resource resource = IEditResourceMapper.INSTANCE.mapFrom(resourceDto);
		ResourceVo resourceVo = findById(resource.getId());
		if (resource.isNewOne() && ObjectUtil.isNull(resourceVo)) {
			return mapper.insert(resource) > 0;
		} else {
			return mapper.updateById(resource) >= 0;
		}
	}

	@Override
	public ResourceVo findById(long id) {
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
	public boolean addViewCount(@Valid @NotNull ResourceOperation resourceOperation) {
		Resource resource = mapper.selectById(resourceOperation.getResourceId());
		if (ObjectUtil.isNull(resource)) {
			return false;
		}

		resource.addViewCount();
		return mapper.updateById(resource) + mapper.addView(resourceOperation) == 2;
	}

	@Override
	public boolean addDownloadCount(@Valid @NotNull ResourceOperation resourceOperation) {
		Resource resource = mapper.selectById(resourceOperation.getResourceId());
		if (ObjectUtil.isNull(resource)) {
			return false;
		}

		resource.addDownloadCount();
		return mapper.updateById(resource) + mapper.addDownload(resourceOperation) == 2;
	}

	@Override
	public boolean setEliteScore(@Valid @NotNull ResourceOperation resourceOperation) {
		return false;
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = GlobalException.class)
	@SystemLog
	public boolean setOrCancelFavorite(@Valid @NotNull ResourceOperation resourceOperation) {
		long userId = resourceOperation.getUserId();
		long resourceId = resourceOperation.getResourceId();

		Resource resource = mapper.selectById(resourceId);
		if (ObjectUtil.isNull(resource)) return false;

		QueryWrapper<ResourceOperation> wrapper = new QueryWrapper<>();
		wrapper.eq("resource_id", userId).eq("resource_id", resourceId);
		resourceOperation = mapper.selectFavorite(wrapper);
		if (ObjectUtil.isNull(resourceOperation)) {
			resource.addFavoriteCount();
			return mapper.updateById(resource) + mapper.addFavorite(resourceOperation) == 2;
		} else {
			resource.deleteFavoriteCount();
			return mapper.updateById(resource) + mapper.deleteFavorite(resourceOperation) == 2;
		}
	}

	@Override
	public boolean setStar(@Valid  @NotNull ResourceOperation resourceOperation) {
		long userId = resourceOperation.getUserId();
		long resourceId = resourceOperation.getResourceId();

		Resource resource = mapper.selectById(resourceId);
		if (ObjectUtil.isNull(resource)) return false;

		resource.addStarCount(resourceOperation.getOperIntResult());
		return mapper.updateById(resource) + mapper.addStar(resourceOperation) == 2;
	}

}
