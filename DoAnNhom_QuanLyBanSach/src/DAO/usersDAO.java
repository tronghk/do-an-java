package DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import DTO.user;
public class usersDAO {
	private connection myConnection;
	
	public connection getMyConnection() {
		return myConnection;
	}
	public void setMyConnection(connection myConnection) {
		this.myConnection = myConnection;
	}
	
	public usersDAO() {
		this.setMyConnection(new connection());
	
	}
	public ArrayList<user> readUser (){
		ArrayList<user> arrUser = new ArrayList<user>();
		try {
			this.getMyConnection().setStatement(this.getMyConnection().getConn().createStatement());
			String sSelection = "select * from users";
			this.getMyConnection().setResultSet(this.getMyConnection().getStatement().executeQuery(sSelection));
			while(this.getMyConnection().getResultSet().next()) {
				String user = this.getMyConnection().getResultSet().getString("user");
				String password = this.getMyConnection().getResultSet().getString("password");
				String status = this.getMyConnection().getResultSet().getNString("status");
				user us = new user(user, password, status);
				arrUser.add(us);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arrUser;
	}
	public void addUser(user us) {
		String sSelection = "insert into users(user, password, status) ";
		String sValue = "values('"+ us.getUsers()+"','"+ us.getPassword()+"','"+ us.getStatus()+"')";
		try {
			this.getMyConnection().setPreparedStatement(this.getMyConnection().getConn().prepareStatement(sSelection+sValue));
			this.getMyConnection().getPreparedStatement().execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void modifyUser (user us) {
		String sSelection = "update users" + " set password = ?, status = ?" + " where user = ?";
		try {
			this.getMyConnection().setPreparedStatement(this.getMyConnection().getConn().prepareStatement(sSelection));
			this.getMyConnection().getPreparedStatement().setNString(1,us.getPassword());
			this.getMyConnection().getPreparedStatement().setNString(2,us.getStatus());
			this.getMyConnection().getPreparedStatement().setNString(3,us.getUsers());
			this.getMyConnection().getPreparedStatement().execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// xóa tài khoản
	public void deleteUser(user us) {
		String drop = "delete from users "+" where user = ?";
		try {
			this.getMyConnection().setPreparedStatement(this.getMyConnection().getConn().prepareStatement(drop));
			this.getMyConnection().getPreparedStatement().setNString(1, us.getUsers());
			this.getMyConnection().getPreparedStatement().execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// ngừng hoạt động tài khoản
	public void disbleUser(user us) {
		String sSelection = "update users" + " set status = ?" + " where user = ?";
		try {
			this.getMyConnection().setPreparedStatement(this.getMyConnection().getConn().prepareStatement(sSelection));
			this.getMyConnection().getPreparedStatement().setNString(1,us.getStatus());
			this.getMyConnection().getPreparedStatement().setNString(2,us.getUsers());
			this.getMyConnection().getPreparedStatement().execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
