package DAO;

import Model.Account;
import Util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


// account_id integer primary key auto_increment,
// username varchar(255),
// password varchar(255)

// Connection connection = ConnectionUtil.getConnection();
// try {
//     // Prepare SQL and prepared statement
//     String sql = "";
//     PreparedStatement preparedStatement = connection.prepareStatement(sql);

//     // Set string of prepared statement
//     preparedStatement.setString(1, account.getUsername());
//     preparedStatement.setString(2, account.getPassword());

//     // Execute 
//     preparedStatement.executeUpdate();

//     // Process response
    
// } catch(SQLException e){
//     System.out.println(e.getMessage());
// }
// return null;




public class AccountDAO {
    // Insert a new account 
    public Account insertAccount(Account account){
        Connection connection = ConnectionUtil.getConnection();
        try {
            // Prepare SQL and prepared statement
            String sql = "INSERT INTO Account (username, password) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // Set string of prepared statement
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());

            // Execute 
            preparedStatement.executeUpdate();
            ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
            if(pkeyResultSet.next()){
                int generated_account_id = (int) pkeyResultSet.getLong(1);
                return new Account(generated_account_id, account.getUsername(), account.getPassword());
            }
            
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    // Delete an account


    // Get account by id
    public Account getAccountByID(long ID){
        Connection connection = ConnectionUtil.getConnection();
        try {
            // Prepare SQL and prepared statement
            String sql = "SELECT * FROM Account WHERE account_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Set string of prepared statement
            preparedStatement.setLong(1, ID);

            // Execute and save results
            ResultSet rs = preparedStatement.executeQuery();

            // Process results
            while(rs.next()){
                return new Account(rs.getInt("account_id"), rs.getString("username"), rs.getString("password"));
            }
            
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    
    // Get account by username
    public Account getAccountByUsername(String username){
        Connection connection = ConnectionUtil.getConnection();
        try {
            // Prepare SQL and prepared statement
            String sql = "SELECT * FROM Account WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Set string of prepared statement
            preparedStatement.setString(1, username);

            // Execute and save results
            ResultSet rs = preparedStatement.executeQuery();

            // Process results
            while(rs.next()){
                return new Account(rs.getInt("account_id"), rs.getString("username"), rs.getString("password"));
            }
            
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    // Change Username?
}
