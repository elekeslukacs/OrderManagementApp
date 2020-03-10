package model;
/**
 * Class that represents an order and maps the orderrequest table in the database.
 * @author Elekes Lukacs
 *
 */
public class OrderRequest {
	private int idcustomer;
	private int idproduct;
	private int amount;
	
	/**Constructor of the class
	 * 
	 * @param idc - Id of the client
	 * @param idp - Id of the product
	 * @param amount - Amount that the client wants to buy 
	 */
	public OrderRequest (int idc, int idp, int amount) {
		super();
		this.idcustomer = idc;
		this.idproduct = idp;
		this.amount = amount;
	}

	public int getIdcustomer() {
		return idcustomer;
	}

	public void setIdcustomer(int idcustomer) {
		this.idcustomer = idcustomer;
	}

	public int getIdproduct() {
		return idproduct;
	}

	public void setIdproduct(int idproduct) {
		this.idproduct = idproduct;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Order [idcustomer=" + idcustomer + ", idproduct=" + idproduct + ", amount=" + amount + "]";
	}
	
	
}
