import java.util.*;
import java.io.*;

public class Interpolasi 
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
                            jawaban.append("a");
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
                System.out.printf("Dapat dilihat bahwa matrix memiliki solusi unik yaitu:\n");

                // Melakukan saving ke buffer 
                // yang nantinya akan di lakukan
                // printing ke file
                finalResult.append("\n");
                finalResult.append("Dapat dilihat bahwa matrix memiliki solusi unik yaitu:\n");

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
                System.out.println("Matrix memiliki tak hingga solusi sehingga tidak dapat dihitung interpolasinya.");          
            }
        } 
        else 
        {
            System.out.println("Dari matrix di atas, dapat dilihat bahwa matrix tidak memiliki solusi.");
        }

    }

    public static void interpolasiPolinom(String outFileName, boolean readFromFile, Scanner readFileScanner)
    {
        
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
            System.out.println("n tidak boleh 0");
        }
        else if (n < 0)
        {
            System.out.println("n tidak boleh bernilai negatif");
        }
        else
        {
            Matrix M = new Matrix(n+1,2);

            // Menginput manual suatu matrix
            if(!readFromFile)
            {
            	System.out.println("Silakan Input Titik:");
            	M.readMatrix(input);
            } 
            // Membaca matrix dari file.txt 
            else 
            {
            	M.readMatrix(readFileScanner);
            }

            System.out.println("Titik yang Anda inputkan adalah: ");
            
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

            // Membuat sistem persamaan dalam bentuk matriks augmented

            Matrix A = new Matrix(n+1,n+2);

            // Inisiasi seluruh nilai matriks dengan 1

            for (int i=0; i<n+1; i++){
                for (int j=0; j<n+2; j++){
                    A.arr[i][j] = 1;
                }
            }

            // Memasukkan nilai X ke dalam matriks
            for (int i=0; i<n+1; i++){
                for (int j=1; j<n+1; j++){
                    for (int k=1; k<=j; k++){
                        A.arr[i][j] *= M.getElmt(i,0);
                    }

                }
            }

            // Memasukkan nilai Y ke dalam matriks
            for (int i=0; i<n+1; i++){
                A.arr[i][n+1] = M.getElmt(i,1);
            }

            // Printing pada terminal dan saving ke buffer matriks augmented yang dihasilkan

            System.out.printf("Display Matrix Augmented dari sistem persamaan lanjar\n");
            outputTxt.append("Display Matrix Augmented dari sistem persamaan lanjar\n");

            A.tulisMatrix();
            outputTxt.append(A.toString());

            // Merapikan tampilan

            System.out.printf("\n");
            outputTxt.append("\n");

            // Menjalankan eliminasi Gauss pada Matriks Augmented
            System.out.println("Jalankan eliminasi Gauss pada matriks di atas");
            outputTxt.append("Jalankan eliminasi Gauss pada matriks di atas");

            obeSolver(A);
            
            System.out.printf("Diperoleh persamaan regresi linear berganda berupa\n");
	        outputTxt.append("Diperoleh persamaan regresi linear berganda berupa:\n");
	        System.out.printf("y = ");
	        outputTxt.append("y = ");
	        System.out.printf("%f", A.getElmt(0,A.getKolom()-1));
	        outputTxt.append(A.getElmt(0,A.getKolom()-1));

	        for (int i = 1; i < A.getBaris(); i++)
	        {
	        	// Melakukan printing pada terminal dan
	        	// Melakukan saving ke buffer 
				// yang nantinya akan di lakukan
				// printing ke fil
	            System.out.printf(" + ");
	            outputTxt.append(" + ");
	            System.out.printf("%f", A.getElmt(i,A.getKolom()-1));
	            outputTxt.append(A.getElmt(i,A.getKolom()-1));
                if (i==1)
                {
                    System.out.printf("x");
                    outputTxt.append("x");
                } else {
                    System.out.printf("x^%d", i);
                    outputTxt.append("x^");
                    outputTxt.append(i);                    
                }

	        }

	        // Melakukan printing pada terminal dan
        	// Melakukan saving ke buffer 
			// yang nantinya akan di lakukan
			// printing ke fil
	        System.out.printf("\n\n");
	        outputTxt.append("\n\n");

            // Melakukan tafsiran nilai x
            // Akan menerima nilai x dari pengguna

            boolean uji = true;
            double x;
            double component;
            int inputUji;

            while (uji)
            {
                double sum = 0;
                // Melakukan printing pada terminal
                System.out.print("Masukkan nilai x yang akan ditaksir: ");
                x = input.nextDouble();
                // Melakukan saving ke buffer
                outputTxt.append("Masukkan nilai x yang akan ditaksir: ");
                outputTxt.append(x);
                outputTxt.append("\n");

                for (int i=0; i<n+1; i++)
                {
                    component = A.getElmt(i, n+1);
                    component *= Math.pow(x,i);

                    sum += component;
                }

                System.out.printf("Hasil tafsiran nilai fungsi adalah %.5f",sum);
                // Saving ke buffer
                outputTxt.append("Hasil tafsiran nilai fungsi adalah ");
                outputTxt.append(sum);
                outputTxt.append("\n");

                System.out.println("\n");
                System.out.println("Apakah masih ingin melakukan tafsiran?");
                System.out.println("1. Ya");
                System.out.println("2. Tidak\n");
                inputUji = input.nextInt();


                if (inputUji == 2){
                    uji = false;
                }

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
