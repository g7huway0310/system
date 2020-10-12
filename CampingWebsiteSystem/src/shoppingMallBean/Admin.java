package shoppingMallBean;

public class Admin {
	private Integer id;				
	private String userName;			
	private String passWord;			
	private String name;
	
	public Admin(){
		
	}
	
	public Admin(Integer id, String userName, String passWord, String name) {
		super();
		this.id = id;
		this.userName = userName;
		this.passWord = passWord;
		this.name = name;
	}
	public Admin(Integer id, String passWord, String name) {
		super();
		this.id = id;
		this.passWord = passWord;
		this.name = name;
	}
	public Admin(String userName,String passWord) {
		this.userName=userName;
		this.passWord=passWord;
	}
	public Admin(String userName, String passWord, String name) {
		super();
		this.userName = userName;
		this.passWord = passWord;
		this.name = name;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
	

}
