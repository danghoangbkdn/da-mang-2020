package tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import entity.RequestSearch;
import entity.RequestGetAll;
import entity.RequestGetListStudentsByRequest;
import entity.Student;

public class ClientHandler {

	private DataInputStream dis = null;
	private ObjectInputStream ois = null;
	private DataOutputStream dos = null;
	private ObjectOutputStream oos = null;
	private final Socket clientSocket;

	public ClientHandler(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	public void run() {
		try {
			dos = new DataOutputStream(clientSocket.getOutputStream());
			oos = new ObjectOutputStream(dos);
			dis = new DataInputStream(clientSocket.getInputStream());
			ois = new ObjectInputStream(dis);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Student getStudent(RequestSearch request) {
		Student student = new Student();

		try {
			oos.writeObject(request);
			System.out.println("- Sending a requestion : " + request.getRequest() + " -> " + request.getInputId()
					+ " -> " + request.getInputYear());
			student = (Student) ois.readObject();
			System.out.println("Student: " + student + "\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return student;
	}

	public int getAll(RequestGetAll request) {
		try {
			oos.writeObject(request);
			System.out.println("- Sending a requestion : " + request.getNameRequest() + " -> " + request.getRequest()
					+ " -> " + request.getYear());
			int all = (int) ois.readObject();
			System.out.println("Successful data retrieval ! \n");
			return all;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@SuppressWarnings("unchecked")
	public List<Student> getListStudentsByRequest(RequestGetListStudentsByRequest request) {
		try {
			oos.writeObject(request);
			System.out.println("- Sending a requestion : " + request.getNameRequest() + " -> " + request.getRequest()
					+ " -> " + request.getYear() + " -> " + request.getPage());
			List<Student> students = (List<Student>) ois.readObject();
			System.out.println("Successful data retrieval ! \n");
			return students;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}