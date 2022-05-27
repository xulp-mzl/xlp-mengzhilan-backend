package com.mengzhilan.entity.menu;

import com.mengzhilan.base.MZBaseEntity;
import org.xlp.db.ddl.annotation.XLPIndex;
import org.xlp.db.ddl.type.IndexType;
import org.xlp.db.tableoption.annotation.XLPColumn;
import org.xlp.db.tableoption.annotation.XLPEntity;
import org.xlp.db.tableoption.xlpenum.DataType;
import org.xlp.javabean.annotation.Bean;
import org.xlp.javabean.annotation.FieldName;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by xlp on 2022/5/1
 *
 * 菜单条目信息
 */
@Bean
@XLPEntity(tableName = "mz_menu_item", descriptor = "菜单条目")
public class MenuItem extends MZBaseEntity {
    private static final long serialVersionUID = 123927590339952896L;

    /**
     * 默认根节点的parentId="-1"
     */
    public static final String DEFAULT_PARENT_ID = null;

    @FieldName
    @XLPIndex(indexType = IndexType.UNIQUE)
    @XLPColumn(columnName = "path", dataType = DataType.VARCHAR,
            length = 1000, descriptor = "菜单路由地址", maxLength = 1000)
    private String path;

    @FieldName
    @XLPColumn(columnName = "icon_type", dataType = DataType.VARCHAR,
            length = 50, descriptor = "图标类型", maxLength = 50)
    private String iconType;

    @FieldName
    @XLPColumn(columnName = "icon", dataType = DataType.VARCHAR,
            length = 200, descriptor = "图标", maxLength = 200)
    private String icon;

    @FieldName
    @XLPIndex(indexType = IndexType.UNIQUE)
    @XLPColumn(columnName = "title", dataType = DataType.VARCHAR,
            length = 50, descriptor = "菜单名称", maxLength = 50)
    private String title;

    @FieldName
    @XLPIndex
    @XLPColumn(columnName = "parentId", dataType = DataType.VARCHAR,
            length = 64, descriptor = "父菜单id", maxLength = 64)
    private String parentId;

    @FieldName
    @XLPColumn(columnName = "descriptor", dataType = DataType.VARCHAR,
            length = 255, descriptor = "菜单描述", maxLength = 255)
    private String descriptor;

    @FieldName
    @XLPColumn(columnName = "weight", dataType = DataType.INT, descriptor = "权重")
    private int weight;

    @FieldName
    private List<MenuItem> children = new ArrayList<>();

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getIconType() {
        return iconType;
    }

    public void setIconType(String iconType) {
        this.iconType = iconType;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(String descriptor) {
        this.descriptor = descriptor;
    }

    public List<MenuItem> getChildren() {
        return children;
    }

    public void setChildren(List<MenuItem> children) {
        this.children = children;
    }

    public void addChildren(MenuItem menuItem) {
        if (menuItem != null){
            children.add(menuItem);
        }
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "path='" + path + '\'' +
                ", iconType='" + iconType + '\'' +
                ", icon='" + icon + '\'' +
                ", title='" + title + '\'' +
                ", parentId='" + parentId + '\'' +
                ", descriptor='" + descriptor + '\'' +
                ", weight=" + weight +
                ", children=" + children +
                "} " + super.toString();
    }
}
