package com.example.demo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;



@Repository
public class CustomerService{
    
	@Autowired
    JdbcTemplate jdbcTemplate;

    public class CustomerRowMapper implements RowMapper < Customer > {
        @Override
        public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
            Customer c= new Customer();
            c.setAccountnumber(rs.getString("accountnumber"));
            c.setFirstname(rs.getString("firstname"));
            c.setLastname(rs.getString("lastname"));
            c.setEmail(rs.getString("email"));
            c.setPhone(rs.getString("phone"));
            c.setAddress(rs.getString("address"));
            
            return c;
        }
    }

    public List <Customer> findAll() {
    
        return jdbcTemplate.query("select * from customers", new CustomerRowMapper());
    }

  
	
	
	
	public Customer findByAccount(String account) {

		List<Customer> res =  jdbcTemplate.query("select * from customers where customers.accountnumber ="+account+";", new CustomerRowMapper());
        		
        return res.get(0);
        		
        	
    }
	

    public int deleteByAccount(String account) {
        return jdbcTemplate.update("delete from customers where accountnumber=?", new Object[] {
            account
        });
    }

    public int insert(Customer c) {
        return jdbcTemplate.update("insert into customers (accountnumber, firstname, lastname, email, phone, address) " + "values(?, ?, ?, ?, ?, ?)",
            new Object[] {
                c.getAccountnumber(), c.getFirstname(),  c.getLastname(), c.getEmail(), c.getPhone(), c.getAddress() 
            });
    }

    public int update(Customer c, String account) {
        return jdbcTemplate.update("update customers " + " set firstname=?, lastname=?, email=?, phone=?, address=?" + " where accountnumber = ?",
            new Object[] {
            		c.getFirstname(),  c.getLastname(), c.getEmail(), c.getPhone(), c.getAddress(),c.getAccountnumber()
            });
    }
}
