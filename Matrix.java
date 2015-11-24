import java.util.*;
public class Matrix{
	double[][]x;
	public Matrix(){
	}
	public Matrix(double[][]x){
		this.x =  x;
	}
    public static void print2D(int[][] x){
	    	System.out.println("the array ");

		for(int i = 0;i<x.length;i++){
			for(int j = 0;j<x[i].length;j++)
				System.out.print(x[i][j]+" ");
			System.out.println();
	           }
			}
	public static void print2D(double[][] x){
	    	System.out.println("the array ");
		for(int i = 0;i<x.length;i++){
			for(int j = 0;j<x[i].length;j++)
				System.out.print(x[i][j]+" ");
			System.out.println();
	           }
			}
	public void display(){
		print2D(x);
	}
	public boolean isEqual(Matrix b){
		if(x.length==b.x.length&&x[0].length==b.x[0].length){
			for(int i =0; i<x.length;i++)
				for(int j = 0;j<x[0].length;j++)
					if(!(x[i][j]==b.x[i][j]))
						return false;
			return true;
		}else
			return false;
	}
	public boolean possibleAdd(Matrix b){
		return(x.length==b.x.length&&x[0].length==b.x[0].length);
	}
	public Matrix matrixAddition(Matrix b){
		if(!(this.possibleAdd(b)))
			return new Matrix(new double[][]{{-1}});
		double[][] result = new double[x.length][x[0].length];
		for(int i = 0;i<x.length;i++)
		    for(int j =0;j<x[i].length;j++)
				result[i][j]=x[i][j]+b.x[i][j];
		return new Matrix (result);
	}
	public Matrix matrixSubstraction(Matrix b){
		if(!(this.possibleAdd(b)))
			return new Matrix(new double[][]{{-1}});
		double[][] result = new double[x.length][x[0].length];
		for(int i = 0;i<x.length;i++)
		    for(int j =0;j<x[i].length;j++)
				result[i][j]=x[i][j]-b.x[i][j];
		return new Matrix (result);
	}
	public void scalarMultiply(int a){
		for(int i = 0;i<x.length;i++)
		    for(int j =0;j<x[i].length;j++)
		    	x[i][j]*=a;
	}
	public Matrix newScalarMultiply(int a){
		double[][] r =new double[x.length][x[0].length];
		for(int i = 0;i<x.length;i++)
		    for(int j =0;j<x[i].length;j++)
		    	r[i][j]=x[i][j]*a;
		    return new Matrix (r);
	}
	public boolean checkMultiplyMatrix(Matrix b){return (x[0].length==b.x.length);}
	public  Matrix multiplyMatrix(Matrix b){
		if(!this.checkMultiplyMatrix(b))
			return new Matrix(new double[][]{{-1}});
		double[][] result = new double[x.length][b.x[0].length];
		for(int i =0 ;i<result[0].length;i++)
			for(int j = 0;j<result.length;j++)
				for(int k= 0;k<x[0].length;k++)
					result[j][i]+=x[j][k]*b.x[k][i];
		return new Matrix (result);
	}
	public Matrix matrixTranspose(){
		double[][] r = new double[x[0].length][x.length];
		for(int i = 0;i<x.length;i++)
			for(int j= 0;j<x[0].length;j++)
				r[j][i] =x[i][j];
		return new Matrix (r);

	}
	public boolean isSymmetric(){
		return(this.isEqual(this.matrixTranspose()));
	}
	public boolean isSkewSymmetric(){
		return (this.newScalarMultiply(-1).isEqual(this.matrixTranspose()));
	}
	public boolean isUpperTriangle(){
		for(int i = 0;i<x.length;i++)
			for(int j = 0;j<i;j++)
				if(x[i][j]!=0)
					return false;
		return true;
	}
	public boolean isLowerTriangle(){
		for(int i = 0;i<x.length;i++)
			for(int j = i+1;j<x[0].length;j++)
				if(x[i][j]!=0)
					return false;
		return true;
	}
	public boolean isSquare(){return (x.length==x[0].length);}
	public Matrix det(){
		if(this.isSquare())
			return this;
		return	new Matrix(new double[][]{{-1}});
		}

		 public Matrix minorDet(int i,int j){
		 	if(!this.isSquare())
		 		return	new Matrix(new double[][]{{-1}});
			i--;
			j--;
			double[][] minor = new double[x.length-1][x[0].length-1];
			int c1,c2;
			c1=0 ;c2=0;
			for(int c = 0;c<x.length;c++){
				for(int k = 0;k<x.length;k++)
					if(!(c==i||j==k)){
						minor[c1][c2]=x[c][k];
						c2++;
					}
				if(i!=c)
					c1++;
				c2=0;
			}
			return new Matrix(minor);

		}
		public double solu2BY2(){
			if(!this.isSquare())
				return -1;
			if(!(x.length==2&&x[0].length==2))
				return -1;
			return x[0][0]*x[1][1]-x[0][1]*x[1][0];

		}
		public double solu3BY3(){
			if(!this.isSquare())
				return -1;
			if(!(x.length==3&&x[0].length==3))
				return -1;
			return x[0][0]*this.minorDet(1,1).solu2BY2()-x[0][1]*this.minorDet(1,2).solu2BY2()+x[0][2]*this.minorDet(1,3).solu2BY2();
		}
		public double soluDet(){
			if(!this.isSquare())
		 		return	-1;
			return helpsolu(this,0);
		}
		 public static double helpsolu(Matrix t,int i){
			if(t.x.length==2&&t.x[0].length==2)
				return t.solu2BY2();
			if(t.x.length==3&&t.x[0].length==3)
				return t.solu3BY3();
			if(i==t.x.length)
				return 0;

			return t.x[0][i]*helpsolu(t.minorDet(1,i+1),0)*Math.pow(-1,i)+ helpsolu(t,++i);
		}
		public double minor(int i ,int j ){
			return this.minorDet(i,j).soluDet();
		}
		public double cofDet(int i ,int j){
			if(!this.isSquare())
		 		return	-1;
			return this.minorDet(i,j).soluDet()*Math.pow(-1,i+j);
		}
	public static void main(String[]args){
		double[][] x= new double[][]{{1,3,-2,1},{5,1,0,-1},{0,1,0,-2},{2,-1,0,3}};
			Matrix a =new Matrix(x);
			System.out.println(a.soluDet());

	}
}
