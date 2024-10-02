package suplementarias;

import java.io.Serializable;

public class Agenda implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Contacto[] listaContactos;

	public Agenda() {
		this.listaContactos = new Contacto[5];

	}

	public int addContacto(Contacto c) {
		int pos = -1;
		if (c != null) {
			pos = 0;
			int posOfFirstNull = -1;
			boolean contained = false;
			while (pos < this.listaContactos.length && !contained) {
				if (c.equals(listaContactos[pos])) contained = true;
				else if (listaContactos[pos]==null && posOfFirstNull==-1) posOfFirstNull = pos;
				else pos++;
			}
			if (pos >= listaContactos.length && !contained && posOfFirstNull==-1) {
				Contacto[] aux = this.listaContactos;
				this.listaContactos = new Contacto[aux.length + 5];
				for (int i = 0; i < aux.length; i++) {
					this.listaContactos[i] = aux[i];
				}
				this.listaContactos[pos] = c;

			} else if (!contained && posOfFirstNull!=-1) {
				this.listaContactos[posOfFirstNull] = c;
				return posOfFirstNull;
			}
		}
		return pos;
	}

	public boolean deleteContacto(Contacto c) {
		if (c != null) {
			int pos = 0;
			boolean contained = false;
			while (pos < this.listaContactos.length && !contained) {
				if (c.equals(this.listaContactos[pos]))
					contained = true;
				else pos++;
			}
			if (contained) {
				this.listaContactos[pos] = null;
			}

			return contained;
		} else
			return false;
	}

	public void showContactos() {
		for (int i = 0; i < this.listaContactos.length; i++) {
			if (this.listaContactos[i] != null)
				System.out.println(this.listaContactos[i]);
		}
	}
	
	public int getNoOfContacts() {
		int counter = 0;
		for (int i = 0; i<this.listaContactos.length; i++) {
			if (this.listaContactos[i]!=null) counter++;
		}
		return counter;
	}

}
