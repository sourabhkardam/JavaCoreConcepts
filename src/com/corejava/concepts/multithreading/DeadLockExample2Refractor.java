package com.corejava.concepts.multithreading;

class Mouse1 {
	private Keyboard1 keyboard;

	public void setKeyboard(Keyboard1 keyboard) {
		this.keyboard = keyboard;
	}

	public synchronized void useMouseAndKeyboard() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		keyboard.useKeyboard();
	}

	public synchronized void useMouse() {
		System.out.println("Mouse is used");
	}
}

class Keyboard1 {
	private Mouse1 mouse;

	public void setMouse(Mouse1 mouse) {
		this.mouse = mouse;
	}

	public synchronized void useKeyboardAndMouse() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		mouse.useMouse();
	}

	public synchronized void useKeyboard() {
		System.out.println("Keyboard is used");
	}
}

class MouseThread1 extends Thread {
	private Mouse1 mouse;

	public MouseThread1(Mouse1 mouse) {
		this.mouse = mouse;
	}

	@Override
	public void run() {
		// acquire the lock on mouse object
		mouse.useMouseAndKeyboard();
	}

}

class KeyboardThread1 extends Thread {
	private Keyboard1 keyboard;

	public KeyboardThread1(Keyboard1 keyboard) {
		this.keyboard = keyboard;
	}

	@Override
	public void run() {
		// acquire the lock on keyboard object
		keyboard.useKeyboardAndMouse();
	}

}

public class DeadLockExample2Refractor {

	public static void main(String[] args) {
		Keyboard1 keyboard = new Keyboard1();
		Mouse1 mouse = new Mouse1();
		keyboard.setMouse(mouse);
		mouse.setKeyboard(keyboard);

		MouseThread1 mouseThread = new MouseThread1(mouse);
		KeyboardThread1 keyboardThread = new KeyboardThread1(keyboard);

		mouseThread.start();
		keyboardThread.start();
	}

}
