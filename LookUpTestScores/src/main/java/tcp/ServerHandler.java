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
import entity.RequestGetAll;
import entity.RequestGetListStudentsByRequest;
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
						if (obj instanceof RequestSearch) {
							oos.writeObject(searchStudent((RequestSearch) obj));
						} else if ((obj instanceof RequestGetAll)) {
							oos.writeObject(getAll((RequestGetAll) obj));
						} else if (obj instanceof RequestGetListStudentsByRequest) {
							oos.writeObject(getListStudentsByRequest((RequestGetListStudentsByRequest) obj));
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

	private Student searchStudent(RequestSearch obj) {
		System.out.println("- Request from Client[" + location + "] : " + obj.getRequest() + " -> " + obj.getInputId()
				+ " -> " + obj.getInputYear());
		return controller.searchStudent(((RequestSearch) obj).getInputId(), ((RequestSearch) obj).getInputYear());
	}

	private int getAll(RequestGetAll obj) {
		System.out.println("- Request from Client[" + location + "] : " + obj.getNameRequest() + " -> "
				+ obj.getRequest() + " -> " + obj.getYear());
		return controller.getAll(obj);
	}

	private List<Student> getListStudentsByRequest(RequestGetListStudentsByRequest obj) {
		System.out.println("- Request from Client[" + location + "] : " + obj.getNameRequest() + " -> "
				+ obj.getRequest() + " -> " + obj.getYear() + " -> " + obj.getPage());
		return controller.getListStudentsByRequest(obj);
	}
}