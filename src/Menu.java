import java.util.*;

public class Menu
{
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);

		int menu;
		int metode;

		do
		{
			System.out.println("");
			System.out.println("---------------------------------------");
			System.out.println("Pilih Menu yang Diinginkan");
			System.out.println("");
			System.out.println("1. Sistem Persamaan Linear");
			System.out.println("2. Determinan");
			System.out.println("3. Matriks balikan");
			System.out.println("4. Interpolasi Polinom");
			System.out.println("5. Regresi linear berganda");
			System.out.println("6. Keluar");
			System.out.println("---------------------------------------");

			menu = input.nextInt();
			switch(menu)
			{
				case 1:
					System.out.println("Sistem Persamaan Linear");
					System.out.println("");
					System.out.println("---------------------------------------");
					System.out.println("Pilih Metode yang Ingin Digunakan");
					System.out.println("");
					System.out.println("1. Metode eliminasi Gauss");
					System.out.println("2. Metode eliminasi Gauss-Jordan");
					System.out.println("3. Matriks matriks balikan");
					System.out.println("4. Kaidah Cramer");
					System.out.println("---------------------------------------");
					metode = input.nextInt();
					switch(metode)
					{
						case 1:
							System.out.println("Gauss");
							break;
						case 2:
							System.out.println("Gauss Jordan");
							break;
						case 3:
							System.out.println("Balikan");
							break;
						case 4:
							System.out.println("Cramer");
							break;
						default:
							System.out.println("Input tidak valid");
					}
					break;

				case 2:
					System.out.println("Determinan");
					System.out.println("");
					System.out.println("---------------------------------------");
					System.out.println("Pilih Metode yang Ingin Digunakan");
					System.out.println("");
					System.out.println("1. Metode eliminasi Gauss");
					System.out.println("2. Metode eliminasi Gauss-Jordan");
					System.out.println("3. Matriks matriks balikan");
					System.out.println("4. Kaidah Cramer");
					System.out.println("---------------------------------------");
					metode = input.nextInt();
					switch(metode)
					{
						case 1:
							System.out.println("Gauss");
							break;
						case 2:
							System.out.println("Gauss Jordan");
							break;
						case 3:
							System.out.println("Balikan");
							break;
						case 4:
							System.out.println("Cramer");
							break;
						default:
							System.out.println("Input tidak valid");
					}
					break;

				case 3:
					System.out.println("Matriks balikan");
					System.out.println("");
					System.out.println("---------------------------------------");
					System.out.println("Pilih Metode yang Ingin Digunakan");
					System.out.println("");
					System.out.println("1. Metode eliminasi Gauss");
					System.out.println("2. Metode eliminasi Gauss-Jordan");
					System.out.println("3. Matriks matriks balikan");
					System.out.println("4. Kaidah Cramer");
					System.out.println("---------------------------------------");
					metode = input.nextInt();
					switch(metode)
					{
						case 1:
							System.out.println("Gauss");
							break;
						case 2:
							System.out.println("Gauss Jordan");
							break;
						case 3:
							System.out.println("Balikan");
							break;
						case 4:
							System.out.println("Cramer");
							break;
						default:
							System.out.println("Input tidak valid");
					}
					break;

				case 4:
					System.out.println("Interpolasi Polinom");
					break;

				case 5:
					System.out.println("Regresi linear berganda");
					break;

				case 6:
					break;

				default:
					System.out.println("Input Tidak Valid");
			}

		} while (menu!=6);

	}
	
}