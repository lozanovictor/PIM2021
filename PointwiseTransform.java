
public class PointwiseTransform extends Object {

	/**
	* Question 2.1 Contrast reversal
	*/
	static public ImageAccess inverse(ImageAccess input) {
		int nx = input.getWidth();
		int ny = input.getHeight();
		ImageAccess output = new ImageAccess(nx, ny);
		double value = 0.0;
		for (int x=0; x<nx; x++)				
		for (int y=0; y<ny; y++) {
			value = input.getPixel(x, y);
			value = 255 - value;
			output.putPixel(x, y, value);
		}
		return output;	
	}

	/**
	* Question 2.2 Stretch normalized constrast
	*/
	static public ImageAccess rescale(ImageAccess input) {
		int nx = input.getWidth();
		int ny = input.getHeight();
		double max = input.getMaximum();
		double min = input.getMinimum();
		ImageAccess output = new ImageAccess(nx, ny);
		/** Código novo **/
		double val = 0;
		double beta = min;
		double alpha = 255/(max - min);
		for (int x=0; x<nx; x++)										//Anda na linha
           for (int y=0; y<ny; y++){									//Anda na coluna
                val = alpha*(input.getPixel(x, y) - beta);				//Calcula a normalização	
                output.putPixel(x, y, val);								//Retorna o novo valor
            }	
		return output;	
	}

	/**
	* Question 2.3 Saturate an image
	*/
	static public ImageAccess saturate(ImageAccess input) {
		int nx = input.getWidth();
		int ny = input.getHeight();
		ImageAccess output = new ImageAccess(nx, ny);
		/** Código novo**/
		double val = 0.0;
		for (int x=0; x<nx; x++){										//Anda na linha
           for (int y=0; y<ny; y++){									//Anda na coluna
                val = input.getPixel(x,y);								//adquire o valor do pixel
				if (val>10000){											//verifica o valor da saturação
				output.putPixel(x, y, 10000);							//altera a saturação
				} else {
                output.putPixel(x, y, val);								//retorna o valor sem alteração
				}
			}
		}	
		return output;
	}
	
	/**
	* Question 4.1 Maximum Intensity Projection
	*/
	static public ImageAccess zprojectMaximum(ImageAccess[] zstack) {
		int nx = zstack[0].getWidth();
		int ny = zstack[0].getHeight();
		int nz = zstack.length;
		ImageAccess output = new ImageAccess(nx, ny);
		// Novo código
		double val = 0.0;
        double aux = 0.0;
        //percorrendo as imagens 
		//x é na largura
		//y é na altura
		//k é a camada do stack
        for(int x=0; x < nx; x++){
            for(int y=0; y < ny; y++){
				val = 0.0;
                for(int k=0; k < nz; k++){
                    aux = zstack[k].getPixel(x,y);			//le o valor do pixel
                    if(aux >= val)							//checa se é o valor maximo
                        val = aux;
                }
                output.putPixel (x, y, val);
            }
        } 
		return output;	
	}

	/**
	* Question 4.2 Z-stack mean
	*/
	static public ImageAccess zprojectMean(ImageAccess[] zstack) {
		int nx = zstack[0].getWidth();
		int ny = zstack[0].getHeight();
		int nz = zstack.length;
		ImageAccess output = new ImageAccess(nx, ny);
		// Novo código
		double val = 0.0;
        //percorrendo as imagens 
		//x é na largura
		//y é na altura
		//z é a camada do stack
        for(int x=0; x < nx; x++){
            for(int y=0; y < ny; y++){
                for(int z=0; z < nz; z++){
                    val = val + zstack[z].getPixel(x,y);      //soma os valores
                }
                val = val/nz;								// faz a media
                output.putPixel(x, y, val);
            }
        }
		return output;	
	}

}
