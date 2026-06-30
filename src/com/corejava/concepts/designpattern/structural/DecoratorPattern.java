package com.corejava.concepts.designpattern.structural;

interface Computer {
	public String getSpecification();

	public int getBuildCost();

	public int getRam();

	public int getSsd();

	public String getDisplayType();

}

class BasicComputer implements Computer {
	private int ram;
	private int ssd;
	private String displayType;

	public BasicComputer(int ram, int ssd, String displayType) {
		super();
		this.ram = ram;
		this.ssd = ssd;
		this.displayType = displayType;
	}

	public int getRam() {
		return ram;
	}

	public void setRam(int ram) {
		this.ram = ram;
	}

	public int getSsd() {
		return ssd;
	}

	public void setSsd(int ssd) {
		this.ssd = ssd;
	}

	public String getDisplayType() {
		return displayType;
	}

	public void setDisplayType(String displayType) {
		this.displayType = displayType;
	}

	@Override
	public String getSpecification() {
		return "[ RAM:" + this.ram + ", SSD:" + this.ssd + ", Display:" + this.displayType + "]";
	}

	@Override
	public int getBuildCost() {
		return 30000;
	}
}

abstract class ComputerDecorator implements Computer {
	protected int ram;
	protected int ssd;
	protected String displayType;
	private int cost;
	private Computer computer;

	public ComputerDecorator(int ram, int ssd, String displayType, int cost, Computer computer) {
		this.ram = ram;
		this.ssd = ssd;
		this.displayType = displayType;
		this.cost = cost;
		this.computer = computer;
	}

	@Override
	public int getRam() {
		return this.ram + computer.getRam();
	}

	@Override
	public int getSsd() {
		return this.ssd + computer.getSsd();
	}

	@Override
	public String getDisplayType() {
		return this.displayType;
	}

	@Override
	public String getSpecification() {
		return "[ RAM:" + this.getRam() + ", SSD:" + this.getSsd() + ", Display:" + this.getDisplayType() + "]";
	}

	@Override
	public int getBuildCost() {
		return this.cost + computer.getBuildCost();
	}

}

class WindowsDecorator extends ComputerDecorator {

	public WindowsDecorator(int ram, int ssd, String displayType, int cost, Computer computer) {
		super(ram, ssd, displayType, cost, computer);
	}

}

class MacBookDecorator extends ComputerDecorator {

	public MacBookDecorator(int ram, int ssd, String displayType, int cost, Computer computer) {
		super(ram, ssd, displayType, cost, computer);
	}

}

public class DecoratorPattern {
	public static void main(String[] args) {
		Computer c1 = new MacBookDecorator(16, 256, "4K", 99999, new BasicComputer(8, 256, "HD"));
		System.out.println(c1.getBuildCost());
		System.out.println(c1.getSpecification());

		Computer c2 = new WindowsDecorator(12, 512, "OLED", 65000, c1);
		System.out.println(c2.getBuildCost());
		System.out.println(c2.getSpecification());
	}
}
