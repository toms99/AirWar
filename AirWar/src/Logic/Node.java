package Logic;

import GameObjects.ID;

/**
 * Implementación de un nodo en el grafo ponderado dirigido
 * Un Nodo representa una ubicación en el mapa.<br>
 * Cada nodo tiene:<br>
 * - id: Un número que le permite identificarse en la matriz de adyacencia del grafo.<br>
 * - name: Nombre de la ubicación geográfica en el mapa.<br>
 * - (x,y): Coordenadas cartesianas donde se ubicará el punto.<br>
 */
public class Node {

  // Atributos
  private int id,x,y;
  private ID name;

  /**
   * Constructor de un nodo
   * @param id : Identificador del nodo en la matriz de adyacencia
   * @param name : Nombre del lugar que representa el nodo
   */
  public Node(int id, ID name, int x, int y){
    this.id = id;
    this.name = name;
    this.x = x;
    this.y = y;
  }

  // Getters & Setters

	/**
	* Returns value of id
	* @return
	*/
	public int getId() {
		return id;
	}

	/**
	* Returns value of x
	* @return
	*/
	public int getX() {
		return x;
	}

	/**
	* Returns value of y
	* @return
	*/
	public int getY() {
		return y;
	}


	/**
	* Returns value of name
	* @return
	*/
	public ID getName() {
		return name;
	}

  /**
	* Returns the (x,y) of this Node
	* @return
	*/
	public String getPosition() {
		return String.format("(%s,%s)",this.x,this.y);
	}

  /**
	* Sets new value of (x,y) position
	* @param x
  * @param y
	*/
	public void setPosition(int x,int y) {
		this.y = y;
    this.x = x;
	}
	/**
	* Crea un string que representa al nodo
	* @return
	*/
	@Override
	public String toString() {
		return String.format("{id= %s;name= %s}",this.id,this.name);
	}
	
	/*
	 *----------------------------------------------------------------------------
	 *                             PROGRAM'S END
	 *----------------------------------------------------------------------------
	 */
}
