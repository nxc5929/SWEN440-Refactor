package org.rit.swen440;

import org.rit.swen440.presentation.menumgr;

import java.io.FileInputStream;
import java.io.IOException;

public class menutest
{
	public static void main(String[] args){
		System.out.println("Hello and welcome to Hippolyta.com");
		menumgr mgr = new menumgr();
		int currentLevel = 0;
		boolean done = false;
		do {
			done = mgr.loadLevel(currentLevel);
		} while (!done);

		System.out.println("Thank you for shopping at Hippolyta.com!");

	}
}