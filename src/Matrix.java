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

	// Mengambil lead dari suatu baris
	public int getLead1(int i)
	{
        boolean found = false;
        int ans = 0;
        for (int j = 0; ((j < this.getKolom()) && !found); j++)
        {
            if (this.getElmt(i,j) == 1)
            {
                ans = j;
                found = true;
            }
        }
        return ans;
    }

    // Melakukan pengaturan sedemikian rupa sehingga
    // terbentuk leading-1 pada suatu baris
	public void setLead1(int i,int k)
	{
        if ((this.getElmt(i,k) != 1) && (this.getElmt(i,k) != 0))
        {
            double koefisien = getElmt(i,k);
            for (int j = k; j < this.getKolom(); j++) 
            {
            	this.setElmt(i, j, ((getElmt(i, j)) / koefisien));
            }
        }
    }

   	

	/* ----------------------------------------------------- */
	/* ------------------ FUNGSI BANTUAN ------------------- */
	/* ----------------------------------------------------- */

	// Membaca inputan pengguna
	// lalu mengisikannya pada matrix
	public void readMatrix(Scanner readScan)
	{
		double inpt;

		for (int i=0; i<getBaris(); i++)
		{
			for (int j=0; j<getKolom(); j++)
			{
				inpt = readScan.nextDouble();
				setElmt(i,j,inpt);
			}
		}
	}

	// Mengoutputkan isi dari sebuah matrix
	public void tulisMatrix()
	{
		for (int i=0; i<getBaris(); i++)
		{
			for (int j=0; j<getKolom()-1; j++)
			{
				System.out.print(getElmt(i,j) + " ");
			}
			System.out.println(getElmt(i,getKolom()-1));
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

	// Menambah elemen matrix[i][j] dengan 
	// sebuah bilangan x
	public void elmtTambahX(int i, int j, double x)
	{
		this.arr[i][j] += x;
	}

	// Mengurang elemen matrix[i][j] dengan 
	// sebuah bilangan x
	public void elmtKurangX(int i, int j, double x)
	{
		this.arr[i][j] -= x;
	}

	// Menjumlahkan baris i1 dengan konstanta kali baris i2
	public void plusBaris(int i1, int i2, double konstanta)
	{
		for (int j = 0; j < this.kolom; j++)
		{
		    this.arr[i1][j] = (Math.round((this.arr[i1][j] + (konstanta * this.arr[i2][j])) * 100.0)/100.0);
		}
    }

    // Mengurangibaris i1 dengan konstanta kali baris i2
   	public void minusBaris(int i1, int i2, double konstanta)
   	{
		for (int j = 0; j < this.kolom; j++)
		{
		    this.arr[i1][j] = (Math.round((this.arr[i1][j] - (konstanta * this.arr[i2][j])) * 100.0)/100.0);
		}
    }

	// Menukar baris yang satu dengan baris lain
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

	// Menukar baris apabila matrix dengan baris i 
	// dan kolom j bernilai 0
	public int swapBarisII0(int i,int j)
	{
        if (this.getElmt(i,j) == 0)
        {
            boolean found = false;
            int ans = 0;
            for (int l = i; ((l < this.getBaris()) && !found); l++)
            {
                ans = l;
                if (this.getElmt(l,j) != 0) found = true;
            }
            if (found)
            {
                this.swapBaris(i,ans);
                return ans;
            }
            else 
            {
            	return -1;
            }
        }
        else
        {
        	return -1;
        }
    }

    // Fungsi bantuan untuk fungsi mengecek
    // suatu matrix memiliki solusi atau tidak
    public boolean solvableHelper(int brs)
    {
        boolean ans = true;
        for (int i = 0; i < kolom; i++)
        {
            if((i != kolom - 1) && (arr[brs][i] != 0))
            {
                ans = false;
            }
            
            if ((i == kolom - 1) && (arr[brs][i] == 0))
            {
                ans = false;
            }
        }
        return (ans);
    }

	// Mengubah matrix semula menjadi
	// transpose matrixnya
	// M => M^T
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
	public boolean isIdentitas()
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

	// Mengecek apakah matrix merupakan matrix segitiga atas
	// syarat: semua bilangan di bawah diagonal utama bernilai 0
	// [ 3 6 4 ]
	// [ 0 9 7 ] = true 
	// [ 0 0 1 ]
	public boolean isSegitigaAtas()
	{
		boolean ans = true;
		if (ans)
		{
			for (int i=0; i<getBaris(); i++)
			{
				for (int j=0; j<getKolom(); j++)
				{
					if (i>j)
					{
						if (getElmt(i,j) != 0)
						{
							ans = false;
						}
					}
				}
			}
		}
		return ans;
	}

	// Mengecek apakah matrix merupakan matrix segitiga bawah
	// syarat: semua bilangan di atas diagonal utama bernilai 0
	// [ 3 0 0 ]
	// [ 4 5 0 ] = true
	// [ 3 8 8 ]
	public boolean isSegitigaBawah()
	{
		boolean ans = true;
		if (ans)
		{
			for (int i=0; i<getBaris(); i++)
			{
				for (int j=0; j<getKolom(); j++)
				{
					if (i<j)
					{
						if (getElmt(i,j) != 0)
						{
							ans = false;
						}
					}
				}
			}
		}
		return ans;
	}

	// Mengecek apakah matrix memiliki
	// jumlah baris dan kolom yang sama
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

	// Mengecek apakah baris dan kolom 
	// dari matrix M1 dan M2 bernilai sama
	public static boolean IsSizeSama(Matrix M1, Matrix M2)
	{
        return (M1.getBaris() == M2.getBaris() && M1.getKolom() == M2.getKolom());
    }

    // Mengecek apakah suatu SPL dapat diselesaikan
    // (memiliki solusi baik unik ataupun tak hingga) 
    // atau tidak memiliki solusi sama sekali
    public static boolean isSolvable(Matrix M)
    {
        boolean solve = true;
        for (int i = 0; ((i < M.getBaris()) && (solve)); i++)
        {
            if (M.solvableHelper(i))
            {
            	solve = false;
            }
        }
        return solve;
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

  

	/* ----------------------------------------------------- */
	/* --------------------- WRITING ----------------------- */
	/* ----------------------------------------------------- */

	// Menuliskan matrix pada file
    @Override
    public String toString()
    {
    	StringBuilder ans = new StringBuilder();
    	for (int i=0; i<getBaris(); i++)
		{
			for (int j=0; j<getKolom()-1; j++)
			{
				ans.append(getElmt(i,j));
				ans.append(" ");
			}
			ans.append(getElmt(i,getKolom()-1));
			ans.append("\n");
		}
		return ans.toString();
    }


}