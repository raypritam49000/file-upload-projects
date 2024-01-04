package com.batch.processing.api.config;

import org.springframework.batch.item.ItemProcessor;

import com.batch.processing.api.model.Product;

public class CustomItemProcessor implements ItemProcessor<Product, Product> {
	
	@Override
	public Product process(Product item) throws Exception {

		try {
			System.out.println(item.getDescription());
			int discountPer = Integer.parseInt(item.getDiscount().trim());
			double originalPrice = Double.parseDouble(item.getPrice().trim());
			double discount = (discountPer / 100) * originalPrice;
			double finalPrice = originalPrice - discount;
			item.setDiscountedPrice(String.valueOf(finalPrice));
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
		}

		return item;
	}
}
