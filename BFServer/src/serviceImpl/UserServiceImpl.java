package serviceImpl;

import java.rmi.RemoteException;

import service.UserService;

public class UserServiceImpl implements UserService{

	public boolean login(String username, String password) throws RemoteException {
		boolean difference=false;
		IOServiceImpl io=new IOServiceImpl();
		String s1=io.readFile("admin", "UserID");
		String s2=io.readFile("admin","Password");
		if(s1.contains(username)&&s2.contains(password)){
			difference=true;
		}
		return difference;
	}

	public boolean logout(String username) throws RemoteException {
		boolean difference=true;
		IOServiceImpl io=new IOServiceImpl();
		String s1=io.readFileList(username);
		if(s1.contains(username)){
		}else{
			difference=false;
		}
		return difference;
	}

}
