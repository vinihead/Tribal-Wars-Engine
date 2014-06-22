package config;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import database.ModeloAldeias;

/**
 * Class that reads the troop models file and creates objects for each model
 * 
 * @author Arthur
 * 
 */
public class ModeloAldeias_Reader {
	
	// Lista de modelos ativos no mundo
	static List<ModeloAldeias> listModelosAtivos = new ArrayList<ModeloAldeias>();

	// Lista de todos os modelos dispon�veis
	static List<ModeloAldeias> listModelos = new ArrayList<ModeloAldeias>();
	
	public static void read(String section) {

		try {

			// read the user-alterable config file
			Scanner in = new Scanner(new StringReader(section));
			
			System.out.println(section);
			
			store(in);

			// in case the file is corrupt, for any reason (thus we generalize
			// the exception), we use
			// the default file
		} catch (IOException e) {
			System.out.println("bugou geral");
		}

	}

	private static void store(Scanner in) throws IOException {

		String total = "";

		// reads the lines to gather all the properties of each world, running
		// once per world
		// breaks once there are no more worlds to read
		while (in.hasNextLine()) {

			String s;
			total += in.nextLine()+"\n";

			// reads the lines to gather all of the properties, breaking once
			// the line
			// contains no more properties (i.e. the world will change)
			while (in.hasNextLine()) {
				
				s = in.nextLine().trim();
				if (!s.equals(""))
					total += s + "\n";
				else
					break;
			}

			Properties i = new Properties();
			i.load(new StringReader(total));
			
			if (!i.isEmpty()) {
				ModeloAldeias modelo = new ModeloAldeias(i);
				addModelo(modelo);
			}
		}

		in.close();

	}

	
	/**
	 * Caso o ModeloTropas fornecido estiver no escopo atual (Global ou no mundo selecionado),
	 * adiciona-o � lista. Caso contr�rio, n�o faz nada.
	 * 
	 * @param modelo a ser adicionado
	 */
	public static void addModelo(ModeloAldeias modelo) {
		
		listModelos.add(modelo);
		
		if (modelo.getEscopo().contains(Mundo_Reader.MundoSelecionado))
			listModelosAtivos.add(modelo);

	}

	/**
	 * Retorna uma lista dos modelos ativos no mundo
	 */
	public static List<ModeloAldeias> getListModelos() {
		return listModelosAtivos;
	}
	
	public static String getModelosConfig() {
		
		// Provavelmente tempor�rio, preciso porque passo os ativos para EditDialog
		for (ModeloAldeias i : listModelosAtivos)
			if (!listModelos.contains(i))
				listModelos.add(i);
		
		String section = "";

		for (ModeloAldeias i : listModelos)
			section += i.getConfigText();
		
		return section;
		
	}
	
}