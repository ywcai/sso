package ywcai.ls.oauth.entity;

 
import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
 
import javax.persistence.OneToMany;
 
import javax.persistence.Table;
@Entity
@Table(name="userbase")
public class UserBase implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8294594776881931656L;

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="userid")
	private Long userid;

	@Column(name="username")
	private String username;

	@Column(name="password")
	private String password;

	@Column(name="createtime")
	private String createtime;

	@OneToMany(cascade={CascadeType.ALL},orphanRemoval=true,fetch=FetchType.EAGER, mappedBy="userbase")
	private Set<Roles> rolelist;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}



	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

 
	 
	public Set<Roles> getRolelist() {
		return rolelist;
	}

	public void setRolelist(Set<Roles> rolelist) {
		this.rolelist = rolelist;
	}

	@Override
	public String toString() {
		return "UserBase [userid=" + userid + ", username=" + username + ", password=" + password + ", createtime="
				+ createtime + ", rolelist=" + rolelist + "]";
	}
 

}