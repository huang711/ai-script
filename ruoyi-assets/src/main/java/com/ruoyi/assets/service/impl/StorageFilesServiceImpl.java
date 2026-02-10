package com.ruoyi.assets.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.assets.mapper.StorageFilesMapper;
import com.ruoyi.assets.domain.StorageFiles;
import com.ruoyi.assets.service.IStorageFilesService;

/**
 * 文件存储Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-02-10
 */
@Service
public class StorageFilesServiceImpl implements IStorageFilesService 
{
    @Autowired
    private StorageFilesMapper storageFilesMapper;

    /**
     * 查询文件存储
     * 
     * @param id 文件存储主键
     * @return 文件存储
     */
    @Override
    public StorageFiles selectStorageFilesById(Long id)
    {
        return storageFilesMapper.selectStorageFilesById(id);
    }

    /**
     * 查询文件存储列表
     * 
     * @param storageFiles 文件存储
     * @return 文件存储
     */
    @Override
    public List<StorageFiles> selectStorageFilesList(StorageFiles storageFiles)
    {
        return storageFilesMapper.selectStorageFilesList(storageFiles);
    }

    /**
     * 新增文件存储
     * 
     * @param storageFiles 文件存储
     * @return 结果
     */
    @Override
    public int insertStorageFiles(StorageFiles storageFiles)
    {
        storageFiles.setCreateTime(DateUtils.getNowDate());
        return storageFilesMapper.insertStorageFiles(storageFiles);
    }

    /**
     * 修改文件存储
     * 
     * @param storageFiles 文件存储
     * @return 结果
     */
    @Override
    public int updateStorageFiles(StorageFiles storageFiles)
    {
        return storageFilesMapper.updateStorageFiles(storageFiles);
    }

    /**
     * 批量删除文件存储
     * 
     * @param ids 需要删除的文件存储主键
     * @return 结果
     */
    @Override
    public int deleteStorageFilesByIds(Long[] ids)
    {
        return storageFilesMapper.deleteStorageFilesByIds(ids);
    }

    /**
     * 删除文件存储信息
     * 
     * @param id 文件存储主键
     * @return 结果
     */
    @Override
    public int deleteStorageFilesById(Long id)
    {
        return storageFilesMapper.deleteStorageFilesById(id);
    }
}
