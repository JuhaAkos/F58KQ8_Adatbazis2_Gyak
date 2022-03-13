package pack;

import java.io.IOException;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			new MyBatisUtil();
			System.out.println("connected");
			
			//insertCar(new Car(30,"Suzuki","zöld",200,1));
			//deleteCar(30)
			//selectAllCar();
			//updateCar(new Car(12,"Suzuki","sárga",100));
			
			System.out.println("Succes yey!");
			
		} catch(IOException e) {
			e.printStackTrace();
		}

	}

	private static void insertCar(Car car) {
		carDAO.insert(car);
	}
	
	private static void selectAllCar() {
		for (Car car:carDAO.selectAll()) {
			System.out.println(car);
		}
	}
	
	private static void delteCar() {
		carDAO.deleteById(id);
	}
}
