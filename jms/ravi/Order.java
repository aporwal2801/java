import java.io.Serializable;


public class Order implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3506535998197388211L;
	Double price;
	Integer quantity;
	Status status;
	public Order(Double price, Integer quantity) {
		super();
		this.price = price;
		this.quantity = quantity;
		status = Status.NEW;
	}
	
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void validateOrder() throws ValidationException{
		if(price <= 0 ){
			throw new ValidationException("Price can not be negative or Zero");
		}
		if(quantity <= 0 ){
			throw new ValidationException("Quantity can not be negative or Zero");
		}
	}
	
	@Override
	public String toString() {
		return "Order [price=" + price + ", quantity=" + quantity + ", status="
				+ status + "]";
	}
}
