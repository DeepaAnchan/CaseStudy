package com.example.product.security.model;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * 
 * @author Deepa Anchan
 * A collection 'user' will be created.
 *
 */
@Document(collection = "user")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class User {

    @Id
    private String userId;   
    private String email;    
    private String password;
    private String name;
    private Set<Role> role;
    private Integer active=1;
    private boolean isLoacked=false;
    private boolean isExpired=false;
    private boolean isEnabled=true;
	
    @Override
    public String toString() {
        return "User{" +
                "id='" + userId + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", role=" + role + 
                ", active=" + active +
                ", isLoacked=" + isLoacked +
                ", isExpired=" + isExpired +
                ", isEnabled=" + isEnabled +
                '}';
    }
}
