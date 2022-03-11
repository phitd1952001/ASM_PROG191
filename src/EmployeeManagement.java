import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

class Employee implements Serializable {
	protected int id;
	protected String firstName;
	protected String lastName;
	protected boolean gender;
	protected LocalDate DoB;
	protected String address;
	protected String phone;
	protected String email;
	protected String SSN;

	public Employee() {
	}

	public Employee(int pid, String fName, String lName, boolean g, LocalDate dob, String addr, String ph, String e) {
		id = pid;
		firstName = fName;
		lastName = lName;
		gender = g;
		DoB = dob;
		address = addr;
		phone = ph;
		email = e;
	}
}

class CommissionEmployee extends Employee {
	double totalSales;
	double commissionRate;

	public CommissionEmployee(String fName, String lName, String ssn, double tSales, double cRate) {
		firstName = fName;
		lastName = lName;
		SSN = ssn;
		totalSales = tSales;
		commissionRate = cRate;
	}

	public double payCommission() {
		return commissionRate * totalSales;
	}

	public void setTotalSales(double s) {
		totalSales = s;
	}

	public double getTotalSales() {
		return totalSales;
	}
}

class BasePlusCommissionEmployee extends CommissionEmployee {
	double baseSalary;

	public BasePlusCommissionEmployee(String fName, String lName, String ssn, double tSales, double cRate,
			double baseSal) {
		super(fName, lName, ssn, tSales, cRate);
		baseSalary = baseSal;
	}

	public double paySalaryAndCommisson() {
		return baseSalary + payCommission();
	}
}

public class EmployeeManagement {
	ArrayList<Employee> eList = new ArrayList<Employee>();
	Employee currentEmp;

	File empFile = new File("Employee.data");
	FileOutputStream empStream;
	ObjectOutputStream empObjectStream;

	FileInputStream empInputStream;
	ObjectInputStream empObjectInputStream;

	private JFrame frame;
	private JTextField txtId;
	private JTextField txtFistName;
	private JTextField txtLastName;
	private JTextField txtPhone;
	private JTextField txtEmail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeManagement window = new EmployeeManagement();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EmployeeManagement() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("EMPLOYEE INFORMATION");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(111, 10, 257, 26);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Gender");
		lblNewLabel_1.setBounds(82, 55, 63, 20);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("First Name");
		lblNewLabel_1_1.setBounds(82, 93, 63, 20);
		frame.getContentPane().add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Last Name");
		lblNewLabel_1_1_1.setBounds(82, 130, 63, 20);
		frame.getContentPane().add(lblNewLabel_1_1_1);

		txtId = new JTextField();
		txtId.setBounds(187, 56, 176, 19);
		frame.getContentPane().add(txtId);
		txtId.setColumns(10);

		txtFistName = new JTextField();
		txtFistName.setColumns(10);
		txtFistName.setBounds(187, 94, 176, 19);
		frame.getContentPane().add(txtFistName);

		txtLastName = new JTextField();
		txtLastName.setColumns(10);
		txtLastName.setBounds(187, 131, 176, 19);
		frame.getContentPane().add(txtLastName);

		JLabel lblNewLabel1 = new JLabel("Gender");
		lblNewLabel_1.setBounds(82, 173, 63, 20);
		frame.getContentPane().add(lblNewLabel_1);

		JRadioButton rdMale = new JRadioButton("Male");
		rdMale.setBounds(187, 173, 90, 21);
		frame.getContentPane().add(rdMale);

		JRadioButton rdFemale = new JRadioButton("Female");
		rdFemale.setBounds(279, 173, 84, 21);
		frame.getContentPane().add(rdFemale);

		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("DoB");
		lblNewLabel_1_1_1_1_1.setBounds(82, 216, 63, 20);
		frame.getContentPane().add(lblNewLabel_1_1_1_1_1);

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		JSpinner spDoB = new JSpinner(new SpinnerDateModel());
		spDoB.setEditor(new JSpinner.DateEditor(spDoB, dateFormat.toPattern()));

		spDoB.setBounds(187, 217, 176, 20);
		frame.getContentPane().add(spDoB);

		JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("Address");
		lblNewLabel_1_1_1_1_1_1.setBounds(82, 246, 63, 20);
		frame.getContentPane().add(lblNewLabel_1_1_1_1_1_1);

		JTextArea taAddress = new JTextArea();
		taAddress.setBounds(187, 247, 176, 44);
		frame.getContentPane().add(taAddress);

		JLabel lblNewLabel_1_1_1_1_1_1_1 = new JLabel("Phone");
		lblNewLabel_1_1_1_1_1_1_1.setBounds(82, 304, 63, 20);
		frame.getContentPane().add(lblNewLabel_1_1_1_1_1_1_1);

		txtPhone = new JTextField();
		txtPhone.setColumns(10);
		txtPhone.setBounds(187, 305, 176, 19);
		frame.getContentPane().add(txtPhone);

		JLabel lblNewLabel_1_1_1_1_1_1_1_1 = new JLabel("Email");
		lblNewLabel_1_1_1_1_1_1_1_1.setBounds(82, 334, 63, 20);
		frame.getContentPane().add(lblNewLabel_1_1_1_1_1_1_1_1);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(187, 334, 176, 19);
		frame.getContentPane().add(txtEmail);

		JButton btnCreate = new JButton("Create");
		btnCreate.setBounds(151, 364, 85, 21);
		frame.getContentPane().add(btnCreate);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(246, 364, 85, 21);
		frame.getContentPane().add(btnCancel);

		JButton btnPrev = new JButton("<<Previous");
		btnPrev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int currentIndex = eList.indexOf(currentEmp);
				if (currentIndex != -1)
					if (currentIndex > 0)
						currentEmp = eList.get(currentIndex - 1);
				txtId.setText(String.valueOf(currentEmp.id));
				txtFistName.setText(currentEmp.firstName);
				txtLastName.setText(currentEmp.lastName);
				taAddress.setText(currentEmp.address);
				txtPhone.setText(currentEmp.phone);
				txtEmail.setText(currentEmp.email);
			}
		});

		btnPrev.setBounds(30, 364, 109, 21);
		frame.getContentPane().add(btnPrev);

		JButton btnNext = new JButton("Next >>");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int currentIndex = eList.indexOf(currentEmp);
				if (currentIndex != -1)
					if (currentIndex < (eList.size() - 1))
						currentEmp = eList.get(currentIndex + 1);
				txtId.setText(String.valueOf(currentEmp.id));
				txtFistName.setText(currentEmp.firstName);
				txtLastName.setText(currentEmp.lastName);
				taAddress.setText(currentEmp.address);
				txtPhone.setText(currentEmp.phone);
				txtEmail.setText(currentEmp.email);

			}
		});
		btnNext.setBounds(345, 364, 95, 21);
		frame.getContentPane().add(btnNext);

		JLabel lblNewLabel_1_1_2 = new JLabel("ID");
		lblNewLabel_1_1_2.setBounds(82, 59, 63, 13);
		frame.getContentPane().add(lblNewLabel_1_1_2);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu menuFile = new JMenu("File");
		menuBar.add(menuFile);

		JMenuItem mLoad = new JMenuItem("Load All Emolyee Data");
		mLoad.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					empInputStream = new FileInputStream(empFile);
					empObjectInputStream = new ObjectInputStream(empInputStream);

					eList = (ArrayList<Employee>) empObjectInputStream.readObject();
					currentEmp = (Employee) eList.get(0);
					empObjectInputStream.close();
					txtId.setText(String.valueOf(currentEmp.id));
					txtFistName.setText(currentEmp.firstName);
					txtLastName.setText(currentEmp.lastName);
					taAddress.setText(currentEmp.address);
					txtPhone.setText(currentEmp.phone);
					txtEmail.setText(currentEmp.email);
				} catch (IOException e1) {
					// TODO: handle exception
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO: handle exception
					e1.printStackTrace();
				}
			}
		});
		menuFile.add(mLoad);

		JMenuItem mSave = new JMenuItem("Save All Employee Data");
		mSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					empStream = new FileOutputStream(empFile);
					empObjectStream = new ObjectOutputStream(empStream);
					empObjectStream.writeObject(eList);
					empObjectStream.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		menuFile.add(mSave);

		btnCreate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(txtId.getText());
				String firstName = txtFistName.getText();
				String lastName = txtLastName.getText();
				String address = taAddress.getText();
				String phone = txtPhone.getText();
				String email = txtEmail.getText();
				Employee emp = new Employee(id, firstName, lastName, true, LocalDate.of(2000, 01, 01), address, phone,
						email);
				JOptionPane.showMessageDialog(null,
						"A new employee is added successfully with: \n" + "Id: " + emp.id + "\n" + "FirstName: "
								+ emp.firstName + "\n" + "LastName: " + emp.lastName + "\n" + "Gender: " + emp.gender + "\n"
								+ "Date of birth: " + emp.DoB + "\n" + "Address: " + emp.address + "\n" + "Phone: "
								+ emp.phone + "\n" + "Email: " + emp.email + "\n");

				eList.add(emp);
				currentEmp = emp;
			}
		});

	}
}
