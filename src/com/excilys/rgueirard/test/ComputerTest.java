package com.excilys.rgueirard.test;

import com.excilys.rgueirard.domain.Computer;

public class ComputerTest {

	public static void main(String[] args) {
		Computer computer1 = new Computer();
		computer1.setId(1);
		computer1.setName("cmpt1");
		computer1.setIntroduced(null);
		computer1.setDiscontinued(null);
		
		Computer computer2 = new Computer();
		computer2.setId(2);
		computer2.setName("cmpt2");
		computer2.setIntroduced(null);
		computer2.setDiscontinued(null);
		
		System.out.println("computer 1 : \n"+computer1.toString());
		System.out.println("\ncomputer 2 : \n"+computer2.toString());
	}

}
