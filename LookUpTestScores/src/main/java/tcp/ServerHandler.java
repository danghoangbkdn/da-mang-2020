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
import entity.RequestGetListStudentsByYear;
import entity.Student;

public class ServerHandler extends Thread {

	private final StudentController controller;

	private DataInputStream dis = null;
	private ObjectInputStream ois = null;
	private DataOutputStream dos = null;
	private ObjectOutputStream oos = null;

	public ServerHandler(Socket socket, int location) {
		controller = new StudentControllerImpl();

		while (true) {
			try {
				dos = new DataOutputStream(socket.getOutputStream());
				oos = new ObjectOutputStream(dos);

				dis = new DataInputStream(socket.getInputStream());
				ois = new ObjectInputStream(dis);

				while (true) {
					try {
						Object obj = ois.readObject();
						if (obj instanceof RequestSearch) {
							if (((RequestSearch) obj).getRequest().equals("Search")) {
								System.out.println("- Request from Client[" + location + "] : Search");
								oos.writeObject(searchStudent(obj));
								continue;
							}
						}
						if (obj instanceof RequestGetListStudentsByYear) {
							if (((RequestGetListStudentsByYear) obj).getRequest()
									.equals("Get List<Student> students by year")) {
								System.out.println(
										"- Request from Client[" + location + "] : Get List<Student> students by year");
								oos.writeObject(getListStudentsByYear(obj));
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
				System.out.println("- Client[" + location + "] disconnected !");
				break;
			}
		}
	}

	private Student searchStudent(Object obj) {
		return controller.searchStudent(((RequestSearch) obj).getInputId(), ((RequestSearch) obj).getInputYear());
	}

	private List<Student> getListStudentsByYear(Object obj) {
		return controller.getListStudentsByYear(((RequestGetListStudentsByYear) obj).getYear());
	}
}