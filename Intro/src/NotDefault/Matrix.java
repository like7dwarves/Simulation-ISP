package NotDefault;

public class Matrix
{
	/**
	 * the stored doubles
	 */
	public double[][] matrix;
	
	/**
	 * makes a matrix with the same elements as d[][]
	 */
	public Matrix(double[][] d)
	{
		matrix = new double[d.length][d[0].length];
		for(int i=0; i<d.length; i++)
		{
			for(int j=0; j<d[0].length; j++)
			{
				matrix[i][j] = d[i][j];
			}
		}
	}
	/**
	 * makes an empty matrix, with dimensions x and y
	 */
	public Matrix(int x,int y)
	{
		matrix = new double[x][y];
	}
	
	/**
	 * combines two matrices horizontally (i.e.: [2x3]comb[1x3] = 3x3)
	 * @param otherMatrix
	 * @return
	 */
	public Matrix combine(Matrix otherMatrix)
	{
		Matrix returned = new Matrix(matrix.length,this.matrix[0].length+otherMatrix.matrix[0].length);
		for(int i=0; i<returned.matrix.length; i++)
		{
			int j=0;
			for(; j<matrix[0].length; j++)
			{
				System.out.println("i: "+i+"j: "+j);
				returned.matrix[i][j] = matrix[i][j];
			}
			for(; j<returned.matrix[0].length; j++)
			{
				returned.matrix[i][j] = otherMatrix.matrix[i][j-(matrix[0].length)];
			}
		}
		return returned;
	}
	
	/**
	 * finds the determinant of the matrix. 
	 * @exception illegal argument exception if matrix isn't square
	 * @return determinant of matrix.
	 */
	public double determinant()
	{
		//if the matrix isn't square, we can't find the determinant
		if(matrix.length!=matrix[0].length)
			throw new IllegalArgumentException();		
		//if the "matrix" is a 1x1, the determinant is just the number
		if(matrix.length==1)
			return matrix[0][0];

		//we have to use object oriented stuff for this, since arrays are reference types (I think)
			Matrix buildM;
		//this is the returned number
			double determinant = 0.0;
		
		//this loop divides an array into the sub-arrays. if we pass it a 4x4 array, this will help us split it into 4 3x3 arrays
		for(int z=0; z<matrix.length; z++)
		{
			buildM = new Matrix(matrix.length-1,matrix.length-1);
			for(int i=1; i<matrix.length; i++)
			{
				for(int j=0; j<matrix.length; j++)
				{
					if(j>z)
						buildM.matrix[i-1][j-1] = matrix[i][j];
					else if(j<z)
						buildM.matrix[i-1][j] = matrix[i][j];
				}
			}
			if(z%2==0)
				determinant+=matrix[0][z]*buildM.determinant();
			else
				determinant-=matrix[0][z]*buildM.determinant();
		}
		return determinant;
	}
	/**adds the second row to the first row
	 * @param row1 the row which is stored
	 * @param row2 the row added to row1
	*/
	public void addRow(int row1, int row2)
	{
		for(int i=0; i<matrix[0].length; i++)
		{
			matrix[row1][i]=matrix[row1][i]+matrix[row2][i];
		}
	}
	/**
	 * multiplies a row by a given scalefactor
	 * @param row the row to scale
	 * @param scale the scalefactor that the row is multiplied by.
	 */
	public void scaleRow(int row, double scale)
	{
		for(int i=0; i<matrix[0].length; i++)
		{
			matrix[row][i] = matrix[row][i]*scale;
		}
	}
	
	/**
	 * finds the dot product of the two given rows
	 */
	public double[] dotProduct(int row1, int row2)
	{
		double[] returned = new double[matrix[0].length];
		for(int i=0; i<matrix[0].length; i++)
		{
			returned[i] = matrix[row1][i]*matrix[row2][i];
		}
		return returned;
	}
	
	/**
	 * assumes matrix only has 2 rows
	 */
	public double[] dotProduct()
	{
		return dotProduct(0,1);
	}
	
	
	/**
	 * Reduces the matrix to have 0's on the left side, and 1's on the diagonal
	 * 
	 * @return The reduced matrix.
	 */
	public Matrix reduce()
	{
		System.out.println(this);
		if(matrix.length>matrix[0].length)
			throw new IllegalArgumentException();
		if(matrix.length==1)
		{
			if(matrix[0].length==1)
			{
				double[][] one = {{1.0}};
				return new Matrix(one);
			}
			else if(matrix[0][0]!=0)
			{
				scaleRow(0,-1/matrix[0][0]);
				for(int i=1; i<matrix.length; i++)
				{
					scaleRow(i,1/matrix[i][0]);
					addRow(i, 0);
				}
				scaleRow(0,-1);
			}
		}
		else
		{
			scaleRow(0,-1/matrix[0][0]);
			for(int i=1; i<matrix.length; i++)
			{
				if(matrix[i][0]!=0.0)
				{
					scaleRow(i,1/matrix[i][0]);
					addRow(i, 0);
				}
			}
			scaleRow(0,-1);
			
			Matrix next = new Matrix(matrix.length-1, matrix[0].length-1);
			for(int i=1; i<matrix.length; i++)
			{
				for(int j=1; j<matrix[0].length; j++)
				{
					next.matrix[i-1][j-1] = matrix[i][j];
				}
			}
			next.reduce();
			for(int i=1; i<matrix.length; i++)
			{
				for(int j=1; j<matrix[0].length; j++)
				{
					matrix[i][j] = next.matrix[i-1][j-1];
				}
			}
		}
		return this;
	}
	/**
	 * Reduces the leftmost square to an identity matrix
	 * @return The changed matrix
	 */
	public Matrix rowEchelon()
	{
		reduce();
		for(int i=matrix.length-1; i>=1; i--)
		{
			for(int j=1; j<=i; j++)
			{
				double temp = matrix[i-j][i];
				scaleRow(i,-temp);
				addRow(i-j,i);
				scaleRow(i,-1/temp);
			}
		}
		return this;
	}
	@Override
	public String toString()
	{
		String returned = "";
		for(int i=0; i<matrix.length; i++)
		{
			for(int j=0; j<matrix[0].length; j++)
			{
				returned = returned + matrix[i][j]+" ";
			}
			returned = returned + "\n";
		}
		return returned;
	}
	/**
	 * @return A new array, with the same elements of "matrix".
	 */
	public double[][] toNewArray()
	{
		if(matrix.length<1)
			return null;
		double[][] returned = new double[matrix.length][matrix[0].length];
		for(int i=0; i<matrix.length; i++)
		{
			for(int j=0; j<matrix[0].length; j++)
			{
				returned[i][j] = matrix[i][j];
			}
		}
		return returned;
	}
}
