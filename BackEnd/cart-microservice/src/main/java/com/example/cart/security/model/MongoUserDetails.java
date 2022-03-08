package com.example.cart.security.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Getter;
import lombok.Setter;

@JsonDeserialize(as = MongoUserDetails.class)
@Getter
@Setter
public class MongoUserDetails implements UserDetails {
	private static final long serialVersionUID = 1L;
	private String userId;  
	private String username;
    private String password;
    private List<GrantedAuthority> grantedAuthorities;
    private int active;
    private boolean isLocked;
    private boolean isExpired;
    private boolean isEnabled;

    public MongoUserDetails(String userId, String username, String password, String [] authorities, 
    		int active, boolean isLocked, boolean isExpired, boolean isEnabled) {
    	this.userId = userId;
        this.username = username;
        this.password = password;
        this.grantedAuthorities = AuthorityUtils.createAuthorityList(authorities);
        this.active = active;
        this.isLocked = isLocked;
        this.isExpired = isExpired;
        this.isEnabled = isEnabled;
    }

    public MongoUserDetails(String username,  String [] authorities) {
        this.username = username;
        this.grantedAuthorities = AuthorityUtils.createAuthorityList(authorities);
    }

    public MongoUserDetails() {
        super();
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return grantedAuthorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return active==1;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !isLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return !isExpired;
	}

	@Override
	public boolean isEnabled() {
		return isEnabled;
	}
}
