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
@Table(name = "UserTable")
@Entity
public class MyUser {
	
	@Setter(value = AccessLevel.NONE)//priekš ID nebūs automātiskais set
	@Column(name = "Idu")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idu;
	
	@NotNull
	@Size(min = 3, max = 20)
	@Pattern(regexp = "[A-Z]{1}[a-z.]+", message = "Only letters and dot allowed")
	@Column(name = "Username")
	private String username;
	
	
	@NotNull
	@Column(name = "Password")
	private String password;
	
	//TODO uztaisīt saiti uz MyAuthority
	//TODO uztaisīt konstruktoru
	
}