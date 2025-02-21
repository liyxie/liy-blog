package com.liy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liy.domain.ResponseResult;
import com.liy.common.FieldConstants;
import com.liy.domain.entity.Photo;
import com.liy.mapper.PhotoMapper;
import com.liy.service.PhotoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liy.util.PageUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 照片 服务实现类
 * </p>
 *
 * @author CodeScholar
 * @date 2023年4月9日
 */
@Service
public class PhotoServiceImpl extends ServiceImpl<PhotoMapper, Photo> implements PhotoService {

    /**
     * 照片列表
     * @param albumId
     * @return
     */
    @Override
    public ResponseResult listPhoto(Integer albumId) {
        Page<Photo> photoPage = baseMapper.selectPage(new Page<>(PageUtils.getPageNo(), PageUtils.getPageSize()), new QueryWrapper<Photo>().eq(FieldConstants.ALBUM_ID, albumId));
        return ResponseResult.success(photoPage);
    }

    /**
     * 照片详情
     * @param id
     * @return
     */
    @Override
    public ResponseResult getPhotoById(Integer id) {
        Photo photo = baseMapper.selectById(id);
        return ResponseResult.success(photo);
    }

    /**
     * 添加照片
     * @param photo
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult insertAlbum(Photo photo) {
        int rows = baseMapper.insert(photo);
        return rows > 0 ? ResponseResult.success(): ResponseResult.error("添加照片失败");
    }

    /**
     * 修改照片
     * @param photo
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult updatePhoto(Photo photo) {
        int rows = baseMapper.updateById(photo);
        return rows > 0 ? ResponseResult.success(): ResponseResult.error("修改照片失败");
    }

    /**
     * 移动照片
     * @param map
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult movePhoto(Map<String,Object> map) {
        Integer albumId = (Integer) map.get("albumId");
        List<Integer> ids = (List<Integer>) map.get("ids");
        baseMapper.movePhoto(ids,albumId);
        return ResponseResult.success();
    }

    /**
     * 批量删除照片
     * @param ids
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult deleteBatch(List<Integer> ids) {
        int rows = baseMapper.deleteBatchIds(ids);
        return rows > 0 ? ResponseResult.success(): ResponseResult.error("删除照片失败");
    }
}
