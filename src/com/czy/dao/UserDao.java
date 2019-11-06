package com.czy.dao;


import com.czy.entity.PageBean;
import com.czy.entity.User;
import com.czy.jdbctemplate.JdbcTemplateUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDao {
    private static JdbcTemplate template = new JdbcTemplate(JdbcTemplateUtil.getDataSource());

    public static List<User> listUser(PageBean pageBean) {
        String sql = "select * from user limit ?,?";
        List<User> list = template.query(sql, new BeanPropertyRowMapper<>(User.class),
                (pageBean.getPage() - 1) * pageBean.getPageSize(), pageBean.getPageSize());
        return list;
    }

    public static List<User> findName(String name) {

        String sql = "select * from user where name like ?";
        List<User> list = template.query(sql, new BeanPropertyRowMapper<>(User.class), "%" + name + "%");
        return list;
    }

    public static void deleteById(long id) {
        String sql = "delete from user where id = ?";
        template.update(sql, id);
    }

    public static void add(User user) {
        String sql = "insert into user(name,age,sex,create_time) values(?,?,?,?)";
        template.update(sql, user.getName(), user.getAge(), user.getSex(), user.getCreateTime());
    }

    public static void update(User user) {
        String sql = "update user set name=?,age=?,sex=?,create_time=? where id=?";
        template.update(sql, user.getName(), user.getAge(), user.getSex(), user.getCreateTime(), user.getId());
    }

    public static void main(String[] args) {
        PageBean pageBean = new PageBean();
        pageBean.setPage(1);
        pageBean.setPageSize(2);
        List<User> users = listUser(pageBean);
//        List<User> users = findName("三");
//        User user=new User();
//        user.setName("day29");
//        user.setAge(29);
//        user.setSex("m");
//        user.setCreateTime("2018-09-09");
//        add(user);
//        deleteById(8);
        User user = new User();
        user.setId(1L);
        user.setName("day29(2)");
        user.setAge(30);
        user.setSex("m");
        user.setCreateTime("2019-09-09");
        update(user);
//        for (User u : users) {
//            System.out.print(u.getId() + "\t");
//            System.out.print(u.getName() + "\t");
//            System.out.print(u.getSex() + "\t");
//            System.out.print(u.getCreateTime() + "\t");
//            System.out.println(u.getDelFlag());
//        }
    }
}
