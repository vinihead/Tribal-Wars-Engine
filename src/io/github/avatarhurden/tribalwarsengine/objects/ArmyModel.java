package io.github.avatarhurden.tribalwarsengine.objects;

import io.github.avatarhurden.tribalwarsengine.objects.unit.Army;
import io.github.avatarhurden.tribalwarsengine.objects.unit.Unit;

import java.util.LinkedHashMap;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Classe que representa modelos de ex�rcitos
 * 
 * @author Arthur
 *
 */
public class ArmyModel implements EditableObject {

	private JSONObject json;
	private Gson gson = new GsonBuilder().create();
	
	/**
	 * Construtor com as informa��es do modelo j� criadas. Para gerar um modelo vazio,
	 * usar o construtor sem par�metros
	 * 
	 * @param json com as informa��es do modelo
	 */
	public ArmyModel(JSONObject json) {
		this.json = json;
	}
	
	public ArmyModel() {
		this(new JSONObject());
		
		setStartingValues();
	}
	
	private void setStartingValues() {
		
		setName("Novo Modelo");
		setArmy(new Army());
		
	}
	
    private Object get(String chave, Object def) {
        try {
            return json.get(chave);
        } catch (JSONException e) {
            return def;
        }
    }

    private void set(String chave, Object valor) {
        json.put(chave, valor);
    }
    
    public JSONObject getJson() {
    	return json;
    }
    
    public String toString() {
    	return getName();
    }
    
    // Getters
    
    public String getName() {
    	return (String) get("name", "");
    }
    
    public Army getArmy() {
    	return gson.fromJson(get("army", "").toString(), Army.class);
    }
    
    // Setters
    
    public ArmyModel setName(String name) {
    	set("name", name);
    	return this;
    }
    
    public ArmyModel setArmy(Army army) {
    	
    	Army toSave = new Army();
    	for (Unit u : toSave.getUnits()) {
    		if (army.contains(u.getName()))
    			toSave.addTropa(army.getTropa(u));
    	}
    	
    	set("army", new JSONObject(gson.toJson(toSave)));
    	return this;
    }
    
    public LinkedHashMap<String, String> getFieldNames() {
    	LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
    	
        map.put("name", "Nome");
        map.put("army", "");
        
        return map;
    }
	
}
