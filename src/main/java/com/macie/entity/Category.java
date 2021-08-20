package com.macie.entity;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @author Macie
 * @date 2020/10/8 -18:32
 */
public class Category {
    private Integer categoryId;

    @NotBlank(message = "分类名称不能为空")
    @Length(max = 10, message = "分类名称不能超过10字符")
    private String categoryName;

    private Date createDate;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
