package org.sid.entities;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Produit implements Serializable{

	@Id @GeneratedValue
	private Long id;
	@NotEmpty
	@Size(min=5,max=15)
	private String designation;
	@DecimalMin("100")
	private double prix;
	@Min(4)
	private int quantite;
}
