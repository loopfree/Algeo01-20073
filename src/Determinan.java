import java.util.*;
import java.io.*;
        
public class Determinan
{
	static Scanner input = new Scanner(System.in);

    static StringBuilder finalResult;

    /* ----------------------------------------------------------------- */
    /* ------------------------- REDUKSI BARIS ------------------------- */
    /* ----------------------------------------------------------------- */
    public static void metodeReduksiBaris(String outFileName, boolean readFromFile, Scanner readFileScanner)
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
        else if (n == 1)
        {
            System.out.println("Matrix 1x1 tidak memiliki determinan.");
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

            // Edge Case Pertama
            // Matrix masukan merupakan matrix identitas
	        if (M.isIdentitas())
	        {
	        	System.out.printf("Setelah dijalankan OBE, diperoleh hasil determinan dari matrix adalah: 1");
	        	finalResult.append("Setelah dijalankan OBE, diperoleh hasil determinan dari matrix adalah: 1");
	        }

	        // Edge Case Kedua
	        // Matrix masukan merupakan matrix segitiga atas
	        else if (M.isSegitigaAtas())
	        {
	        	float ans = 1;
	        	for (int i=0; i<n; i++)
	        	{
	        		ans *= M.getElmt(i,i);
	        	}
	        	System.out.printf("Setelah dijalankan OBE, diperoleh hasil determinan dari matrix adalah: ");
	        	finalResult.append("Setelah dijalankan OBE, diperoleh hasil determinan dari matrix adalah: ");
	        	System.out.println(ans);
	        	finalResult.append(ans);

	        }

	        // Edge Case Ketiga
	        // Matrix masukan merupakan matrix segitiga bawah
	        else if (M.isSegitigaBawah())
	        {
	        	float ans = 1;
	        	for (int i=0; i<n; i++)
	        	{
	        		ans *= M.getElmt(i,i);
	        	}
	        	System.out.printf("Setelah dijalankan OBE, diperoleh hasil determinan dari matrix adalah: ");
	        	finalResult.append("Setelah dijalankan OBE, diperoleh hasil determinan dari matrix adalah: ");
	        	System.out.println(ans);
	        	finalResult.append(ans);
	        }

	        else
	        {
	        	System.out.printf("Setelah dijalankan OBE, diperoleh hasil determinan dari matrix adalah: ");
	        	finalResult.append("Setelah dijalankan OBE, diperoleh hasil determinan dari matrix adalah: ");

	            boolean flag = false;
	            double swapCount = 0;
	            double det = 1;
	            double ans;
	            for (int i = 0; ((i < M.getBaris()) && (!flag)); i++)
	            {
	                if (M.getElmt(i,i) == 0)
	                {
	                    // Tidak ada yang bisa di swap, artinya kolom bernilai 0 semua.
	                    if (M.getElmt(i,i) == 0)
	                    {
	                        flag = true;
	                    } 
	                    else 
	                    {
	                        swapCount += 1;
	                    }
	                }

	                if (!flag)
	                {
	                    for (int l = i+1; l < M.getBaris(); l++)
	                    {
	                        double koefisien;
	                        koefisien = M.getElmt(l,i)/M.getElmt(i,i);
	                        M.minusBaris(l,i,koefisien);
	                    }
	                    det *= M.getElmt(i,i);
	                } else det *= 0;
	            }
	            ans = det * Math.pow(-1,swapCount);
	            System.out.println(ans);
	            finalResult.append(ans);
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
    /* --------------------------- KOFAKTOR ---------------------------- */
    /* ----------------------------------------------------------------- */
	public static void Kofaktor(String outFileName, boolean readFromFile, Scanner readFileScanner) 
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
		else if (n == 1)
        {
            System.out.println("Matrix 1x1 tidak memiliki determinan.");
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

			System.out.printf("Dengan menggunakan metode ekspansi kofaktor, diperoleh hasil determinan dari matrix adalah: \n");
			finalResult.append("Dengan menggunakan metode ekspansi kofaktor, diperoleh hasil determinan dari matrix adalah: \n");

			//Hasil perhitungan
			double ans;
			ans = DetKofaktor(M);
			System.out.println(ans);
			finalResult.append(ans);

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

	//Fungsi untuk menghitung determinan dengan Kofaktor
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
}