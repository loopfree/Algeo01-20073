import java.util.*;
import java.io.*;
        
public class Regresi
{
	static Scanner input = new Scanner(System.in);

    static StringBuilder finalResult;

    static StringBuilder outputTxt;

    /* ----------------------------------------------------------------- */
    /* -------------------------- OBE SOLVER --------------------------- */
    /* ----------------------------------------------------------------- */
    public static void obeSolver(Matrix M)
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
                            }
                        }
                    }
                }
                obe++;
            }
        }
        obeHelper(M, true);
    }

    public static void obeHelper(Matrix M, boolean jordan)
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
                }
                if ((obe < M.getKolom()) && (M.getElmt(i,obe) != 0))
                {
                    for (int l = 0; l < M.getBaris(); l++)
                    {
                        if (l != i)
                        {
                            double coef = M.getElmt(l,obe)/M.getElmt(i,obe);
                            if (coef != 0)
                            {
                                M.minusBaris(l, i, coef);
                            }
                        }
                    }
                }
                obe++;
            }
        }

        if (Matrix.isSolvable(M))
        {
            finalResult = new StringBuilder();

            StringBuilder jawaban = new StringBuilder();
            boolean unik = true;
            for (int i = 0; i < M.getBaris(); i++)
            {
                if (!M.isBaris0(i))
                {
                    
                    for (int j = M.getLead1(i); j < M.getKolom()-1; j++)
                    {
                        if (j == M.getLead1(i)) 
                        {
                            // Melakukan saving ke buffer 
                            // yang nantinya akan di lakukan
                            // printing ke file
                            jawaban.append("x");
                            jawaban.append(j);
                            jawaban.append(" = ");
                            jawaban.append(M.getElmt(i,M.getKolom()-1));
                        }
                        else 
                        {
                            if (M.getElmt(i,j) < 0)
                            {
                                unik = false;
                            }
                            if (M.getElmt(i,j) > 0)
                            {
                                unik = false;
                            }
                        }        
                    }
                    jawaban.append("\n");
                }
            }
            if (unik==true)
            {
                // Melakukan printing pada terminal
                System.out.printf("Dari matrix di atas, dapat dilihat bahwa matrix memiliki solusi unik yaitu:\n");

                // Melakukan saving ke buffer 
                // yang nantinya akan di lakukan
                // printing ke file
                finalResult.append("Dari matrix di atas, dapat dilihat bahwa matrix memiliki solusi unik yaitu:\n");

                // Melakukan printing pada terminal
                System.out.println(jawaban.toString());

                // Melakukan saving ke buffer 
                // yang nantinya akan di lakukan
                // printing ke file
                finalResult.append(jawaban.toString() + "\n");
                outputTxt.append(finalResult.toString());
            }
            else
            {
                System.out.println("Matrix memiliki tak hingga solusi sehingga tidak dapat dihitung regresinya.");          
            }
        } 
        else 
        {
            System.out.println("Dari matrix di atas, dapat dilihat bahwa matrix tidak memiliki solusi.");
        }

    }

	/* ----------------------------------------------------------------- */
	/* -------------------- REGRESI LINEAR BERGANDA -------------------- */
	/* ----------------------------------------------------------------- */
    public static void regresiLinearBerganda(String outFileName, boolean readFromFile, Scanner readFileScanner)
    {
    	int n;
        int klm;

        // Menginput manual suatu matrix
    	if(!readFromFile)
        {
    		System.out.print("Masukkan nilai n: ");
	        n = input.nextInt();	
	        System.out.print("Masukkan jumlah kolom: ");
	        klm = input.nextInt();
    	}
    	// Membaca matrix dari file.txt 
        else
        {
    		n = readFileScanner.nextInt();
    		klm = readFileScanner.nextInt();
    	}

    	if (n==0 || klm==0)
        {
            System.out.println("Matrix yang Anda merupakan sebuah matrix kosong.");
        }
        else if (n < 0 || klm < 0)
        {
            System.out.println("Baris dan/atau kolom sebuah matrix tidak boleh bernilai negatif.");
        }
        else
        {
            Matrix M = new Matrix(n, klm);

            // Menginput manual suatu matrix
            if(!readFromFile)
            {
            	System.out.println("Silahkan Input Matrix Anda:");
            	M.readMatrix(input);
            } 
            // Membaca matrix dari file.txt 
            else 
            {
            	M.readMatrix(readFileScanner);
            }

            System.out.println("Matrix yang Anda inputkan adalah: ");
            
            // Melakukan printing pada terminal
            System.out.print(dekorAtas("PERSOALAN"));
            M.tulisMatrix();
            System.out.print(dekorBawah(21));

            // Melakukan saving ke buffer 
            // yang nantinya akan di lakukan
            // printing ke file
            outputTxt = new StringBuilder();
            outputTxt.append(dekorAtas("PERSOALAN"));
            outputTxt.append(M.toString());
            outputTxt.append(dekorBawah(21));

            // Note
            // nbo + b1 sigma(i to n) x1i + b2 sigma(i to n) x2i + ... = sigma(i to n) yi
            //  .																.
            //  .																.
            //  .																.
            // bo sigma(i to n) xki + b1 sigma(i to n) xki x2i + ... = sigma(i to n) xkiyi

            // Kalau dilihat dari contoh ada polanya
            // - diagonal utama berubah-ubah
            // - selain  diagonal utama nilainya hanyalah mirror terhadap diagonal utama

            // Gambaran kasar
            // [ a 1 2 ]
            // [ 1 b 3 ]
            // [ 2 3 c ]

            // Membuat A pada bentuk SPL Ax = B
            int square = M.getKolom();        
	        Matrix A = new Matrix(square, square);	        
	        A.setElmt(0,0,M.getBaris());
	        
	        for (int i = 0; i < square; i++)
	        {
	            for (int j = 0; j < square; j++)
	            {
	                if ((i != 0) && (j != 0))
	                {
	                    double hold = 0;                    
	                    for (int k = 0; k < M.getBaris(); k++)
	                    {
	                        hold += M.getElmt(k,i-1) * M.getElmt(k,j-1);
	                    }	                    
	                    A.setElmt(i,j,hold);
	                }
	                else if ((i == 0) && (j != 0))
	                {
	                    double hold = 0;	                    
	                    for (int k = 0; k < M.getBaris(); k++)
	                    {
	                        hold += M.getElmt(k,j-1);
	                    }	                    
	                    A.setElmt(i,j,hold);
	                }
	                else if ((i != 0) && (j == 0))
	                {
	                    double hold = 0;	                    
	                    for (int k = 0; k < M.getBaris(); k++)
	                    {
	                        hold += M.getElmt(k,i-1);
	                    }	                    
	                    A.setElmt(i,j,hold);
	                }
	            }
	        }
	        
	        // Membuat B pada bentuk SPL Ax = B
	        Matrix B = new Matrix(M.getKolom(),1);	        
	        for (int i = 0; i < M.getKolom(); i++)
	        {
	            if (i == 0)
	            {
	                double hold = 0;	                
	                for (int j = 0; j < M.getBaris(); j++)
	                {
	                    hold += M.getElmt(j,M.getKolom()-1);
	                }
	                B.setElmt(i,0,hold);
	            }
	            else
	            {
	                double hold = 0;	            
	                for (int j = 0; j < M.getBaris(); j++)
	                {
	                    hold += M.getElmt(j,M.getKolom()-1) * M.getElmt(j,i-1);
	                }	                
	                B.setElmt(i,0,hold);
	            }
	            
	        }

	        // Melakukan printing pada terminal dan
        	// Melakukan saving ke buffer 
			// yang nantinya akan di lakukan
			// printing ke fil
	        System.out.printf("Setelah menjalankan Normal Estimation Equation for Multiple Linear Regression, diperoleh SPL.\n\n");
	        outputTxt.append("Setelah menjalankan Normal Estimation Equation for Multiple Linear Regression, diperoleh SPL.\n\n");
	        
	        System.out.printf("Display SPL dalam bentuk Ax = B adalah:\n\n");
	        outputTxt.append("Display SPL dalam bentuk Ax = B adalah:\n\n");

	        System.out.printf("Display A\n");
	        outputTxt.append("Display A\n");
	        A.tulisMatrix();
	        outputTxt.append(A.toString());
	        System.out.println("");
	        outputTxt.append("\n");

	        System.out.printf("Display B\n");
	        outputTxt.append("Display B\n");
	        B.tulisMatrix();
	        outputTxt.append(B.toString());
	        System.out.println("");
	        outputTxt.append("\n");

	        // Menggabungkan A dan B dari Ax = B
	        // untuk menghasilkan matrix  augmented
	        int mergedBaris = A.getBaris();
	        int mergedKolom = A.getKolom()+B.getKolom();

	        Matrix merged = new Matrix(mergedBaris, mergedKolom);

	        for(int i = 0; i < mergedBaris; i++)
	        {
	            for(int j = 0; j < mergedKolom; j++)
	            {
	                if(j != mergedKolom-1)
	                {
	                    merged.setElmt(i, j, A.getElmt(i,j));
	                }
	                else
	                {
	                    merged.setElmt(i, j, B.getElmt(i,0));
	                }
	            }
	        }

	        // Melakukan printing pada terminal dan
        	// Melakukan saving ke buffer 
			// yang nantinya akan di lakukan
			// printing ke fil
	        System.out.printf("Display Matrix Augmented\n");
	        outputTxt.append("Display Matrix Augmented\n");

	        merged.tulisMatrix();
	        outputTxt.append(merged.toString());

	        // Merapihkan tampilan pada terminal dan file.txt
	        System.out.printf("\n");
	        outputTxt.append("\n");

	        // Menjalankan OBE
	        System.out.println("Setelah menjalankan OBE pada matrix di atas");
	        outputTxt.append("Setelah menjalankan OBE pada matrix di atas");
	        obeSolver(merged);

	        // merged.tulisMatrix();
	        // System.out.printf("baris = %d\n", merged.getBaris());
	        // System.out.printf("kolom = %d\n", merged.getKolom());

	        // Melakukan printing pada terminal dan
        	// Melakukan saving ke buffer 
			// yang nantinya akan di lakukan
			// printing ke fil
	        System.out.printf("Diperoleh persamaan regresi linear berganda berupa\n");
	        outputTxt.append("Diperoleh persamaan regresi linear berganda berupa:\n");
	        System.out.printf("y = ");
	        outputTxt.append("y = ");
	        System.out.printf("%f", merged.getElmt(0,merged.getKolom()-1));
	        outputTxt.append(merged.getElmt(0,merged.getKolom()-1));

	        for (int i = 1; i < merged.getBaris(); i++)
	        {
	        	// Melakukan printing pada terminal dan
	        	// Melakukan saving ke buffer 
				// yang nantinya akan di lakukan
				// printing ke fil
	            System.out.printf(" + ");
	            outputTxt.append(" + ");
	            System.out.printf("%f", merged.getElmt(i,merged.getKolom()-1));
	            outputTxt.append(merged.getElmt(i,merged.getKolom()-1));
	            System.out.printf("x%d", i);
	            outputTxt.append("x");
	            outputTxt.append(i);
	        }

	        // Melakukan printing pada terminal dan
        	// Melakukan saving ke buffer 
			// yang nantinya akan di lakukan
			// printing ke fil
	        System.out.printf("\n\n");
	        outputTxt.append("\n\n");

	        // Melakukan Pengujian
	        // Menginputkan nilai x1 sampai xn
	        // untuk memperoleh hasil regresi linear berganda
	        System.out.printf("Melakukan pengujian dengan input manual\n");

	        // Melakukan saving ke buffer 
			// yang nantinya akan di lakukan
			// printing ke fil
	        outputTxt.append("Melakukan pengujian dengan input manual\n");

	        double ans = 0;
	        double temp;
	        double manualInpt;
	        ans += merged.getElmt(0,merged.getKolom()-1);

	        for (int i = 1; i < merged.getBaris(); i++)
	        {
	            temp = merged.getElmt(i,merged.getKolom()-1);

	            // Melakukan printing pada terminal
	            System.out.printf("Masukkan nilai x%d: ", i);

	            // Melakukan saving ke buffer 
				// yang nantinya akan di lakukan
				// printing ke fil
	            outputTxt.append("Masukkan nilai x");
	            outputTxt.append(i);
	            outputTxt.append(": ");

	            // Meminta input pengguna
	            manualInpt = input.nextDouble();

	            // Melakukan saving ke buffer 
				// yang nantinya akan di lakukan
				// printing ke fil
	            outputTxt.append(manualInpt);
	            outputTxt.append("\n");

	            // Kalkulasi jawaban
	            ans += temp * manualInpt;
	        }

	        // Melakukan printing pada terminal
	        System.out.printf("\n");	        

	        System.out.printf("Hasilnya adalah: ");	        

	        System.out.printf("%f", ans);
	        
	        // Melakukan saving ke buffer 
			// yang nantinya akan di lakukan
			// printing ke fil
	        outputTxt.append("\n");
	        outputTxt.append("Hasilnya adalah: ");
	        outputTxt.append(ans);

	        try
	        {
		        // Melakukan printing ke file
		        FileWriter file = new FileWriter(outFileName);

		        PrintWriter pw = new PrintWriter(file);

		        pw.print(outputTxt.toString());
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

}