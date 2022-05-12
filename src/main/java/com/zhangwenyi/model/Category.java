package com.zhangwenyi.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Category {
    private Integer CategoryId;
    private String CategoryName;
    private String Description;
    private Boolean Active;

    public Category() {}

    public Category(String categoryName) {
        CategoryName = categoryName;
    }

    public Category(String categoryName, String description, Boolean active) {
        CategoryName = categoryName;
        Description = description;
        Active = active;
    }

    public static List<Category> findAllCategory(Connection con) {
        List<Category> categories = new ArrayList<>();
        String queryString = "select * from category";
        try {
            PreparedStatement statement = con.prepareStatement(queryString);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Category category = new Category();
                category.setCategoryId(resultSet.getInt("CategoryId"));
                category.setCategoryName(resultSet.getString("CategoryName"));
                category.setDescription(resultSet.getString("Description"));
                categories.add(category);
            }
        } catch (SQLException throwAbles) {
            throwAbles.printStackTrace();
        }
        return categories;
    }

    public static String findByCategory(Connection con, int categoryId){
        String categoryName = null;
        try {
            PreparedStatement statement = con.prepareStatement("select CategoryName from category where CategoryId == ?");
            statement.setInt(1, categoryId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                categoryName = resultSet.getString("CategoryName");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return categoryName;
    }

    public Integer getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(Integer categoryId) {
        CategoryId = categoryId;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Boolean getActive() {
        return Active;
    }

    public void setActive(Boolean active) {
        Active = active;
    }
}
