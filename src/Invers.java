import java.util.*;
import java.io.*;

public class Invers 
{
    static Scanner input = new Scanner(System.in);

    static StringBuilder finalResult;

    /* ----------------------------------------------------------------- */
    /* ----------------------------- ADJOIN ---------------------------- */
    /* ----------------------------------------------------------------- */
    public static void inversAdjoin(String outFileName, boolean readFromFile, Scanner readFileScanner)
    {
        finalResult = new StringBuilder();

    	int n;
    	// Menginput manual suatu matrix
    	if(!readFromFile)
        {
    		System.out.print("Masukkan nilai n: ");
	        n = input.nextInt();	
    	}
    	// Membaca matrix dari file.txt 
        else
        {
    		n = readFileScanner.nextInt();
    	}

        if (n==0)
        {
            System.out.println("Baris dan kolom anda akan membentuk matrix kosong.");
        }
        else if (n < 0)
        {
            System.out.println("Baris dan kolom sebuah matrix tidak boleh bernilai negatif.");
        }
        else
        {
            System.out.println("Silahkan Input Matrix Anda:");

            Matrix M = new Matrix(n, n);

            // Asumsikan inputan pengguna sudah benar
            // Apabila inputan pengguna salah, maka akan otomatis dibenarkan
            // Misalnya n = 2
            // Input pengguna berupa:
            // 1 2 3
            // 4 5 6
            // Maka akan di autocorrect menjadi
            // 1 2
            // 3 4

            // Menginput manual suatu matrix
            if(!readFromFile)
            {
            	M.readMatrix(input);
            }
            // Membaca matrix dari file.txt 
            else 
            {
            	M.readMatrix(readFileScanner);
            }

            // Melakukan printing pada terminal
            System.out.print(dekorAtas("PERSOALAN"));
            M.tulisMatrix();
            System.out.print(dekorBawah(21));

            // Melakukan saving ke buffer 
            // yang nantinya akan di lakukan
            // printing ke file
            finalResult = new StringBuilder();
            finalResult.append(dekorAtas("PERSOALAN"));
            finalResult.append(M.toString());
            finalResult.append(dekorBawah(21));
            
            Matrix invers = new Matrix(n,n);
            double det = DetKofaktor(M);

            //Matrix memiliki balikan
            if (det != 0)
            {
                //Membuat matrix adjoin dengan mentranspose matrix kofaktor
                Matrix adj = matKofaktor(M).createTranspose();

                //Matrix balikan
                for (int i = 0; i < n; i++)
                {
                    for (int j = 0; j < n; j++)
                    {
                        invers.arr[i][j] = adj.arr[i][j]/det;
                    }
                }

                System.out.println("Matrix balikan dari matrix diatas adalah : \n");
                finalResult.append("Matrix balikan dari matrix diatas adalah : \n"); 

                // Melakukan printing pada terminal
                System.out.print(dekorAtas("PENYELESAIAN"));
                invers.tulisMatrix();
                System.out.print(dekorBawah(24));

                // Melakukan saving ke buffer 
                // yang nantinya akan di lakukan
                // printing ke file
                finalResult.append(dekorAtas("PENYELESAIAN"));
                finalResult.append(invers.toString());
                finalResult.append(dekorBawah(24));

            }

            //Determinan matrix = 0, tidak memiliki balikan.
            else 
            {
                System.out.println("Karena determinan dari matrix diatas adalah 0, maka matrix tidak memiliki balikan.");

                finalResult.append("Karena determinan dari matrix diatas adalah 0, maka matrix tidak memiliki balikan.");   
            }

            try
			{
	
				// Melakukan printing ke file
				FileWriter file = new FileWriter(outFileName);
	
				PrintWriter pw = new PrintWriter(file);
	
				pw.print(finalResult.toString());
				pw.flush();
			} 
			catch(Exception e) 
			{
				e.getStackTrace();
			}

        }

    }

    //Membuat minor entri dari matrix
    public static Matrix Minor(Matrix M, int row, int column)
    {
        Matrix temp = new Matrix(M.getBaris()-1, M.getKolom()-1);

        int x = 0; 
        int y = 0;

        //Memasukkan elemen yang bukan berada di row dan column ke matrix temp
        for (int i = 0; i < M.getBaris(); i++)
        {
            if ( i != row)
            {
                for (int j = 0; j < M.getKolom(); j++)
                {
                    if ( j!= column)
                    {
                        temp.arr[x][y] = M.arr[i][j];
                        y++;

                        //Mereset indeks kolom
                        if (y >= temp.getKolom())
                        {
                            y = 0;
                            x++;
                        }
                    }
                }
            }
        }
        return temp;
    }


    //Membuat matrix kofaktor
    public static Matrix matKofaktor(Matrix M)
    {
        int sign;
        Matrix temp = new Matrix(M.getBaris(), M.getKolom());

        for (int i = 0; i < M.getBaris(); i++)
        {
            for (int j = 0; j < M.getKolom(); j++)
            {
                if ((i+j)%2 == 0)
                {
                    sign = 1;
                }
                else
                {
                    sign = -1;
                }

                temp.arr[i][j] = sign * DetKofaktor(Minor(M, i, j));
            }
        }
        return temp;
    }

    //Menghitung determinan matrix
    public static double DetKofaktor(Matrix M) 
	{
		int i,j,k,x,y;
		double det;
		Matrix temp = new Matrix(M.getBaris() - 1, M.getKolom()-1);

		//Matriks 2x2
		if (M.getBaris() == 2 && M.getKolom() == 2)
		{
			return (M.getElmt(0, 0)*M.getElmt(1, 1) - M.getElmt(0, 1)* M.getElmt(1, 0));
		}

		//Membuat minor dari matriks
		else
		{
			det = 0;
			for ( i = 0; i < M.getBaris(); i++)
			{
				x = 0;
				for (j = 1; j < M.getKolom(); j++)
				{
					y = 0;
					for (k = 0; k < M.getBaris(); k++)
					{
						if ( k != i)
						{
							temp.arr[x][y] = M.arr[j][k];
							y++;
						}
					}
					x++;
				}

				//Menghitung determinan kofaktor secara baris
				if (i % 2 == 0)
				{
					det += M.getElmt(0, i) * DetKofaktor(temp); 
				}
				
				else 
				{
					det -= M.getElmt(0, i) * DetKofaktor(temp);
				}
			}
			return det;
		}
	} 


    /* ----------------------------------------------------------------- */
    /* ----------------------- FUNGSI DEKORASI ------------------------- */
    /* ----------------------------------------------------------------- */

	// Menerima banyaknya kolom dari suatu matrix
	// lalu mengoutputkan "-" sebanyak kolom
	// dan kata middleText yang dilanjutkan lagi
	// dengan output "-" sebanyak kolom;
	public static String dekorAtas(String middleText)
	{
		StringBuilder result = new StringBuilder();
		int length = 6;

		for (int i=0; i<length; i++)
		{
			result.append("-");
		}
		result.append(middleText);

		for (int i=0; i<length; i++)
		{
			result.append("-");
		}
		result.append("\n");
		return result.toString();
	}

    // Menerima dekorLength lalu mengoutputkan
    // "-" sebanyak dekorLength
    // Note: soal = 21, solusi = 24
	public static String dekorBawah(int dekorLength)
	{
		StringBuilder result = new StringBuilder();
		for (int i=0; i<dekorLength; i++)
		{
			result.append("-");
		}
		result.append("\n");
		return result.toString();
	}

    /* ----------------------------------------------------------------- */
    /* ----------------------- GAUSS-JORDAN ---------------------------- */
    /* ----------------------------------------------------------------- */

    public static void inversGaussJordan(String outFileName, boolean readFromFile, Scanner readFileScanner) 
    {
        finalResult = new StringBuilder();

    	int n;
    	// Menginput manual suatu matrix
    	if(!readFromFile)
        {
    		System.out.print("Masukkan nilai n: ");
	        n = input.nextInt();	
    	}
    	// Membaca matrix dari file.txt 
        else
        {
    		n = readFileScanner.nextInt();
    	}

        if (n==0)
        {
            System.out.println("Baris dan kolom anda akan membentuk matrix kosong.");
        }
        else if (n < 0)
        {
            System.out.println("Baris dan kolom sebuah matrix tidak boleh bernilai negatif.");
        }
        else
        {
            System.out.println("Silahkan Input Matrix Anda:");

            Matrix M = new Matrix(n, n);

            // Asumsikan inputan pengguna sudah benar
            // Apabila inputan pengguna salah, maka akan otomatis dibenarkan
            // Misalnya n = 2
            // Input pengguna berupa:
            // 1 2 3
            // 4 5 6
            // Maka akan di autocorrect menjadi
            // 1 2
            // 3 4

            // Menginput manual suatu matrix
            if(!readFromFile)
            {
            	M.readMatrix(input);
            }
            // Membaca matrix dari file.txt 
            else 
            {
            	M.readMatrix(readFileScanner);
            }

            // Melakukan printing pada terminal
            System.out.print(dekorAtas("PERSOALAN"));
            M.tulisMatrix();
            System.out.print(dekorBawah(21));

            // Melakukan saving ke buffer 
            // yang nantinya akan di lakukan
            // printing ke file
            finalResult = new StringBuilder();
            finalResult.append(dekorAtas("PERSOALAN"));
            finalResult.append(M.toString());
            finalResult.append(dekorBawah(21));
            
            // Membuat matriks identitas

            Matrix I = new Matrix(n,n);

            for (int i=0; i<n; i++)
            {
                for(int j=0; j<n; j++)
                {
                    if (i==j)
                    {
                        I.arr[i][j] = 1;
                    }
                    else
                    {
                        I.arr[i][j] = 0;
                    }
                }
            }

            /// tes
            I.tulisMatrix();

            if (!M.isMatrix0())
            {
                int obe = 0;
                for (int i = 0; i < M.getBaris(); i++)
                {
                    if (obe < M.getKolom())
                    {
                        while ((M.getElmt(i,obe) == 0) && (obe < M.getKolom()-1))
                        {
                            if ((M.getElmt(i,obe) == 0)) 
                            {
                                obe++;
                            }
                        }
                        boolean sudahlead1 = (M.getElmt(i, obe) == 1);
                        boolean sudahkosong = (M.getElmt(i, obe) == 0);
                        if (!sudahlead1 && !sudahkosong)
                        {
                            M.setLead1(i, obe);
                            I.setLead1(i, obe);
                            M.tulisMatrix();
                            I.tulisMatrix();
                        }
                        if ((obe < M.getKolom()) && (M.getElmt(i,obe) != 0))
                        {
                            for (int l = i + 1; l < M.getBaris(); l++)
                            {
                                if (l != i)
                                {
                                    double coef = M.getElmt(l,obe)/M.getElmt(i,obe);
                                    if (coef != 0)
                                    {
                                        M.minusBaris(l, i, coef);
                                        I.minusBaris(l, i, coef);
                                        M.tulisMatrix();
                                        I.tulisMatrix();
                                        System.out.println("\n");
                                    }
                                }
                            }
                        }
                        obe++;
                    }
                }
            }

            int obe2 = 0;
            for (int i = 0; i < M.getBaris(); i++)
            {
                if (obe2 < M.getKolom())
                {
                    while ((M.getElmt(i,obe2) == 0) && (obe2 < M.getKolom()-1))
                    {
                        if ((M.getElmt(i,obe2) == 0))
                        {
                            obe2++;
                        }
                    }
                    boolean sudahlead1 = (M.getElmt(i, obe2) == 1);
                    boolean sudahkosong = (M.getElmt(i, obe2) == 0);
                    if (!sudahlead1 && !sudahkosong)
                    {
                        M.setLead1(i, obe2);
                        I.setLead1(i, obe2);
                        M.tulisMatrix();
                        I.tulisMatrix();
                    }
                    if ((obe2 < M.getKolom()) && (M.getElmt(i,obe2) != 0))
                    {
                        for (int l = 0; l < M.getBaris(); l++)
                        {
                            if (l != i)
                            {
                                double coef = M.getElmt(l,obe2)/M.getElmt(i,obe2);
                                if (coef != 0)
                                {
                                    M.minusBaris(l, i, coef);
                                    I.minusBaris(l, i, coef);
                                    M.tulisMatrix();
                                    I.tulisMatrix();
                                    System.out.println("\n");
                                }
                            }
                        }
                    }
                    obe2++;
                }
            }

            if (!M.isIdentitas())
            {
                System.out.print("Karena ada baris bernilai 0, matrix tidak memiliki balikan");
                finalResult.append("Karena ada baris bernilai 0, maka matrix tidak memiliki balikan.");   
            }
            else
            {
                // Melakukan printing pada terminal
                System.out.print(dekorAtas("PENYELESAIAN"));
                M.tulisMatrix();
                System.out.print(dekorBawah(24));
                
                // Melakukan saving ke buffer 
                // yang nantinya akan di lakukan
                // printing ke file
                finalResult.append(dekorAtas("PENYELESAIAN"));
                finalResult.append(M.toString());
                finalResult.append(dekorBawah(24));

                // Melakukan printing pada terminal
                System.out.print(dekorAtas("PENYELESAIAN"));
                I.tulisMatrix();
                System.out.print(dekorBawah(24));
                
                // Melakukan saving ke buffer 
                // yang nantinya akan di lakukan
                // printing ke file
                finalResult.append(dekorAtas("PENYELESAIAN"));
                finalResult.append(I.toString());
                finalResult.append(dekorBawah(24));

            }

            try
			{
	
				// Melakukan printing ke file
				FileWriter file = new FileWriter(outFileName);
	
				PrintWriter pw = new PrintWriter(file);
	
				pw.print(finalResult.toString());
				pw.flush();
			} 
			catch(Exception e) 
			{
				e.getStackTrace();
			}

        
            
    }  
}
}
