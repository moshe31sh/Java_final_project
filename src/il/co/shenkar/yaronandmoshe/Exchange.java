package il.co.shenkar.yaronandmoshe;

public class Exchange implements Exchangable
{
	private double resultExchanged;
	private Currency from, to;
	public double getresultExchanged() {return resultExchanged;}
	public Exchange(Currency from, Currency to)
	{	
		this.from = from;
		this.to = to;
	}
	public double resultExchanged(double amount)
	{
		resultExchanged=amount*(from.getValueInNIS()/from.getUnit());
		resultExchanged=resultExchanged/(to.getValueInNIS()/to.getUnit());
		return resultExchanged;
	}
	
}
