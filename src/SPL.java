import java.util.*;
import java.io.*;
        
public class SPL
{
    /*
    // Logic Awal Gauss dan Gauss Jordan
    // Hanya berlaku pada beberapa kasus

    static Scanner input = new Scanner(System.in);
    
    public static void swapRow(Matrix m, int baris1, int baris2)
    {
        for(int i = 0; i<m.getKolom(); i++)
        {
            double temp = m.getElmt(baris1, i);
            m.setElmt(baris1, i, m.getElmt(baris2, i));
            m.setElmt(baris2, i, temp);
        }
        return;
    }
    
    public static void checkRow(Matrix m)
    {
        for(int i = 0; i<m.getKolom()-1; i++)
        {
            if(m.getElmt(i,i) == 0)
            {
                if(i==0)
                {
                   swapRow(m, i, i+1);
                   checkRow(m);
                }
                else
                {
                    swapRow(m,i,i+1);
                    checkRow(m);
                }
            }
        }
    }

    public static void metodeGauss()
    {
        System.out.print("Jumlah baris matrix: ");
        int row = input.nextInt();

        System.out.print("Jumlah kolom matrix: ");
        int column = input.nextInt();

        System.out.println("GAUSS");

        Matrix m = new Matrix(row, column);
        m.readMatrix(input);

        System.out.println("Matrix yang anda inputkan adalah: ");
        m.tulisMatrix();
        
        checkRow(m);
        
        //buat Matrix segitiga bawah
        for(int i = 0; i < m.getBaris(); i++) //untuk looping semua baris
        {   
            for(int j = 0; j < i; j++)//untuk looping dari awal sampe n (membuat 0)
            {
                double divider = m.getElmt(i,j);

                if(divider != 0) //karena x/0 tidak boleh
                {
                    for(int k = 0; k < m.getKolom(); k++) // kurang semua kolom buat jadi 0
                    {
                        m.setElmt(i,k, m.getElmt(i,k)/divider- m.getElmt(j,k));
                    }
                }
            }
            
            double divider = m.getElmt(i,i);
            for(int j = 0; j < m.getKolom(); j++)
            {
                m.setElmt(i, j, m.getElmt(i,j)/divider);
            }
        }
        
        System.out.println("Hasil:");
        m.tulisMatrix();
    }

    public static void metodeGaussJordan()
    {
        //lakukan yang dilakukan di metode gauss
        System.out.print("Jumlah baris Matrix: ");
        int row = input.nextInt();

        System.out.print("Jumlah kolom Matrix: ");
        int column = input.nextInt();

        System.out.println("GAUSS JORDAN");

        Matrix m = new Matrix(row, column);
        m.readMatrix(input);

        System.out.println("Matrik yang anda inputkan adalah: ");
        m.tulisMatrix();
        
        checkRow(m);
        
        //buat Matrix segitiga bawah
        for(int i = 0; i < m.getBaris(); i++) //untuk looping semua baris
        {   
            for(int j = 0; j < i; j++)//untuk looping dari awal sampe n (membuat 0)
            {
                double divider = m.getElmt(i,j);

                if(divider != 0) //karena x/0 tidak boleh
                {
                    for(int k = 0; k < m.getKolom(); k++) // kurang semua kolom buat jadi 0
                    {
                        m.setElmt(i,k, m.getElmt(i,k)/divider- m.getElmt(j,k));
                    }
                }
            }
            
            double divider = m.getElmt(i,i);
            for(int j = 0; j < m.getKolom(); j++)
            {
                m.setElmt(i, j, m.getElmt(i,j)/divider);
            }
        }
        
        // Mengubah matrix menjadi bentuk Gauss Jordan     
        for(int i = m.getBaris()-2; i >=0 ; i--)
        {
            for(int j = m.getKolom()-2; j!=i; j--)
            {
                double multiplier = m.getElmt(i,j);
                for(int k = 0; k < m.getKolom(); k++)
                {
                    m.setElmt(i,k,m.getElmt(i,k) - multiplier*m.getElmt(j,k));
                }
                
            }
        }      
        System.out.println("Hasil:");
        m.tulisMatrix();
    }
    */
    static Scanner input = new Scanner(System.in);

    static StringBuilder finalResult;

    /* ----------------------------------------------------------------- */
    /* ----------------------------- GAUSS ----------------------------- */
    /* ----------------------------------------------------------------- */
    public static void metodeGauss(String outFileName, boolean readFromFile, Scanner readFileScanner)
    {
    	int row;
    	int column;

        // Menginput manual suatu matrix
    	if(!readFromFile)
        {
    		System.out.print("Jumlah baris matrix: ");
	        row = input.nextInt();

    	    System.out.print("Jumlah kolom matrix: ");
        	column = input.nextInt();	
    	}
        // Membaca matrix dari file.txt 
        else
        {
    		row = readFileScanner.nextInt();
    		column = readFileScanner.nextInt();
    	}	
        

        if (row==0 || column==0)
        {
            System.out.println("Matrix yang Anda merupakan sebuah matrix kosong.");
        }
        else if (row < 0 || column < 0)
        {
            System.out.println("Baris dan/atau kolom sebuah matrix tidak boleh bernilai negatif.");
        }
        else
        {
            System.out.println("Silahkan Input Matrix Anda:");

            Matrix M = new Matrix(row, column);

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
            
            System.out.println("Matrix yang Anda inputkan adalah: ");
            
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

            // Biar rapih di terminal
            System.out.println("\n");

            if (!M.isMatrix0())
            {
                int obe = 0;
                for (int i = 0; i < M.getBaris(); i++)
                {
                    if (obe < M.getKolom())
                    {
                        while ((M.getElmt(i,obe) == 0) && (obe < M.getKolom()-1))
                        {
                            int loc = M.swapBarisII0(i,obe);
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

                helperGauss(M, false, outFileName);
            }
        }
    }

    /* ----------------------------------------------------------------- */
    /* ------------------------ GAUSS JORDAN --------------------------- */
    /* ----------------------------------------------------------------- */
    public static void metodeGaussJordan(String outFileName, boolean readFromFile, Scanner readFileScanner)
    {
        int row;
        int column;

        // Menginput manual suatu matrix
        if(!readFromFile)
        {
            System.out.print("Jumlah baris matrix: ");
            row = input.nextInt();

            System.out.print("Jumlah kolom matrix: ");
            column = input.nextInt();   
        }
        // Membaca matrix dari file.txt  
        else
        {
            row = readFileScanner.nextInt();
            column = readFileScanner.nextInt();
        }   

        if (row==0 || column==0)
        {
            System.out.println("Matrix yang Anda merupakan sebuah matrix kosong.");
        }
        else if (row < 0 || column < 0)
        {
            System.out.println("Baris dan/atau kolom sebuah matrix tidak boleh bernilai negatif.");
        }
        else
        {

            System.out.println("Silahkan Input Matrix Anda:");

            Matrix M = new Matrix(row, column);

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
            
            System.out.println("Matrix yang Anda inputkan adalah: ");

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

            if (!M.isMatrix0())
            {
                int obe = 0;
                for (int i = 0; i < M.getBaris(); i++)
                {
                    if (obe < M.getKolom())
                    {
                        while ((M.getElmt(i,obe) == 0) && (obe < M.getKolom()-1))
                        {
                            int loc = M.swapBarisII0(i,obe);
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
                helperGauss(M, true, outFileName);
            }
        }
    }

    public static void helperGauss(Matrix M, boolean jordan, String outFileName)
    {
        int obe = 0;
        for (int i = 0; i < M.getBaris(); i++)
        {
            if (obe < M.getKolom())
            {
                while ((M.getElmt(i,obe) == 0) && (obe < M.getKolom()-1))
                {
                    int loc = M.swapBarisII0(i,obe);
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
        if (jordan)
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
        }

        if (Matrix.isSolvable(M))
        {
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
                        	jawaban.append(j+1);
                            jawaban.append(" = ");
                            jawaban.append(M.getElmt(i,M.getKolom()-1));
                        }
                        else 
                        {
                            if (M.getElmt(i,j) < 0)
                            {
                            	unik = false;

                                // Melakukan saving ke buffer 
                                // yang nantinya akan di lakukan
                                // printing ke file
                                jawaban.append(" + ");
                                jawaban.append(Math.abs(M.getElmt(i,j)));
                                jawaban.append("t");
                                jawaban.append(j+1);
                            }
                            if (M.getElmt(i,j) > 0)
                            {
                            	unik = false;

                                // Melakukan saving ke buffer 
                                // yang nantinya akan di lakukan
                                // printing ke file
                                jawaban.append(" - ");
                                jawaban.append(Math.abs(M.getElmt(i,j)));
                                jawaban.append("t");
                                jawaban.append(j+1);
                                
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
            }
            else
            {
                StringBuilder inf = new StringBuilder();

                // Melakukan printing pada terminal
                System.out.printf("Dari matrix di atas, dapat dilihat bahwa matrix memiliki solusi tak terhingga.\n");
                System.out.printf("Oleh karena itu, ditulis solusi matrix berupa parametrik seperti berikut:\n");

                // Melakukan saving ke buffer 
                // yang nantinya akan di lakukan
                // printing ke file
                finalResult.append("Dari matrix di atas, dapat dilihat bahwa matrix memiliki solusi tak terhingga.\n");
                finalResult.append("Oleh karena itu, ditulis solusi matrix berupa parametrik seperti berikut:\n");

                // Melakukan printing pada terminal
                System.out.printf(jawaban.toString());

                // Melakukan saving ke buffer 
                // yang nantinya akan di lakukan
                // printing ke file
                finalResult.append(jawaban.toString());

                for (int j = 0; j < M.getKolom()-1; j++){
                    if (M.isKolom0(j)) 
                    {
                        // Melakukan printing pada terminal
                        System.out.printf("x%d = t%d\n", j+1, j+1);

                        // Melakukan saving ke buffer 
                        // yang nantinya akan di lakukan
                        // printing ke file
                        inf.append("x");
                        inf.append(j+1);
                        inf.append(" = ");
                        inf.append("t");
                        inf.append(j+1);
                        inf.append("\n");
                    }
                    else if (M.getLead1(M.idxValue1(j)) != j)
                    {  
                        // Melakukan printing pada terminal
                        System.out.printf("x%d = t%d\n", j+1, j+1);

                        // Melakukan saving ke buffer 
                        // yang nantinya akan di lakukan
                        // printing ke file
                        inf.append("x");
                        inf.append(j+1);
                        inf.append(" = ");
                        inf.append("t");
                        inf.append(j+1);
                        inf.append("\n");
                    }
                }

                // Melakukan saving ke buffer 
                // yang nantinya akan di lakukan
                // printing ke file
                finalResult.append(inf.toString() + "\n");          
            }
        } 
        else 
        {
        	System.out.println("Dari matrix di atas, dapat dilihat bahwa matrix tidak memiliki solusi.");

        	finalResult.append("Dari matrix di atas, dapat dilihat bahwa matrix tidak memiliki solusi.");
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
    /* ------------------------ MATRIKS BALIKAN ------------------------ */
    /* ----------------------------------------------------------------- */

    public static void matriksInvers(String outFileName, boolean readFromFile, Scanner readFileScanner)
    {
        int row;
    	int column;

        // Menginput manual suatu matrix
    	if(!readFromFile)
        {
    		System.out.print("Jumlah baris matrix: ");
	        row = input.nextInt();

    	    System.out.print("Jumlah kolom matrix: ");
        	column = input.nextInt();	
    	}
        // Membaca matrix dari file.txt 
        else
        {
    		row = readFileScanner.nextInt();
    		column = readFileScanner.nextInt();
    	}	
        

        if (row==0 || column==0)
        {
            System.out.println("Matrix yang Anda merupakan sebuah matrix kosong.");
        }
        else if (row < 0 || column < 0)
        {
            System.out.println("Baris dan/atau kolom sebuah matrix tidak boleh bernilai negatif.");
        }
        else
        {
            System.out.println("Silahkan Input Matrix Anda:");

            Matrix M = new Matrix(row, column);

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
            
            System.out.println("Matrix yang Anda inputkan adalah: ");
            
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

            // Biar rapih di terminal
            System.out.println("\n");

            if (M.getKolom() == M.getBaris()+1)
            {
                row = M.getBaris();

                //Memisahkan matriks augmented menjadi matriks A dan B
                //Membuat matriks persegi A
                Matrix A = new Matrix(row, row);
                for (int i = 0; i < A.getBaris(); i++)
                {
                    for (int j = 0; j < A.getKolom(); j++)
                    {
                        A.arr[i][j] = M.arr[i][j];
                    }
                }
    
                //Membuat matriks B, yaitu kolom paling kanan dari matriks augmented
                Matrix B = new Matrix(row, 1);
                for (int i = 0; i < M.getBaris(); i++)
                {
                    B.arr[i][0] = M.arr[i][row];
                }
    
                Matrix invers = new Matrix(row,row);
                double det = DetKofaktor(A);
    
                //Matrix memiliki balikan
                if (det != 0)
                {
                    //Membuat matrix adjoin dengan mentranspose matrix kofaktor
                    Matrix adj = Invers.matKofaktor(A).createTranspose();
    
                    //Matrix balikan
                    for (int i = 0; i < row; i++)
                    {
                        for (int j = 0; j < row; j++)
                        {
                            invers.arr[i][j] = adj.arr[i][j]/det;
                        }
                    }

                    invers.tulisMatrix();
    
                    Matrix X = new Matrix(row,1);


                    for (int i =0; i<row; i++)
                    {
                        X.arr[i][0] = 0;
                        for (int k=0; k<row; k++){
                            X.arr[i][0] += invers.arr[i][k] * B.arr[k][0];                       
                        }

                    }

                    System.out.printf("Matrix memiliki solusi unik yaitu:\n");
        
                    finalResult.append("Matrix memiliki solusi unik yaitu:\n");
        
                    for(int i = 0; i < row; i++)
                    {
                        System.out.printf("x" + (i+1) + " = " + X.arr[i][0] + "\n");
                        finalResult.append("x" + (i+1) + " = " + X.arr[i][0] + "\n");
                    }
    
                }
    
                //Determinan matrix = 0, tidak memiliki balikan.
                else 
                {
                    System.out.println("Karena matrix di atas tidak memiliki balikan, tidak ada solusi yang memenuhi.");
    
                    finalResult.append("Karena matrix di atas tidak memiliki balikan, tidak ada solusi yang memenuhi.");   
                }
           
            }
            else 
            {
                System.out.printf("Matrix utama dari matrix diatas bukanlah matrix persegi, sehingga tidak memiliki balikan. \nOleh karena itu, SPL ini tidak dapat diselesaikan dengan Metode Matriks Balikan.");
                finalResult.append("Matrix utama dari matrix diatas bukanlah matrix persegi, sehingga tidak memiliki balikan. \nOleh karena itu, SPL ini tidak dapat diselesaikan dengan Metode Matriks Balikan.");
            }
            
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



    /* ----------------------------------------------------------------- */
    /* ---------------------------- CRAMER ----------------------------- */
    /* ----------------------------------------------------------------- */
    public static void kaidahCramer(String outFileName, boolean readFromFile, Scanner readFileScanner)
    {
        int row;
    	int column;

        // Menginput manual suatu matrix
    	if(!readFromFile)
        {
    		System.out.print("Jumlah baris matrix: ");
	        row = input.nextInt();

    	    System.out.print("Jumlah kolom matrix: ");
        	column = input.nextInt();	
    	}
        // Membaca matrix dari file.txt 
        else
        {
    		row = readFileScanner.nextInt();
    		column = readFileScanner.nextInt();
    	}	
        

        if (row==0 || column==0)
        {
            System.out.println("Matrix yang Anda merupakan sebuah matrix kosong.");
        }
        else if (row < 0 || column < 0)
        {
            System.out.println("Baris dan/atau kolom sebuah matrix tidak boleh bernilai negatif.");
        }
        else
        {
            System.out.println("Silahkan Input Matrix Anda:");

            Matrix M = new Matrix(row, column);

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
            
            System.out.println("Matrix yang Anda inputkan adalah: ");
            
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

            // Biar rapih di terminal
            System.out.println("\n");

            if (M.getKolom() == M.getBaris()+1)
            {
                row = M.getBaris();

                //Memisahkan matriks augmented menjadi matriks A dan B
                //Membuat matriks persegi A
                Matrix A = new Matrix(row, row);
                for (int i = 0; i < A.getBaris(); i++)
                {
                    for (int j = 0; j < A.getKolom(); j++)
                    {
                        A.arr[i][j] = M.arr[i][j];
                    }
                }
    
                //Membuat matriks B, yaitu kolom paling kanan dari matriks augmented
                Matrix B = new Matrix(row, 1);
                for (int i = 0; i < M.getBaris(); i++)
                {
                    B.arr[i][0] = M.arr[i][row];
                }
    
                double detA = DetKofaktor(A);
                if (detA == 0)
                {
                    System.out.println("Determinan dari matrix utamanya adalah 0, sehingga tidak dapat ditentukan solusinya.");
        
                    finalResult.append("Determinan dari matrix utamanya adalah 0, sehingga tidak dapat ditentukan solusinya.");
                }
        
                else 
                {
                    Matrix temp = new Matrix(row, row);
                    double[] x = new double[row];
                    for (int i = 0; i < row; i++)
                    {
                        for (int j = 0; j < row; j++)
                        {
                            for (int k = 0; k < row; k++)
                            {
                                if (k == i)
                                {
                                    temp.arr[j][k] = B.arr[j][0];
                                }
        
                                else
                                {
                                    temp.arr[j][k] = A.arr[j][k];
                                }
                            }
                        }
                        x[i] = DetKofaktor(temp)/ detA;
                    }
        
                    System.out.printf("Dari matrix di atas, dapat dilihat bahwa matrix memiliki solusi unik yaitu:\n");
        
                    finalResult.append("Dari matrix di atas, dapat dilihat bahwa matrix memiliki solusi unik yaitu:\n");
        
                    for(int i = 0; i < row; i++)
                    {
                        System.out.printf("x" + (i+1) + " = " + x[i] + "\n");
                        finalResult.append("x" + (i+1) + " = " + x[i] + "\n");
                    }

                }
           
            }
            else 
            {
                System.out.printf("Matrix utama dari matrix diatas bukanlah matrix persegi, sehingga tidak dapat dihitung determinannya. \nOleh karena itu, SPL ini tidak dapat diselesaikan dengan Metode Cramer.");
                finalResult.append("Matrix utama dari matrix diatas bukanlah matrix persegi, sehingga tidak dapat dihitung determinannya. \nOleh karena itu, SPL ini tidak dapat diselesaikan dengan Metode Cramer.");
            }
            
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
    

    private static double DetKofaktor(Matrix M) 
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