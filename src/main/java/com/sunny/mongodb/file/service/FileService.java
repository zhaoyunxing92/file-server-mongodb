package com.sunny.mongodb.file.service;

import com.sunny.mongodb.file.model.File;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author sunny
 * @class: com.sunny.mongodb.file.service.FileService
 * @date: 2018-05-18 18:04
 * @des:
 */
public interface FileService {
    /**
     * 保存文件
     *
     * @param file
     * @return
     */
    File addFile(MultipartFile file);

    /**
     * 获取文件
     *
     * @param id
     * @return
     */
    File getFileById(String id);

    /**
     * 处理图片
     *
     * @param id
     * @param process
     * @return
     */
    File getProcessFileById(String id, String process);

    /**
     * 获取图片列表
     *
     * @param start
     * @param item
     * @return
     */
    List<File> getAll(Integer start, Integer item);

    /**
     * 根据id删除文件
     *
     * @param id
     */
    void removeFile(String id);
}
