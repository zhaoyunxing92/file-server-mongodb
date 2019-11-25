package com.sunny.mongodb.file.model;

import lombok.Data;
import org.bson.types.Binary;

import java.io.Serializable;
import java.util.Date;

/**
 * @author sunny
 * @date: 2018-05-18 18:06
 * @des: 文件
 */
@Data
public class File implements Serializable {
    private static final long serialVersionUID = 3718103350794331126L;
    /**
     * 主键id
     */
    private String id;
    /**
     * 文件名称
     */
    private String name;

    /**
     * 文件md5
     */
    private String md5;
    /**
     * 文件大小
     */
    private Integer size;
    /**
     * 文件内容
     */
    private Binary content;

    /**
     * 文件类型
     */
    private String contentType;
    /**
     * 上传时间
     */
    private Date uploadDate;
}
