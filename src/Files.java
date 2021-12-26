import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Files {
	
	private static boolean run = true;
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static File main = new File("./Main");
	
	public static void main(String[] args) {
		int choice;
		System.out.println("Welcome to LockedMe.com\n"
				+ "Application developed by Lakesh Kumar\n"
				+ "-------------------------------------\n");
		if(main.exists() == false)
			main.mkdir();
		do {
			showOptions();
			choice = getChoice();
			switch(choice) {
			case 1	:	displayAllFiles();
						break;						
			case 2	:	fileOperations();
						break;					
			case 3	:	run = false;
						break;						
			default	:	System.out.println("Select a valid choice");
			}
		} while(run);
		System.out.println("Program terminated successfully");
	}

	private static void fileOperations() {
		int choice;
		do {
			showFileOptions();
			choice = getChoice();
			switch(choice) {
			case 1	:	createNewFile();
						break;						
			case 2	:	deleteFile();
						break;
			case 3  :	searchFile();
						break;
			case 4	:	return;
			case 5  :	run = false;
						break;
			default	:	System.out.println("Select a valid choice");
			}
		} while(run);
	}
	private static void showOptions() {
		System.out.println("Select an option below\n"
				+ "-----------------------------------\n"
				+ "1. Show all files in the directory\n"
				+ "2. Add, delete or search for a file\n"
				+ "3. Exit the application\n");
	}
	
	private static void showFileOptions() {
		System.out.println("Select an option below\n"
				+ "-------------------------------------\n"
				+ "1. Add a new file to the directory\n"
				+ "2. Delete a file from the directory\n"
				+ "3. Search for a file in the directory\n"
				+ "4. Return to the previous menu\n"
				+ "5. Exit the application\n");
	}
	
	private static void searchFile() {
		int i;
		String name;
		boolean found = false;
		File list[] = main.listFiles();
		System.out.println("Enter name of the file to search");
		name = getName();
		if (name != null) {
			System.out.println("Search Results for " + name);
			for (i = 0; i < list.length; i++)
				if (list[i].getName().startsWith(name)) {
					System.out.println(list[i].getName());
					found = true;
				}
		}
		if (found == false)
			System.out.println("No such file was found");
		System.out.println();
	}

	private static void deleteFile() {
		String name;
		System.out.println("Enter name of the file to delete");
		name = getName();
		File newFile = new File("./Main/" + name);
		if (newFile.exists() == true) {
			newFile.delete();
			if (newFile.isDirectory() == true)
				System.out.println("Directory \"" + newFile.getName() + " was deleted successfully");
			else
				System.out.println("File " + newFile.getName() + " was deleted successfully");
		} else {
			System.out.println("File was not found");
		}
		System.out.println();
	}

	private static void createNewFile() {		
		String name = null;
		System.out.println("Enter name of the file to add");
		name = getName();
		File newFile = new File("./Main/" + name);
		if (newFile.exists() == true)
			System.out.println("File " + newFile.getName() + " already exists");
		else {
			try {
				newFile.createNewFile();
				System.out.println("File " + newFile.getName() + " was added successfully");
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Exception" + name);
			}
		}
		System.out.println();
	}

	private static void displayAllFiles() {
		int i;
		File list[] = main.listFiles();
		if (list.length == 0)
			System.out.println("Directory is empty");
		else {
			System.out.println("List of files in the directory");
			for (i = 0; i < list.length; i++) {
			if (list[i].isDirectory())
				System.out.println("Directory\t" + list[i].getName());
			else
				System.out.println("File\t\t" + list[i].getName());
			}
		}
		System.out.println();
	}
	
	private static int getChoice() {
		int input = 0;
		boolean run = true;
		while (run) {
			try {
				input = Integer.parseInt(br.readLine());
				run = false;
			} catch (Exception e) {
				System.out.println("Enter a valid choice");
			}
		}
		return input;
	}
	
	private static String getName() {
		String name = null;
		boolean run = true;
		while (run) {
			try {
				name = br.readLine();
				run = false;
			} catch (Exception e) {
				System.out.println("Enter a valid file name");
			}
		}
		return name;
	}
}