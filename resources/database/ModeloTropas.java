package database;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import property_classes.Property;
import property_classes.Property_Escopo;
import property_classes.Property_Nome;
import property_classes.Property_UnidadeList;
import config.Mundo_Reader;

/**
 * Class that stores a specific number of every unit to be used in different
 * tools and reused after closing program.
 * 
 * @author Arthur
 * 
 */
public class ModeloTropas {

	private Property_Nome nome;

	private Property_UnidadeList quantidades = new Property_UnidadeList();
	
	private Property_Escopo escopo;
	
	// This list will declare the order in which the map will be displayed
	public List<Property> variableList = new ArrayList<Property>();

	public ModeloTropas() {

		nome = new Property_Nome("Novo Modelo");
		
		for (Unidade i : Unidade.values())
			quantidades.put(i, BigDecimal.ZERO);
		
		escopo = new Property_Escopo(Mundo_Reader.MundoSelecionado);
		
		setVariableList();

	}
	
	public ModeloTropas(Properties p) {

		nome = new Property_Nome(p.getProperty("nome"));

		for (Unidade i : Unidade.values()) {
			String nome = i.nome().toLowerCase().replace(' ', '_');
			quantidades.put(i, new BigDecimal(p.getProperty(nome)));
		}
		
		
		String[] worlds = p.getProperty("escopo").split(" \",\"");
		
		List<Mundo> mundos = new ArrayList<Mundo>();
		for (String s : worlds)
			mundos.add(Mundo_Reader.getMundo(s));
		
		escopo = new Property_Escopo(mundos);

		setVariableList();

	}

	public ModeloTropas(String nome, Map<Unidade, BigDecimal> map, Mundo... mundos) {

		if (nome == null)
			this.nome = new Property_Nome("Novo Modelo");
		else
			this.nome = new Property_Nome(nome);

		for (Entry<Unidade, BigDecimal> i : map.entrySet())
			quantidades.put(i.getKey(), i.getValue());
		
		escopo = new Property_Escopo(mundos);
		
		setVariableList();

	}

	private void setVariableList() {

		variableList.add(nome);
		variableList.add(quantidades);
		variableList.add(escopo);
		
	}

	public String toString() {
		return nome.getValueName();
	}

	public void setNome(String s) {
		nome = new Property_Nome(s);
	}

	/**
	 * @param map A map<Unidade, BigDecimal>
	 */
	public void setMap(Map<Unidade, BigDecimal> map) {

		for (Entry<Unidade, BigDecimal> i : map.entrySet())
			quantidades.put(i.getKey(), i.getValue());

	}
	
	public void setEscopo(List<Mundo> mundos){
		escopo = new Property_Escopo(mundos);
	}

	public String getNome() {
		return nome.getValueName();
	}

	public BigDecimal getQuantidade(Unidade i) {

		return quantidades.get(i);

	}

	public Map<Unidade, BigDecimal> getList() {
		return quantidades;
	}
	
	/**
	 * Retorna uma lista com todos os mundos nos quais o modelo est� dispon�vel.
	 */
	public List<Mundo> getEscopo() {
		
		if (escopo.getListMundos().isEmpty())
			return Mundo_Reader.getMundoList();
		else
			return escopo.getListMundos();
		
	}

	public String getConfigText() {
		
		String s = "\n";
		
		s += ("\tnome=" + nome.getValueName() + "\n");

		s += ("\tescopo=");
		for (Mundo m : escopo.getListMundos())
			s += m.toString() + " \",\"";
		s += "\n";
		
		s += ("\tlanceiro=" + quantidades.get(Unidade.LANCEIRO) + "\n");
		s += ("\tespadachim=" + quantidades.get(Unidade.ESPADACHIM) + "\n");
		s += ("\tb�rbaro=" + quantidades.get(Unidade.B�RBARO) + "\n");
		s += ("\tarqueiro=" + quantidades.get(Unidade.ARQUEIRO) + "\n");
		s += ("\texplorador=" + quantidades.get(Unidade.EXPLORADOR) + "\n");
		s += ("\tcavalaria_leve=" + quantidades.get(Unidade.CAVALOLEVE) + "\n");
		s += ("\tarqueiro_a_cavalo=" + quantidades.get(Unidade.ARCOCAVALO) + "\n");
		s += ("\tcavalaria_pesada=" + quantidades.get(Unidade.CAVALOPESADO) + "\n");
		s += ("\tar�ete=" + quantidades.get(Unidade.AR�ETE) + "\n");
		s += ("\tcatapulta=" + quantidades.get(Unidade.CATAPULTA) + "\n");
		s += ("\tpaladino=" + quantidades.get(Unidade.PALADINO) + "\n");
		s += ("\tnobre=" + quantidades.get(Unidade.NOBRE) + "\n");
		s += ("\tmil�cia=" + quantidades.get(Unidade.MIL�CIA) + "\n");

		return s;
	}
	
}
