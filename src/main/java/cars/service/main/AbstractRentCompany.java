package cars.service.main;


public abstract class AbstractRentCompany implements IRentCompany {
	private int finePercent=15;
	private int gasPrice=10;
	
	
	public int getFinePercent() {
		return finePercent;
	}
	public void setFinePercent(int finePercent) {
		this.finePercent = finePercent;
	}
	public int getGasPrice() {
		return gasPrice;
	}
	public void setGasPrice(int gasPrice) {
		this.gasPrice = gasPrice;
	}

}
