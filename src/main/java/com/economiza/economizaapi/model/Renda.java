package com.economiza.economizaapi.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor

@Entity
@Table(name = "Renda")
public class Renda extends Movimentacao {

}
