package com.guigu.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Menu implements Serializable {
    private Integer id;

    private String name;

    private String linkUrl;

    private String path;

    private Integer priority;

    private String icon;

    private String description;

    private Integer parentMenuId;

    private Integer level;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl == null ? null : linkUrl.trim();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getParentMenuId() {
        return parentMenuId;
    }

    public void setParentMenuId(Integer parentMenuId) {
        this.parentMenuId = parentMenuId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Menu(Integer id, String name, String linkUrl, String path, Integer priority, String icon, String description, Integer parentMenuId, Integer level) {
        this.id = id;
        this.name = name;
        this.linkUrl = linkUrl;
        this.path = path;
        this.priority = priority;
        this.icon = icon;
        this.description = description;
        this.parentMenuId = parentMenuId;
        this.level = level;
    }

    public Menu() {
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", linkUrl='" + linkUrl + '\'' +
                ", path='" + path + '\'' +
                ", priority=" + priority +
                ", icon='" + icon + '\'' +
                ", description='" + description + '\'' +
                ", parentMenuId=" + parentMenuId +
                ", level=" + level +
                '}';
    }

    private Set<Role> roles = new HashSet<Role>(0);//角色集合
    private List<Menu> children = new ArrayList<>();//子菜单集合
    public Set<Role> getRoles() {
        return roles;
    }
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    public List<Menu> getChildren() {
        return children;
    }
    public void setChildren(List<Menu> children) {
        this.children = children;
    }
}