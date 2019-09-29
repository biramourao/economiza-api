package com.economiza.economizaapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class UsuarioLoginDTO {
	private String email;
	private String senha;
}
