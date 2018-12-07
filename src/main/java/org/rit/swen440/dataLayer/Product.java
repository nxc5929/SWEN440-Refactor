package org.rit.swen440.dataLayer;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.nio.file.Path;

/**
 * A record of each product type
 */
@Data
public class Product {
	@Setter(AccessLevel.PRIVATE)
	private boolean updated = false;

	private Path path;

	private int skuCode;
	private int itemCount;
	private int threshold;
	private int reorderAmount;
	private String title;
	private String description;
	private BigDecimal cost;

	/**
	 * Check to see if we have enough of this represented item for an order
	 *
	 * @param amount Number of items being ordered
	 * @return true if enough stock
	 */
	public boolean canOrder(int amount) {
		return (itemCount - amount >= 0 && amount > 0);
	}

	/**
	 * Place an order, decrement the represented available item count
	 *
	 * @param amount being ordered
	 * @return if order was successfully processed
	 */
	public boolean order(int amount) {
		if (canOrder(amount)) {
			itemCount = itemCount - amount;
			setUpdated(true);  // Need to store the updated product information

			// TODO:  add stock management functionality
			return true;
		}

		return false;
	}

	private void setUpdated(boolean b) {
		updated = b;
	}

	public String getTitle() {
		return title;
	}

	public Path getPath() {
		return path;
	}

	public void setPath(Path path) {
		this.path = path;
	}

	public int getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(int skuCode) {
		this.skuCode = skuCode;
	}

	public int getItemCount() {
		return itemCount;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

	public int getThreshold() {
		return threshold;
	}

	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}

	public int getReorderAmount() {
		return reorderAmount;
	}

	public void setReorderAmount(int reorderAmount) {
		this.reorderAmount = reorderAmount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public boolean isUpdated() {
		return updated;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
