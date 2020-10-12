
public class Member {
	public String mobile,password,name,nickname,gender,email,address,birthday;
	private int orders,canceltag;
	
	
	
	
	
	
	public Member() {
		super();
	}
	public Member(String mobile, String password, String name, String nickname, String gender, String birthday, String email,
			String address) {
		super();
		this.mobile = mobile;
		this.password = password;
		this.name = name;
		this.nickname = nickname;
		this.gender = gender;
		this.birthday = birthday;
		this.email = email;
		this.address = address;
		
		
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public int getOrders() {
		return orders;
	}
	public void setOrders(int orders) {
		this.orders = orders;
	}
	public int getCanceltag() {
		return canceltag;
	}
	public void setCanceltag(int canceltag) {
		this.canceltag = canceltag;
	}

}
