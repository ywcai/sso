package ywcai.ls.oauth.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Entity
@Table(name="roles")
@JsonIgnoreProperties(value={"userbase"})
public class Roles {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="roleid")
	private Long roleid;

	@Column(name="rolename")
	private String rolename;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="userid")//将userBase的主键加入当前表作为作为外键，并且字段名称为userid
	private UserBase userbase;
	
	public Long getRoleid() {
		return roleid;
	}

	public void setRoleid(Long roleid) {
		this.roleid = roleid;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

 

 
	public UserBase getUserbase() {
		return userbase;
	}

	public void setUserbase(UserBase userbase) {
		this.userbase = userbase;
	}

	@Override
	public String toString() {
		return "Roles [roleid=" + roleid + ", rolename=" + rolename + ", owner=" + userbase + "]";
	}
}