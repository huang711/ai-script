package com.ruoyi.assets.service;

import java.util.List;
import com.ruoyi.assets.domain.StorageFiles;

/**
 * 文件存储Service接口
 * 
 * @author ruoyi
 * @date 2026-02-10
 */
public interface IStorageFilesService 
{
    /**
     * 查询文件存储
     * 
     * @param id 文件存储主键
     * @return 文件存储
     */
    public StorageFiles selectStorageFilesById(Long id);

    /**
     * 查询文件存储列表
     * 
     * @param storageFiles 文件存储
     * @return 文件存储集合
     */
    public List<StorageFiles> selectStorageFilesList(StorageFiles storageFiles);

    /**
     * 新增文件存储
     * 
     * @param storageFiles 文件存储
     * @return 结果
     */
    public int insertStorageFiles(StorageFiles storageFiles);

    /**
     * 修改文件存储
     * 
     * @param storageFiles 文件存储
     * @return 结果
     */
    public int updateStorageFiles(StorageFiles storageFiles);

    /**
     * 批量删除文件存储
     * 
     * @param ids 需要删除的文件存储主键集合
     * @return 结果
     */
    public int deleteStorageFilesByIds(Long[] ids);

    /**
     * 删除文件存储信息
     * 
     * @param id 文件存储主键
     * @return 结果
     */
    public int deleteStorageFilesById(Long id);
}
