package entity;

public class SaleItem {

	/*=============================================================
	create table sale_item
	(
	   sale_item_id         bigint not null auto_increment,
	   ticket_id            bigint,
	   sale_ID              bigint,
	   sale_item_price      numeric(10,2),
	   primary key (sale_item_id)
	);

	==============================================================*/
	
	private int id;
	private int ticketId;
	private int saleId;
	private float price;
	
	public SaleItem() {
		// TODO Auto-generated constructor stub
	}

	public SaleItem(int id, int ticketId, int saleId, float price) {
		super();
		this.id = id;
		this.ticketId = ticketId;
		this.saleId = saleId;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public int getSaleId() {
		return saleId;
	}

	public void setSaleId(int saleId) {
		this.saleId = saleId;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "SaleItem [id=" + id + ", ticketId=" + ticketId + ", saleId=" + saleId + ", price=" + price + "]";
	}
	
}
