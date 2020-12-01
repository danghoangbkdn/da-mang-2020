package tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import entity.RequestSearch;
import entity.RequestShowAll;
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
			System.out.println("Searching ...");
			student = (Student) ois.readObject();
			System.out.println("Student: " + student);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return student;
	}

	@SuppressWarnings("unchecked")
	public List<Student> getListStudents(RequestSearch request) {
		List<Student> students = new ArrayList<>();

		try {
			oos.writeObject(request);
			students = (List<Student>) ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return students;
	}

	public Object[][] getListStudentsByYear(RequestShowAll request) {
		try {
			oos.writeObject(request);
			Object[][] students = (Object[][]) ois.readObject();
			return students;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}