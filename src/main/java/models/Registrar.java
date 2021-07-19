package models;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

public class Registrar implements RegistrarManager{
	private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	private static List<Person> employees = new ArrayList<>();
	
	private static List<Package> packagesList = new ArrayList<>();
	
	private static Package[][][] store = new Package[2][2][20];
	
	private File packagesFile = new File("src"+File.separator+"main"+File.separator+"java"+File.separator+
			"textFiles"+File.separator+"packagesFile.txt");
	//private File storeFile = new File("src"+File.separator+"textFiles"+File.separator+"storeFile.txt");
	private File employeesFile = new File("src"+File.separator+"main"+File.separator+"java"+File.separator+
			"textFiles"+File.separator+"employeesFile.txt");

	@Override
	public void registerPackage(Package pckg) {
		if (!packagesList.contains(pckg)){
			packagesList.add(pckg);
		}
		
		savePackageData(packagesFile);
	}

	@SuppressWarnings("static-access")
	@Override
	public void storePackage(String packageRegistrationNo, int storeId, int cupboardId, String storeOfficerId) {
		packageLoop:
		for (Package pckg : packagesList) {
			if (pckg.getPackageRegistrationNo().equalsIgnoreCase(packageRegistrationNo)) {
				for (Person employee : employees) {
					if (employee.getId().equalsIgnoreCase(storeOfficerId)) {
						Date today = new Date();
						pckg.setDateStored(formatter.format(today));
						pckg.setStoredOfficer(employee);
						pckg.setStoreId(storeId);
						pckg.setCupboardId(cupboardId);
						
						for (Package.Categories category : Package.Categories.values()) {
							if (category.toString().equals(pckg.getPackageType())) {
								store[storeId - 1][cupboardId - 1][category.id] = pckg;
								break;
							}
						}					
						pckg.setPackageFlag("Stored");
						
						System.out.println("Successfully stored!");
						
						break packageLoop;
					}
				}
			}
		}
		
		savePackageData(packagesFile);
	}
	
	@Override
	public void assignPackage(String packageRegistrationNo, String assignerId, String assigneeId) {
		  for (Package pckg : packagesList) {
			  if (pckg.getPackageRegistrationNo().equalsIgnoreCase(packageRegistrationNo)) {
				  for (Person employee : employees) {
					  if (employee.getId().equalsIgnoreCase(assignerId)) {
						  pckg.setAssigner(employee);
					  } else if (employee.getId().equalsIgnoreCase(assigneeId)) {
						  pckg.setAssignee(employee);
					  } else {
						  continue;
					  }
				  }
				  
				  if (pckg.getAssigner() != null && pckg.getAssignee() != null) {
					  Date today = new Date();
					  pckg.setAssignmentdate(formatter.format(today));
					  pckg.setPackageFlag("Delivering...");
					  
					  System.out.println("Successfully assigned!");
					  
					  break;
				  }
			  }
		  }
	}

	private void savePackageData(File file){
		FileOutputStream packageFOS = null;
		ObjectOutputStream packageOOS = null;
		
		try {
			packageFOS = new FileOutputStream(file);
			packageOOS = new ObjectOutputStream(packageFOS);
		
			for (Package pckg : packagesList) {
				packageOOS.writeObject(pckg);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				packageOOS.flush();
				packageFOS.close();
				packageOOS.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void loadPackageData(File packageFile) {
		packagesList.clear();
		FileInputStream packageFIS = null;
		ObjectInputStream packageOIS = null;
		
		try {
			packageFIS = new FileInputStream(packageFile);
			packageOIS = new ObjectInputStream(packageFIS);
			
			for (;;) {
				try {
					Package pckg = (Package) packageOIS.readObject();
					packagesList.add(pckg);
				} catch (EOFException | ClassNotFoundException e) {
					System.out.println(e);
					break;
				}
			}
			
			packageFIS.close();
			packageOIS.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void saveStoreData(File file) {
		FileOutputStream storeFOS = null;
		ObjectOutputStream storeOOS = null;
		
		try {
			storeFOS = new FileOutputStream(file);
			storeOOS = new ObjectOutputStream(storeFOS);
		
			for (int i = 0; i < store.length; i++) {
				for (int j = 0; j < store[0].length; j++) {
					for (int k = 0; k < store[0][0].length; k++) {
						if (store[i][j][k] != null) {
							storeOOS.writeObject(store[i][j][k]);
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				storeOOS.flush();
				storeFOS.close();
				storeOOS.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void loadStoreData(File packageFile) {
		loadPackageData(packageFile);
//		FileInputStream storeFIS = null;
//		ObjectInputStream storeOIS = null;
//		
//		try {
//			storeFIS = new FileInputStream(storeFile);
//			storeOIS = new ObjectInputStream(storeFIS);
//			
//			for (;;) {
//				try {
//					Package pckg = (Package) storeOIS.readObject();
//					store[pckg.getStoreId()][pckg.getCupboardId()][pckg.]
//				} catch (EOFException | ClassNotFoundException e) {
//					System.out.println(e);
//					break;
//				}
//			}
//			
//			storeFIS.close();
//			storeOIS.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		for (Package pckg : packagesList) {
			if (pckg.getStoreId() != 0 && pckg.getCupboardId() != 0) {
				for (Package.Categories category : Package.Categories.values()) {
					if (category.toString().equals(pckg.getPackageType())) {
						store[pckg.getStoreId() - 1][pckg.getCupboardId() - 1][category.id] = pckg;
						break;
					}
				}
			}
		}
	}
	
	private static void saveEmployees(File file) {
		FileOutputStream employeeFOS = null;
		ObjectOutputStream employeeOOS = null;
		
		try {
			employeeFOS = new FileOutputStream(file);
			employeeOOS = new ObjectOutputStream(employeeFOS);
		
			for (Person employee : employees) {
				employeeOOS.writeObject(employee);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				employeeOOS.flush();
				employeeFOS.close();
				employeeOOS.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void loadEmployees(File employeesFile) {
		employees.clear();
		FileInputStream employeeFIS = null;
		ObjectInputStream employeeOIS = null;
		
		try {
			employeeFIS = new FileInputStream(employeesFile);
			employeeOIS = new ObjectInputStream(employeeFIS);
			
			for (;;) {
				try {
					Person employee = (Employee) employeeOIS.readObject();
					employees.add(employee);
				} catch (EOFException | ClassNotFoundException e) {
					System.out.println(e);
					break;
				}
			}
			
			employeeFIS.close();
			employeeOIS.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Package> getPackagesList() {
		return packagesList;
	}
	
	public List<Person> getEmployeesList() {
		return employees;
	}

	public void setPackagesList(List<Package> packagesList) {
		this.packagesList = packagesList;
	}
	
//	public static void addEmployee(String name, String id) {
//		Person employee = new Employee(name, id);
//		employees.add(employee);
//		saveEmployees(employeesFile);
//	}
	
//	public static void main(String[] args) {
//		addEmployee("John", "S101");
//		addEmployee("Sean", "S110");
//		addEmployee("Chris", "D098");
//		addEmployee("Tyson", "D118");
//		addEmployee("Rick", "S009");
//		addEmployee("Joey", "S066");
//	}
}
