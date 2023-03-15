package com.generate.entity;

import java.io.Serializable;

/**
 * (Test)实体类
 *
 * @author makejava
 * @since 2021-07-01 10:16:57
 */
public class Test implements Serializable {
    private static final long serialVersionUID = -65620507700170182L;

    private Integer id;

    private Integer bl;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBl() {
        return bl;
    }

    public void setBl(Integer bl) {
        this.bl = bl;
    }

}
