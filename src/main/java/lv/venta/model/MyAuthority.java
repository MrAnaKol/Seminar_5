package lv.venta.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "AuthTable")
@Entity
public class MyAuthority {
	
	@Setter(value = AccessLevel.NONE)
	@Column(name = "IdA")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int ida;
	
	@NotNull
	@Size(min = 3, max = 20)
	@Pattern(regexp = "[A-Z]{1}[a-z ]+", message = "Only letters allowed")
	@Column(name = "Title")
	private String title;
	
	public MyAuthority(String title) {
		setTitle(title);
	}
}