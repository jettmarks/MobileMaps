package com.jettmarks.auth;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

// Generated Aug 9, 2014 8:35:05 PM by Hibernate Tools 3.4.0.CR1

/**
 * Fbauth generated by hbm2java
 */
@Entity
public class FBAuth implements java.io.Serializable {

    @Id
    @GeneratedValue
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String validatedId;
    private String displayName;
    private String location;
    private String imageUrl;

    public FBAuth() {
    }

    public FBAuth(String firstName, String lastName, String validatedId) {
	this.firstName = firstName;
	this.lastName = lastName;
	this.validatedId = validatedId;
    }

    public FBAuth(String firstName, String lastName, String email,
	    String validatedId, String displayName, String location,
	    String imageUrl) {
	this.firstName = firstName;
	this.lastName = lastName;
	this.email = email;
	this.validatedId = validatedId;
	this.displayName = displayName;
	this.location = location;
	this.imageUrl = imageUrl;
    }

    public Integer getId() {
	return this.id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public String getFirstName() {
	return this.firstName;
    }

    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }

    public String getLastName() {
	return this.lastName;
    }

    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    public String getEmail() {
	return this.email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getValidatedId() {
	return this.validatedId;
    }

    public void setValidatedId(String validatedId) {
	this.validatedId = validatedId;
    }

    public String getDisplayName() {
	return this.displayName;
    }

    public void setDisplayName(String displayName) {
	this.displayName = displayName;
    }

    public String getLocation() {
	return this.location;
    }

    public void setLocation(String location) {
	this.location = location;
    }

    public String getImageUrl() {
	return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
	this.imageUrl = imageUrl;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "FBAuth [id=" + id + ", firstName=" + firstName + ", lastName="
		+ lastName + ", email=" + email + ", validatedId="
		+ validatedId + ", displayName=" + displayName + ", location="
		+ location + ", imageUrl=" + imageUrl + "]";
    }

}
