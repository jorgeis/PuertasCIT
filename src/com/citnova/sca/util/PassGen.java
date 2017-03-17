package com.citnova.sca.util;

public class PassGen {
	public static String generatePass() {
		String clave = "";
		for(int i=0; i<6; i++) {
			int comp = (int) (Math.random() * 14);
			if(comp==10) {
				clave = clave + 'A';
			}
			else if(comp==11) {
				clave = clave + 'B';
			}
			else if(comp==12) {
				clave = clave + 'C';
			}
			else if(comp==13) {
				clave = clave + 'D';
			}
			else {
				clave = clave + String.valueOf(comp);
			}
		}
		return clave;
	}
}
