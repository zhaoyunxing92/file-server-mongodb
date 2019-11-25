package com.sunny.mongodb.file.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author sunny
 * @class: com.sunny.mongodb.file.model.FileProcess
 * @date: 2018-05-22 10:44
 * @des:
 */
@Data
public class FileProcess implements Serializable {

    private static final long serialVersionUID = 1770399523395749814L;
    /**
     * 宽
     */
    private int width;
    /**
     * 高
     */
    private int height;

}
