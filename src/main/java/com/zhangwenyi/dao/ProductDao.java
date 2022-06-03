package com.zhangwenyi.dao;

import com.zhangwenyi.model.Product;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao implements IProductDao {
    @Override
    public int save(Product product, InputStream picture, Connection con) throws SQLException {
        int n = 0;
        String sql = "insert into product(ProductName,ProductDescription,Picture,price,CategoryID) values(?,?,?,?,?)";
        PreparedStatement pt = con.prepareStatement(sql);
        pt.setString(1, product.getProductName());
        pt.setString(2, product.getProductDescription());
        if (picture != null) {
            //for sql server
            // pt.setBinaryStream(3, product.getPicture());
            //for mysql
            pt.setBlob(3, picture);
        }
        pt.setDouble(4, product.getPrice());
        pt.setInt(5, product.getCategoryId());
        n = pt.executeUpdate();
        if (n > 0) {
            return n;
        }
        return 0;
    }//end save

    @Override
    public int delete(Integer productId, Connection con) {
        return 0;
    }

    @Override
    public int update(Product instance, Connection con) {
        return 0;
    }

    @Override
    public Product findById(Integer productId, Connection con) throws SQLException {
        PreparedStatement statement = con.prepareStatement("select * from product where ProductId=?");
        statement.setInt(1, productId);
        ResultSet resultSet = statement.executeQuery();
        Product product = new Product();
        while (resultSet.next()) {
            product.setProductId(resultSet.getInt("ProductID"));
            product.setProductName(resultSet.getString("ProductName"));
            product.setProductDescription(resultSet.getString("ProductDescription"));
            product.setPrice(resultSet.getDouble("price"));
            product.setCategoryId(resultSet.getInt("CategoryId"));
        }
        return product;
    }

    @Override
    public List<Product> findByCategoryId(int categoryId, Connection con) {
        List<Product> list=new ArrayList<>();
        try{
            String queryString = "select * from Product where categoryId=?";
            PreparedStatement pt = con.prepareStatement(queryString);
            pt.setInt(1,categoryId);
            ResultSet rs = pt.executeQuery();
            while(rs.next()){
                Product product = new Product();
                product.setProductId(rs.getInt("ProductId"));
                product.setProductName(rs.getString("ProductName"));
                product.setProductDescription(rs.getString("ProductDescription"));
                product.setPrice(rs.getDouble("price"));
                product.setCategoryId(rs.getInt("CategoryId"));
                list.add(product);
            }
            System.out.println("successful");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Product> findByPrice(double minPrice, double maxPrice, Connection con) throws SQLException {
        return null;
    }

    @Override
    public List<Product> findAll(Connection con) throws SQLException {
        ArrayList<Product> products = new ArrayList<>();
        try {
            PreparedStatement statement = con.prepareStatement("select * from product");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setProductId(resultSet.getInt("ProductId"));
                product.setProductName(resultSet.getString("ProductName"));
                product.setProductDescription(resultSet.getString("ProductDescription"));
                product.setPrice(resultSet.getDouble("price"));
                product.setCategoryId(resultSet.getInt("CategoryId"));
                products.add(product);
            }
            System.out.println("Get productList successful");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return products;
    }

    @Override
    public List<Product> findByProductName(String productName, Connection con) throws SQLException {
        return null;
    }

    @Override
    public List<Product> getPicture(Integer productId, Connection con) throws SQLException {
        return null;
    }

    @Override
    public byte[] getPictureById(Integer productId, Connection con) throws SQLException {
        byte[] imgByte = null;
        PreparedStatement statement = con.prepareStatement("select picture from product where ProductId = ?");
        statement.setInt(1, productId);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Blob blob = resultSet.getBlob("picture");
            imgByte = blob.getBytes(1, (int)blob.length());
        }
        return imgByte;
    }
}
