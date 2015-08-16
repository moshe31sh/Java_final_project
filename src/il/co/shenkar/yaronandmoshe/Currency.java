package il.co.shenkar.yaronandmoshe;

public class Currency
{
	private String name;
	private String country;
	private String code;
	private double unit;
	private double valueInNIS;
	public Currency() {}

	public Currency(String name, String country, String code, double valueInNIS,double unit)
	{
		this.unit = unit;
		this.name = name;
		this.country = country;
		this.code = code;
		this.valueInNIS = valueInNIS;
	}
	public double getUnit() {return unit;}
	public void setUnit(double unit) {this.unit = unit;}
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public String getCountry() {return country;}
	public void setCountry(String country) {this.country = country;}
	public double getValueInNIS() {return valueInNIS;}
	public void setValueInNIS(double valueInNIS) {this.valueInNIS = valueInNIS;}
	public String getCode() {return code;}
	public void setCode(String code) {this.code = code;}

}


