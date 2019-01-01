package zy.daoImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
public class BaseDao {
	private File file;
	private FileInputStream fis;
	private FileOutputStream fos;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;

	public BaseDao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BaseDao(File file) {
		super();
		this.file = file;
	}


	public <T> void write(ArrayList<T> al) {
		try {
			fos = new FileOutputStream(file);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(al);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public <T> ArrayList<T> read() {
		ArrayList<T> al = null;
		try {
			fis = new FileInputStream(file);
			ois = new ObjectInputStream(fis);
			al = (ArrayList<T>) ois.readObject();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			al = new ArrayList<>();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return al;
	}

	public void closeAll() {

		if (ois != null) {
			try {
				ois.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		if (oos != null) {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
