package xyz.kbws.common.model;

import java.io.Serializable;

/**
 * @author kbws
 * @date 2024/3/10
 * @description: 用户
 */
public class User implements Serializable {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
