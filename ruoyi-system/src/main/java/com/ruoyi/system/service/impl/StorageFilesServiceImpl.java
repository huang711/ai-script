package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.StorageFilesMapper;
import com.ruoyi.system.domain.StorageFiles;
import com.ruoyi.system.service.IStorageFilesService;

/**
 * 物理文件Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-02-03
 */
@Service
public class StorageFilesServiceImpl implements IStorageFilesService 
{
    @Autowired
    private StorageFilesMapper storageFilesMapper;

    /**
     * 查询物理文件
     * 
     * @param id 物理文件主键
     * @return 物理文件
     */
    @Override
    public StorageFiles selectStorageFilesById(Long id)
    {
        return storageFilesMapper.selectStorageFilesById(id);
    }

    /**
     * 查询物理文件列表
     * 
     * @param storageFiles 物理文件
     * @return 物理文件
     */
    @Override
    public List<StorageFiles> selectStorageFilesList(StorageFiles storageFiles)
    {
        return storageFilesMapper.selectStorageFilesList(storageFiles);
    }

    /**
     * 新增物理文件
     * 
     * @param storageFiles 物理文件
     * @return 结果
     */
    @Override
    public int insertStorageFiles(StorageFiles storageFiles)
    {
        storageFiles.setCreateTime(DateUtils.getNowDate());
        return storageFilesMapper.insertStorageFiles(storageFiles);
    }

    /**
     * 修改物理文件
     * 
     * @param storageFiles 物理文件
     * @return 结果
     */
    @Override
    public int updateStorageFiles(StorageFiles storageFiles)
    {
        return storageFilesMapper.updateStorageFiles(storageFiles);
    }

    /**
     * 批量删除物理文件
     * 
     * @param ids 需要删除的物理文件主键
     * @return 结果
     */
    @Override
    public int deleteStorageFilesByIds(Long[] ids)
    {
        return storageFilesMapper.deleteStorageFilesByIds(ids);
    }

    /**
     * 删除物理文件信息
     * 
     * @param id 物理文件主键
     * @return 结果
     */
    @Override
    public int deleteStorageFilesById(Long id)
    {
        return storageFilesMapper.deleteStorageFilesById(id);
    }
}
