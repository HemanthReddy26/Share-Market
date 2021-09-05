package com.hcl.portfolio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "portfolio")
public class Portfolio {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int portfolioId;

	@Column(name = "name")
	private String name;

	@Column(name = "price")
	private double price;

	public Portfolio() {
		super();
	}

	public Portfolio(int portfolioId, String name, double price) {
		super();
		this.portfolioId = portfolioId;
		this.name = name;
		this.price = price;
	}

	public int getPortfolioId() {
		return portfolioId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setPortfolioId(int portfolioId) {
		this.portfolioId = portfolioId;
	}

}
