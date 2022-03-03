package com.bank.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PayAmmount {
	private String id;
	private Double ammount;
	private String idyankiaccount;
}
