import java.util.Random;

public class Barcos {
	int i, j;
	int matriz[][];
	int tamanio;
	Random posRandom = new Random();
	public Barcos(){ 
		matriz=new int [10][10]; 
		tamanio=4;
		llenarMatriz();
		Flota();
	}
	
	public void llenarMatriz(){
		for(i=0;i<10;i++){
			for(j=0;j<10;j++){
				matriz[i][j]=0;
			}
		}
	}
	
	public void Flota(){	
		  for(i=1;i<=4;i++){
		 	for(j=1;j<=i;){
		 		if (Posicionamiento(tamanio)){
		 			j++;
		 		}
		 	}
		 	tamanio--;
		}
	}
	
	public boolean Posicionamiento(int tamanio){
		posRandom=new Random();
		int i=posRandom.nextInt(10);
		int j=posRandom.nextInt(10);
		int z=posRandom.nextInt(2)+1;
		switch(z){
		case 1:
			if(j+tamanio-1<10){
				if (ColocarBarcos(i,j,z,tamanio)){
					ReservarEspacio(i,j,z,tamanio);
					return true;
				}
				else{
					return false;
				}
			}else{
				return false;
				
			}
		case 2:
			if(i+tamanio-1<10){
				if (ColocarBarcos(i,j,z,tamanio)){
					ReservarEspacio(i, j,z,tamanio);
					return true;
				}
				else{
					return false;
				}
			}else{
				return false;
			}
		}
		return  true;
	}
	public void ReservarEspacio(int i,int j,int z,int tamanio){
		int filaI=0; int filaJ=0; int columnaI=0;int columnaJ=0;
		if(z==1){
			filaI=i-1;
			filaJ=i+1;
			columnaI=j-1;
			columnaJ=j+tamanio;
		}
		else{
			filaI=i-1;
			filaJ=i+tamanio;
			columnaI=j-1;
			columnaJ=j+1;
		}
		if(filaI<0){
			filaI=0;
		}
		if(filaJ>9){
			filaJ=9;
		}
		if(columnaI<0){
			columnaI=0;
		}
		if(columnaJ>9){
			columnaJ=9;
		}
		for(i=filaI;i<=filaJ;i++){
			for(j=columnaI;j<=columnaJ;j++){
				if(matriz[i][j]!=1){
					matriz[i][j]=2;
				}
			}
		}
	}
	
	public boolean ColocarBarcos(int i,int j,int z,int tamanio){		
		if (z==1){
			for(int k=j; k<j+tamanio;k++){
				if (matriz[i][k]==0)
					matriz[i][k]=1;
				else
					return false;
			}
		}
		else{
			for(int k=i; k<i+tamanio;k++){
				if (matriz[k][j]==0)
					matriz[k][j]=1;
				else
					return false;
			}
		}
		return true;
	}
	
	public void VisualizarMatriz(){
		for(i=0;i<10;i++){
			for(j=0;j<10;j++){
				System.out.print("     "+matriz[i][j]);
			}
			System.out.println();
			System.out.println();
		}
	}
}	
