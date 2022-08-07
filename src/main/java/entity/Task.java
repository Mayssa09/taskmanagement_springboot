package entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data //getter&setter
public class Task {
	@Id 
	
	@Column(name = "Id")
	private Long id;
	private String title;
	private String type;
	private Date dueDate;
	private String description;
}
