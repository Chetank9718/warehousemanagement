package com.jsp.wms.entity;

import java.time.LocalDate;
import java.util.List;

import com.jsp.wms.enums.MaterialTypes;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productId;
	private String productTitle;
	private double lengthInMeters;
	private double breadthInMeters;
	private double heightInMeters;
	private double weightInKg;

	List<MaterialTypes> materialTypes;

	private LocalDate restockedAt;
	private int sellerId;


	@ManyToOne
	private Client client;

	@ManyToMany(mappedBy = "inventories")
	private List<PurchaseOrder> purchaseOrders;
	
	@OneToMany(mappedBy = "inventory")
	private List<Batch> batchs;
}
