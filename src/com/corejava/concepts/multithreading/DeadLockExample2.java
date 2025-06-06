package com.corejava.concepts.multithreading;

/**
 * Deadlock is a situation where one thread acquire locks on one resource and
 * trying to hold a lock on other resource which is locked by another thread.
 * And that another thread is trying to acquire lock on resource which is
 * acquired by first thread.
 */
class Mouse {
	public synchronized void useMouseAndKeyboard(Keyboard keyboard) {
		System.out.println(
				Thread.currentThread().getName() + " acquired locked on " + this + " and trying to lock " + keyboard);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// MouseThread trying to acquire lock on keyboard but keyboard is already locked
		// by KeyboardThread
		keyboard.useKeyboard();
	}

	public synchronized void useMouse() {
		System.out.println("Mouse is used");
	}
}

class Keyboard {
	public synchronized void useKeyboardAndMouse(Mouse mouse) {
		System.out.println(
				Thread.currentThread().getName() + " acquired locked on " + this + " and trying to lock " + mouse);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// KeyboardThread trying to acquire lock on mouse but mouse is already locked by
		// MouseThread
		mouse.useMouse();
	}

	public synchronized void useKeyboard() {
		System.out.println("Keyboard is used");
	}
}

class MouseThread extends Thread {
	private Mouse mouse;
	private Keyboard keyboard;

	public MouseThread(Mouse mouse, Keyboard keyboard) {
		this.mouse = mouse;
		this.keyboard = keyboard;
	}

	@Override
	public void run() {
		// acquire the lock on mouse object
		mouse.useMouseAndKeyboard(keyboard);
	}

}

class KeyboardThread extends Thread {
	private Mouse mouse;
	private Keyboard keyboard;

	public KeyboardThread(Mouse mouse, Keyboard keyboard) {
		this.mouse = mouse;
		this.keyboard = keyboard;
	}

	@Override
	public void run() {
		// acquire the lock on keyboard object
		keyboard.useKeyboardAndMouse(mouse);
	}

}

public class DeadLockExample2 {

	public static void main(String[] args) {
		Mouse mouse = new Mouse();
		Keyboard keyboard = new Keyboard();

		MouseThread mouseThread = new MouseThread(mouse, keyboard);
		KeyboardThread keyboardThread = new KeyboardThread(mouse, keyboard);

		mouseThread.start();
		keyboardThread.start();
	}

}
