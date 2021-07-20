package com.nipun.abxPackageDeliveryService;

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

@XmlRootElement
public class Registrar implements RegistrarManager{
	private static Registrar instance = null;
	
	private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	private static List<Employee> employees = new ArrayList<>();
	
	private static List<Package> packagesList = new ArrayList<>();
	
	private static Package[][][] store = new Package[2][2][20];
	
	private static File packagesFile = new File("D:"+File.separator+"eclipse-web-workspace"+File.separator+"abxPackageDeliveryService"+File.separator+"src"+File.separator+"main"+File.separator+"java"+File.separator+
			"textFiles"+File.separator+"packagesFile.txt");
	//private File storeFile = new File("src"+File.separator+"textFiles"+File.separator+"storeFile.txt");
	private static File employeesFile = new File("D:"+File.separator+"eclipse-web-workspace"+File.separator+"abxPackageDeliveryService"+File.separator+"src"+File.separator+"main"+File.separator+"java"+File.separator+
			"textFiles"+File.separator+"employeesFile.txt");

	public static Registrar getInstance() {
		if (instance == null) {
			synchronized (Registrar.class) {
				if (instance == null) {
					instance = new Registrar();
				}
			}
		}
		return instance;
	}
	
	@Override
	public Package registerPackage(Package pckg) {
		//loadPackageData(packagesFile);
		if (!packagesList.contains(pckg)){
			//pckg = new Package();
			
			packagesList.add(pckg);
			System.out.println(pckg.getBearer());
			System.out.println(pckg);
		}
		
		savePackageData(packagesFile);
		return pckg;
	}

	@Override
	public Package storePackage(Package pkg) {
		//packageLoop:
		loadEmployees(employeesFile);
		for (Package pckg : packagesList) {
			if (pckg.getPackageRegistrationNo().equalsIgnoreCase(pkg.getPackageRegistrationNo())) {
				for (Employee employee : employees) {
					if (employee.getId().equalsIgnoreCase(pkg.getStoredOfficer().getId())) {
						Date today = new Date();
						pckg.setDateStored(formatter.format(today));
						pckg.setStoredOfficer(employee);
						pckg.setStoreId(pkg.getStoreId());
						pckg.setCupboardId(pkg.getCupboardId());
						
						for (Package.Categories category : Package.Categories.values()) {
							if (category.toString().equals(pckg.getPackageType())) {
								store[pkg.getStoreId() - 1][pkg.getCupboardId() - 1][category.id] = pckg;
								break;
							}
						}					
						pckg.setPackageFlag("Stored");
						
						System.out.println("Successfully stored!");
						savePackageData(packagesFile);
						System.out.println(pckg);
						return pckg;
					}
				}
			}
		}
		return null;
	}
	
	@Override
	public Package assignPackage(Package item) {
		  for (Package pckg : packagesList) {
			  if (pckg.getPackageRegistrationNo().equalsIgnoreCase(item.getPackageRegistrationNo())) {
				  for (Employee employee : employees) {
					  if (employee.getId().equalsIgnoreCase(item.getAssigner().getId())) {
						  pckg.setAssigner(employee);
					  } else if (employee.getId().equalsIgnoreCase(item.getAssignee().getId())) {
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
					  savePackageData(packagesFile);
					  System.out.println(pckg);
					  
					  return pckg;
				  }
			  }
		  }
		  return null;
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
					Employee employee = (Employee) employeeOIS.readObject();
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
		loadPackageData(packagesFile);
		return packagesList;
	}
	
	public List<Employee> getEmployeesList() {
		return employees;
	}

	public void setPackagesList(List<Package> packagesList) {
		this.packagesList = packagesList;
	}
	
	public static void addEmployee(String name, String id) {
		Employee employee = new Employee(name, id);
		employees.add(employee);
		saveEmployees(employeesFile);
	}
	
	public static void main(String[] args) {
		addEmployee("John", "S101");
		addEmployee("Sean", "S110");
		addEmployee("Chris", "D098");
		addEmployee("Tyson", "D118");
		addEmployee("Rick", "S009");
		addEmployee("Joey", "S066");
		System.out.println(packagesFile.getAbsolutePath());
	}
}
