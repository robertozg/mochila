package home;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Solucion {
	
	List<Integer> info = null;
	List<Producto> prductos = null;
	Random rnd = null;
	Integer disponible = null;
	
	public Solucion (List<Producto> productos, Random rnd, Integer disponible){
		info = new ArrayList<Integer>();
		this.prductos = productos;
		this.rnd = rnd;
		this.disponible = disponible;
	}

	public Solucion (List<Integer> info , List<Producto> productos, Random rnd, Integer disponible){
		this.info = info;
		info = new ArrayList<Integer>();
		this.prductos = productos;
		this.rnd = rnd;
		this.disponible = disponible;
	}
	
	
	public Integer getCosto(){
		Integer costo = 0;
		
		for (int i=0; i < prductos.size(); i++){
			if (info.get(i).equals(1)){
				costo += prductos.get(i).getCosto(); 
			}
		}
		
		return costo;
	}

	public Integer getBenef(){
		Integer benef = 0;
		
		for (int i=0; i < prductos.size(); i++){
			if (info.get(i).equals(1)){
				benef += prductos.get(i).getBenef(); 
			}
		}
		
		return benef;
	}
	
	public void genAletoria(){
		info = new ArrayList<Integer>();
		
		for (int i=0; i < prductos.size(); i++)
			info.add(0);
		
		Integer costoTemp = 0;
		boolean sw = true;
		while (this.getCosto() < this.disponible && sw){
			Integer dado = rnd.nextInt(this.prductos.size());		
			
			if (costoTemp + this.prductos.get(dado).getCosto() > disponible)
				sw = false;
			else{
				costoTemp += this.prductos.get(dado).getCosto();
				this.info.set(dado, 1);
			}
		}
		
		
		System.out.println("Costo = " + this.getCosto());
		System.out.println("benef = " + this.getBenef());
		
	}

	public List<Integer> getInfo() {
		return info;
	}

	public List<Solucion> cruzaminto(Solucion s2){
		List<Solucion> listaSalida = new ArrayList<Solucion>();
		
		Integer dado = rnd.nextInt(this.prductos.size());
		
		List<Integer> adn1 = new ArrayList<Integer>();
		List<Integer> adn2 = new ArrayList<Integer>();
		
		for (int i=0; i < this.prductos.size() ; i++){
			if (i < dado){
				adn1.add(this.info.get(i));
				adn2.add(s2.getInfo().get(i));
			} else {
				adn1.add(s2.getInfo().get(i));
				adn2.add(this.info.get(i));
			}
		}
		
		listaSalida.add(new Solucion(adn1, this.prductos, this.rnd, this.disponible));
		listaSalida.add(new Solucion(adn1, this.prductos, this.rnd, this.disponible));
		
		
		return listaSalida;
	}
	
	
	@Override
	public String toString() {
		return "Solucion " + info ;
	}
	
	
	
}
