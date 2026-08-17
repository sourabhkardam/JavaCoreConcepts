package com.corejava.concepts.exceptionhandling;

import java.sql.SQLException;

public class ThrowAndThrows {

	/**
	 * If we throw checked exception, we must handle it either in try-catch or
	 * throws it (in method signature) to calling method.
	 * 
	 * @throws SQLException
	 */
	public static void throwCheckedException() throws SQLException {
		throw new SQLException();
//		throw new FileNotFoundException();
	}

	/**
	 * If we throw unchecked exception, we don't need to handle it in try-catch or
	 * throws it (in method signature) to calling method.
	 */
	public static void throwUnCheckedException() {
//		throw new ArithmeticException("Throwing arithmetic exception");
//		throw new NumberFormatException();
		throw new NullPointerException();
//		throw new ArrayIndexOutOfBoundsException();
	}

	public static void main(String[] args) throws SQLException {
		// Calling an method which have 'throws checked_exception' in it's signature, we
		// must either have to wrap it in try-catch or throws it (propagate it) because
		// compiler will force us to handle.
		throwCheckedException();

		// As we calling an method which doesn't have throws in it's method signature or
		// it throws an Unchecked exception explicitly in it's method signature, it's
		// not mandatory to wrap it in try-catch or throws it because compiler won't
		// force us to handle it.
		throwUnCheckedException();
	}

}
