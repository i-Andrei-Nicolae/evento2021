package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileUtils {
	//UTILS
	
	/**
	 * OK
	 * Function that creates a file in java5 with all the checks
	 * @param folder
	 * @param fileName
	 */
	public static boolean createFile(String folder, String fileName) {
		try {
			File filePath = new File(folder);
			File file = new File(filePath, fileName);
			
			if (!file.exists()) {
				if (!filePath.exists()) { // the folder does not exist
					if (filePath.mkdir()) { // the folder has created
						return file.createNewFile();
					}
				} else return file.createNewFile();
			}
						/*System.out.println(f.createNewFile() ? "Fichero " + f.getName() + " creado"
								: "No se ha podido crear el fichero " + f.getName());
					} else { // La carpeta no se ha podido crear
						System.out.println("No he podido crear la carpeta " + ruta.getName());
						
					}
				} else { // La carpeta si existe
					if (f.createNewFile()) { // El fichero se ha creado
						System.out.println("Fichero " + f.getName() + " creado");
					} else {
						System.out.println("No se ha podido crear el fichero " + f.getName());
					}
				}
			} else {// La carpeta si existe
				System.out.println("Fichero " + f.getName() + " ya existe");
				System.out.println("Tamaño " + f.length() + " bytes");
			}*/
		} catch (FileAlreadyExistsException e) {
			e.printStackTrace();
		} catch (DirectoryIteratorException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * OK
	 * Function that creates a file in java5 with all the checks
	 * @param completePath
	 */
	public static boolean createFile(String completePath) {
		File file = new File(completePath);
		return createFile(file.getParent(), file.getName());
	}

	/**
	 * OK
	 * Delete file with a folder path and the file name
	 * @param path
	 * @param fileName
	 */
	public static boolean deleteFile(String path, String fileName) {
		File filePath = new File(path);
		File f = new File(filePath, fileName);
		if (f.exists()) { // Delete the file
			if (f.isFile()) { // We make sure that it only deletes files and not folders
				return f.delete();
			}
		}
				/*System.out.println(f.delete() ? "Fichero eliminado" : 
					"No he podido eliminar el fichero " + f.getName());
			} else {
				System.out.println("El nombre " + f.getName() + " no es un fichero simple");
			}
		} else { // El fichero no existe, no borramos nada
			System.out.println("El fichero " + f.getName() + " no existe.");
		}*/
		return false;		
	}
	
	/**
	 * OK
	 * Delete file with a complete path
	 * @param completePath
	 */
	public static boolean deleteFile(String completePath) {
		File file = new File(completePath);
		return deleteFile(file.getParent(), file.getName());
	}
	
	/**
	 * OK
	 * Rename files by the directory path and the file name you want to use
	 * @param path
	 * @param fileName
	 * @param newFileName
	 */
	public static boolean renameFile(String path, String fileName, String newFileName) {
		File filePath = new File(path);
		File f = new File(filePath, fileName);
		if (f.exists()) {
			if (f.isFile()) {
				return f.renameTo(new File(filePath, newFileName));
			}
		}
				/*System.out.println(f.renameTo(new File(filePath, newFileName)) ?
						"Se ha cambiado el nombre correctamente" : 
							"No he podido cambiar el nombre");
				
			} else {
				System.out.println("El nombre " + f.getName() + " no es un fichero simple");
			}
		} else {
			System.out.println("El fichero " + f.getName() + " no existe");
		}*/
		return false;
	}
	
	/**
	 * OK
	 * Read all data in the directory and print by screen
	 * @param path
	 */
	public static void readDirectory(String path) {
		File dir = new File(path);
		
		if (dir.exists()) {
			for (String d : dir.list()) System.out.println(d);
		} else {
			System.out.println("The directory " + path + " doesn`t exist");
		}
	}
	
	/**
	 * OK
	 * It obtains the list of files that a folder contains and returns it to you 
	 * in an array
	 * @param path
	 * @return array
	 */
	public static String[] getDataDirectory(String path) {
		File dir = new File(path);
		
		if (dir.exists()) return dir.list(); 
		else return null;
	}
	
	//JAVA 5
	
	/**
	 * OK
	 * Returns an arrayList with the data that is inside the file that is
	 * found in the specified path
	 * @param path
	 * @param fileName
	 * @return arrayList
	 */
	public static List<String> returnFileLines5(String path, String fileName) {
		List<String> lines = new ArrayList<String>();
		FileReader fr = null;
		BufferedReader input = null;
		try {
			fr = new FileReader(path + "/" + fileName);
			input = new BufferedReader(fr);
			String line = input.readLine();
			while (line != null) {
				lines.add(line);
				line = input.readLine();
			}
		} catch (FileNotFoundException e) { 
			System.out.println("No se ha encontrado el fichero");
		} catch (IOException e) {
			e.printStackTrace();
			
		} finally { //If something goes wrong, close any resource
			try {
				if (input != null) {
					input.close();
				}
				if (fr != null) {
					fr.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return lines;
	}
	
	/**
	 * OK
	 * Read the file content and print on screen
	 * @param path
	 * @param fileName
	 */
	public static void readFile5(String path, String fileName) {
		List<String> lines = returnFileLines5(path, fileName);
		for (String line : lines) System.out.println(line);
	}
	/**
	 * OK
	 * Read the file content and print on screen
	 * @param completePath
	 */
	public static void readFile5(String completePath) {
		File file = new File(completePath);
		readFile5(file.getParent(), file.getName());
	}
	
	/**
	 * OK
	 * It will write the data in the array to a file
	 * @param path
	 * @param fileName
	 * @param lines
	 * @param overWrite -> it is used to say if you want me to overwrite the content or not
	 */
	public static void writeFile5(String path, String fileName, List<String> lines, boolean overWrite) {
		try {
			BufferedWriter fileExit = new BufferedWriter(new FileWriter(new File(path + "/" + fileName), overWrite));
			for(String line : lines) {
				fileExit.write(line);
				fileExit.newLine();
			}
			fileExit.close();
		} catch (IOException fileError) {
			System.out.println("There have been problems writing to file: " + fileError.getMessage());
		}
	}
	
	public static void writeFile5(String path, String fileName, List<String> lines) {
		writeFile5(path, fileName, lines, true);
	}
	
	//JAVA 8
	
	/**
	 * OK
	 * Read the file content and print on screen
	 * @param path
	 * @param fileName
	 */
	public static void readFile8(String path, String fileName) {
		try {
			Files.readAllLines(Paths.get(path + "/" + fileName), Charset.defaultCharset())
					.forEach(line -> System.out.println(line));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * OK
	 * Read the file content and print on screen
	 * @param path
	 */
	public static void readFile8(String path) {
		File file = new File(path);
		readFile8(file.getParent(), file.getName());
	}
	
	/**
	 * OK
	 * return an array with all file lines
	 * @param completePath
	 * @return data
	 */
	public static List<String> returnFileLines8(String completePath) {
		File file = new File(completePath);
		return returnFileLines8(file.getParent(), file.getName());
	}

	/**
	 * OK
	 * return an array with all file lines
	 * @param path
	 * @param fileName
	 * @return data
	 */
	public static List<String> returnFileLines8(String path, String fileName) {
		try {
			return Files.readAllLines(Paths.get(path + "/" + fileName), Charset.defaultCharset());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * OK
	 * return an array with the file bytes
	 * @param path
	 * @param fileName
	 * @return
	 */
	public static byte[] returnBinari8(String path, String fileName) {
		try {
			return Files.readAllBytes(Paths.get(path + "/" + fileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * OK
	 * !CAUTION¡ overwriting the file
	 * Write all array data in the file
	 * @param path
	 * @param fileName
	 * @param lines
	 * @return
	 */
	public static boolean writeFile8(String path, String fileName, List<String> lines) {
		try {
			Files.write(Paths.get(path + "/" + fileName), lines);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * OK
	 * !CAUTION¡ overwriting the file
	 * Write all array data in the file
	 * @param path
	 * @param fileName
	 * @param lines
	 * @return
	 */
	public static boolean writeFile8(String path, String fileName, String... lines) {
		try {
			Files.write(Paths.get(path + "/" + fileName), Arrays.asList(lines));
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * OK
	 * Write all array data in the file, without overwriting
	 * @param path
	 * @param fileName
	 * @param lines
	 * @return
	 */
	public static boolean appendFile8(String path, String fileName, List<String> lines) {
		try {
			Files.write(Paths.get(path + "/" + fileName), lines, StandardOpenOption.APPEND);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * OK
	 * Delete file content
	 * @param path
	 * @param file
	 */
	public static void deleteContentFile8(String path, String file) {
		writeFile8(path, file, new ArrayList<String>());
	}
}
