package tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import controller.StudentController;
import controller.StudentControllerImpl;
import entity.RequestSearch;
import entity.RequestShowAll;
import entity.Student;

public class ServerHandler extends Thread {

	private final StudentController controller;
	private final int location;

	private DataInputStream dis = null;
	private ObjectInputStream ois = null;
	private DataOutputStream dos = null;
	private ObjectOutputStream oos = null;

	public ServerHandler(Socket socket, int location) {
		controller = new StudentControllerImpl();
		this.location = location;

		while (true) {
			try {
				dos = new DataOutputStream(socket.getOutputStream());
				oos = new ObjectOutputStream(dos);

				dis = new DataInputStream(socket.getInputStream());
				ois = new ObjectInputStream(dis);

				while (true) {
					try {
						Object obj = ois.readObject();
						System.out.println(obj);
						if (obj instanceof RequestSearch) {
							if (((RequestSearch) obj).getId().equals("s1")) {
								System.out.println("Searching the ...");
								oos.writeObject(searchStudent(obj));
								System.out.println("Searched ...");
							} else if (((RequestSearch) obj).getId().equals("s2")) {
								System.out.println("Searching the ...");
								oos.writeObject(getListStudents(obj));
								System.out.println("Searched ...");
							}
						} else if (obj instanceof RequestShowAll) {
							if (((RequestShowAll) obj).getRequest().equals("ShowAll")) {
								System.out.println("Searching the...");
								oos.writeObject(getListStudentsByYear(obj));
								System.out.println("Searched ...");
							}
						}
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				}
			} catch (IOException e) {
				try {
					oos.close();
					dos.close();
					ois.close();
					dis.close();
					socket.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				System.out.println("- Client[" + this.location + "] disconnected !");
				break;
			}
		}
	}

	private Student searchStudent(Object obj) {
		return controller.searchStudent(((RequestSearch) obj).getInputId(), ((RequestSearch) obj).getInputYear());
	}

	private List<Student> getListStudents(Object obj) {
		return controller.searchListStudents(((RequestSearch) obj).getInputYear(), ((RequestSearch) obj).getInputId());
	}

	private Object[][] getListStudentsByYear(Object obj) {
		return controller.getListStudentObj(((RequestShowAll) obj).getYear());
	}
}