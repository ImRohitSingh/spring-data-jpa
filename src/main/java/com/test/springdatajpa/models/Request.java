package com.test.springdatajpa.models;

import java.io.Serializable;

import lombok.Data;

@Data
public class Request implements Serializable {

	private static final long serialVersionUID = 6799455135873035357L;

	private String name;

	private Double price;

}
