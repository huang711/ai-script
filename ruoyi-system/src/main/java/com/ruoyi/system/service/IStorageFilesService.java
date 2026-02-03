package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.StorageFiles;

/**
 * 物理文件Service接口
 * 
 * @author ruoyi
 * @date 2026-02-03
 */
public interface IStorageFilesService 
{
    /**
     * 查询物理文件
     * 
     * @param id 物理文件主键
     * @return 物理文件
     */
    public StorageFiles selectStorageFilesById(Long id);

    /**
     * 查询物理文件列表
     * 
     * @param storageFiles 物理文件
     * @return 物理文件集合
     */
    public List<StorageFiles> selectStorageFilesList(StorageFiles storageFiles);

    /**
     * 新增物理文件
     * 
     * @param storageFiles 物理文件
     * @return 结果
     */
    public int insertStorageFiles(StorageFiles storageFiles);

    /**
     * 修改物理文件
     * 
     * @param storageFiles 物理文件
     * @return 结果
     */
    public int updateStorageFiles(StorageFiles storageFiles);

    /**
     * 批量删除物理文件
     * 
     * @param ids 需要删除的物理文件主键集合
     * @return 结果
     */
    public int deleteStorageFilesByIds(Long[] ids);

    /**
     * 删除物理文件信息
     * 
     * @param id 物理文件主键
     * @return 结果
     */
    public int deleteStorageFilesById(Long id);
}
