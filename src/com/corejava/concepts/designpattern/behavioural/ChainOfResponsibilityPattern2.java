package com.corejava.concepts.designpattern.behavioural;

interface ATMDispenser {

	public void setNext(ATMDispenser atmDispenser);

	public void dispense(int amount);
}

class Notes500Dispneser implements ATMDispenser {
	private ATMDispenser atmDispenser;

	@Override
	public void setNext(ATMDispenser atmDispenser) {
		this.atmDispenser = atmDispenser;
	}

	@Override
	public void dispense(int amount) {
		if (amount >= 500) {
			int numOfNotes = amount / 500;
			int remainingAmount = amount % 500;
			System.out.println(numOfNotes + " notes of 500 dispensed");
			if (remainingAmount > 0) {
				atmDispenser.dispense(remainingAmount);
			}
		} else {
			atmDispenser.dispense(amount);
		}

	}
}

class Notes100Dispneser implements ATMDispenser {
	private ATMDispenser atmDispenser;

	@Override
	public void setNext(ATMDispenser atmDispenser) {
		this.atmDispenser = atmDispenser;
	}

	@Override
	public void dispense(int amount) {
		if (amount >= 100) {
			int numOfNotes = amount / 100;
			int remainingAmount = amount % 100;
			System.out.println(numOfNotes + " notes of 100 dispensed");
			if (remainingAmount > 0) {
				atmDispenser.dispense(remainingAmount);
			}
		} else {
			atmDispenser.dispense(amount);
		}

	}
}

class Notes50Dispneser implements ATMDispenser {
	private ATMDispenser atmDispenser;

	@Override
	public void setNext(ATMDispenser atmDispenser) {
		this.atmDispenser = atmDispenser;
	}

	@Override
	public void dispense(int amount) {
		if (amount >= 50) {
			int numOfNotes = amount / 50;
			int remainingAmount = amount % 50;
			System.out.println(numOfNotes + " notes of 50 dispensed");
			if (remainingAmount > 0) {
				atmDispenser.dispense(remainingAmount);
			}
		} else {
			atmDispenser.dispense(amount);
		}

	}
}

public class ChainOfResponsibilityPattern2 {

	public static void main(String[] args) {
		int amount = 150;
		ATMDispenser notesof500 = new Notes500Dispneser();
		ATMDispenser notesof100 = new Notes100Dispneser();
		ATMDispenser notesof50 = new Notes50Dispneser();
		notesof500.setNext(notesof100);
		notesof100.setNext(notesof50);
		System.out.println("Amount must be multiple of 50");
		notesof500.dispense(amount);
	}

}
