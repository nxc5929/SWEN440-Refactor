package org.rit.swen440.control;

import org.rit.swen440.dataLayer.Category;
import org.rit.swen440.dataLayer.History;
import org.rit.swen440.dataLayer.Product;
import org.rit.swen440.dataLayer.Repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Controls access to data, on start-up scans directories and builds internal
 * representation of categories and items within each category.  Isolates the
 * categories and products from information on the underlying file system.
 */
public class Controller {

	public  enum PRODUCT_FIELD {
		NAME,
		DESCRIPTION,
		COST,
		INVENTORY,
		SKU_CODE
	};
	
	private Repository repository;

	public Controller() {
		repository = new Repository();
	} 

	/**
	 * Get a list of all category names
	 *
	 * @return list of categories
	 */
	public List<String> getCategories() {
		return repository.getAllCategories();
	}

	/**
	 * Get the description of the named category
	 * @param category name
	 * @return description
	 */
	public String getCategoryDescription(String category) {
		return repository.getCategoryDesc(category);
	}

	/**
	 * Return a list of Products based on the provided category.
	 *
	 * @param categoryName Name of Category to use
	 * @return List of Products in the category
	 */
	public List<String> getProducts(String categoryName) {
		return repository.getAllProducts(categoryName);
	}

	public List<History> getHistoryItems() {
		return repository.getHistoryItems();
	}


	public String getProductInformation(String category, String product, PRODUCT_FIELD field) {
		Product productObj = repository.getProductInfo(category, product);
		if(productObj == null){
			return null;
		}
		switch (field) {
		case NAME:
			return productObj.getTitle();

		case DESCRIPTION:
			return productObj.getDescription();

		case COST:
			String toReturn = String.valueOf(productObj.getCost());
			int indexOfDecimalPoint = toReturn.indexOf(".");

			if(indexOfDecimalPoint > -1)
				while(indexOfDecimalPoint >= toReturn.length()-2) //Add missing decimal places after decimal
					toReturn += "0";

			return toReturn;

		case INVENTORY:
			return String.valueOf(productObj.getItemCount());

		case SKU_CODE:
			return String.valueOf(productObj.getSkuCode());
		}

		return null;
	}

	public void addHistoryItem(int sku, int quantity, String name, BigDecimal price) {
		History item = new History(sku, quantity, name, price);

		repository.addHistoryItem(item);
	}

	public void writeProduct(Product prod) {
		repository.updateProductCount(prod.getTitle(), prod.getItemCount());
	}
}
