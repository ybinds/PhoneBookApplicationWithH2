package com.app.myconame.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="contacts")
public class Contact {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message="NAME CANNOT BE BLANK")
	@Size(min=3, max=100, message="NAME SHOULD BE BETWEEN 3-10 CHARACTERS")
	@Pattern(regexp="[A-Za-z]{3,100}",message="NAME SHOULD HAVE ONLY ALPHABETS")
	private String name;
	
	@Email(message="INVALID EMAIL ADDRESS")
	@Pattern(regexp=".+@.+\\..+", message="INVALID EMAIL ADDRESS")
	private String email;
	
	private Long phoneNumber;
	
	private boolean active;
}
