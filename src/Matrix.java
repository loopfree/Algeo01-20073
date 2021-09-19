import java.util.*;

public class Matrix
{
	Scanner input = new Scanner(System.in);
	int baris;
	int kolom;
	double[][] arr;

	/* ----------------------------------------------------- */
	/* -------------------- KONSTRUCTOR -------------------- */
	/* ----------------------------------------------------- */

	// Membuat matrix
	public Matrix (int baris, int kolom)
	{
		arr = new double[baris][kolom];
		this.baris = baris;
		this.kolom = kolom;
	}

	// Mengisi matrix
	// Misalkan matrix 3x2
	/*
		a b
		a b
		a b
	*/
	// Secara array direpresentasikan menjadi
	// [ [ab] [ab] [ab] ]
	public Matrix (double[][] arr)
	{
		baris = arr.length; // [ [ab] [ab] [ab] ]
		kolom = arr[0].length; // [ab]
		this.arr = new double[baris][kolom];
		for (int i=0; i<baris; i++)
		{
			for (int j=0; j<kolom; j++)
			{
				this.arr[i][j] = arr[i][j];
			}
		}
	}

	/* ----------------------------------------------------- */
	/* ----------------- GETTER AND SETTER ----------------- */
	/* ----------------------------------------------------- */

	// Mengambil baris dari matrix
	public int getBaris()
	{
		return baris;
	}

	// Menginisiasi baris dari matrix
	public void  setBaris()
	{
		this.baris = baris;
	}

	// Mengambil kolom dari matrix
	public int getKolom()
	{
		return kolom;
	}

	// Menginisiasi kolom dari matrix
	public void setKolom()
	{
		this.kolom = kolom;
	}

	// Mengambil elemen dari suatu matrix
	public double getElmt(int i, int j)
	{
		return arr[i][j];
	}

	// Menginisiasi elemen dari suatu matrix
	public void setElmt(int i, int j, double x)
	{
		arr[i][j] = x;
	}

	/* ----------------------------------------------------- */
	/* ------------------ FUNGSI BANTUAN ------------------- */
	/* ----------------------------------------------------- */

	// Membaca matrix
	public void readMatrix()
	{
		double inpt;

		for (int i=0; i<getBaris(); i++)
		{
			for (int j=0; j<getKolom(); j++)
			{
				inpt = input.nextDouble();
				setElmt(i,j,inpt);
			}
		}
	}

	// Menulis matrix
	public void tulisMatrix()
	{
		for (int i=0; i<getBaris(); i++)
		{
			for (int j=0; j<getKolom()-1; j++)
			{
				System.out.print(getElmt(i,j) + " ");
			}
			System.out.println(getElmt(i,getKolom()-1));
			if (i!=getBaris()-1)
			{
				System.out.println("");
			}
		}
	}

	// Mengkalikan konstanta dengan matrix
	//     [ 1 ]   [ 2 ]
	// 2 x [ 4 ] = [ 8 ]
	//     [ 2 ]   [ 4 ]
	public void xKaliMatrix(double x)
	{
		for (int i=0; i<this.getBaris(); i++)
		{
			for (int j=0; j<this.getKolom(); j++)
			{
				this.arr[i][j] *= x;
			}
		}
	}

	// Menambah elemen matrix[i][j] dengan x
	public void elmtTambahX(int i, int j, double x)
	{
		this.arr[i][j] += x;
	}

	// Mengurang elemen matrix[i][j] dengan x
	public void elmtKurangX(int i, int j, double x)
	{
		this.arr[i][j] -= x;
	}

	// Menukar baris dengan baris lain
	public void swapBaris(int a, int b)
	{
		double temp = 0;
		for (int j=0; j<this.getKolom(); j++)
		{
			temp = this.arr[a][j];
			this.arr[a][j] = this.arr[b][j];
			this.arr[b][j] = temp;
		}
	}

	// Membuat transpose matrix
	public Matrix createTranspose()
	{
		Matrix ans;
		ans = new Matrix(this.kolom, this.baris);

		for (int i=0; i<getBaris(); i++)
		{
			for (int j=0; j<getKolom(); j++)
			{
				ans.arr[i][j] = this.arr[i][j];
			}
		}
		return ans;
	}

	/* ----------------------------------------------------- */
	/* --------------------- CHECKING ---------------------- */
	/* ----------------------------------------------------- */

	// Mengecek apakah matrix kosong
	// matrix kosong = []
	public boolean isKosong()
	{
		boolean ans;
		if (getBaris()==0 && getKolom()==0)
		{
			ans = true;
		}
		else
		{
			ans = false;
		}
		return ans;
	}

	// Mengecek apakah matrix bernilai 0
	// [ 0 0 0 ]
	// [ 0 0 0 ] = true
	// [ 0 0 0 ]
	public boolean isMatrix0()
	{
		boolean ans = true;
		if (this.isKosong())
		{
			return ans;
		}
		else
		{
			for (int i=0; i<this.getBaris(); i++)
			{
				for (int j=0; j<this.getKolom(); j++)
				{
					if(this.getElmt(i,j) != 0)
					{
						ans = false;
					}
				}
			}
		}
		return ans;
	}

	// Mengecek apakah matrix merupakan matrix identitas
	// syarat: 1 di diagonal utama dan sisanya 0
	// [ 1 0 0 ]
	// [ 0 1 0 ] = true
	// [ 0 0 1 ]
	public boolean isIdentity()
	{
		boolean ans = true;
		if (ans)
		{
			for (int i=0; i<getBaris(); i++)
			{
				for (int j=0; j<getKolom(); j++)
				{
					if (i==j)
					{
						ans = ans && (getElmt(i,j)==1);
					}
					else
					{
						ans = ans && (getElmt(i,j)==0);
					}
				}
			}
		}
		return ans;
	}

	// Mengecek apakah matrix memiliki
	// baris dan kolom yang sama
	public boolean isSquare()
	{
		boolean ans;
		if (getBaris()==getKolom())
		{
			ans = true;
		}
		else
		{
			ans = false;
		}
		return ans;
	}

	// Mengecek apakah baris ke-i = 0
	// [ 1 0 0 ] = false
	// [ 0 0 0 ] = true
	public boolean isBaris0(int i)
	{
		boolean ans = true;
		for (int j=0; j<this.getKolom(); j++)
		{
			if (this.getElmt(i,j) != 0)
			{
				ans = false;
			}
		}
		return ans;
	}

	// Mengecek apakah kolom ke-j = 0
	// [ 0 ]           [ 0 ]
	// [ 1 ] = false   [ 0 ] = true
	// [ 0 ]           [ 0 ]
	public boolean isKolom0(int j)
	{
		boolean ans = true;
		for (int i=0; i<this.getBaris(); i++)
		{
			if (this.getElmt(i,j) != 0)
			{
				ans = false;
			}
		}
		return ans;
	}

	// Mengecek apakah arr[i][j] = 0
	public boolean isElmt0(int i, int j)
	{
		return (this.arr[i][j] == 0);
	}

	// Mengecek apakah arr[i][j] = 1
	public boolean isElmt1(int i, int j)
	{
		return (this.arr[i][j] == 1);
	}


}